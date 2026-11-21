package iscteiul.ista.blackbattleship.us83590;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class da User Story 6: Obter o link de convite em "Play with a friend".
 * Usa apenas os métodos de {@link UserStory6}.
 * <p>Gerado a partir do cenário {@code UserStoryTest6} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play with a friend' para jogar com um amigo</li>
 * <li>Escrever o nickname de convidado no diálogo de registo</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Verificar que é apresentado o link de convite</li>
 * <li>Verificar que existe o ícone para copiar o link</li>
 * <li>Clicar no ícone para copiar o link de convite</li>
 * <li>Clicar em 'Leave room' para sair da sala</li>
 * <li>Verificar que voltei à página inicial do jogo</li>
 * </ol>
 */
public class UserStory6Test {
    private WebDriver driver;
    private UserStory6 page;

    /** Abre um Chrome novo, maximizado, e a página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        page = new UserStory6(driver);
        page.open();
    }

    /** Espera 4 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pausa para ver o resultado final antes de fechar o browser
        Thread.sleep(4000);
        driver.quit();
    }

    /** Verifica que a sala mostra o link de convite e o ícone de copiar, e que se consegue sair da sala. */
    @Test
    public void shouldShowInviteLink() throws InterruptedException {
        Thread.sleep(1000);
        page.startFriendGame(BattleshipBasePage.NICKNAME);
        Thread.sleep(1000);

        assertTrue(page.isInviteLinkShown());
        assertTrue(page.isCopyIconShown());

        page.clickCopyIcon();
        Thread.sleep(1000);
        page.leaveRoom();

        assertTrue(page.isBackAtHome());
    }
}
