package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class: testes JUnit da página principal da JetBrains.
 * Só usa os métodos de {@link MainPage}, sem localizadores.
 */
public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pausa para ver o resultado final antes de fechar o browser
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void search() throws InterruptedException {
        Thread.sleep(1500);
        mainPage.search("Selenium");
        Thread.sleep(1500);

        assertEquals("Selenium", mainPage.getSearchValue("Selenium"));
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        mainPage.openToolsMenu();
        Thread.sleep(1500);

        assertTrue(mainPage.isToolsSubmenuDisplayed());
    }

    @Test
    public void navigationToAllTools() throws InterruptedException {
        mainPage.goToAllTools();
        Thread.sleep(1500);

        assertTrue(mainPage.getCurrentUrl().contains("/products/"));
        assertEquals("All Developer Tools and Products by JetBrains", mainPage.getTitle());
    }
}
