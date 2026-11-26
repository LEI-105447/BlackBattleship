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
 * US10: "Como jogador, quero usar o míssil grande (uma cruz de 5 casas, com 2 unidades no início),
 * para atingir várias casas de uma vez."
 */
@Epic("Battleship online")
@Feature("Jogar a partida")
public class UserStory4BigMissileTest extends BaseTest {

    /** Verifica que o míssil grande marca uma cruz de 5 casas e gasta uma unidade. */
    @Test
    @Story("US10 - Míssil grande em cruz")
    @Severity(SeverityLevel.NORMAL)
    @Description("Selecionar o míssil grande (2 unidades), disparar sobre o centro do tabuleiro e verificar "
            + "que ficam 5 casas marcadas em cruz e sobra 1 unidade.")
    public void shouldHitCrossWithBigMissile() {
        GameRoomPage room = HomePage.openPage().startGameVsRobot(NICKNAME);

        room.shouldHaveBigMissiles(2)
                .selectBigMissile()
                .fireBigMissileAt(4, 4)
                .shouldHaveMarkedCells(5)
                .shouldBeMarked(3, 4)
                .shouldBeMarked(4, 3)
                .shouldBeMarked(4, 4)
                .shouldBeMarked(4, 5)
                .shouldBeMarked(5, 4)
                .shouldHaveBigMissiles(1);
    }
}
