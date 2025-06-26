package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.constant.EnvConst.*;


public class MainPageSteps {

    private final MainPage mainPage;
    private WebDriver driver;

    public MainPageSteps(WebDriver driver) {
        this.mainPage = new MainPage(driver);
        this.driver = driver;
    }

    @Step("Open main page")
    public void openMainPage() {
        mainPage.openPage();
    }

    @Step("Login from main page button")
    public void loginUserFromMainPageButton() {
        mainPage.clickLoginAccountButton();
    }

    @Step("Redirect to login page")
    public void checkMainRedirectToLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        assertEquals("Incorrect URL after click button user", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
