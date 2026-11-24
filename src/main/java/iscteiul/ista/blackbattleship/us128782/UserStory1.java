package iscteiul.ista.blackbattleship.us128782;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class da User Story 4:
 * "Como jogador, quero ver o ranking diário e o ranking dos últimos 30 dias,
 * para saber quem está a jogar melhor."
 * <p>Gerada a partir do cenário {@code UserStoryTest1} de {@code TestSuite_128782.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Esperar que os rankings carreguem</li>
 * <li>Verificar que o ranking diário é apresentado</li>
 * <li>Verificar que o ranking diário lista jogadores</li>
 * <li>Verificar que o ranking dos últimos 30 dias é apresentado</li>
 * <li>Verificar que o ranking dos últimos 30 dias lista jogadores</li>
 * </ol>
 * <p>Contém apenas os localizadores (CSS/XPath) e as operações sobre a página;
 * as verificações (asserções) ficam em {@code UserStory1Test}.
 */
public class UserStory1 {

    /** URL da página do jogo Battleship. */
    public static final String URL = "https://papergames.io/en/battleship";

    /** Botão "Do not consent" do aviso de cookies. */
    private static final By DECLINE_COOKIES = By.cssSelector(".fc-cta-do-not-consent");

    /** Título do ranking diário ("Daily leaderboard, ends in"). */
    private static final By DAILY_TITLE = By.xpath("//span[contains(.,'Daily leaderboard')]");

    /** Nomes dos jogadores do ranking diário. */
    private static final By DAILY_PLAYERS = By.cssSelector("app-tournament-leaderboard tr.item .player-name");

    /** Título do ranking dos últimos 30 dias ("RANKA"). */
    private static final By RANKA_TITLE = By.xpath("//label[contains(.,'RANKA')]");

    /** Nomes dos jogadores do ranking dos últimos 30 dias. */
    private static final By RANKA_PLAYERS = By.cssSelector("app-ranka-leaderboard tr.item .player-name");

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página do jogo Battleship e recusa os cookies, se o aviso aparecer.
     */
    public void open() {
        driver.get(URL);
        declineCookiesIfShown();
    }

    /**
     * Clica em "Do not consent" no aviso de cookies.
     * Se o aviso não aparecer em 5 segundos, não faz nada.
     */
    public void declineCookiesIfShown() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(DECLINE_COOKIES))
                    .click();
        } catch (TimeoutException e) {
            // O aviso de cookies não apareceu; segue normalmente.
        }
    }

    /**
     * Espera que o ranking diário seja apresentado e devolve o seu título.
     *
     * @return o texto do título do ranking diário
     */
    public String getDailyLeaderboardTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DAILY_TITLE)).getText().trim();
    }

    /**
     * Espera que o ranking diário liste jogadores e devolve quantos são.
     *
     * @return o número de jogadores no ranking diário
     */
    public int countDailyPlayers() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DAILY_PLAYERS)).size();
    }

    /**
     * Espera que o ranking dos últimos 30 dias seja apresentado e devolve o seu título.
     *
     * @return o texto do título do ranking dos últimos 30 dias
     */
    public String getRankaTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(RANKA_TITLE)).getText().trim();
    }

    /**
     * Espera que o ranking dos últimos 30 dias liste jogadores e devolve quantos são.
     *
     * @return o número de jogadores no ranking dos últimos 30 dias
     */
    public int countRankaPlayers() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(RANKA_PLAYERS)).size();
    }
}
