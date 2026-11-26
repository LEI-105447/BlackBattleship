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
 * US15: "Como jogador, quero escolher 'Play with a friend' e obter um link de convite, para jogar
 * com alguém que conheço."
 */
@Epic("Battleship online")
@Feature("Jogar com outras pessoas")
public class UserStory6InviteLinkTest extends BaseTest {

    /** Verifica que a sala mostra o link de convite e o ícone de copiar, e que se consegue sair. */
    @Test
    @Story("US15 - Link de convite")
    @Severity(SeverityLevel.NORMAL)
    @Description("Clicar em 'Play with a friend', ver o link de convite e o ícone de copiar, copiar o link e "
            + "sair da sala.")
    public void shouldShowInviteLink() {
        HomePage.openPage()
                .startFriendGame(NICKNAME)
                .shouldShowInviteLink()
                .shouldShowCopyIcon()
                .clickCopyIcon()
                .leave()
                .shouldBeDisplayed();
    }
}
