package ru.yandex.practicum;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.steps.LoginSteps;
import ru.yandex.practicum.steps.RegisterSteps;

import java.util.concurrent.ThreadLocalRandom;

import static ru.yandex.practicum.constant.EnvConst.DOMAINS;


public class RegisterTest {

    @Rule
    public final DriverFactory driverFactory = new DriverFactory();

    private WebDriver driver;
    private RegisterSteps registerSteps;
    private LoginSteps loginSteps;

    private String registerName;
    private String registerEmail;
    private String registerPassword;

    private String generateRandomString(int minLen, int maxLen) {
        return RandomStringUtils.randomAlphanumeric(minLen, maxLen).toLowerCase();
    }

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        registerSteps = new RegisterSteps(driver);
        loginSteps = new LoginSteps(driver);
        registerSteps.openRegisterPage();

        registerName = generateRandomString(3, 12);
        registerEmail = generateRandomString(3, 12) + DOMAINS[ThreadLocalRandom.current().nextInt(DOMAINS.length)];
        //Длина пароля минимум 6 символов
        registerPassword = generateRandomString(6, 15);
    }

    @Test
    public void registerValidTest() {
        registerSteps.registerUser(registerName, registerEmail, registerPassword);
        registerSteps.checkRegisterRedirectToLogin();
        loginSteps.loginUser(registerEmail, registerPassword);
        loginSteps.checkLoginRedirectToMain();
    }

    @After
    public void tearDown() {
        registerSteps.deleteUser(registerEmail, registerPassword);
    }
}
