package iscteiul.ista.blackbattleship.suite83590.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.page;

/**
 * Page Object da sala criada por "Play with a friend": mostra o link de convite, um QR code, o
 * ícone para copiar o link e o botão "Leave room".
 */
public class InviteRoomPage {

    @FindBy(xpath = "//span[contains(.,'papergames.io/en/r/')]")
    private SelenideElement inviteLink;

    @FindBy(css = ".fa-copy")
    private SelenideElement copyIcon;

    @FindBy(xpath = "//button[contains(.,'Leave room')]")
    private SelenideElement leaveRoom;

    /**
     * Verifica que é apresentado o link de convite.
     */
    @Step("Verificar que é apresentado o link de convite")
    public InviteRoomPage shouldShowInviteLink() {
        inviteLink.shouldBe(visible);
        return this;
    }

    /**
     * Verifica que existe o ícone para copiar o link.
     */
    @Step("Verificar que existe o ícone para copiar o link")
    public InviteRoomPage shouldShowCopyIcon() {
        copyIcon.should(exist);
        return this;
    }

    /**
     * Clica no ícone para copiar o link. Há um botão transparente por cima do ícone, por isso o
     * clique é feito nas coordenadas do ícone, como faria um utilizador.
     */
    @Step("Clicar no ícone para copiar o link de convite")
    public InviteRoomPage clickCopyIcon() {
        actions().moveToElement(copyIcon).click().perform();
        return this;
    }

    /**
     * Sai da sala e volta à página inicial.
     */
    @Step("Clicar em 'Leave room' para sair da sala")
    public HomePage leave() {
        leaveRoom.shouldBe(visible).click();
        return page(HomePage.class);
    }
}
