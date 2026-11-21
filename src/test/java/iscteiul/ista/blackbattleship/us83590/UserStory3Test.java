package iscteiul.ista.blackbattleship.us83590;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class da User Story 3: Disparar sobre uma casa do tabuleiro do adversário.
 * Usa apenas os métodos de {@link UserStory3}.
 * <p>Gerado a partir do cenário {@code UserStoryTest3} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play vs robot' para jogar contra o computador</li>
 * <li>Escrever o nickname de convidado no diálogo de registo</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Esperar que o tabuleiro apareça</li>
 * <li>Disparar sobre a casa A1 do tabuleiro do adversário</li>
 * <li>Verificar que a casa disparada ficou marcada (falha ou acerto)</li>
 * </ol>
 */
public class UserStory3Test {
    private WebDriver driver;
    private UserStory3 page;

    /** Abre um Chrome novo, maximizado, e a página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        page = new UserStory3(driver);
        page.open();
    }

    /** Espera 4 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pausa para ver o resultado final antes de fechar o browser
        Thread.sleep(4000);
        driver.quit();
    }

    /** Verifica que, depois de disparar, a casa fica marcada (falha ou acerto). */
    @Test
    public void shouldMarkFiredCell() throws InterruptedException {
        Thread.sleep(1000);
        page.startGameVsRobot(BattleshipBasePage.NICKNAME);
        Thread.sleep(1000);

        page.fireAt(0, 0);
        Thread.sleep(1000);

        assertTrue(page.isShotMarked(0, 0));
    }
}
