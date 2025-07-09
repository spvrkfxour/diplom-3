package ru.yandex.practicum.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static ru.yandex.practicum.constant.EnvConst.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.dto.CreateUserRequest;
import ru.yandex.practicum.dto.LoginUserRequest;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;


public class LoginSteps {

    private final LoginPage loginPage;
    private final MainPage mainPage;
    private final AccountPageSteps accountPageSteps;
    private final MainPageSteps mainPageSteps;
    private final UserApi userApi = new UserApi();
    private WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.mainPage = new MainPage(driver);
        this.accountPageSteps = new AccountPageSteps(driver);
        this.mainPageSteps = new MainPageSteps(driver);
        this.driver = driver;
    }

    @Step("Login user")
    public void loginUser(String email, String password) {
        loginPage.loginUser(email, password);
    }

    @Step("Create user")
    public void createUser(CreateUserRequest request) {
        Response response = userApi.createUser(request);
        Allure.step("Create user: " + response.getBody().asString());
    }

    @Step("Check user is login in account page")
    public void checkUserIsLoginAccountPage() {
        mainPageSteps.clickAccountProfilePageButtonToAccount();
        assertEquals("Account profile page do not open", ACCOUNT_PROFILE_PAGE_URL, driver.getCurrentUrl());
    }

    @Step("Redirect to main page")
    public void checkLoginRedirectToMain() {
        mainPage.redirectToMainPageLoader();
        assertEquals("Incorrect URL after login user", URL, driver.getCurrentUrl());
    }

    @Step("Check login user is correct")
    public void checkUserCanLogin(String email, String password) {
        loginUser(email, password);
        checkLoginRedirectToMain();
        checkUserIsLoginAccountPage();
        accountPageSteps.checkAccountEmail(email);
    }

    @Step("Add access token to local storage")
    public void addAccessTokenToLocalStorage(LoginUserRequest request) {
        loginPage.addAccessTokenToLocalStorage(request);
    }

    @Step("Check null access token in local storage")
    public void checkNullAccessTokenInLocalStorage() {
        String token = loginPage.getAccessTokenFromLocalStorage();
        assertNull("Access token not delete after logout", token);
    }
}
