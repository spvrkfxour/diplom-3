package ru.yandex.practicum.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.pages.RegisterPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.constant.EnvConst.EXPLICIT_TIMEOUT;
import static ru.yandex.practicum.constant.EnvConst.LOGIN_PAGE_URL;


public class RegisterSteps {

    private final RegisterPage registerPage;
    private final UserApi userApi = new UserApi();
    private WebDriver driver;
    private String accessToken;

    public RegisterSteps(WebDriver driver) {
        this.registerPage = new RegisterPage(driver);
        this.driver = driver;
    }

    @Step("Open register page")
    public void openRegisterPage() {
        registerPage.openPage();
    }

    @Step("Register user")
    public void registerUser(String name, String email, String password) {
        registerPage.registerUser(name, email, password);
    }

    @Step("Redirect to login page")
    public void checkRegisterRedirectToLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlContains("/login"));
        assertEquals("Incorrect URL after registration user", LOGIN_PAGE_URL, registerPage.registerRedirectToLoginURL());
    }

    @Step("Delete user")
    public void deleteUser(String email, String password) {
        accessToken = userApi.getAccessToken(email, password);
        if (accessToken != null) {
            Response response = userApi.deleteUser(accessToken);
            Allure.step("Delete user: " + response.getBody().asString());
        }
    }
}
