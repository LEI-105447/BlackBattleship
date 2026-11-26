package iscteiul.ista.blackbattleship.suite83590.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import iscteiul.ista.blackbattleship.suite83590.pages.HomePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

/**
 * US2: "Como convidado, quero indicar um nickname, para poder jogar sem criar conta."
 */
@Epic("Battleship online")
@Feature("Entrar e começar a jogar")
public class UserStory2NicknameTest extends BaseTest {

    /** Verifica que o diálogo "Who are you?" aceita o nickname e que se entra na sala de jogo. */
    @Test
    @Story("US2 - Nickname de convidado")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Clicar em 'Play vs robot', ver o diálogo 'Who are you?', escrever o nickname e entrar na sala.")
    public void shouldAcceptGuestNickname() {
        HomePage.openPage()
                .clickPlayVsRobot()
                .shouldAskForNickname()
                .confirm(NICKNAME);

        webdriver().shouldHave(urlContaining("/r/"));
    }
}
