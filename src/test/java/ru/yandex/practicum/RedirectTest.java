package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.steps.AccountPageSteps;
import ru.yandex.practicum.steps.LoginSteps;
import ru.yandex.practicum.steps.MainPageSteps;
import ru.yandex.practicum.steps.RegisterSteps;

import java.util.concurrent.ThreadLocalRandom;

import static ru.yandex.practicum.constant.EnvConst.DOMAINS;


public class RedirectTest {

    @Rule
    public final DriverFactory driverFactory = new DriverFactory();

    private WebDriver driver;
    private LoginSteps loginSteps;
    private RegisterSteps registerSteps;
    private MainPageSteps mainPageSteps;
    private AccountPageSteps accountPageSteps;

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
        accountPageSteps = new AccountPageSteps(driver);

        registerName = generateRandomString(3, 12);
        registerEmail = generateRandomString(3, 12) + DOMAINS[ThreadLocalRandom.current().nextInt(DOMAINS.length)];
        //Длина пароля минимум 6 символов
        registerPassword = generateRandomString(6, 15);

        loginSteps.createUser(registerEmail, registerPassword, registerName);
    }

    @Test
    @DisplayName("Not auth user redirect to login page")
    public void notAuthUserRedirectToRegisterTest() {
        mainPageSteps.openMainPage();
        mainPageSteps.loginUserFromAccountProfilePageButton();
        mainPageSteps.checkMainRedirectToLogin();
    }

    @Test
    @DisplayName("Auth user redirect to account page")
    public void authUserRedirectToAccountPageTest() {
        mainPageSteps.openMainPage();
        loginSteps.addAccessTokenToLocalStorage(registerEmail, registerPassword);
        loginSteps.checkUserIsLogin();
        accountPageSteps.checkAccountEmail(registerEmail);
    }

    @After
    public void tearDown() {
        registerSteps.deleteUser(registerEmail, registerPassword);
    }
}
