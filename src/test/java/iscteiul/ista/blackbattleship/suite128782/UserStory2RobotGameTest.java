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
import iscteiul.ista.blackbattleship.suite128782.pages.GameRoomPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * UserStory2 (US3): "Como jogador, quero começar uma partida contra o robot, para jogar a qualquer hora."
 * <p>Versão Selenide do cenário {@code UserStoryTest2} de {@code TestSuite_128782.side}.
 */
@Epic("Épico A – Entrar e começar a jogar")
@Feature("US3 – Partida contra o robot")
@Owner("128782")
@DisplayName("US3 – Começar uma partida contra o robot")
public class UserStory2RobotGameTest {

    /** Nickname de convidado usado se o site o pedir. */
    private static final String NICKNAME = "Tester128782";

    private BattleshipPage battleshipPage;
    private GameRoomPage gameRoom;

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

    /** Pausa curta, termina a partida (se houver) e fecha o browser. */
    @AfterEach
    public void tearDown() {
        sleep(1500);
        if (gameRoom != null) {
            gameRoom.abortGame();
        }
        closeWebDriver();
    }

    @Test
    @Story("Ao começar uma partida contra o robot aparecem os dois tabuleiros e o botão 'Abort game'")
    @DisplayName("Começar uma partida contra o robot mostra os dois tabuleiros e 'Abort game'")
    @Severity(SeverityLevel.CRITICAL)
    public void startingAGameShouldShowBothBoardsAndAbort() {
        gameRoom = battleshipPage.startGameVsRobot(NICKNAME);

        gameRoom.myBoardHeader().shouldHave(exactText("Your boats"));
        gameRoom.opponentBoard().shouldBe(visible);
        gameRoom.abortGameButton().shouldHave(exactText("Abort game"));
    }
}
