package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.AccountPage;

import static org.junit.Assert.assertEquals;


public class AccountPageSteps {

    private final AccountPage accountPage;
    private WebDriver driver;

    public AccountPageSteps(WebDriver driver) {
        this.accountPage = new AccountPage(driver);
        this.driver = driver;
    }

    @Step("Open account page")
    public void openAccountPage() {
        accountPage.openPage();
    }

    @Step("Check user email equals account email")
    public void checkAccountEmail(String email) {
        String accountEmail = driver.findElement(accountPage.getAccountProfileEmail()).getDomAttribute("value");
        assertEquals("Account email in profile is different", email, accountEmail);
    }
}
