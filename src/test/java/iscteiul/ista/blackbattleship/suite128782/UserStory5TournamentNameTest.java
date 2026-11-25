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
import iscteiul.ista.blackbattleship.suite128782.pages.TournamentCreationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * UserStory5 (US21): "Como organizador, quero dar um nome ao torneio, para os amigos o reconhecerem."
 * <p>Versão Selenide do cenário {@code UserStoryTest5} de {@code TestSuite_128782.side}.
 * O torneio nunca é criado: o botão "Create and share" não é premido.
 */
@Epic("Épico D – Torneios")
@Feature("US21 – Nome do torneio")
@Owner("128782")
@DisplayName("US21 – Dar um nome ao torneio")
public class UserStory5TournamentNameTest {

    /** Nome de torneio usado no teste. */
    private static final String TOURNAMENT_NAME = "Torneio 128782";

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

    /** Pausa curta para se ver o resultado e fecha o browser (sem criar o torneio). */
    @AfterEach
    public void tearDown() {
        sleep(1500);
        closeWebDriver();
    }

    @Test
    @Story("O campo 'Tournament name' aceita o nome do torneio")
    @DisplayName("O campo 'Tournament name' aceita o nome e ativa 'Create and share'")
    @Severity(SeverityLevel.NORMAL)
    public void tournamentNameShouldBeAccepted() {
        TournamentCreationPage creation = battleshipPage.openTournamentCreation();

        creation.title().shouldHave(exactText("Tournament creation"));
        creation.createAndShareButton().shouldBe(disabled);

        creation.typeTournamentName(TOURNAMENT_NAME);

        creation.tournamentNameField().shouldHave(value(TOURNAMENT_NAME), cssClass("ng-valid"));
        creation.createAndShareButton().shouldBe(enabled);
    }
}
