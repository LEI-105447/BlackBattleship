package iscteiul.ista.blackbattleship.us83590;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object da User Story 3:
 * "Como jogador, quero disparar sobre uma casa do tabuleiro, para tentar
 * afundar os navios do adversário."
 * <p>Gerado a partir do cenário {@code UserStoryTest3} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play vs robot' para jogar contra o computador</li>
 * <li>Escrever o nickname de convidado no diálogo de registo</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Esperar que o tabuleiro apareça</li>
 * <li>Disparar sobre a casa A1 do tabuleiro do adversário</li>
 * <li>Verificar que a casa disparada ficou marcada (falha ou acerto)</li>
 * </ol>
 */
public class UserStory3 extends BattleshipBasePage {

    private static final By PLAY_VS_ROBOT = By.xpath("//app-juicy-button[contains(.,'Play vs robot')]//button");
    private static final By NICKNAME_INPUT = By.cssSelector("mat-dialog-container input.input-xl");
    private static final By CONTINUE_BUTTON = By.cssSelector("mat-dialog-container button[type='submit']");
    private static final By BOARDS = By.cssSelector("table.table-board");

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory3(WebDriver driver) {
        super(driver);
    }

    /**
     * Inicia uma partida contra o robot como convidado.
     *
     * @param nickname nickname de convidado
     */
    public void startGameVsRobot(String nickname) {
        wait.until(ExpectedConditions.elementToBeClickable(PLAY_VS_ROBOT)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(NICKNAME_INPUT)).sendKeys(nickname);
        wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON)).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(BOARDS, 2));
    }

    /**
     * Dispara sobre uma casa do tabuleiro do adversário (o segundo tabuleiro).
     *
     * @param row    linha (0 a 9)
     * @param column coluna (0 a 9)
     */
    public void fireAt(int row, int column) {
        By cell = opponentCell(row, column);
        By mark = shotMark(row, column);
        // O jogo mostra um aviso animado "It's your turn!" no início; repete o clique até a casa ficar marcada.
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
            d.findElement(cell).click();
            return !d.findElements(mark).isEmpty();
        });
    }

    /**
     * Indica se a casa disparada ficou marcada (falha ou acerto).
     *
     * @param row    linha (0 a 9)
     * @param column coluna (0 a 9)
     * @return true se a casa apresenta uma marca de disparo
     */
    public boolean isShotMarked(int row, int column) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(shotMark(row, column))) != null;
    }

    private By shotMark(int row, int column) {
        return By.xpath("(//table[contains(@class,'table-board')])[2]"
                + "//td[contains(@class,'cell-" + row + "-" + column + "')]"
                // falha: <svg class="... no-hit">; acerto: <div class="hit fire">
                + "//*[contains(@class,'hit')]");
    }

    private By opponentCell(int row, int column) {
        return By.xpath("(//table[contains(@class,'table-board')])[2]"
                + "//td[contains(@class,'cell-" + row + "-" + column + "')]");
    }
}
