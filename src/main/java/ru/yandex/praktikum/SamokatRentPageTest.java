package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class SamokatRentPageTest {
    private WebDriver driver;
    // Заголовок "Про аренду"
    private final By inputOrderTitle = new By.ByClassName("Order_Header__BZXOb");
    // Заголовок сообщения о создании заказа
    private final By inputOrderCreatedTitle = new By.ByClassName("Order_ModalHeader__3FDaJ");
    // Кнопка завершения заказа
    private final By buttonOrderFinish = new By.ByXPath(".//*[contains(@class,'Button_Button__ra12g Button_Middle') and text() = 'Заказать']");
    // Кнопка подтверждения заказа
    private final By buttonOrderConfirm = new By.ByXPath(".//button[text()='Да']");
    // Поле даты когда привезти самокат
    private final By inputOrderDate = new By.ByXPath(".//*[contains(@placeholder,'Когда')]");
    // Поле длительности аренды
    private final By inputOrderTime = new By.ByXPath(".//*[contains(@class,'Dropdown-root')]");
    // Список опций по длительности аренды
    private final By inputOrderTimeMenu = new By.ByXPath(".//*[@class = 'Dropdown-option']");
    // Чек бокс черный цвет
    private final By inputOrderColorBalck = new By.ByXPath(".//*[@class='Checkbox_Input__14A2w' and @id='black']");
    // Чек бокс серый цвет
    private final By inputOrderColorGrey = new By.ByXPath(".//*[@class='Checkbox_Input__14A2w' and @id='grey']");
    // Поле комментария
    private final By inputOrderComment = new By.ByXPath(".//*[contains(@placeholder,'омментарий')]");

    // конструктор второй страницы заказа
    public SamokatRentPageTest(WebDriver driver){
        this.driver = driver;
    }
    // ввод данных на второй странице заказа
    public void OrderRentDataComplete(String orderDate, String orderTime, String orderColor, String orderComment){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(inputOrderTitle));

        driver.findElement(inputOrderComment).clear();

        driver.findElement(inputOrderDate).sendKeys(orderDate);
        driver.findElement(inputOrderDate).sendKeys(Keys.ENTER);

        driver.findElement(inputOrderTime).click();

        for(int i=0; i < driver.findElements(inputOrderTimeMenu).size(); i++){
            if(Objects.equals(driver.findElements(inputOrderTimeMenu).get(i).getText(), orderTime)) {
                driver.findElements(inputOrderTimeMenu).get(i).click();
            }
        }

        if(Objects.equals(orderColor, "black")) {
            driver.findElement(inputOrderColorBalck).click();
        } else if(Objects.equals(orderColor, "grey")){
            driver.findElement(inputOrderColorGrey).click();
        } else {
            System.out.println("Expected color 'black' or 'grey', while color <<" + orderColor + ">> provided.");
        }
        driver.findElement(inputOrderComment).sendKeys(orderComment);
    }
    // нажатие на кнопку завершения заказа
    public void buttonNextClick(){
        driver.findElement(buttonOrderFinish).click();
    }
    // нажатие на кнопку подтверждения заказа
    public void buttonOrderConfirm(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(buttonOrderConfirm));
        driver.findElement(buttonOrderConfirm).click();
    }
    // проверка открытия диалога с сообщением об успешном формировании заказа
    public void buttonOrderCreatedCheck(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(inputOrderCreatedTitle));
        String strResult = driver.findElement(inputOrderCreatedTitle).getText();
        assertTrue(strResult.startsWith("Заказ оформлен"));
    }
}
