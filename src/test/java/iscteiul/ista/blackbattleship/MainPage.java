package iscteiul.ista.blackbattleship;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object (padrão POM) da página principal de https://www.jetbrains.com/.
 * Contém os localizadores dos elementos e as operações sobre a página.
 * A classe de teste ({@link MainPageTest}) só usa os métodos daqui.
 */
// page_url = https://www.jetbrains.com/
public class MainPage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[@data-test-marker='Products']")
    public WebElement seeDeveloperToolsButton;

    @FindBy(xpath = "//*[@data-test='suggestion-link']")
    public WebElement findYourToolsButton;

    @FindBy(xpath = "//div[@data-test='main-menu-item'and @data-test-marker = 'Products']")
    public WebElement toolsMenu;

    @FindBy(css = "div[data-test='main-submenu']")
    public WebElement toolsSubmenu;

    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    @FindBy(css = "[data-test-id='search-input']")
    public WebElement searchInput;

    @FindBy(css = "button[data-test='full-search-button']")
    public WebElement fullSearchButton;

    @FindBy(css = "button[class='ch2-btn ch2-allow-all-btn ch2-btn-primary']")
    public WebElement cookieAllowButton;

    /**
     * Inicializa os elementos da página.
     *
     * @param driver o WebDriver em uso
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Fecha o pop-up de cookies clicando em "Allow All".
     */
    public void closePopUp() {
        cookieAllowButton.click();
    }

    /**
     * Abre a pesquisa, escreve o termo e submete a pesquisa completa.
     *
     * @param term texto a pesquisar
     */
    public void search(String term) {
        searchButton.click();
        searchInput.sendKeys(term);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(fullSearchButton)).click();
    }

    /**
     * Devolve o valor do campo de pesquisa, esperando até que seja o termo indicado.
     *
     * @param expected termo esperado
     * @return o valor atual do campo de pesquisa
     */
    public String getSearchValue(String expected) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(searchInput, "value", expected));
        return searchInput.getAttribute("value");
    }

    /**
     * Abre o menu de ferramentas (Products).
     */
    public void openToolsMenu() {
        toolsMenu.click();
    }

    /**
     * Indica se o submenu de ferramentas está visível.
     *
     * @return true se estiver visível
     */
    public boolean isToolsSubmenuDisplayed() {
        return toolsSubmenu.isDisplayed();
    }

    /**
     * Navega para a página com todas as ferramentas (Products -> Find your tools).
     */
    public void goToAllTools() {
        seeDeveloperToolsButton.click();
        findYourToolsButton.click();
    }

    /**
     * @return o URL atual
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * @return o título da página atual
     */
    public String getTitle() {
        return driver.getTitle();
    }
}
