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

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * UserStory1 (US4): "Como jogador, quero ver o ranking diário e o ranking dos últimos 30 dias,
 * para saber quem está a jogar melhor."
 * <p>Versão Selenide do cenário {@code UserStoryTest1} de {@code TestSuite_128782.side}.
 */
@Epic("Épico A – Entrar e começar a jogar")
@Feature("US4 – Rankings")
@Owner("128782")
@DisplayName("US4 – Ver o ranking diário e o ranking dos últimos 30 dias")
public class UserStory1RankingTest {

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
    @Story("O ranking diário é apresentado e lista jogadores")
    @DisplayName("O ranking diário é apresentado e lista jogadores")
    @Severity(SeverityLevel.NORMAL)
    public void dailyLeaderboardShouldListPlayers() {
        battleshipPage.dailyLeaderboardTitle().shouldHave(exactText("Daily leaderboard, ends in"));
        battleshipPage.dailyLeaderboardPlayers().shouldHave(sizeGreaterThan(0));
    }

    @Test
    @Story("O ranking dos últimos 30 dias (RANKA) é apresentado e lista jogadores")
    @DisplayName("O ranking dos últimos 30 dias (RANKA) é apresentado e lista jogadores")
    @Severity(SeverityLevel.NORMAL)
    public void last30DaysRankingShouldListPlayers() {
        battleshipPage.rankaTitle().shouldHave(exactText("RANKA"));
        battleshipPage.rankaPlayers().shouldHave(sizeGreaterThan(0));
    }
}
