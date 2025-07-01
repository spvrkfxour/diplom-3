package ru.yandex.practicum;

import static ru.yandex.practicum.constant.EnvConst.DOMAINS;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.dto.CreateUserRequest;
import ru.yandex.practicum.dto.LoginUserRequest;
import ru.yandex.practicum.steps.AccountPageSteps;
import ru.yandex.practicum.steps.LoginSteps;
import ru.yandex.practicum.steps.MainPageSteps;
import ru.yandex.practicum.steps.RegisterSteps;
import java.util.concurrent.ThreadLocalRandom;


public class RedirectTest {

    @Rule
    public final DriverFactory driverFactory = new DriverFactory();

    private WebDriver driver;
    private LoginSteps loginSteps;
    private RegisterSteps registerSteps;
    private MainPageSteps mainPageSteps;
    private AccountPageSteps accountPageSteps;
    private LoginUserRequest request;
    private String registerName;
    private String registerEmail;
    private String registerPassword;

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        loginSteps = new LoginSteps(driver);
        registerSteps = new RegisterSteps(driver);
        mainPageSteps = new MainPageSteps(driver);
        accountPageSteps = new AccountPageSteps(driver);

        createTestUser();
        mainPageSteps.openMainPage();
        request = new LoginUserRequest(registerEmail, registerPassword);
    }

    private void createTestUser() {
        registerName = generateRandomString(3, 12);
        registerEmail = generateRandomString(3, 12) + DOMAINS[ThreadLocalRandom.current().nextInt(DOMAINS.length)];
        //Длина пароля минимум 6 символов
        registerPassword = generateRandomString(6, 15);

        CreateUserRequest request = new CreateUserRequest(registerEmail, registerPassword, registerName);
        loginSteps.createUser(request);
    }

    private String generateRandomString(int minLen, int maxLen) {
        return RandomStringUtils.randomAlphanumeric(minLen, maxLen).toLowerCase();
    }


    @Test
    @DisplayName("Not auth user redirect to login page")
    public void notAuthUserRedirectToRegisterTest() {
        mainPageSteps.clickAccountProfilePageButtonToLogin();
        mainPageSteps.checkMainRedirectToLogin();
    }

    @Test
    @DisplayName("Auth user redirect to account page")
    public void authUserRedirectToAccountPageTest() {
        loginSteps.addAccessTokenToLocalStorage(request);
        loginSteps.checkUserIsLogin();
        accountPageSteps.checkAccountEmail(registerEmail);
    }

    @Test
    @DisplayName("Constructor tab redirect to main page from account page")
    public void constructorTabRedirectToMainPageTest() {
        loginSteps.addAccessTokenToLocalStorage(request);
        mainPageSteps.clickAccountProfilePageButtonToAccount();
        mainPageSteps.clickConstructorButton();
        accountPageSteps.checkAccountPageRedirectToMain();
    }

    @Test
    @DisplayName("Logo redirect to main page from account page")
    public void logoRedirectToMainPageTest() {
        loginSteps.addAccessTokenToLocalStorage(request);
        mainPageSteps.clickAccountProfilePageButtonToAccount();
        mainPageSteps.clickLogo();
        accountPageSteps.checkAccountPageRedirectToMain();
    }

    @After
    public void tearDown() {
        registerSteps.deleteUser(request);
    }
}
