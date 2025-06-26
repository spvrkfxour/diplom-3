package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.steps.LoginSteps;
import ru.yandex.practicum.steps.MainPageSteps;
import ru.yandex.practicum.steps.RegisterSteps;

import java.util.concurrent.ThreadLocalRandom;

import static ru.yandex.practicum.constant.EnvConst.DOMAINS;


public class LoginTest {

    @Rule
    public final DriverFactory driverFactory = new DriverFactory();

    private WebDriver driver;
    private LoginSteps loginSteps;
    private RegisterSteps registerSteps;
    private MainPageSteps mainPageSteps;

    private String registerName;
    private String registerEmail;
    private String registerPassword;

    private String generateRandomString(int minLen, int maxLen) {
        return RandomStringUtils.randomAlphanumeric(minLen, maxLen).toLowerCase();
    }

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        loginSteps = new LoginSteps(driver);
        registerSteps = new RegisterSteps(driver);
        mainPageSteps = new MainPageSteps(driver);

        registerName = generateRandomString(3, 12);
        registerEmail = generateRandomString(3, 12) + DOMAINS[ThreadLocalRandom.current().nextInt(DOMAINS.length)];
        //Длина пароля минимум 6 символов
        registerPassword = generateRandomString(6, 15);

        loginSteps.createUser(registerEmail, registerPassword, registerName);
    }

    @Test
    @DisplayName("Login user from main page button")
    @Description("Success login user with valid parameters from main page button")
    public void loginUserFromMainPageButtonTest() {
        mainPageSteps.openMainPage();
        mainPageSteps.loginUserFromMainPageButton();
        mainPageSteps.checkMainRedirectToLogin();
        loginSteps.loginUser(registerEmail, registerPassword);
        loginSteps.checkLoginRedirectToMain();
    }

    @After
    public void tearDown() {
        registerSteps.deleteUser(registerEmail, registerPassword);
    }
}
