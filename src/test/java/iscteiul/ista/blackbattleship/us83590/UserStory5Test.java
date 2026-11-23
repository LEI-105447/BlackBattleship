package iscteiul.ista.blackbattleship.us83590;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class da User Story 5: Desistir de uma partida, com confirmação.
 * Usa apenas os métodos de {@link UserStory5}.
 * <p>Gerado a partir do cenário {@code UserStoryTest5} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play vs robot' para jogar contra o computador</li>
 * <li>Escrever o nickname de convidado no diálogo de registo</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Esperar que o tabuleiro apareça</li>
 * <li>Clicar no botão de desistir</li>
 * <li>Verificar que é pedida confirmação ('Are you sure')</li>
 * <li>Confirmar a desistência</li>
 * <li>Verificar que a partida terminou (tabuleiros desapareceram)</li>
 * </ol>
 */
public class UserStory5Test {
    private WebDriver driver;
    private UserStory5 page;

    /** Abre um Chrome novo, maximizado, e a página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        page = new UserStory5(driver);
        page.open();
    }

    /** Espera 4 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pausa para ver o resultado final antes de fechar o browser
        Thread.sleep(4000);
        driver.quit();
    }

    /** Verifica que desistir pede confirmação e, ao confirmar, a partida termina. */
    @Test
    public void shouldEndGameWhenResigning() throws InterruptedException {
        Thread.sleep(1000);
        page.startGameVsRobot(BattleshipBasePage.NICKNAME);
        Thread.sleep(1000);

        page.clickQuit();
        Thread.sleep(1000);
        assertTrue(page.getConfirmationText().contains("Are you sure"));

        page.confirmQuit();

        assertTrue(page.isGameOver());
    }
}
