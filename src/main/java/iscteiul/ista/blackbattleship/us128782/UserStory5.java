package iscteiul.ista.blackbattleship.us128782;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

/**
 * Page Object Class da User Story 21:
 * "Como organizador, quero dar um nome ao torneio, para os amigos o reconhecerem."
 * <p>Gerada a partir do cenário {@code UserStoryTest5} de {@code TestSuite_128782.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Recusar os cookies, se o aviso aparecer</li>
 * <li>Escolher 'Create tournament'</li>
 * <li>Esperar pela página de criação de torneios</li>
 * <li>Verificar que a página 'Tournament creation' é apresentada</li>
 * <li>Verificar que 'Create and share' está desativado enquanto não há nome</li>
 * <li>Escrever o nome do torneio no campo 'Tournament name'</li>
 * <li>Verificar que o campo aceitou o nome do torneio</li>
 * <li>Verificar que o nome é considerado válido</li>
 * <li>Verificar que 'Create and share' fica ativo (sem o premir)</li>
 * </ol>
 * <p>O botão 'Create and share' nunca é premido, para não criar um torneio real.
 * <p>Contém apenas os localizadores (CSS/XPath) e as operações sobre a página;
 * as verificações (asserções) ficam em {@code UserStory5Test}.
 */
public class UserStory5 {

    /** URL da página do jogo Battleship. */
    public static final String URL = "https://papergames.io/en/battleship";

    /** Botão "Do not consent" do aviso de cookies. */
    private static final By DECLINE_COOKIES = By.cssSelector(".fc-cta-do-not-consent");

    /** Botão (ligação) "Create tournament" da página do jogo. */
    private static final By CREATE_TOURNAMENT = By.xpath("//app-juicy-button[contains(.,'Create tournament')]//a");

    /** Título da página de criação de torneios. */
    private static final By PAGE_TITLE = By.xpath("//h1[contains(.,'Tournament creation')]");

    /** Campo "Tournament name". */
    private static final By TOURNAMENT_NAME = By.id("tf-name");

    /** Botão "Create and share". */
    private static final By CREATE_AND_SHARE = By.cssSelector("button.tf__cta-btn");

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory5(WebDriver driver) {
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
     * Escolhe "Create tournament" e espera que o campo do nome fique visível.
     */
    public void openTournamentCreation() {
        wait.until(ExpectedConditions.elementToBeClickable(CREATE_TOURNAMENT)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(TOURNAMENT_NAME));
    }

    /**
     * @return o título da página de criação de torneios
     */
    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE)).getText().trim();
    }

    /**
     * Escreve o nome do torneio no campo "Tournament name".
     *
     * @param name nome do torneio
     */
    public void typeTournamentName(String name) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(TOURNAMENT_NAME));
        field.clear();
        field.sendKeys(name);
    }

    /**
     * @return o valor atual do campo "Tournament name"
     */
    public String getTournamentName() {
        return driver.findElement(TOURNAMENT_NAME).getDomProperty("value");
    }

    /**
     * Indica se o site considera o nome válido (o campo tem a classe "ng-valid" do Angular).
     *
     * @return true se o nome for válido
     */
    public boolean isTournamentNameValid() {
        String classes = driver.findElement(TOURNAMENT_NAME).getAttribute("class");
        return classes != null && Arrays.asList(classes.split("\\s+")).contains("ng-valid");
    }

    /**
     * Indica se o botão "Create and share" está ativo. Não o prime.
     *
     * @return true se o botão estiver ativo
     */
    public boolean isCreateAndShareEnabled() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(CREATE_AND_SHARE)).isEnabled();
    }

    /**
     * Espera (até 10 segundos) que o botão "Create and share" fique ativo. Não o prime.
     *
     * @return true se o botão ficou ativo dentro do tempo de espera
     */
    public boolean waitUntilCreateAndShareEnabled() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(CREATE_AND_SHARE));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
