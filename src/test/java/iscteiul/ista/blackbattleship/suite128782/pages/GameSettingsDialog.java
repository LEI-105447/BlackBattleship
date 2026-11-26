package iscteiul.ista.blackbattleship.suite128782.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

/**
 * Page Object do diálogo "Game settings" (aberto pela roda dentada de "Play with a friend").
 * <p>Os elementos são inicializados pela Page Factory do Selenide ({@code page(GameSettingsDialog.class)}).
 */
public class GameSettingsDialog {

    @FindBy(css = "app-game-settings-dialog")
    private SelenideElement dialog;

    @FindBy(xpath = "//app-game-settings-dialog//h2")
    private SelenideElement title;

    @FindBy(xpath = "//app-game-settings-dialog//div[contains(.,'Time per turn')]/strong")
    private SelenideElement timePerTurn;

    @FindBy(xpath = "//app-game-settings-dialog//div[contains(.,'Minutes per player')]/strong")
    private SelenideElement minutesPerPlayer;

    @FindBy(xpath = "//app-game-settings-dialog//div[contains(.,'Who plays first')]/strong")
    private SelenideElement whoPlaysFirst;

    @FindBy(xpath = "//app-game-settings-dialog//button[contains(.,'Save settings')]")
    private SelenideElement saveSettingsButton;

    /**
     * Espera que o diálogo fique visível.
     *
     * @return este diálogo
     */
    @Step("Esperar pelo diálogo 'Game settings'")
    public GameSettingsDialog waitUntilOpen() {
        dialog.shouldBe(visible);
        return this;
    }

    /**
     * Guarda as definições com "Save settings".
     *
     * @return este diálogo
     */
    @Step("Guardar as definições com 'Save settings'")
    public GameSettingsDialog save() {
        ConsentDialog.declineIfShown(2);
        saveSettingsButton.shouldBe(enabled).click();
        return this;
    }

    /** @return o diálogo em si (para verificar se está aberto ou fechado) */
    public SelenideElement dialog() {
        return dialog;
    }

    /** @return o título do diálogo */
    public SelenideElement title() {
        return title;
    }

    /** @return o valor de "Time per turn" */
    public SelenideElement timePerTurn() {
        return timePerTurn;
    }

    /** @return o valor de "Minutes per player" */
    public SelenideElement minutesPerPlayer() {
        return minutesPerPlayer;
    }

    /** @return o valor de "Who plays first?" */
    public SelenideElement whoPlaysFirst() {
        return whoPlaysFirst;
    }
}
