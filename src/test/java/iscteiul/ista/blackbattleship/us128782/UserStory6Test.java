package iscteiul.ista.blackbattleship.us128782;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class da User Story 31: ler as regras do jogo na própria página.
 * <p>Gerada a partir do cenário {@code UserStoryTest6} de {@code TestSuite_128782.side}.
 * Usa apenas os métodos de {@link UserStory6}; não contém localizadores.
 */
public class UserStory6Test {

    private WebDriver driver;
    private UserStory6 page;

    /** Abre um Chrome novo, maximizado, na página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new UserStory6(driver);
        page.open();
    }

    /** Espera 3 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    /**
     * Verifica que a página mostra a secção "Rules of Battleship game online"
     * e que as regras indicam a grelha de 10×10 e a frota de cinco navios (5, 4, 3, 3 e 2 casas).
     */
    @Test
    public void rulesShouldDescribeGridAndFleet() throws InterruptedException {
        Thread.sleep(1500);
        page.scrollToRules();
        Thread.sleep(1500);

        assertEquals("Rules of Battleship game online", page.getRulesTitle());

        String rules = page.getRulesFirstParagraph();
        assertTrue(rules.contains("10x10"), "As regras deviam indicar a grelha de 10x10");
        assertTrue(rules.contains("5, 4, 3, 3 and 2"), "As regras deviam indicar a frota de 5, 4, 3, 3 e 2 casas");
    }
}
