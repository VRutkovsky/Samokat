package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SamokatCookieOk {
    private WebDriver driver;
    // кнопка согласия на куки
    private final By buttonCookieOk = new By.ByClassName("App_CookieButton__3cvqF");

    // конструктор класса диалог куки
    public SamokatCookieOk(WebDriver driver) {
        this.driver = driver;
    }

    // нажатие на кнопку согласия с куки
    public void buttonCookieOkClick() {
        if (driver.findElements(buttonCookieOk).size() > 0) {
            driver.findElement(buttonCookieOk).click();
        }
    }
}
