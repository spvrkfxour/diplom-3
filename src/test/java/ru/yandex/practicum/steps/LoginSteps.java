package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.LoginPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.constant.EnvConst.*;


public class LoginSteps {

    private final LoginPage loginPage;
    private WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.driver = driver;
    }

    @Step("Open login page")
    public void openLoginPage() {
        loginPage.openPage();
    }

    @Step("Login user")
    public void loginUser(String email, String password) {
        loginPage.loginUser(email, password);
    }

    @Step("Redirect to main page")
    public void checkLoginRedirectToMain() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(URL));
        assertEquals("Incorrect URL after login user", URL, loginPage.loginRedirectToMainURL());
    }
}
