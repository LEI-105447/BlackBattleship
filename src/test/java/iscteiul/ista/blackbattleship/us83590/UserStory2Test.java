package iscteiul.ista.blackbattleship.us83590;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class da User Story 2: Indicar um nickname como convidado para poder jogar sem conta.
 * Usa apenas os métodos de {@link UserStory2}.
 * <p>Gerado a partir do cenário {@code UserStoryTest2} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play vs robot' para abrir o diálogo de registo</li>
 * <li>Verificar que o diálogo pergunta 'Who are you?'</li>
 * <li>Escrever o nickname de convidado</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Verificar que entrei na sala de jogo</li>
 * </ol>
 */
public class UserStory2Test {
    private WebDriver driver;
    private UserStory2 page;

    /** Abre um Chrome novo, maximizado, e a página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        page = new UserStory2(driver);
        page.open();
    }

    /** Espera 4 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pausa para ver o resultado final antes de fechar o browser
        Thread.sleep(4000);
        driver.quit();
    }

    /** Verifica que o diálogo pede o nickname e que, após confirmar, o jogador entra na sala de jogo. */
    @Test
    public void shouldAcceptGuestNickname() throws InterruptedException {
        page.clickPlayVsRobot();
        Thread.sleep(1000);
        assertTrue(page.isNicknameDialogShown());

        page.enterNickname(BattleshipBasePage.NICKNAME);
        Thread.sleep(1000);
        page.clickContinue();

        assertTrue(page.isInGameRoom());
    }
}
