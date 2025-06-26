package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import static ru.yandex.practicum.constant.EnvConst.LOGIN_PAGE_URL;


public class LoginPage {

    private final WebDriver driver;

    private final By loginEmailInput = By.xpath("//label[text()='Email']/../*[self::input]");
    private final By loginPasswordInput = By.xpath("//label[text()='Пароль']/../*[self::input]");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(LOGIN_PAGE_URL);
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

    public String getAccessTokenFromLocalStorage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return window.localStorage.getItem('accessToken');");
    }
}
