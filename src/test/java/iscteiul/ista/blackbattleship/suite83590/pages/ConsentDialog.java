package iscteiul.ista.blackbattleship.suite83590.pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Componente do aviso de cookies do papergames.io.
 * <p>O site mostra um aviso de consentimento ("Do not consent" ou "DISAGREE") alguns segundos depois
 * de a página carregar, e pode mostrá-lo outra vez a meio de uma partida. Enquanto está visível, tapa
 * os botões do jogo. Este componente recusa o aviso assim que ele aparece, sem esperas fixas.
 */
public final class ConsentDialog {

    /** Botão de recusa de qualquer um dos dois avisos. */
    private static final By DECLINE_BUTTON =
            By.cssSelector(".fc-cta-do-not-consent, .qc-cmp2-container #disagree-btn");

    /** Os próprios avisos (para esperar que desapareçam depois de recusar). */
    private static final By CONSENT_OVERLAY = By.cssSelector(".fc-consent-root, .qc-cmp2-container");

    /**
     * Script instalado na página: sempre que o DOM muda, procura um botão de recusa visível e clica
     * nele. A marca {@code __autoDeclineConsent} evita instalar o observador duas vezes.
     */
    private static final String AUTO_DECLINE_SCRIPT =
            "if (!window.__autoDeclineConsent) {"
            + "  window.__autoDeclineConsent = true;"
            + "  const decline = () => document"
            + "    .querySelectorAll('.fc-cta-do-not-consent, .qc-cmp2-container #disagree-btn')"
            + "    .forEach(b => { if (b.getClientRects().length > 0) { b.click(); } });"
            + "  new MutationObserver(decline).observe(document.documentElement, {childList: true, subtree: true});"
            + "  decline();"
            + "}";

    private ConsentDialog() {
        // Só métodos estáticos.
    }

    /**
     * Instala na página atual um observador que recusa o aviso de cookies assim que aparecer, em
     * qualquer momento do teste. Deve ser chamado logo depois de abrir a página.
     */
    @Step("Recusar automaticamente o aviso de cookies sempre que aparecer")
    public static void autoDecline() {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(AUTO_DECLINE_SCRIPT);
    }

    /**
     * Espera até {@code seconds} segundos pelo aviso; se aparecer, recusa-o e espera que desapareça.
     * Se não aparecer, não faz nada.
     *
     * @param seconds tempo máximo de espera pelo aviso
     */
    @Step("Recusar os cookies, se o aviso aparecer")
    public static void declineIfShown(int seconds) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        try {
            WebElement decline = new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .ignoring(StaleElementReferenceException.class)
                    .until(d -> d.findElements(DECLINE_BUTTON).stream()
                            .filter(b -> b.isDisplayed() && b.isEnabled())
                            .findFirst()
                            .orElse(null));
            decline.click();
        } catch (TimeoutException e) {
            return; // o aviso não apareceu
        }
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .ignoring(StaleElementReferenceException.class)
                    .until(d -> d.findElements(CONSENT_OVERLAY).stream().noneMatch(WebElement::isDisplayed));
        } catch (TimeoutException e) {
            // o clique de recusa foi feito; segue normalmente
        }
    }
}
