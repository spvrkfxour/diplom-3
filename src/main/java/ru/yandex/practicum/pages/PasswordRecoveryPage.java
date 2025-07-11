package ru.yandex.practicum.pages;

import static ru.yandex.practicum.constant.EnvConst.PASSWORD_RECOVERY_PAGE_URL;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Getter
public class PasswordRecoveryPage {

    private final WebDriver driver;

    private final By loginButton = By.className("Auth_link__1fOlj");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PASSWORD_RECOVERY_PAGE_URL);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
