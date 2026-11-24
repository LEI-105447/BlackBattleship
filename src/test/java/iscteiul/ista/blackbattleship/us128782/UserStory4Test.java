package iscteiul.ista.blackbattleship.us128782;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class da User Story 17: configurar o jogo nas "Game settings".
 * <p>Gerada a partir do cenário {@code UserStoryTest4} de {@code TestSuite_128782.side}.
 * Usa apenas os métodos de {@link UserStory4}; não contém localizadores.
 */
public class UserStory4Test {

    private WebDriver driver;
    private UserStory4 page;

    /** Abre um Chrome novo, maximizado, na página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new UserStory4(driver);
        page.open();
    }

    /** Espera 3 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    /**
     * Verifica que as "Game settings" mostram as opções por omissão
     * (40 segundos por jogada, 3 minutos por jogador, primeiro jogador aleatório)
     * e que podem ser guardadas.
     */
    @Test
    public void gameSettingsShouldShowDefaultsAndBeSaved() throws InterruptedException {
        Thread.sleep(1500);
        page.openGameSettings();
        Thread.sleep(1500);

        assertEquals("Game settings", page.getDialogTitle());
        assertEquals("40 seconds", page.getTimePerTurn());
        assertEquals("3 minutes", page.getMinutesPerPlayer());
        assertEquals("Random", page.getWhoPlaysFirst());

        page.saveSettings();
        assertTrue(page.isSettingsDialogClosed(), "O diálogo devia fechar depois de guardar as definições");
    }
}
