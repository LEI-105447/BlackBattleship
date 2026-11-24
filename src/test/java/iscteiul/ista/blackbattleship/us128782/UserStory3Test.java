package iscteiul.ista.blackbattleship.us128782;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class da User Story 34: o míssil simples pode ser usado sem limite.
 * <p>Gerada a partir do cenário {@code UserStoryTest3} de {@code TestSuite_128782.side}.
 * Usa apenas os métodos de {@link UserStory3}; não contém localizadores.
 */
public class UserStory3Test {

    /** Nickname de convidado usado se o site o pedir. */
    private static final String NICKNAME = "Tester128782";

    private WebDriver driver;
    private UserStory3 page;

    /**
     * Abre um Chrome novo, maximizado, na página do jogo (recusando os cookies)
     * e começa uma partida contra o robot.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new UserStory3(driver);
        page.open();
        page.startGameVsRobot(NICKNAME);
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
     * Verifica que, no início da partida, o míssil simples está na barra de armas,
     * vem selecionado e não tem contador de unidades (pode ser usado sempre).
     */
    @Test
    public void simpleMissileShouldBeSelectedAndUnlimited() throws InterruptedException {
        Thread.sleep(1500);
        assertTrue(page.isSimpleMissileShown(), "O míssil simples devia estar na barra de armas");
        assertTrue(page.isSimpleMissileSelected(), "O míssil simples devia vir selecionado");
        assertFalse(page.hasSimpleMissileCounter(), "O míssil simples não devia ter contador");
    }
}
