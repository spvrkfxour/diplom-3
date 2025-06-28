package ru.yandex.practicum;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.steps.LoginSteps;
import ru.yandex.practicum.steps.MainPageSteps;
import ru.yandex.practicum.steps.PasswordRecoveryPageSteps;
import ru.yandex.practicum.steps.RegisterSteps;

import java.util.concurrent.ThreadLocalRandom;

import static ru.yandex.practicum.constant.EnvConst.DOMAINS;


@RunWith(Parameterized.class)
public class LoginTest {

    @Rule
    public final DriverFactory driverFactory = new DriverFactory();

    private WebDriver driver;
    private LoginSteps loginSteps;
    private RegisterSteps registerSteps;
    private MainPageSteps mainPageSteps;
    private PasswordRecoveryPageSteps passwordRecoveryPageSteps;

    private String registerName;
    private String registerEmail;
    private String registerPassword;

    private EntryPoint entryPoint;

    private String generateRandomString(int minLen, int maxLen) {
        return RandomStringUtils.randomAlphanumeric(minLen, maxLen).toLowerCase();
    }

    private enum EntryPoint {
        MAIN_PAGE_BUTTON, PROFILE_PAGE_BUTTON, REGISTER_PAGE_BUTTON, PASSWORD_RECOVERY_PAGE_BUTTON
    }

    public LoginTest(EntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Parameterized.Parameters(name = "entryPoint = {0}")
    public static Object[][] data() {
        return new Object[][] {
                { EntryPoint.MAIN_PAGE_BUTTON },
                { EntryPoint.PROFILE_PAGE_BUTTON },
                { EntryPoint.REGISTER_PAGE_BUTTON },
                { EntryPoint.PASSWORD_RECOVERY_PAGE_BUTTON },
        };
    }

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        loginSteps = new LoginSteps(driver);
        registerSteps = new RegisterSteps(driver);
        mainPageSteps = new MainPageSteps(driver);
        passwordRecoveryPageSteps = new PasswordRecoveryPageSteps(driver);

        registerName = generateRandomString(3, 12);
        registerEmail = generateRandomString(3, 12) + DOMAINS[ThreadLocalRandom.current().nextInt(DOMAINS.length)];
        //Длина пароля минимум 6 символов
        registerPassword = generateRandomString(6, 15);

        loginSteps.createUser(registerEmail, registerPassword, registerName);
    }

    @Test
    @DisplayName("Login user from different entry points")
    public void loginUserFromEntryPointsTest() {
        Allure.parameter("Entry Point", entryPoint.toString());
        performLoginBasedOnEntryPoint(entryPoint);
        mainPageSteps.checkMainRedirectToLogin();
        loginSteps.checkLoginUser(registerEmail, registerPassword);
    }

    @Step("Login user {entryPoint}")
    private void performLoginBasedOnEntryPoint(EntryPoint entryPoint) {
        switch (entryPoint) {
            case MAIN_PAGE_BUTTON:
                mainPageSteps.openMainPage();
                mainPageSteps.loginUserFromMainPageButton();
                break;
            case PROFILE_PAGE_BUTTON:
                mainPageSteps.openMainPage();
                mainPageSteps.clickAccountProfilePageButton();
                break;
            case REGISTER_PAGE_BUTTON:
                registerSteps.openRegisterPage();
                registerSteps.loginUserFromRegisterPageButton();
                break;
            case PASSWORD_RECOVERY_PAGE_BUTTON:
                passwordRecoveryPageSteps.openPasswordRecoveryPage();
                passwordRecoveryPageSteps.loginUserFromPasswordRecoveryPageButton();
        }
    }

    @After
    public void tearDown() {
        registerSteps.deleteUser(registerEmail, registerPassword);
    }
}
