package ru.yandex.practicum.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Getter
public class AccountPage {

    private final WebDriver driver;

    private final By accountProfileEmail = By.xpath("//label[text()='Логин']/../*[self::input]");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
