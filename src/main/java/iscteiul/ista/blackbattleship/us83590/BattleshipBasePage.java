package iscteiul.ista.blackbattleship.us83590;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Classe base dos Page Objects do jogo Battleship em https://papergames.io/en/battleship.
 * Reúne o que é comum a todas as user stories: abrir a página e recusar os cookies.
 */
public abstract class BattleshipBasePage {

    /** URL da página do jogo. */
    public static final String URL = "https://papergames.io/en/battleship";

    private static final By DECLINE_COOKIES = By.cssSelector(".fc-cta-do-not-consent");

    /** Driver usado nos testes. */
    protected final WebDriver driver;

    /** Espera explícita (10 segundos). */
    protected final WebDriverWait wait;

    /**
     * @param driver o WebDriver em uso
     */
    protected BattleshipBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /** Nickname de convidado usado em todos os testes. */
    public static final String NICKNAME = "Tester83590";

    /**
     * Abre a página do jogo e recusa os cookies, se o aviso aparecer.
     */
    public void open() {
        driver.get(URL);
        declineCookies();
    }

    /**
     * Clica em "Do not consent" no aviso de cookies. Se o aviso não aparecer, não faz nada.
     */
    protected void declineCookies() {
        try {
            WebElement decline = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(DECLINE_COOKIES));
            // Pausa para se ver o aviso (botões de aceitar e recusar) antes de o recusar.
            pause(2000);
            decline.click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(DECLINE_COOKIES));
        } catch (TimeoutException e) {
            // O aviso de cookies não apareceu; segue normalmente.
        }
    }

    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Recusa os cookies se o aviso estiver visível neste momento, sem esperar por ele.
     * Serve para quando o aviso aparece tarde e tapa os botões da página.
     */
    protected void dismissConsentIfPresent() {
        var banner = driver.findElements(DECLINE_COOKIES);
        if (!banner.isEmpty()) {
            try {
                banner.get(0).click();
            } catch (WebDriverException e) {
                // O aviso desapareceu entretanto; segue normalmente.
            }
        }
    }

    /**
     * Se o site pedir o nickname (diálogo "Who are you?"), escreve-o e confirma com "Continue".
     * Se o jogador já for conhecido pelo site, o diálogo não aparece e não faz nada.
     *
     * @param nickname nickname de convidado
     */
    protected void enterNicknameIfAsked(String nickname) {
        By nicknameInput = By.cssSelector("mat-dialog-container input.input-xl");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfElementLocated(nicknameInput)).sendKeys(nickname);
        } catch (TimeoutException e) {
            return; // o site já conhece o jogador; não pede o nickname
        }
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("mat-dialog-container button[type='submit']"))).click();
    }

    /**
     * Inicia uma partida contra o robot como convidado: "Play vs robot", nickname e "Continue",
     * e espera pelos dois tabuleiros.
     *
     * @param nickname nickname de convidado
     */
    public void startGameVsRobot(String nickname) {
        By playVsRobot = By.xpath("//app-juicy-button[contains(.,'Play vs robot')]//button");
        By nicknameInput = By.cssSelector("mat-dialog-container input.input-xl");
        By continueButton = By.cssSelector("mat-dialog-container button[type='submit']");

        // O clique pode perder-se se a página ainda estiver a carregar ou se o aviso de cookies
        // aparecer tarde; repete até o diálogo do nickname estar visível.
        new WebDriverWait(driver, Duration.ofSeconds(25)).pollingEvery(Duration.ofSeconds(2)).until(d -> {
            dismissConsentIfPresent();
            var input = d.findElements(nicknameInput);
            if (!input.isEmpty() && input.get(0).isDisplayed()) {
                return true;
            }
            try {
                d.findElement(playVsRobot).click();
            } catch (WebDriverException e) {
                // Botão ainda tapado ou a carregar; tenta outra vez.
            }
            return false;
        });
        wait.until(ExpectedConditions.visibilityOfElementLocated(nicknameInput)).sendKeys(nickname);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.table-board"), 2));
    }

    /**
     * @return o URL atual
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
