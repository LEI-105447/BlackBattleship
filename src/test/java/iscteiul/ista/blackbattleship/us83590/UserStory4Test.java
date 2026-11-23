package iscteiul.ista.blackbattleship.us83590;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class da User Story 4: Usar o míssil grande para atingir uma cruz de 5 casas.
 * Usa apenas os métodos de {@link UserStory4}.
 * <p>Gerado a partir do cenário {@code UserStoryTest4} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play vs robot' para jogar contra o computador</li>
 * <li>Escrever o nickname de convidado no diálogo de registo</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Esperar que o tabuleiro apareça</li>
 * <li>Verificar que há 2 mísseis grandes disponíveis</li>
 * <li>Selecionar o míssil grande na barra de armas</li>
 * <li>Disparar o míssil grande sobre a casa central do tabuleiro do adversário</li>
 * <li>Verificar que a casa de cima da cruz ficou marcada</li>
 * <li>Verificar que a casa de baixo da cruz ficou marcada</li>
 * <li>Verificar que sobra 1 míssil grande</li>
 * </ol>
 */
public class UserStory4Test {
    private WebDriver driver;
    private UserStory4 page;

    /** Abre um Chrome novo, maximizado, e a página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        page = new UserStory4(driver);
        page.open();
    }

    /** Espera 4 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pausa para ver o resultado final antes de fechar o browser
        Thread.sleep(4000);
        driver.quit();
    }

    /** Verifica que o míssil grande marca uma cruz de 5 casas e gasta uma unidade. */
    @Test
    public void shouldHitCrossWithBigMissile() throws InterruptedException {
        Thread.sleep(1000);
        page.startGameVsRobot(BattleshipBasePage.NICKNAME);
        assertEquals(2, page.getBigMissileCount());

        page.selectBigMissile();
        Thread.sleep(1000);
        page.fireBigMissileAt(4, 4);
        Thread.sleep(1000);

        assertTrue(page.waitForMarkedCells(5));
        assertTrue(page.isCellMarked(3, 4));
        assertTrue(page.isCellMarked(4, 3));
        assertTrue(page.isCellMarked(4, 4));
        assertTrue(page.isCellMarked(4, 5));
        assertTrue(page.isCellMarked(5, 4));
        assertEquals(1, page.getBigMissileCount());
    }
}
