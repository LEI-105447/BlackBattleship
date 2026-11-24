package iscteiul.ista.blackbattleship.us128782;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class da User Story 4: ver o ranking diário e o ranking dos últimos 30 dias.
 * <p>Gerada a partir do cenário {@code UserStoryTest1} de {@code TestSuite_128782.side}.
 * Usa apenas os métodos de {@link UserStory1}; não contém localizadores.
 */
public class UserStory1Test {

    private WebDriver driver;
    private UserStory1 page;

    /** Abre um Chrome novo, maximizado, na página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new UserStory1(driver);
        page.open();
    }

    /** Espera 3 segundos para se ver o resultado final e fecha o Chrome. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    /** Verifica que o ranking diário é apresentado e lista jogadores. */
    @Test
    public void shouldShowDailyLeaderboardWithPlayers() throws InterruptedException {
        Thread.sleep(1500);
        assertEquals("Daily leaderboard, ends in", page.getDailyLeaderboardTitle());
        assertTrue(page.countDailyPlayers() > 0, "O ranking diário devia listar pelo menos um jogador");
    }

    /** Verifica que o ranking dos últimos 30 dias (RANKA) é apresentado e lista jogadores. */
    @Test
    public void shouldShowLast30DaysRankingWithPlayers() throws InterruptedException {
        Thread.sleep(1500);
        assertEquals("RANKA", page.getRankaTitle());
        assertTrue(page.countRankaPlayers() > 0, "O ranking dos últimos 30 dias devia listar pelo menos um jogador");
    }
}
