package ru.yandex.practicum;

import static ru.yandex.practicum.constant.EnvConst.*;
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
    @DisplayName("Constructor tabs navigate correctly")
    public void constructorTabsTest() {
        mainPageSteps.checkBunsSectionTop();
        mainPageSteps.clickSaucesTab();
        mainPageSteps.checkSaucesSectionTop();
        mainPageSteps.clickFillingsTab();
        mainPageSteps.checkFillingsSectionTop();
        mainPageSteps.clickBunsTab();
        mainPageSteps.checkBunsSectionTop();
    }
}
