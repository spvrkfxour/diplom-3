package ru.yandex.practicum.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.constant.EnvConst.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.AccountPage;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;
import java.time.Duration;


public class MainPageSteps {

    private final MainPage mainPage;
    private WebDriver driver;
    private final WebDriverWait wait;
    private final LoginPage loginPage;
    private final AccountPage accountPage;

    public MainPageSteps(WebDriver driver) {
        this.mainPage = new MainPage(driver);
        this.loginPage = new LoginPage(driver);
        this.accountPage = new AccountPage(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));
    }

    @Step("Open main page")
    public void openMainPage() {
        mainPage.openPage();
    }

    @Step("Login from main page button")
    public void loginUserFromMainPageButton() {
        mainPage.clickLoginAccountButton();
    }

    @Step("Click on account profile page button")
    public void clickAccountProfilePageButtonToLogin() {
        mainPage.clickAccountProfileButton();
        loginPage.redirectToLoginPageLoader();
    }

    @Step("Click on account profile page button")
    public void clickAccountProfilePageButtonToAccount() {
        mainPage.clickAccountProfileButton();
        accountPage.redirectToAccountPageLoader();
    }

    @Step("Click on logo")
    public void clickLogo() {
        mainPage.clickLogo();
    }

    @Step("Click on constructor tab")
    public void clickConstructorButton() {
        mainPage.clickConstructorButton();
    }

    @Step("Click on buns tab")
    public void clickBunsTab() {
        mainPage.clickBunsTab();
    }

    @Step("Click on sauces tab")
    public void clickSaucesTab() {
        mainPage.clickSaucesTab();
    }

    @Step("Click on fillings tab")
    public void clickFillingsTab() {
        mainPage.clickFillingsTab();
    }

    @Step("Redirect to login page")
    public void checkMainRedirectToLogin() {
        loginPage.redirectToLoginPageLoader();
        assertEquals("Incorrect URL after click button user", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Step("Check buns section top")
    public void checkBunsSectionTop() {
        checkIngredientContainerTop(mainPage.getBunsSection());
    }

    @Step("Check sauces section top")
    public void checkSaucesSectionTop() {
        checkIngredientContainerTop(mainPage.getSaucesSection());
    }

    @Step("Check fillings section top")
    public void checkFillingsSectionTop() {
        checkIngredientContainerTop(mainPage.getFillingsSection());
    }

    @Step("Check ingredients container - {sectionName} top")
    public void checkIngredientContainerTop(By sectionName) {
        waitForScrollCompletion(sectionName);

        int sectionTop = mainPage.getSectionCoordinatesY(sectionName);
        int containerTop = mainPage.getIngredientsContainerCoordinatesY();

        assertTrue("Selected section " + sectionName + " not on top of ingredients container",
                sectionTop >= containerTop && sectionTop <= containerTop + 50);
    }

    private void waitForScrollCompletion(By sectionName) {

        wait.until(driver -> {
            WebElement section = driver.findElement(sectionName);
            WebElement container = driver.findElement(mainPage.getIngredientsContainer());

            int sectionTop = section.getLocation().getY();
            int containerTop = container.getLocation().getY();

            return sectionTop >= containerTop && sectionTop <= containerTop + 50;
        });
    }
}
