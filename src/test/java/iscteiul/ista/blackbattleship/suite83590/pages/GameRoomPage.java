package iscteiul.ista.blackbattleship.suite83590.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.Wait;

/**
 * Page Object do ecrã de uma partida contra o robot: os dois tabuleiros, a barra de armas e o
 * botão de desistir.
 */
public class GameRoomPage {

    /** Tempo máximo à espera da vez do jogador, quando é o robot a começar. */
    private static final Duration ROBOT_FIRST_TIMEOUT = Duration.ofSeconds(120);

    private static final String OPPONENT_BOARD = "(//table[contains(@class,'table-board')])[2]";

    @FindBy(css = "table.table-board")
    private ElementsCollection boards;

    /** Cabeçalho do tabuleiro do adversário; só diz "Attack your opponent!" quando é a vez do jogador. */
    @FindBy(xpath = "//*[contains(normalize-space(.),'Attack your opponent')][not(*)]")
    private SelenideElement yourTurnHeader;

    @FindBy(xpath = "//button[contains(.,'Abort game') or contains(.,'Resign')]")
    private SelenideElement quitButton;

    /** Míssil grande: botão de arma ativo e com contador (o míssil simples não tem número). */
    @FindBy(xpath = "//button[contains(@class,'weapon-button')][not(@disabled)][normalize-space(.)!='']")
    private SelenideElement bigMissile;

    /**
     * Verifica que a partida mostra dois tabuleiros.
     */
    @Step("Verificar que são apresentados os dois tabuleiros")
    public GameRoomPage shouldShowTwoBoards() {
        boards.shouldHave(CollectionCondition.size(2));
        return this;
    }

    /**
     * Dispara sobre uma casa do tabuleiro do adversário. Repete o clique até a casa ficar marcada,
     * porque o jogo mostra um aviso animado no início da partida.
     *
     * @param row    linha (0 a 9)
     * @param column coluna (0 a 9)
     */
    @Step("Disparar sobre a casa ({row},{column}) do tabuleiro do adversário")
    public GameRoomPage fireAt(int row, int column) {
        // O jogo sorteia quem começa; se for o robot, os cliques são ignorados até ele jogar.
        yourTurnHeader.shouldBe(visible, ROBOT_FIRST_TIMEOUT);
        try {
            Wait().withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).until(d -> {
                if (!markOf(row, column).exists()) {
                    try {
                        opponentCell(row, column).click(ClickOptions.usingDefaultMethod().timeout(Duration.ofSeconds(2)));
                    } catch (Throwable e) {
                        return false; // ainda tapada pelo aviso animado; tenta de novo
                    }
                }
                return markOf(row, column).exists();
            });
        } catch (org.openqa.selenium.TimeoutException e) {
            // Falha com uma asserção do Selenide, que guarda a captura de ecrã e o HTML da página.
            markOf(row, column).shouldBe(exist, Duration.ofSeconds(1));
        }
        return this;
    }

    /**
     * Verifica que a casa ficou marcada (falha ou acerto).
     */
    @Step("Verificar que a casa ({row},{column}) ficou marcada")
    public GameRoomPage shouldBeMarked(int row, int column) {
        markOf(row, column).should(exist);
        return this;
    }

    /**
     * Verifica o número de unidades de míssil grande.
     */
    @Step("Verificar que há {expected} míssil(eis) grande(s)")
    public GameRoomPage shouldHaveBigMissiles(int expected) {
        bigMissile.shouldHave(text(String.valueOf(expected)));
        return this;
    }

    /**
     * Seleciona o míssil grande na barra de armas.
     */
    @Step("Selecionar o míssil grande")
    public GameRoomPage selectBigMissile() {
        // O aviso animado do início pode tapar a barra de armas; repete até ficar selecionado.
        Wait().withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).until(d -> {
            if (!bigMissile.has(cssClass("is-selected"))) {
                bigMissile.click();
            }
            return bigMissile.has(cssClass("is-selected"));
        });
        return this;
    }

    /**
     * Verifica o número de casas marcadas no tabuleiro do adversário.
     */
    @Step("Verificar que há {expected} casas marcadas no tabuleiro do adversário")
    public GameRoomPage shouldHaveMarkedCells(int expected) {
        $$x(OPPONENT_BOARD + "//td[.//*[contains(@class,'hit')]]").shouldHave(CollectionCondition.size(expected));
        return this;
    }

    /**
     * Clica no botão de desistir ("Abort game" ou "Resign").
     */
    @Step("Clicar no botão de desistir")
    public GameRoomPage clickQuit() {
        // O aviso animado do início pode tapar o botão; repete o clique até funcionar.
        Wait().withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).until(d -> {
            try {
                quitButton.click();
                return true;
            } catch (Throwable e) {
                return false;
            }
        });
        return this;
    }

    /**
     * Verifica que é pedida confirmação para desistir.
     */
    @Step("Verificar que é pedida confirmação ('Are you sure')")
    public GameRoomPage shouldAskConfirmation() {
        $("mat-dialog-container").shouldHave(text("Are you sure"));
        return this;
    }

    /**
     * Confirma a desistência (último botão do diálogo; o primeiro é "Cancel").
     */
    @Step("Confirmar a desistência")
    public void confirmQuit() {
        $$("mat-dialog-container button").last().shouldBe(visible);
        $$("mat-dialog-container button").last().click();
    }

    /**
     * Verifica que a partida terminou (os tabuleiros desapareceram).
     */
    @Step("Verificar que a partida terminou (os tabuleiros desapareceram)")
    public void shouldBeOver() {
        boards.shouldHave(CollectionCondition.size(0));
    }

    /**
     * Dispara o míssil grande sobre uma casa do tabuleiro do adversário (a arma já selecionada).
     */
    @Step("Disparar o míssil grande sobre a casa ({row},{column})")
    public GameRoomPage fireBigMissileAt(int row, int column) {
        return fireAt(row, column);
    }

    private SelenideElement opponentCell(int row, int column) {
        return $x(OPPONENT_BOARD + "//td[contains(@class,'cell-" + row + "-" + column + "')]");
    }

    private SelenideElement markOf(int row, int column) {
        // falha: <svg class="... no-hit">; acerto: <div class="hit fire">
        return $x(OPPONENT_BOARD + "//td[contains(@class,'cell-" + row + "-" + column + "')]//*[contains(@class,'hit')]");
    }
}
