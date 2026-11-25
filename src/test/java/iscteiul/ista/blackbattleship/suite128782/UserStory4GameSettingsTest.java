package iscteiul.ista.blackbattleship.suite128782;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.suite128782.pages.BattleshipPage;
import iscteiul.ista.blackbattleship.suite128782.pages.GameSettingsDialog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * UserStory4 (US17): "Como jogador, quero configurar o jogo (tempo por jogada, minutos por jogador,
 * quem joga primeiro, tempo ilimitado), para adaptar as regras à partida."
 * <p>Versão Selenide do cenário {@code UserStoryTest4} de {@code TestSuite_128782.side}.
 */
@Epic("Épico C – Jogar com outras pessoas")
@Feature("US17 – Definições de jogo")
@Owner("128782")
@DisplayName("US17 – Configurar o jogo nas 'Game settings'")
public class UserStory4GameSettingsTest {

    private BattleshipPage battleshipPage;

    /** Configura o Selenide (Chrome, tempo de espera) e liga o registo de passos no Allure. */
    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    /** Abre a página do jogo num browser novo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        battleshipPage = BattleshipPage.openPage();
    }

    /** Pausa curta para se ver o resultado e fecha o browser. */
    @AfterEach
    public void tearDown() {
        sleep(1500);
        closeWebDriver();
    }

    @Test
    @Story("As 'Game settings' mostram as opções por omissão e podem ser guardadas")
    @DisplayName("As 'Game settings' mostram 40 s, 3 min e primeiro jogador aleatório, e fecham ao guardar")
    @Severity(SeverityLevel.NORMAL)
    public void gameSettingsShouldShowDefaultsAndBeSaved() {
        GameSettingsDialog settings = battleshipPage.openGameSettings();

        settings.title().shouldHave(exactText("Game settings"));
        settings.timePerTurn().shouldHave(exactText("40 seconds"));
        settings.minutesPerPlayer().shouldHave(exactText("3 minutes"));
        settings.whoPlaysFirst().shouldHave(exactText("Random"));

        settings.save();
        settings.dialog().shouldNot(exist);
    }
}
