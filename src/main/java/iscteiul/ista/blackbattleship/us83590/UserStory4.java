package iscteiul.ista.blackbattleship.us83590;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object da User Story 4 (US10 do backlog):
 * "Como jogador, quero usar armas especiais (míssil grande), para atingir várias
 * casas de uma vez."
 * O míssil grande marca uma cruz de 5 casas e gasta uma das 2 unidades iniciais.
 * <p>Gerado a partir do cenário {@code UserStoryTest4} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Definir o tamanho da janela do navegador</li>
 * <li>Clicar em 'Play vs robot' para jogar contra o computador</li>
 * <li>Escrever o nickname de convidado no diálogo de registo</li>
 * <li>Clicar em 'Continue' para confirmar o nickname</li>
 * <li>Esperar que o tabuleiro apareça</li>
 * <li>Verificar que há 2 mísseis grandes disponíveis</li>
 * <li>Selecionar o míssil grande na barra de armas</li>
 * <li>Disparar o míssil grande sobre a casa central do tabuleiro do adversário</li>
 * <li>Verificar que a casa de cima da cruz ficou marcada</li>
 * <li>Verificar que a casa de baixo da cruz ficou marcada</li>
 * <li>Verificar que sobra 1 míssil grande</li>
 * </ol>
 */
public class UserStory4 extends BattleshipBasePage {

    /** Míssil grande: botão de arma ativo e com contador (o míssil simples não tem número). */
    private static final By BIG_MISSILE =
            By.xpath("//button[contains(@class,'weapon-button')][not(@disabled)][normalize-space(.)!='']");
    private static final String OPPONENT_BOARD = "(//table[contains(@class,'table-board')])[2]";

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory4(WebDriver driver) {
        super(driver);
    }

    /**
     * @return o número de unidades de míssil grande disponíveis (2 no início)
     */
    public int getBigMissileCount() {
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(BIG_MISSILE)).getText().trim());
    }

    /**
     * Seleciona o míssil grande na barra de armas.
     */
    public void selectBigMissile() {
        // O aviso animado do início pode tapar a barra de armas; repete até a arma ficar selecionada.
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
            WebElement big = d.findElement(BIG_MISSILE);
            if (!big.getAttribute("class").contains("is-selected")) {
                big.click();
            }
            return big.getAttribute("class").contains("is-selected");
        });
    }

    /**
     * Espera até haver o número indicado de casas marcadas no tabuleiro do adversário.
     *
     * @param expected número esperado de casas marcadas
     * @return true se o número foi atingido
     */
    public boolean waitForMarkedCells(int expected) {
        return wait.until(d -> getMarkedCellCount() == expected);
    }

    /**
     * Dispara o míssil grande sobre uma casa do tabuleiro do adversário. O clique é repetido
     * até haver casas marcadas, porque o jogo mostra um aviso animado no início da partida.
     *
     * @param row    linha central (1 a 8)
     * @param column coluna central (1 a 8)
     */
    public void fireBigMissileAt(int row, int column) {
        By cell = By.xpath(OPPONENT_BOARD + "//td[contains(@class,'cell-" + row + "-" + column + "')]");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
            d.findElement(cell).click();
            return getMarkedCellCount() > 0;
        });
    }

    /**
     * @return o número de casas marcadas (falha ou acerto) no tabuleiro do adversário
     */
    public int getMarkedCellCount() {
        return driver.findElements(By.xpath(OPPONENT_BOARD + "//td[.//*[contains(@class,'hit')]]")).size();
    }

    /**
     * Indica se uma casa está marcada (falha ou acerto).
     *
     * @param row    linha
     * @param column coluna
     * @return true se a casa está marcada
     */
    public boolean isCellMarked(int row, int column) {
        return !driver.findElements(By.xpath(OPPONENT_BOARD + "//td[contains(@class,'cell-" + row + "-" + column
                + "')][.//*[contains(@class,'hit')]]")).isEmpty();
    }
}
