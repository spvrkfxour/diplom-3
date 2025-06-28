package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.AccountPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.constant.EnvConst.*;


public class AccountPageSteps {

    private final AccountPage accountPage;
    private WebDriver driver;

    public AccountPageSteps(WebDriver driver) {
        this.accountPage = new AccountPage(driver);
        this.driver = driver;
    }

    @Step("Click logout button")
    public void clickLogoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(accountPage.getLogoutButton()));
        accountPage.clickLogoutButton();
    }

    @Step("Check user email equals account email")
    public void checkAccountEmail(String email) {
        String accountEmail = driver.findElement(accountPage.getAccountProfileEmail()).getDomAttribute("value");
        assertEquals("Account email in profile is different", email, accountEmail);
    }

    @Step("Redirect to main page")
    public void checkAccountPageRedirectToMain() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(URL));
        assertEquals("Incorrect URL after redirect to main page", URL, driver.getCurrentUrl());
    }
}
