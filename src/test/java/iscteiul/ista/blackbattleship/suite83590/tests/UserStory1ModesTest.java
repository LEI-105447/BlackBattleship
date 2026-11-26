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
 * US1: "Como jogador, quero abrir a página do jogo e ver os modos disponíveis, para escolher como
 * quero jogar."
 */
@Epic("Battleship online")
@Feature("Entrar e começar a jogar")
public class UserStory1ModesTest extends BaseTest {

    /** Verifica que a página inicial mostra os quatro modos de jogo. */
    @Test
    @Story("US1 - Ver os modos de jogo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Abrir a página do jogo e verificar que aparecem 'Play with a friend', 'Play vs robot', "
            + "'Create tournament' e 'Play online'.")
    public void shouldShowGameModes() {
        HomePage.openPage().shouldShowAllModes();
    }
}
