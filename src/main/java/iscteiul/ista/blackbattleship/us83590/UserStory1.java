package iscteiul.ista.blackbattleship.us83590;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object da User Story 1:
 * "Como jogador, quero abrir a página do jogo e ver os modos disponíveis,
 * para escolher como jogar."
 * <p>Gerado a partir do cenário {@code UserStoryTest1} de {@code TestSuite_83590.side}:
 * <ol>
 * <li>Abrir a página do jogo Battleship</li>
 * <li>Verificar que o modo 'Play with a friend' está disponível</li>
 * <li>Verificar que o modo 'Play vs robot' está disponível</li>
 * <li>Verificar que o modo 'Create tournament' está disponível</li>
 * <li>Verificar que o modo 'Play online' está disponível</li>
 * </ol>
 */
public class UserStory1 extends BattleshipBasePage {

    private static final By PLAY_WITH_FRIEND = By.xpath("//app-juicy-button[contains(.,'Play with a friend')]");
    private static final By PLAY_VS_ROBOT = By.xpath("//app-juicy-button[contains(.,'Play vs robot')]");
    private static final By CREATE_TOURNAMENT = By.xpath("//app-juicy-button[contains(.,'Create tournament')]");
    private static final By PLAY_ONLINE = By.xpath("//app-juicy-button[contains(.,'Play online')]");

    /**
     * @param driver o WebDriver em uso
     */
    public UserStory1(WebDriver driver) {
        super(driver);
    }

    /**
     * @return true se o modo "Play with a friend" está visível
     */
    public boolean isPlayWithFriendVisible() {
        return isVisible(PLAY_WITH_FRIEND);
    }

    /**
     * @return true se o modo "Play vs robot" está visível
     */
    public boolean isPlayVsRobotVisible() {
        return isVisible(PLAY_VS_ROBOT);
    }

    /**
     * @return true se o modo "Create tournament" está visível
     */
    public boolean isCreateTournamentVisible() {
        return isVisible(CREATE_TOURNAMENT);
    }

    /**
     * @return true se o modo "Play online" está visível
     */
    public boolean isPlayOnlineVisible() {
        return isVisible(PLAY_ONLINE);
    }

    private boolean isVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }
}
