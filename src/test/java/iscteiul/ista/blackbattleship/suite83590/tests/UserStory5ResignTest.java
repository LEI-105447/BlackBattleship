package iscteiul.ista.blackbattleship.suite83590.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import iscteiul.ista.blackbattleship.suite83590.pages.GameRoomPage;
import iscteiul.ista.blackbattleship.suite83590.pages.HomePage;
import org.junit.jupiter.api.Test;

/**
 * US12: "Como jogador, quero desistir de uma partida, para terminar o jogo quando já não quero
 * continuar."
 */
@Epic("Battleship online")
@Feature("Jogar a partida")
public class UserStory5ResignTest extends BaseTest {

    /** Verifica que desistir pede confirmação e que, ao confirmar, a partida termina. */
    @Test
    @Story("US12 - Desistir da partida")
    @Severity(SeverityLevel.NORMAL)
    @Description("Começar uma partida, clicar em desistir, ver a confirmação 'Are you sure' e confirmar.")
    public void shouldEndGameWhenResigning() {
        GameRoomPage room = HomePage.openPage().startGameVsRobot(NICKNAME);

        room.clickQuit().shouldAskConfirmation().confirmQuit();

        room.shouldBeOver();
    }
}
