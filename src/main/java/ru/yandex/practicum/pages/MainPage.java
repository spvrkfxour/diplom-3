package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.yandex.practicum.constant.EnvConst.URL;


public class MainPage {

    private final WebDriver driver;

    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By accountProfileButton = By.xpath("//a[.//p[text()='Личный Кабинет']]");
    private final By headerButtons = By.className("AppHeader_header__link__3D_hX");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(URL);
    }

    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    public void clickAccountProfileButton() {
        driver.findElement(accountProfileButton).click();
    }

    public void clickLogo() {
        driver.findElement(logoButton).click();
    }

    public void clickConstructorButton() {
        driver.findElements(headerButtons).get(0).click();
    }
}
