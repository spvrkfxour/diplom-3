package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.yandex.practicum.constant.EnvConst.REGISTER_PAGE_URL;


public class RegisterPage {

    private final WebDriver driver;

    private final By registerNameInput = By.xpath("//label[text()='Имя']/../*[self::input]");
    private final By registerEmailInput = By.xpath("//label[text()='Email']/../*[self::input]");
    private final By registerPasswordInput = By.xpath("//label[text()='Пароль']/../*[self::input]");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By registerPasswordInputError = By.cssSelector("p.input__error.text_type_main-default");
    private final By loginButton = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(REGISTER_PAGE_URL);
    }

    public void clickRegisterNameInput() {
        driver.findElement(registerNameInput).click();
    }

    public void clickRegisterEmailInput() {
        driver.findElement(registerEmailInput).click();
    }

    public void clickRegisterPasswordInput() {
        driver.findElement(registerPasswordInput).click();
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void fillRegisterNameInput(String name) {
        clickRegisterNameInput();
        driver.findElement(registerNameInput).sendKeys(name);
    }

    public void fillRegisterEmailInput(String email) {
        clickRegisterEmailInput();
        driver.findElement(registerEmailInput).sendKeys(email);
    }

    public void fillRegisterPasswordInput(String password) {
        clickRegisterPasswordInput();
        driver.findElement(registerPasswordInput).sendKeys(password);
    }

    public void fillRegisterForm(String name, String email, String password) {
        fillRegisterNameInput(name);
        fillRegisterEmailInput(email);
        fillRegisterPasswordInput(password);
    }

    public void registerUser(String name, String email, String password) {
        fillRegisterForm(name, email, password);
        clickRegisterButton();
    }

    public By getRegisterPasswordInputErrorElement() {
        return registerPasswordInputError;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
