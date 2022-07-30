package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SamokatOrderPageTest {
    private WebDriver driver;
    // Кнопка Далее
    private final By buttonNext = new By.ByXPath(".//button[contains(text(),'Далее')]");
    // Поле ввода Имя
    private final By inputOrderName = new By.ByXPath(".//div[@class='Input_InputContainer__3NykH']//*[contains(@placeholder,'Имя')]");
    // Поле ввода фамилия
    private final By inputOrderSurname = new By.ByXPath(".//div[@class='Input_InputContainer__3NykH']//*[contains(@placeholder,'Фамилия')]");
    // Поле ввода Адрес
    private final By inputOrderAddress = new By.ByXPath(".//div[@class='Input_InputContainer__3NykH']//*[contains(@placeholder,'Адрес')]");
    // Поле ввода Станция метро
    private final By inputOrderMetro = new By.ByXPath(".//*[contains(@placeholder,'Станция')]");
    // Поле ввода Телефон
    private final By inputOrderPhone = new By.ByXPath(".//*[contains(@placeholder,'Телефон')]");
    // Конструктор первой страницы заказа
    public SamokatOrderPageTest(WebDriver driver){
        this.driver = driver;

    }
    // Ввод данных на первую страницу заказа
    public void OrderFormDataComplete(String userName, String userSurname, String userAddress, String userMetro, String userPhone){

        driver.findElement(inputOrderName).clear();
        driver.findElement(inputOrderSurname).clear();
        driver.findElement(inputOrderAddress).clear();
        driver.findElement(inputOrderMetro).clear();
        driver.findElement(inputOrderPhone).clear();

        SamokatCookieOk cookieDialog = new SamokatCookieOk(driver);
        cookieDialog.buttonCookieOkClick();

        driver.findElement(inputOrderName).sendKeys(userName);
        driver.findElement(inputOrderSurname).sendKeys(userSurname);
        driver.findElement(inputOrderAddress).sendKeys(userAddress);

        driver.findElement(inputOrderMetro).click();
        driver.findElement(inputOrderMetro).sendKeys(userMetro);
        driver.findElement(inputOrderMetro).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(inputOrderMetro).sendKeys(Keys.ENTER);

        driver.findElement(inputOrderPhone).sendKeys(userPhone);
    }
    // Нажатие на кнопку Далее
    public void buttonNextClick(){
        driver.findElement(buttonNext).click();
    }
}
