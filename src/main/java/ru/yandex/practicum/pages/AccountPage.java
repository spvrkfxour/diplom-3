package ru.yandex.practicum.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static ru.yandex.practicum.constant.EnvConst.*;


@Getter
public class AccountPage {

    private final WebDriver driver;

    private final By accountProfileEmailInput = By.xpath("//label[text()='Логин']/../*[self::input]");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }

    public String getAccountPageUserEmail() {
        return driver.findElement(getAccountProfileEmailInput()).getDomAttribute("value");
    }

    public void redirectToAccountPageLoader() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(ACCOUNT_PROFILE_PAGE_URL));
    }
}
