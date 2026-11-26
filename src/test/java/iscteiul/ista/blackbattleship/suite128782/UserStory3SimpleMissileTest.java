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

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * UserStory3 (US34): "Como jogador, quero usar o míssil simples tantas vezes quantas quiser,
 * para poder disparar sempre, mesmo sem armas especiais."
 * <p>Versão Selenide do cenário {@code UserStoryTest3} de {@code TestSuite_128782.side}.
 */
@Epic("Épico F – Complementos da partida e dos torneios")
@Feature("US34 – Míssil simples")
@Owner("128782")
@DisplayName("US34 – Usar o míssil simples sem limite")
public class UserStory3SimpleMissileTest {

    /** Nickname de convidado usado se o site o pedir. */
    private static final String NICKNAME = "Tester128782";

    private GameRoomPage gameRoom;

    /** Configura o Selenide (Chrome, tempo de espera) e liga o registo de passos no Allure. */
    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    /** Abre a página do jogo num browser novo e começa uma partida contra o robot (pré-condição). */
    @BeforeEach
    public void setUp() {
        gameRoom = BattleshipPage.openPage().startGameVsRobot(NICKNAME);
    }

    /** Pausa curta, termina a partida e fecha o browser. */
    @AfterEach
    public void tearDown() {
        sleep(1500);
        if (gameRoom != null) {
            gameRoom.abortGame();
        }
        closeWebDriver();
    }

    @Test
    @Story("Na barra de armas o míssil simples vem selecionado e não tem contador")
    @DisplayName("O míssil simples vem selecionado e não tem contador")
    @Severity(SeverityLevel.NORMAL)
    public void simpleMissileShouldBeSelectedAndUnlimited() {
        gameRoom.weaponBar().shouldBe(visible);
        gameRoom.simpleMissile().shouldBe(visible).shouldHave(cssClass("is-selected"));
        gameRoom.simpleMissileCounter().shouldNot(exist);
    }
}
