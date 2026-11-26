package iscteiul.ista.blackbattleship.suite83590.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.Selenide.webdriver;

/**
 * Page Object da página inicial do jogo, https://papergames.io/en/battleship.
 * Usa o padrão Page Factory: os elementos são campos anotados com {@code @FindBy}.
 */
public class HomePage {

    /** URL da página do jogo. */
    public static final String URL = "https://papergames.io/en/battleship";

    @FindBy(xpath = "(//app-juicy-button[contains(.,'Play with a friend')]//button)[1]")
    private SelenideElement playWithFriend;

    @FindBy(xpath = "(//app-juicy-button[contains(.,'Play vs robot')]//button)[1]")
    private SelenideElement playVsRobot;

    @FindBy(xpath = "//app-juicy-button[contains(.,'Create tournament')]")
    private SelenideElement createTournament;

    @FindBy(xpath = "//app-juicy-button[contains(.,'Play online')]")
    private SelenideElement playOnline;

    /**
     * Serviços de publicidade e estatística que a página carrega. Não fazem parte do jogo testado e,
     * num browser novo, podem bloquear o Chrome e tapar a página com avisos de cookies.
     */
    private static final List<String> BLOCKED_URLS = List.of(
            "*vntsm.com*", "*venatusmedia.com*", "*doubleclick.net*", "*googlesyndication.com*",
            "*googletagmanager.com*", "*google-analytics.com*", "*imasdk.googleapis.com*",
            "*adtrafficquality.google*", "*quantcast*", "*consensu.org*", "*inmobi.com*",
            "*atmtd.com*", "*bids.ws*", "*richaudience.com*", "*360yield*", "*analytics.yahoo.com*",
            "*3lift.com*", "*casalemedia.com*", "*ingage.tech*", "*adnxs*", "*the-ozone-project.com*",
            "*optidigital.com*", "*yellowblue.io*", "*adsrvr.org*", "*rubiconproject.com*", "*a-mo.net*",
            "*pubmatic.com*", "*criteo.com*", "*script.ac*", "*clean.gg*", "*affec.tv*", "*rlcdn.com*",
            "*creativecdn.com*", "*seedtag.com*", "*blismedia.com*", "*2mdn.net*");

    /**
     * Abre a página do jogo, sem publicidade, e recusa o aviso de cookies sempre que ele aparecer.
     *
     * @return a página inicial
     */
    @Step("Abrir a página do jogo Battleship")
    public static HomePage openPage() {
        blockAdvertising();
        HomePage home = open(URL, HomePage.class);
        ConsentDialog.autoDecline();
        ConsentDialog.declineIfShown(3);
        return home;
    }

    /**
     * Abre o browser (ainda sem página) e, através do Chrome DevTools Protocol, impede-o de carregar
     * os serviços de publicidade. O próprio jogo não é afetado.
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
     * Verifica que os quatro modos de jogo estão disponíveis.
     */
    @Step("Verificar que os 4 modos de jogo estão disponíveis")
    public HomePage shouldShowAllModes() {
        playWithFriend.shouldBe(visible);
        playVsRobot.shouldBe(visible);
        createTournament.shouldBe(visible);
        playOnline.shouldBe(visible);
        return this;
    }

    /**
     * Clica em "Play vs robot" (abre o diálogo do nickname, se o site o pedir).
     */
    @Step("Clicar em 'Play vs robot'")
    public NicknameDialog clickPlayVsRobot() {
        ConsentDialog.declineIfShown(2);
        playVsRobot.click();
        return page(NicknameDialog.class);
    }

    /**
     * Começa uma partida contra o robot como convidado.
     *
     * @param nickname nickname de convidado
     * @return o ecrã da partida
     */
    @Step("Começar uma partida contra o robot como '{nickname}'")
    public GameRoomPage startGameVsRobot(String nickname) {
        clickPlayVsRobot().confirmIfAsked(nickname);
        webdriver().shouldHave(urlContaining("/r/"));
        return page(GameRoomPage.class);
    }

    /**
     * Clica em "Play with a friend" e entra na sala do convite.
     *
     * @param nickname nickname de convidado
     * @return a sala com o link de convite
     */
    @Step("Clicar em 'Play with a friend' como '{nickname}'")
    public InviteRoomPage startFriendGame(String nickname) {
        ConsentDialog.declineIfShown(2);
        playWithFriend.click();
        page(NicknameDialog.class).confirmIfAsked(nickname);
        webdriver().shouldHave(urlContaining("/r/"));
        return page(InviteRoomPage.class);
    }

    /**
     * Verifica que se está na página inicial (o botão "Play with a friend" está visível).
     */
    @Step("Verificar que estou na página inicial do jogo")
    public HomePage shouldBeDisplayed() {
        playWithFriend.shouldBe(visible);
        return this;
    }
}
