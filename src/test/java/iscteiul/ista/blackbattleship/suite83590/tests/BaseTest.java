package iscteiul.ista.blackbattleship.suite83590.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;

/**
 * Classe base dos testes da suite 83590: configura o Selenide e o Allure e fecha o browser no fim.
 */
public abstract class BaseTest {

    /** Nickname de convidado usado em todos os testes. */
    protected static final String NICKNAME = "Tester83590";

    /** Configura o Chrome (maximizado) e liga o Allure aos passos do Selenide. */
    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = null;
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--start-maximized");
        // O arranque de uma partida contra o robot pode demorar; o Selenide espera até 30 s por cada condição.
        Configuration.timeout = 30_000;
        Configuration.pageLoadTimeout = 60_000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    /** Retira o listener do Allure e fecha o browser. */
    @AfterEach
    public void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
        closeWebDriver();
    }
}
