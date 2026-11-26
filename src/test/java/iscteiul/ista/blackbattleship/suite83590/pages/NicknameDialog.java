package iscteiul.ista.blackbattleship.suite83590.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.Wait;

/**
 * Page Object do diálogo "Who are you?", onde o convidado escolhe o nickname.
 * O diálogo só aparece a quem o site ainda não conhece.
 */
public class NicknameDialog {

    @FindBy(css = "mat-dialog-container")
    private SelenideElement dialog;

    @FindBy(css = "mat-dialog-container input.input-xl")
    private SelenideElement nicknameInput;

    @FindBy(css = "mat-dialog-container button[type='submit']")
    private SelenideElement continueButton;

    /**
     * Verifica que o diálogo pergunta "Who are you?".
     */
    @Step("Verificar que o diálogo pergunta 'Who are you?'")
    public NicknameDialog shouldAskForNickname() {
        dialog.shouldHave(text("Who are you?"));
        return this;
    }

    /**
     * Escreve o nickname e confirma com "Continue".
     *
     * @param nickname nickname de convidado
     */
    @Step("Escrever o nickname '{nickname}' e confirmar com 'Continue'")
    public void confirm(String nickname) {
        // O diálogo tem uma animação de entrada; repete até o campo aceitar o texto.
        Wait().withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(500)).until(d -> {
            try {
                nicknameInput.setValue(nickname);
                return nickname.equals(nicknameInput.getValue());
            } catch (WebDriverException e) {
                return false;
            }
        });
        continueButton.shouldBe(enabled).click();
    }

    /**
     * Se o site pedir o nickname, escreve-o e confirma; se já conhecer o jogador, não faz nada.
     *
     * @param nickname nickname de convidado
     */
    @Step("Indicar o nickname '{nickname}' se o site o pedir")
    public void confirmIfAsked(String nickname) {
        try {
            nicknameInput.shouldBe(visible, Duration.ofSeconds(8));
        } catch (AssertionError e) {
            return; // o site já conhece o jogador; não pede o nickname
        }
        confirm(nickname);
    }
}
