package iscteiul.ista.blackbattleship.us83590;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object da User Story 2:
 * "Como convidado, quero indicar um nickname, para poder jogar sem conta."
 * <p>Gerado a partir do cenário {@code UserStoryTest2} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play vs robot' para abrir o diálogo de registo</li>
 * <li>Verificar que o diálogo pergunta 'Who are you?'</li>
 * <li>Escrever o nickname de convidado</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Verificar que entrei na sala de jogo</li>
 * </ol>
 */
public class UserStory2 extends BattleshipBasePage {

    private static final By PLAY_VS_ROBOT = By.xpath("//app-juicy-button[contains(.,'Play vs robot')]//button");
    private static final By DIALOG = By.cssSelector("mat-dialog-container");
    private static final By NICKNAME_INPUT = By.cssSelector("mat-dialog-container input.input-xl");
    private static final By CONTINUE_BUTTON = By.cssSelector("mat-dialog-container button[type='submit']");

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory2(WebDriver driver) {
        super(driver);
    }

    /**
     * Clica em "Play vs robot", o que abre o diálogo de registo de convidado.
     */
    public void clickPlayVsRobot() {
        wait.until(ExpectedConditions.elementToBeClickable(PLAY_VS_ROBOT)).click();
    }

    /**
     * Espera que o diálogo de registo mostre a pergunta "Who are you?".
     *
     * @return true se o diálogo apresenta a pergunta
     */
    public boolean isNicknameDialogShown() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(DIALOG, "Who are you?"));
    }

    /**
     * Escreve o nickname no diálogo de registo.
     *
     * @param nickname nickname a usar
     */
    public void enterNickname(String nickname) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(NICKNAME_INPUT));
        input.clear();
        input.sendKeys(nickname);
    }

    /**
     * Clica em "Continue" para confirmar o nickname.
     */
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON)).click();
    }

    /**
     * Espera que o jogo entre na sala de jogo (URL com "/r/").
     *
     * @return true se o URL passou a conter "/r/"
     */
    public boolean isInGameRoom() {
        return wait.until(ExpectedConditions.urlContains("/r/"));
    }
}
