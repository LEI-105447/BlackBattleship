package iscteiul.ista.blackbattleship;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

@BeforeEach    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);
    }

@AfterEach    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();
        mainPage.searchInput.sendKeys("Selenium");
        mainPage.fullSearchButton.click();

        assertEquals("Selenium", mainPage.searchInput.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        assertTrue(mainPage.toolsSubmenu.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        assertTrue(driver.getCurrentUrl().contains("/products/"));
assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());    }
}
