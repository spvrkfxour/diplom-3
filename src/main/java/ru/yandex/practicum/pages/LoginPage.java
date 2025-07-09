package ru.yandex.practicum.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.dto.LoginUserRequest;

import java.time.Duration;

import static ru.yandex.practicum.constant.EnvConst.*;


@Getter
public class LoginPage {

    private final WebDriver driver;
    private final UserApi userApi = new UserApi();

    private final By loginEmailInput = By.xpath("//label[text()='Email']/../*[self::input]");
    private final By loginPasswordInput = By.xpath("//label[text()='Пароль']/../*[self::input]");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginEmailInput() {
        driver.findElement(loginEmailInput).click();
    }

    public void clickLoginPasswordInput() {
        driver.findElement(loginPasswordInput).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void fillLoginEmailInput(String email) {
        clickLoginEmailInput();
        driver.findElement(loginEmailInput).sendKeys(email);
    }

    public void fillLoginPasswordInput(String password) {
        clickLoginPasswordInput();
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public void fillLoginForm(String email, String password) {
        fillLoginEmailInput(email);
        fillLoginPasswordInput(password);
    }

    public void loginUser(String email, String password) {
        fillLoginForm(email, password);
        clickLoginButton();
    }

    public void addAccessTokenToLocalStorage(LoginUserRequest request) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('accessToken', '" + userApi.getAccessToken(request) + "');");
    }

    public String getAccessTokenFromLocalStorage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return localStorage.getItem('accessToken');");
    }

    public void redirectToLoginPageLoader() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
    }
}
