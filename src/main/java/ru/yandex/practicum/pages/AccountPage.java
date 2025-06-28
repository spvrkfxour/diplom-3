package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.yandex.practicum.constant.EnvConst.ACCOUNT_PROFILE_PAGE_URL;


public class AccountPage {

    private final WebDriver driver;

    private final By accountProfileEmail = By.xpath("//label[text()='Логин']/../*[self::input]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getAccountProfileEmail() {
        return accountProfileEmail;
    }
}
