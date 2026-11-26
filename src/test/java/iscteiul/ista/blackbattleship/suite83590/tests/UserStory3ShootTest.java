package iscteiul.ista.blackbattleship.suite83590.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import iscteiul.ista.blackbattleship.suite83590.pages.HomePage;
import org.junit.jupiter.api.Test;

/**
 * US5: "Como jogador, quero disparar sobre uma casa do tabuleiro do adversário, para tentar afundar
 * os seus navios."
 */
@Epic("Battleship online")
@Feature("Jogar a partida")
public class UserStory3ShootTest extends BaseTest {

    /** Verifica que, depois de disparar sobre a casa A1, a casa fica marcada (falha ou acerto). */
    @Test
    @Story("US5 - Disparar sobre uma casa")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Começar uma partida contra o robot, disparar sobre a casa A1 do adversário e verificar a marca.")
    public void shouldMarkFiredCell() {
        HomePage.openPage()
                .startGameVsRobot(NICKNAME)
                .shouldShowTwoBoards()
                .fireAt(0, 0)
                .shouldBeMarked(0, 0);
    }
}
