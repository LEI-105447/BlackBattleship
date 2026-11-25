package iscteiul.ista.blackbattleship.suite128782.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;

/**
 * Page Object da página "Tournament creation".
 * <p>O botão "Create and share" nunca é premido, para não criar torneios reais.
 * Os elementos são inicializados pela Page Factory do Selenide ({@code page(TournamentCreationPage.class)}).
 */
public class TournamentCreationPage {

    @FindBy(xpath = "//h1[contains(.,'Tournament creation')]")
    private SelenideElement title;

    @FindBy(id = "tf-name")
    private SelenideElement tournamentNameField;

    @FindBy(css = "button.tf__cta-btn")
    private SelenideElement createAndShareButton;

    /**
     * Espera que o campo do nome do torneio fique visível.
     *
     * @return esta página
     */
    @Step("Esperar pela página de criação de torneios")
    public TournamentCreationPage waitUntilLoaded() {
        tournamentNameField.shouldBe(visible);
        // O aviso de cookies pode voltar a aparecer depois de mudar de página
        ConsentDialog.declineIfShown(5);
        return this;
    }

    /**
     * Escreve o nome do torneio no campo "Tournament name".
     *
     * @param name nome do torneio
     * @return esta página
     */
    @Step("Escrever o nome do torneio")
    public TournamentCreationPage typeTournamentName(String name) {
        ConsentDialog.declineIfShown(2);
        tournamentNameField.setValue(name);
        return this;
    }

    /** @return o título da página */
    public SelenideElement title() {
        return title;
    }

    /** @return o campo "Tournament name" */
    public SelenideElement tournamentNameField() {
        return tournamentNameField;
    }

    /** @return o botão "Create and share" (não deve ser premido) */
    public SelenideElement createAndShareButton() {
        return createAndShareButton;
    }
}
