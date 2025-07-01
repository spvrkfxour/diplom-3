package ru.yandex.practicum.pages;

import static ru.yandex.practicum.constant.EnvConst.URL;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Getter
public class MainPage {

    private final WebDriver driver;

    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By accountProfileButton = By.xpath("//a[.//p[text()='Личный Кабинет']]");
    private final By headerButtons = By.className("AppHeader_header__link__3D_hX");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private final By bunsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]");
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]");
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");
    private final By ingredientsContainer = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo");
    private final By bunsSection = By.xpath("//h2[text()='Булки']");
    private final By saucesSection = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSection = By.xpath("//h2[text()='Начинки']");

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

    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    public int getIngredientsContainerCoordinatesY() {
        return driver.findElement(ingredientsContainer).getLocation().getY();
    }

    public int getSectionCoordinatesY(By sectionName) {
        return driver.findElement(sectionName)
                .getLocation()
                .getY();
    }
}
