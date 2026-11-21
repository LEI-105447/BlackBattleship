package iscteiul.ista.blackbattleship.us83590;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object da User Story 5 (US12 do backlog):
 * "Como jogador, quero desistir de uma partida, para terminar o jogo quando já
 * não quero continuar."
 * O botão chama-se "Abort game" no início e "Resign" depois de jogar; pede confirmação.
 * <p>Gerado a partir do cenário {@code UserStoryTest5} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play vs robot' para jogar contra o computador</li>
 * <li>Escrever o nickname de convidado no diálogo de registo</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Esperar que o tabuleiro apareça</li>
 * <li>Clicar no botão de desistir</li>
 * <li>Verificar que é pedida confirmação ('Are you sure')</li>
 * <li>Confirmar a desistência</li>
 * <li>Verificar que a partida terminou (tabuleiros desapareceram)</li>
 * </ol>
 */
public class UserStory5 extends BattleshipBasePage {

    private static final By QUIT_BUTTON = By.xpath("//button[contains(.,'Abort game') or contains(.,'Resign')]");
    private static final By DIALOG = By.cssSelector("mat-dialog-container");
    private static final By DIALOG_BUTTONS = By.cssSelector("mat-dialog-container button");
    private static final By BOARDS = By.cssSelector("table.table-board");

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory5(WebDriver driver) {
        super(driver);
    }

    /**
     * Clica no botão de desistir ("Abort game" ou "Resign").
     */
    public void clickQuit() {
        // Se o aviso de cookies aparecer tarde e tapar o botão, recusa-o e repete o clique.
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
            try {
                d.findElement(QUIT_BUTTON).click();
                return true;
            } catch (ElementClickInterceptedException e) {
                dismissConsentIfPresent();
                return false;
            }
        });
    }

    /**
     * @return o texto do diálogo de confirmação
     */
    public String getConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DIALOG)).getText();
    }

    /**
     * Confirma a desistência (último botão do diálogo; o primeiro é "Cancel").
     */
    public void confirmQuit() {
        var buttons = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(DIALOG_BUTTONS, 1));
        buttons.get(buttons.size() - 1).click();
    }

    /**
     * @return true se os tabuleiros desapareceram, ou seja, a partida terminou
     */
    public boolean isGameOver() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(BOARDS));
    }
}
