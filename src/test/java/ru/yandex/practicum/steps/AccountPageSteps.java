package ru.yandex.practicum.steps;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.constant.EnvConst.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.AccountPage;
import ru.yandex.practicum.pages.MainPage;


public class AccountPageSteps {

    private final AccountPage accountPage;
    private final MainPage mainPage;
    private WebDriver driver;

    public AccountPageSteps(WebDriver driver) {
        this.accountPage = new AccountPage(driver);
        this.mainPage = new MainPage(driver);
        this.driver = driver;
    }

    @Step("Click logout button")
    public void clickLogoutButton() {
        accountPage.clickLogoutButton();
    }

    @Step("Check user email equals account email")
    public void checkAccountEmail(String email) {
        String accountEmail = accountPage.getAccountPageUserEmail();
        assertEquals("Account email in profile is different", email, accountEmail);
    }

    @Step("Redirect to main page")
    public void checkAccountPageRedirectToMain() {
        mainPage.redirectToMainPageLoader();
        assertEquals("Incorrect URL after redirect to main page", URL, driver.getCurrentUrl());
    }
}
