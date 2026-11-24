package iscteiul.ista.blackbattleship.us128782;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class da User Story 17:
 * "Como jogador, quero configurar o jogo (tempo por jogada, minutos por jogador,
 * quem joga primeiro, tempo ilimitado), para adaptar as regras à partida."
 * <p>Gerada a partir do cenário {@code UserStoryTest4} de {@code TestSuite_128782.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Recusar os cookies, se o aviso aparecer</li>
 * <li>Abrir as definições de jogo (roda dentada de 'Play with a friend')</li>
 * <li>Esperar pelo diálogo 'Game settings'</li>
 * <li>Verificar que o diálogo 'Game settings' é apresentado</li>
 * <li>Verificar o tempo por jogada por omissão (40 segundos)</li>
 * <li>Verificar os minutos por jogador por omissão (3 minutos)</li>
 * <li>Verificar que o primeiro jogador é aleatório por omissão</li>
 * <li>Guardar as definições com 'Save settings'</li>
 * <li>Verificar que as definições foram guardadas e o diálogo fechou</li>
 * </ol>
 * <p>Contém apenas os localizadores (CSS/XPath) e as operações sobre a página;
 * as verificações (asserções) ficam em {@code UserStory4Test}.
 */
public class UserStory4 {

    /** URL da página do jogo Battleship. */
    public static final String URL = "https://papergames.io/en/battleship";

    /** Botão "Do not consent" do aviso de cookies. */
    private static final By DECLINE_COOKIES = By.cssSelector(".fc-cta-do-not-consent");

    /** Roda dentada (definições) dentro do botão "Play with a friend". */
    private static final By SETTINGS_GEAR =
            By.xpath("//app-juicy-button[contains(.,'Play with a friend')]//button[@mat-icon-button]");

    /** Diálogo "Game settings". */
    private static final By SETTINGS_DIALOG = By.cssSelector("app-game-settings-dialog");

    /** Título do diálogo. */
    private static final By DIALOG_TITLE = By.xpath("//app-game-settings-dialog//h2");

    /** Valor de "Time per turn". */
    private static final By TIME_PER_TURN =
            By.xpath("//app-game-settings-dialog//div[contains(.,'Time per turn')]/strong");

    /** Valor de "Minutes per player". */
    private static final By MINUTES_PER_PLAYER =
            By.xpath("//app-game-settings-dialog//div[contains(.,'Minutes per player')]/strong");

    /** Valor de "Who plays first?". */
    private static final By WHO_PLAYS_FIRST =
            By.xpath("//app-game-settings-dialog//div[contains(.,'Who plays first')]/strong");

    /** Botão "Save settings". */
    private static final By SAVE_SETTINGS =
            By.xpath("//app-game-settings-dialog//button[contains(.,'Save settings')]");

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory4(WebDriver driver) {
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
     * Abre o diálogo "Game settings" pela roda dentada de "Play with a friend"
     * e espera que fique visível.
     */
    public void openGameSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(SETTINGS_GEAR)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SETTINGS_DIALOG));
    }

    /**
     * @return o título do diálogo de definições
     */
    public String getDialogTitle() {
        return textOf(DIALOG_TITLE);
    }

    /**
     * @return o valor de "Time per turn" (ex.: "40 seconds")
     */
    public String getTimePerTurn() {
        return textOf(TIME_PER_TURN);
    }

    /**
     * @return o valor de "Minutes per player" (ex.: "3 minutes")
     */
    public String getMinutesPerPlayer() {
        return textOf(MINUTES_PER_PLAYER);
    }

    /**
     * @return o valor de "Who plays first?" (ex.: "Random")
     */
    public String getWhoPlaysFirst() {
        return textOf(WHO_PLAYS_FIRST);
    }

    /**
     * Guarda as definições com "Save settings".
     */
    public void saveSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(SAVE_SETTINGS)).click();
    }

    /**
     * Espera que o diálogo de definições feche.
     *
     * @return true se o diálogo fechou dentro do tempo de espera
     */
    public boolean isSettingsDialogClosed() {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(SETTINGS_DIALOG));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Espera que o elemento fique visível e devolve o seu texto, sem espaços nas pontas.
     *
     * @param locator localizador do elemento
     * @return o texto do elemento
     */
    private String textOf(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }
}
