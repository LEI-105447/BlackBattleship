package iscteiul.ista.blackbattleship.suite128782.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Page Object da página do jogo Battleship (https://papergames.io/en/battleship).
 * <p>Contém os modos de jogo, os rankings, a secção das regras e o diálogo "Who are you?"
 * que aparece a convidados novos. Os elementos são inicializados pela Page Factory do Selenide
 * ({@code open(URL, BattleshipPage.class)}), a partir das anotações {@link FindBy}.
 */
public class BattleshipPage {

    /** URL da página do jogo Battleship. */
    public static final String URL = "https://papergames.io/en/battleship";

    /** Campo do nickname do diálogo "Who are you?" (opcional: só aparece a convidados novos). */
    private static final By NICKNAME_INPUT = By.cssSelector("mat-dialog-container input.input-xl");

    // ---- Modos de jogo ----

    @FindBy(xpath = "//app-juicy-button[contains(.,'Play vs robot')]/button")
    private SelenideElement playVsRobotButton;

    @FindBy(xpath = "//app-juicy-button[contains(.,'Play with a friend')]//button[@mat-icon-button]")
    private SelenideElement gameSettingsGear;

    @FindBy(xpath = "//app-juicy-button[contains(.,'Create tournament')]//a")
    private SelenideElement createTournamentLink;

    // ---- Diálogo "Who are you?" ----

    @FindBy(css = "mat-dialog-container input.input-xl")
    private SelenideElement nicknameInput;

    @FindBy(css = "mat-dialog-container button[type='submit']")
    private SelenideElement continueButton;

    // ---- Rankings ----

    @FindBy(xpath = "//span[contains(.,'Daily leaderboard')]")
    private SelenideElement dailyLeaderboardTitle;

    @FindBy(css = "app-tournament-leaderboard tr.item .player-name")
    private ElementsCollection dailyLeaderboardPlayers;

    @FindBy(xpath = "//label[contains(.,'RANKA')]")
    private SelenideElement rankaTitle;

    @FindBy(css = "app-ranka-leaderboard tr.item .player-name")
    private ElementsCollection rankaPlayers;

    // ---- Regras ----

    @FindBy(xpath = "//h2[contains(.,'Rules of Battleship')]")
    private SelenideElement rulesTitle;

    @FindBy(xpath = "//h2[contains(.,'Rules of Battleship')]/following-sibling::p[1]")
    private SelenideElement rulesFirstParagraph;

    /**
     * Abre a página do jogo (Page Factory do Selenide), sem publicidade, e recusa os cookies se algum aviso aparecer.
     *
     * @return a página do jogo
     */
    @Step("Abrir a página do jogo Battleship")
    public static BattleshipPage openPage() {
        blockAdvertising();
        BattleshipPage battleshipPage = open(URL, BattleshipPage.class);
        ConsentDialog.autoDecline();
        ConsentDialog.declineIfShown(3);
        return battleshipPage;
    }

    /**
     * Escolhe "Play vs robot", indica o nickname se o site o pedir e espera pela sala de jogo.
     *
     * @param nickname nickname de convidado a usar se o diálogo "Who are you?" aparecer
     * @return a sala de jogo
     */
    @Step("Começar uma partida contra o robot")
    public GameRoomPage startGameVsRobot(String nickname) {
        ConsentDialog.declineIfShown(2);
        playVsRobotButton.shouldBe(enabled).click();
        if (appears(NICKNAME_INPUT, 8)) {
            nicknameInput.setValue(nickname);
            // "Continue" só fica ativo depois de o site validar o nickname
            continueButton.shouldBe(enabled).click();
        }
        return page(GameRoomPage.class).waitUntilLoaded();
    }

    /**
     * Abre o diálogo "Game settings" pela roda dentada de "Play with a friend".
     *
     * @return o diálogo de definições
     */
    @Step("Abrir as definições de jogo (roda dentada de 'Play with a friend')")
    public GameSettingsDialog openGameSettings() {
        ConsentDialog.declineIfShown(2);
        gameSettingsGear.shouldBe(enabled).click();
        return page(GameSettingsDialog.class).waitUntilOpen();
    }

    /**
     * Escolhe "Create tournament".
     *
     * @return a página de criação de torneios
     */
    @Step("Escolher 'Create tournament'")
    public TournamentCreationPage openTournamentCreation() {
        ConsentDialog.declineIfShown(2);
        createTournamentLink.shouldBe(visible).click();
        return page(TournamentCreationPage.class).waitUntilLoaded();
    }

    /**
     * Desloca a página até à secção das regras, deixando o título a meio do ecrã.
     *
     * @return esta página
     */
    @Step("Deslocar a página até à secção das regras")
    public BattleshipPage scrollToRules() {
        ConsentDialog.declineIfShown(2);
        rulesTitle.scrollIntoView("{block: 'center'}").shouldBe(visible);
        return this;
    }

    /** @return o título do ranking diário ("Daily leaderboard, ends in") */
    public SelenideElement dailyLeaderboardTitle() {
        return dailyLeaderboardTitle;
    }

    /** @return os nomes dos jogadores do ranking diário */
    public ElementsCollection dailyLeaderboardPlayers() {
        return dailyLeaderboardPlayers;
    }

    /** @return o título do ranking dos últimos 30 dias ("RANKA") */
    public SelenideElement rankaTitle() {
        return rankaTitle;
    }

    /** @return os nomes dos jogadores do ranking dos últimos 30 dias */
    public ElementsCollection rankaPlayers() {
        return rankaPlayers;
    }

    /** @return o título da secção das regras */
    public SelenideElement rulesTitle() {
        return rulesTitle;
    }

    /** @return o primeiro parágrafo das regras (grelha e frota) */
    public SelenideElement rulesFirstParagraph() {
        return rulesFirstParagraph;
    }

    /**
     * Serviços de publicidade, leilões de anúncios, vídeo publicitário e estatística que a página carrega.
     * Não fazem parte do jogo testado e, num browser novo, podem bloquear o Chrome
     * ("Timed out receiving message from renderer") e tapar a página com avisos de cookies.
     */
    private static final List<String> BLOCKED_URLS = List.of(
            "*vntsm.com*", "*venatusmedia.com*", "*doubleclick.net*", "*googlesyndication.com*",
            "*googletagmanager.com*", "*google-analytics.com*", "*imasdk.googleapis.com*", "*adtrafficquality.google*",
            "*fundingchoicesmessages.google.com*", "*quantcast*", "*consensu.org*", "*inmobi.com*",
            "*atmtd.com*", "*bids.ws*", "*richaudience.com*", "*360yield*", "*analytics.yahoo.com*", "*3lift.com*",
            "*casalemedia.com*", "*ingage.tech*", "*adnxs*", "*the-ozone-project.com*", "*optidigital.com*",
            "*yellowblue.io*", "*adsrvr.org*", "*rubiconproject.com*", "*a-mo.net*", "*pubmatic.com*", "*criteo.com*",
            "*script.ac*", "*clean.gg*", "*affec.tv*", "*rlcdn.com*", "*creativecdn.com*", "*seedtag.com*",
            "*blismedia.com*", "*2mdn.net*");

    /**
     * Abre o browser (ainda sem página) e, através do Chrome DevTools Protocol, impede-o de carregar
     * os serviços de publicidade em {@link #BLOCKED_URLS}. O próprio jogo (papergames.io) não é afetado.
     */
    @Step("Bloquear a publicidade da página (não faz parte do jogo testado)")
    private static void blockAdvertising() {
        open(); // abre o Chrome sem carregar nenhuma página
        WebDriver driver = WebDriverRunner.getWebDriver();
        if (driver instanceof ChromiumDriver chromium) {
            chromium.executeCdpCommand("Network.enable", Map.of());
            chromium.executeCdpCommand("Network.setBlockedURLs", Map.of("urls", BLOCKED_URLS));
        }
    }

    /**
     * Espera até {@code seconds} segundos que um elemento opcional fique visível.
     * Usa o WebDriver diretamente para que a espera não apareça como passo falhado no relatório Allure.
     *
     * @param locator localizador do elemento
     * @param seconds tempo máximo de espera
     * @return true se o elemento ficou visível
     */
    private static boolean appears(By locator, int seconds) {
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
