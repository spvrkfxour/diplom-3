package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.yandex.practicum.constant.EnvConst.ACCOUNT_PROFILE_PAGE_URL;


public class AccountPage {

    private final WebDriver driver;

    private final By accountProfileName = By.xpath("//label[text()='Имя']/../*[self::input]");
    private final By accountProfileEmail = By.xpath("//label[text()='Логин']/../*[self::input]");
    private final By accountProfilePassword = By.xpath("//label[text()='Пароль']/../*[self::input]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(ACCOUNT_PROFILE_PAGE_URL);
    }

    public By getAccountProfileName() {
        return accountProfileName;
    }

    public By getAccountProfileEmail() {
        return accountProfileEmail;
    }

    public By getAccountProfilePassword() {
        return accountProfilePassword;
    }
}
