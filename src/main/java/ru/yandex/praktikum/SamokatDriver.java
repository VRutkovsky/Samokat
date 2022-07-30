package ru.yandex.praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Objects;

public abstract class SamokatDriver {

    public static WebDriver initDriver(String useBrowser){
        WebDriver iDriver = null;
        if(Objects.equals(useBrowser,"Chrome") ) {
            iDriver = new ChromeDriver();
        } else if(Objects.equals(useBrowser, "EDGE")) {
            System.setProperty("webdriver.edge.driver", "C:\\Program Files\\WebDriver\\bin\\msedgedriver.exe");
            iDriver = new EdgeDriver();
        } else {
            System.out.println("Browser " + useBrowser + " not supported.");
        }
        return iDriver;
    }

}
