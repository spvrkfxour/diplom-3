package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.yandex.practicum.constant.EnvConst.REGISTER_PAGE_URL;


public class RegisterPage {

    private final WebDriver driver;

    private final By registerNameInput = By.xpath(".//*[@class='Order_Buttons__1xGrp']//*[text()='Да']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(REGISTER_PAGE_URL);
    }

    public void clickRegisterNameInput() {
        driver.findElement(registerNameInput).click();
    }
}
