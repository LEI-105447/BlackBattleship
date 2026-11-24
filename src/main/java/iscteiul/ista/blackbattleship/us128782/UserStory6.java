package iscteiul.ista.blackbattleship.us128782;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class da User Story 31:
 * "Como jogador, quero ler as regras do jogo na própria página,
 * para saber como se joga antes de começar."
 * <p>Gerada a partir do cenário {@code UserStoryTest6} de {@code TestSuite_128782.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Recusar os cookies, se o aviso aparecer</li>
 * <li>Deslocar a página até à secção das regras</li>
 * <li>Verificar que a secção 'Rules of Battleship game online' é apresentada</li>
 * <li>Verificar que as regras indicam a grelha de 10×10</li>
 * <li>Verificar que as regras indicam a frota de cinco navios (5, 4, 3, 3 e 2 casas)</li>
 * </ol>
 * <p>Contém apenas os localizadores (CSS/XPath) e as operações sobre a página;
 * as verificações (asserções) ficam em {@code UserStory6Test}.
 */
public class UserStory6 {

    /** URL da página do jogo Battleship. */
    public static final String URL = "https://papergames.io/en/battleship";

    /** Botão "Do not consent" do aviso de cookies. */
    private static final By DECLINE_COOKIES = By.cssSelector(".fc-cta-do-not-consent");

    /** Título da secção das regras. */
    private static final By RULES_TITLE = By.xpath("//h2[contains(.,'Rules of Battleship')]");

    /** Primeiro parágrafo da secção das regras (grelha e frota). */
    private static final By RULES_FIRST_PARAGRAPH =
            By.xpath("//h2[contains(.,'Rules of Battleship')]/following-sibling::p[1]");

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory6(WebDriver driver) {
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
     * Desloca a página até à secção das regras, deixando o título a meio do ecrã,
     * como faria um utilizador com a roda do rato.
     */
    public void scrollToRules() {
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(RULES_TITLE));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", title);
        wait.until(ExpectedConditions.visibilityOf(title));
    }

    /**
     * @return o título da secção das regras
     */
    public String getRulesTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(RULES_TITLE)).getText().trim();
    }

    /**
     * @return o texto do primeiro parágrafo das regras (descrição da grelha e da frota)
     */
    public String getRulesFirstParagraph() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(RULES_FIRST_PARAGRAPH)).getText().trim();
    }
}
