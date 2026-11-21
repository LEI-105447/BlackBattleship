package iscteiul.ista.blackbattleship.us83590;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class da User Story 1: Abrir a página do jogo e ver os modos disponíveis.
 * Usa apenas os métodos de {@link UserStory1}.
 * <p>Gerado a partir do cenário {@code UserStoryTest1} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Verificar que o modo 'Play with a friend' está disponível</li>
 * <li>Verificar que o modo 'Play vs robot' está disponível</li>
 * <li>Verificar que o modo 'Create tournament' está disponível</li>
 * <li>Verificar que o modo 'Play online' está disponível</li>
 * </ol>
 */
public class UserStory1Test {
    private WebDriver driver;
    private UserStory1 page;

    /** Abre um Chrome novo, maximizado, e a página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        page = new UserStory1(driver);
        page.open();
    }

    /** Espera 4 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pausa para ver o resultado final antes de fechar o browser
        Thread.sleep(4000);
        driver.quit();
    }

    /** Verifica que os quatro modos de jogo aparecem na página inicial. */
    @Test
    public void shouldShowGameModes() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue(page.isPlayWithFriendVisible());
        assertTrue(page.isPlayVsRobotVisible());
        assertTrue(page.isCreateTournamentVisible());
        assertTrue(page.isPlayOnlineVisible());
    }
}
