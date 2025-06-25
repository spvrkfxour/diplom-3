package ru.yandex.practicum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.steps.RegisterSteps;


public class RegisterTest {

    @Rule
    public final DriverFactory driverFactory = new DriverFactory();

    private WebDriver driver;
    private RegisterSteps registerSteps;

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        registerSteps = new RegisterSteps(driver);
        registerSteps.openRegisterPage();
    }

    @Test
    public void registerValidTest() {

    }
}
