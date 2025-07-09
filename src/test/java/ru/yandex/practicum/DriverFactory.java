package ru.yandex.practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public void initDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "yandex":
                startYandex();
                break;
            case "chrome":
                startChrome();
                break;
            default:
                System.out.println("Unknown browser: " + browser + ". Using Chrome as default.");
                startChrome();
        }

        maximizeWindow();
    }

    private void startChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void startYandex() {
        //windows only
        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        driver = new ChromeDriver();
    }

    private void maximizeWindow() {
        if (driver != null) {
            driver.manage().window().maximize();
        }
    }

    @Override
    protected void before() {
        initDriver();
    }

    @Override
    protected void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}
