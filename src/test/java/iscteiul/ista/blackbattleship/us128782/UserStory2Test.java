package iscteiul.ista.blackbattleship.us128782;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class da User Story 3: começar uma partida contra o robot.
 * <p>Gerada a partir do cenário {@code UserStoryTest2} de {@code TestSuite_128782.side}.
 * Usa apenas os métodos de {@link UserStory2}; não contém localizadores.
 */
public class UserStory2Test {

    /** Nickname de convidado usado se o site o pedir. */
    private static final String NICKNAME = "Tester128782";

    private WebDriver driver;
    private UserStory2 page;

    /** Abre um Chrome novo, maximizado, na página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new UserStory2(driver);
        page.open();
    }

    /** Espera 3 segundos para se ver o resultado, termina a partida e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        page.abortGame();
        Thread.sleep(1000);
        driver.quit();
    }

    /**
     * Verifica que, ao começar uma partida contra o robot, são apresentados
     * o meu tabuleiro ("Your boats"), o tabuleiro do adversário e o botão "Abort game".
     */
    @Test
    public void shouldStartGameAgainstRobot() throws InterruptedException {
        Thread.sleep(1500);
        page.startGameVsRobot(NICKNAME);
        Thread.sleep(1500);

        assertEquals("Your boats", page.getMyBoardHeader());
        assertTrue(page.isOpponentBoardDisplayed(), "O tabuleiro do adversário devia estar visível");
        assertEquals("Abort game", page.getAbortButtonText());
    }
}
