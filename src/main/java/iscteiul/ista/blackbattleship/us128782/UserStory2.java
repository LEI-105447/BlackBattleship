package iscteiul.ista.blackbattleship.us128782;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class da User Story 3:
 * "Como jogador, quero começar uma partida contra o robot, para jogar a qualquer hora."
 * <p>Gerada a partir do cenário {@code UserStoryTest2} de {@code TestSuite_128782.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Recusar os cookies, se o aviso aparecer</li>
 * <li>Escolher o modo 'Play vs robot'</li>
 * <li>Se o site pedir o nickname: indicar o nickname de convidado e confirmar com 'Continue'</li>
 * <li>Esperar que a sala de jogo carregue</li>
 * <li>Verificar que o meu tabuleiro é apresentado</li>
 * <li>Verificar que o tabuleiro do adversário é apresentado</li>
 * <li>Verificar que o botão 'Abort game' está disponível</li>
 * <li>Terminar a partida com 'Abort game' e confirmar (limpeza)</li>
 * </ol>
 * <p>Contém apenas os localizadores (CSS/XPath) e as operações sobre a página;
 * as verificações (asserções) ficam em {@code UserStory2Test}.
 */
public class UserStory2 {

    /** URL da página do jogo Battleship. */
    public static final String URL = "https://papergames.io/en/battleship";

    /** Botão "Do not consent" do aviso de cookies. */
    private static final By DECLINE_COOKIES = By.cssSelector(".fc-cta-do-not-consent");

    /** Botão principal do modo "Play vs robot" (sem a roda dentada das definições). */
    private static final By PLAY_VS_ROBOT = By.xpath("//app-juicy-button[contains(.,'Play vs robot')]/button");

    /** Campo do nickname no diálogo "Who are you?". */
    private static final By NICKNAME_INPUT = By.cssSelector("mat-dialog-container input.input-xl");

    /** Botão "Continue" do diálogo "Who are you?". */
    private static final By CONTINUE_BUTTON = By.cssSelector("mat-dialog-container button[type='submit']");

    /** Cabeçalho do meu tabuleiro ("Your boats"). */
    private static final By MY_BOARD_HEADER = By.cssSelector(".current-player .header");

    /** Tabuleiro do adversário. */
    private static final By OPPONENT_BOARD = By.cssSelector(".opponent table.table-board");

    /** Botão "Abort game" da sala de jogo. */
    private static final By ABORT_GAME = By.xpath("//button[contains(.,'Abort game')]");

    /** Botão "Abort game" do pedido de confirmação. */
    private static final By CONFIRM_ABORT = By.xpath("//mat-dialog-container//button[contains(.,'Abort game')]");

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory2(WebDriver driver) {
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
     * Começa uma partida contra o robot: escolhe "Play vs robot", indica o nickname
     * se o site o pedir e espera que a sala de jogo carregue.
     *
     * @param nickname nickname de convidado a usar se o site o pedir
     */
    public void startGameVsRobot(String nickname) {
        wait.until(ExpectedConditions.elementToBeClickable(PLAY_VS_ROBOT)).click();
        enterNicknameIfAsked(nickname);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MY_BOARD_HEADER));
    }

    /**
     * Se aparecer o diálogo "Who are you?", escreve o nickname e confirma com "Continue".
     * Se o site já conhecer o jogador, o diálogo não aparece e não faz nada.
     *
     * @param nickname nickname de convidado
     */
    private void enterNicknameIfAsked(String nickname) {
        WebElement input;
        try {
            input = new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfElementLocated(NICKNAME_INPUT));
        } catch (TimeoutException e) {
            return; // o diálogo não apareceu
        }
        input.sendKeys(nickname);
        // "Continue" só fica ativo depois de o site validar o nickname
        wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON)).click();
    }

    /**
     * @return o texto do cabeçalho do meu tabuleiro
     */
    public String getMyBoardHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MY_BOARD_HEADER)).getText().trim();
    }

    /**
     * @return true se o tabuleiro do adversário estiver visível
     */
    public boolean isOpponentBoardDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(OPPONENT_BOARD)).isDisplayed();
    }

    /**
     * @return o texto do botão "Abort game"
     */
    public String getAbortButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ABORT_GAME)).getText().trim();
    }

    /**
     * Termina a partida com "Abort game" e confirma (limpeza no fim do teste).
     * Se não houver partida a decorrer, não faz nada.
     */
    public void abortGame() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.elementToBeClickable(ABORT_GAME)).click();
            shortWait.until(ExpectedConditions.elementToBeClickable(CONFIRM_ABORT)).click();
        } catch (TimeoutException e) {
            // Não há partida para terminar; segue normalmente.
        }
    }
}
