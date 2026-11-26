package iscteiul.ista.blackbattleship.suite128782.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

/**
 * Page Object da sala de jogo contra o robot.
 * <p>Contém os dois tabuleiros, a barra de armas e o botão "Abort game".
 * Os elementos são inicializados pela Page Factory do Selenide ({@code page(GameRoomPage.class)}).
 */
public class GameRoomPage {

    @FindBy(css = ".current-player .header")
    private SelenideElement myBoardHeader;

    @FindBy(css = ".opponent table.table-board")
    private SelenideElement opponentBoard;

    @FindBy(id = "player_weapons")
    private SelenideElement weaponBar;

    /** Míssil simples: o botão da barra de armas cuja imagem tem alt "default". */
    @FindBy(xpath = "//div[@id='player_weapons']/button[img[@alt='default']]")
    private SelenideElement simpleMissile;

    /** Contador (badge) do míssil simples, só se tiver texto. */
    @FindBy(xpath = "//div[@id='player_weapons']/button[img[@alt='default']]/span[contains(@class,'badge')][normalize-space(.)!='']")
    private SelenideElement simpleMissileCounter;

    /** "Abort game" da sala (exclui o botão com o mesmo texto do pedido de confirmação). */
    @FindBy(xpath = "//button[contains(.,'Abort game')][not(ancestor::mat-dialog-container)]")
    private SelenideElement abortGameButton;

    @FindBy(xpath = "//mat-dialog-container//button[contains(.,'Abort game')]")
    private SelenideElement confirmAbortButton;

    /**
     * Espera (até 40 segundos) que a sala de jogo carregue.
     * O servidor do jogo pode demorar a criar a partida contra o robot, por isso a espera é longa.
     *
     * @return esta página
     */
    @Step("Esperar que a sala de jogo carregue")
    public GameRoomPage waitUntilLoaded() {
        myBoardHeader.shouldBe(visible, Duration.ofSeconds(40));
        return this;
    }

    /**
     * Termina a partida com "Abort game" e confirma (limpeza no fim do teste).
     * Recusa primeiro um aviso de cookies que tenha aparecido entretanto.
     * Se não houver partida a terminar, ou a limpeza falhar, não faz o teste falhar.
     */
    @Step("Terminar a partida com 'Abort game' (limpeza)")
    public void abortGame() {
        ConsentDialog.declineIfShown(2);
        try {
            if (abortGameButton.is(visible)) {
                abortGameButton.click();
                confirmAbortButton.shouldBe(visible).click();
            }
        } catch (UIAssertionError e) {
            // A limpeza não é o objeto do teste; o browser é fechado a seguir de qualquer forma.
        }
    }

    /** @return o cabeçalho do meu tabuleiro ("Your boats") */
    public SelenideElement myBoardHeader() {
        return myBoardHeader;
    }

    /** @return o tabuleiro do adversário */
    public SelenideElement opponentBoard() {
        return opponentBoard;
    }

    /** @return a barra de armas */
    public SelenideElement weaponBar() {
        return weaponBar;
    }

    /** @return o botão do míssil simples na barra de armas */
    public SelenideElement simpleMissile() {
        return simpleMissile;
    }

    /** @return o contador do míssil simples (não deve existir) */
    public SelenideElement simpleMissileCounter() {
        return simpleMissileCounter;
    }

    /** @return o botão "Abort game" da sala */
    public SelenideElement abortGameButton() {
        return abortGameButton;
    }
}
