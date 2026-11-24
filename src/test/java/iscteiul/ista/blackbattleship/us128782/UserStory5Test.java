package iscteiul.ista.blackbattleship.us128782;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class da User Story 21: dar um nome ao torneio.
 * <p>Gerada a partir do cenário {@code UserStoryTest5} de {@code TestSuite_128782.side}.
 * Usa apenas os métodos de {@link UserStory5}; não contém localizadores.
 * O torneio nunca é criado (o botão 'Create and share' não é premido).
 */
public class UserStory5Test {

    /** Nome de torneio usado no teste. */
    private static final String TOURNAMENT_NAME = "Torneio 128782";

    private WebDriver driver;
    private UserStory5 page;

    /** Abre um Chrome novo, maximizado, na página do jogo (recusando os cookies). */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new UserStory5(driver);
        page.open();
    }

    /** Espera 3 segundos para se ver o resultado final e fecha o Chrome (sem criar o torneio). */
    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    /**
     * Verifica que o campo "Tournament name" aceita o nome do torneio:
     * sem nome, "Create and share" está desativado; com nome, o campo fica com o valor,
     * é considerado válido e "Create and share" fica ativo.
     */
    @Test
    public void tournamentNameShouldBeAccepted() throws InterruptedException {
        Thread.sleep(1500);
        page.openTournamentCreation();
        Thread.sleep(1500);

        assertEquals("Tournament creation", page.getPageTitle());
        assertFalse(page.isCreateAndShareEnabled(), "'Create and share' devia estar desativado sem nome");

        page.typeTournamentName(TOURNAMENT_NAME);
        Thread.sleep(1500);

        assertEquals(TOURNAMENT_NAME, page.getTournamentName());
        assertTrue(page.isTournamentNameValid(), "O nome do torneio devia ser considerado válido");
        assertTrue(page.waitUntilCreateAndShareEnabled(), "'Create and share' devia ficar ativo com um nome");
    }
}
