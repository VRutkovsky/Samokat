package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;


@RunWith(Parameterized.class)

public class SamokatTestFAQ {
    private final String useBrowser; // Use "Chrome" or "EDGE"

    private WebDriver driver;

    private final String pageMain = "https://qa-scooter.praktikum-services.ru/";

    public SamokatTestFAQ(String useBrowser){
        this.useBrowser = useBrowser;
    }
    @Parameterized.Parameters
    public static Object[] getTestData() {
        return new Object[] {"EDGE","Chrome",};
        }
    @Test
    public void TestFAQ() {

        driver = SamokatDriver.initDriver(useBrowser);
        driver.get(pageMain);

        SamokatCookieOk cookieDialog = new SamokatCookieOk(driver);
        cookieDialog.buttonCookieOkClick();

        SamokatMainPageTest mainPage = new SamokatMainPageTest(driver);
        mainPage.userFAQTest();

    }
    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
