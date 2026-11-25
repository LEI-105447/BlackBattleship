package iscteiul.ista.blackbattleship.suite128782.pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Componente do aviso de cookies do papergames.io.
 * <p>O site usa dois avisos diferentes, que podem aparecer alguns segundos depois de a página carregar
 * e tapar os botões: o banner com "Do not consent" e o diálogo "We value your privacy" com "DISAGREE".
 * Este componente recusa o que estiver visível; se nenhum aparecer, não faz nada.
 */
public final class ConsentDialog {

    /** Botão de recusa de qualquer um dos dois avisos ("Do not consent" ou "DISAGREE"). */
    private static final By DECLINE_BUTTON = By.cssSelector(".fc-cta-do-not-consent, .qc-cmp2-container #disagree-btn");

    /** Os próprios avisos (para esperar que desapareçam depois de recusar). */
    private static final By CONSENT_OVERLAY = By.cssSelector(".fc-consent-root, .qc-cmp2-container");

    private ConsentDialog() {
        // Só métodos estáticos.
    }

    /**
     * Espera até {@code seconds} segundos por um aviso de cookies; se aparecer, recusa-o e espera que desapareça.
     * Usa o WebDriver diretamente para que a ausência do aviso não apareça como passo falhado no Allure.
     *
     * @param seconds tempo máximo de espera pelo aviso
     */
    @Step("Recusar os cookies, se o aviso aparecer")
    public static void declineIfShown(int seconds) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        try {
            // Escolhe o botão de recusa que estiver mesmo visível (o outro aviso pode existir escondido no DOM)
            WebElement decline = new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .ignoring(StaleElementReferenceException.class)
                    .until(d -> d.findElements(DECLINE_BUTTON).stream()
                            .filter(b -> b.isDisplayed() && b.isEnabled())
                            .findFirst()
                            .orElse(null));
            decline.click();
        } catch (TimeoutException e) {
            return; // nenhum aviso apareceu
        }
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .ignoring(StaleElementReferenceException.class)
                    .until(d -> d.findElements(CONSENT_OVERLAY).stream().noneMatch(WebElement::isDisplayed));
        } catch (TimeoutException e) {
            // O aviso ficou visível no DOM mas o clique de recusa foi feito; segue normalmente.
        }
    }
}
