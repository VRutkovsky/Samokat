package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

@RunWith(Parameterized.class)
public class SamokatTestOrder {
    private final String useBrowser; // Use "Chrome" or "EDGE"
    private final String useButton; // Use "TOP" or "BOTTOM"
    private final String userName;
    private final String userSurname;
    private final String userAddress;
    private final String userMetro;
    private final String userPhone;
    private final String orderDate;
    private final String orderTime;
    private final String orderColor;
    private final String orderComment;

    private WebDriver driver;

    private final String pageMain = "https://qa-scooter.praktikum-services.ru/";
    private final String pageOrder = "https://qa-scooter.praktikum-services.ru/order";

    public SamokatTestOrder(String useBrowser, String useButton, String userName, String userSurname, String userAddress, String userMetro, String userPhone, String orderDate, String orderTime, String orderColor, String orderComment){
        this.useBrowser = useBrowser;
        this.useButton = useButton;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAddress = userAddress;
        this.userMetro = userMetro;
        this.userPhone = userPhone;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderColor = orderColor;
        this.orderComment = orderComment;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"EDGE",   "TOP",    "Иван",  "Иванов","Москва ЕТ", "Сокол",    "+79998880001", "09.08.2022", "сутки",        "black", "EDGE TOP"},
                {"EDGE",   "BOTTOM", "Петр",  "Петров","Москва ЕВ", "Лубянка",  "+79998880002", "10.08.2022", "трое суток",   "grey",  "EDGE BOTTOM"},
                {"Chrome", "TOP",    "Ольга", "Белых", "Москва СТ", "Строгино", "+79998880003", "11.08.2022", "пятеро суток", "black", "Chrome TOP"},
                {"Chrome", "BOTTOM", "Ирина", "Черных","Москва СВ", "ВДНХ",     "+79998880004", "12.08.2022", "семеро суток", "grey",  "Chrome BOTTOM"}
        };
    }
    @Test
    public void TestOrder() {

        driver = SamokatDriver.initDriver(useBrowser);
        driver.get(pageMain);

        SamokatCookieOk cookieDialog = new SamokatCookieOk(driver);
        cookieDialog.buttonCookieOkClick();

        SamokatMainPageTest mainPage = new SamokatMainPageTest(driver);

        if(Objects.equals(useButton, "TOP")) {
            mainPage.buttonOrderTopClick();
        } else if (Objects.equals(useButton, "BOTTOM")){
            mainPage.buttonOrderBottomClick();
        } else {
            System.out.println("Button usage should be <<TOP>> or <<BOTTOM>>");
        }

        driver.get(pageOrder);

        SamokatOrderPageTest orderPage = new SamokatOrderPageTest(driver);

        orderPage.OrderFormDataComplete(userName, userSurname,userAddress, userMetro, userPhone);
        orderPage.buttonNextClick();

        SamokatRentPageTest rentPage = new SamokatRentPageTest(driver);

        rentPage.OrderRentDataComplete(orderDate, orderTime, orderColor, orderComment);
        rentPage.buttonNextClick();
        rentPage.buttonOrderConfirm();
        rentPage.buttonOrderCreatedCheck();
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
