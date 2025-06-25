package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.RegisterPage;


public class RegisterSteps {

    private final RegisterPage registerPage;

    public RegisterSteps(WebDriver driver) {
        this.registerPage = new RegisterPage(driver);
    }

    @Step("Open register page")
    public void openRegisterPage() {
        registerPage.openPage();
    }

}
