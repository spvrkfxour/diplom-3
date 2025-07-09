package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.steps.MainPageSteps;


public class ConstructorTabsTest {

    @Rule
    public final DriverFactory driverFactory = new DriverFactory();

    private WebDriver driver;
    private MainPageSteps mainPageSteps;

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        mainPageSteps = new MainPageSteps(driver);

        mainPageSteps.openMainPage();
    }

    @Test
    @DisplayName("Constructor buns tab after redirect displayed correctly")
    public void BunsTabAfterRedirectTest() {
        mainPageSteps.checkBunsSectionTop();
    }

    @Test
    @DisplayName("Constructor sauces tab after click displayed correctly")
    public void SaucesTabAfterClickTest() {
        mainPageSteps.clickSaucesTab();
        mainPageSteps.checkSaucesSectionTop();
    }

    @Test
    @DisplayName("Constructor fillings tab after click displayed correctly")
    public void FillingsTabAfterClickTest() {
        mainPageSteps.clickFillingsTab();
        mainPageSteps.checkFillingsSectionTop();
    }

    @Test
    @DisplayName("Constructor buns tab after click displayed correctly")
    public void BunsTabAfterClickTest() {
        mainPageSteps.clickFillingsTab();
        mainPageSteps.clickBunsTab();
        mainPageSteps.checkBunsSectionTop();
    }
}
