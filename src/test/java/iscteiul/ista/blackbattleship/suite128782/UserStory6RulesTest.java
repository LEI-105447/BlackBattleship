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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * UserStory6 (US31): "Como jogador, quero ler as regras do jogo na própria página,
 * para saber como se joga antes de começar."
 * <p>Versão Selenide do cenário {@code UserStoryTest6} de {@code TestSuite_128782.side}.
 */
@Epic("Épico F – Complementos da partida e dos torneios")
@Feature("US31 – Regras do jogo")
@Owner("128782")
@DisplayName("US31 – Ler as regras do jogo na própria página")
public class UserStory6RulesTest {

    private BattleshipPage battleshipPage;

    /** Configura o Selenide (Chrome, tempo de espera) e liga o registo de passos no Allure. */
    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        // Não esperar que todos os anúncios/vídeos da página carreguem (o site é pesado e pode exceder o tempo limite)
        Configuration.pageLoadStrategy = "eager";
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
    @Story("A secção das regras indica a grelha de 10x10 e a frota de cinco navios")
    @DisplayName("As regras indicam a grelha de 10x10 e a frota de 5, 4, 3, 3 e 2 casas")
    @Severity(SeverityLevel.MINOR)
    public void rulesShouldDescribeGridAndFleet() {
        battleshipPage.scrollToRules();

        battleshipPage.rulesTitle().shouldHave(exactText("Rules of Battleship game online"));
        battleshipPage.rulesFirstParagraph().shouldHave(text("10x10"), text("5, 4, 3, 3 and 2"));
    }
}
