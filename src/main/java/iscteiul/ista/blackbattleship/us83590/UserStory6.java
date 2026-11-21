package iscteiul.ista.blackbattleship.us83590;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object da User Story 6 (US15 do backlog):
 * "Como jogador, quero escolher 'Play with a friend' e obter um link de convite,
 * para jogar com alguém que conheço."
 * A sala mostra o link de convite, um QR code, o ícone para copiar o link e o
 * botão "Leave room".
 * <p>Gerado a partir do cenário {@code UserStoryTest6} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play with a friend' para jogar com um amigo</li>
 * <li>Escrever o nickname de convidado no diálogo de registo</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Verificar que é apresentado o link de convite</li>
 * <li>Verificar que existe o ícone para copiar o link</li>
 * <li>Clicar no ícone para copiar o link de convite</li>
 * <li>Clicar em 'Leave room' para sair da sala</li>
 * <li>Verificar que voltei à página inicial do jogo</li>
 * </ol>
 */
public class UserStory6 extends BattleshipBasePage {

    private static final By PLAY_WITH_FRIEND =
            By.xpath("(//app-juicy-button[contains(.,'Play with a friend')]//button)[1]");
    private static final By INVITE_LINK =
            By.xpath("//*[contains(normalize-space(.),'papergames.io/en/r/')]");
    private static final By COPY_ICON = By.cssSelector(".fa-copy");
    private static final By LEAVE_ROOM = By.xpath("//button[contains(.,'Leave room')]");

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory6(WebDriver driver) {
        super(driver);
    }

    /**
     * Clica em "Play with a friend", indica o nickname se o site o pedir e espera pela sala.
     *
     * @param nickname nickname de convidado
     */
    public void startFriendGame(String nickname) {
        wait.until(ExpectedConditions.elementToBeClickable(PLAY_WITH_FRIEND)).click();
        enterNicknameIfAsked(nickname);
        wait.until(ExpectedConditions.urlContains("/r/"));
    }

    /**
     * @return true se o link de convite está visível na sala
     */
    public boolean isInviteLinkShown() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(INVITE_LINK)).isDisplayed();
    }

    /**
     * @return true se o ícone para copiar o link está presente na sala
     */
    public boolean isCopyIconShown() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(COPY_ICON)) != null;
    }

    /**
     * Clica no ícone para copiar o link de convite.
     */
    public void clickCopyIcon() {
        // Há um botão transparente por cima do ícone, por isso o clique normal é intercetado;
        // clica-se nas coordenadas do ícone, como faria um utilizador.
        WebElement icon = wait.until(ExpectedConditions.presenceOfElementLocated(COPY_ICON));
        new Actions(driver).moveToElement(icon).click().perform();
    }

    /**
     * Clica em "Leave room" para sair da sala.
     */
    public void leaveRoom() {
        wait.until(ExpectedConditions.elementToBeClickable(LEAVE_ROOM)).click();
    }

    /**
     * @return true se o jogador voltou à página inicial do jogo (botão "Play with a friend" visível)
     */
    public boolean isBackAtHome() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PLAY_WITH_FRIEND)).isDisplayed();
    }
}
