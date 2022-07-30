package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;


public class SamokatMainPageTest {
    private WebDriver driver;

    // Заголовок главной страницы
    private final By headerSamokat = new By.ByXPath(".//*[contains(text(),'Самокат')]");
    // Верхняя кнопка создания заказа
    private final By buttonOrderTop = new By.ByClassName("Button_Button__ra12g");
    // Нижняя кнопка создания заказа
    private final By buttonOrderBottom = new By.ByXPath(".//div[contains(@class,'Home_RoadMap')]//*[contains(@class,'Button_Button__ra12g')]");
    // Кнопка проверки статуса заказа
    private final By buttonOrderStatus = new By.ByClassName("Header_Link__1TAG7");


    private final int numberFAQ = 8;

    private By[] userQuestions = new By[numberFAQ];      // Список вопросов FAQ
    private By[] userAnswers = new By[numberFAQ];        // Список ответов FAQ
    private final int questionCostPayment = 0;    // Номер вопроса Сколько это стоит
    private final int questionSamokatNumber = 1;  // Номер вопроса Хочу сразу несколько
    private final int questionRentTimeCalc = 2;  // Номер вопроса Как рассчитать время
    private final int questionSamokatToday = 3;  // Номер вопроса Можно взять сегодня
    private final int questionRentTimeChange = 4;  // Номер вопроса Можно ли продлить
    private final int questionCharger = 5;  // Номер вопроса Вы привозите зараядку
    private final int questionOrderCancel = 6;  // Номер вопроса Можно ли отменить
    private final int questionMKAD = 7;  // Номер вопроса За МКАДОМ

    // Main Page Constructor
    public SamokatMainPageTest(WebDriver driver){
        this.driver = driver;

        this.userQuestions[questionCostPayment] = new By.ByXPath(".//div[@class='accordion__item']//div[contains(text(),'Сколько это стоит?')]");     // Сколько это стоит
        this.userQuestions[questionSamokatNumber] = new By.ByXPath(".//div[@class='accordion__item']//div[contains(text(),'Хочу сразу несколько')]");   // Хочу сразу несколько
        this.userQuestions[questionRentTimeCalc] = new By.ByXPath(".//div[@class='accordion__item']//div[contains(text(),'Как рассчитывается время')]");   // Как рассчитать время
        this.userQuestions[questionSamokatToday] = new By.ByXPath(".//div[@class='accordion__item']//div[contains(text(),'самокат прямо на сегодня')]");   // Можно взять сегодня
        this.userQuestions[questionRentTimeChange] = new By.ByXPath(".//div[@class='accordion__item']//div[contains(text(),'Можно ли продлить заказ')]"); // Можно ли продлить
        this.userQuestions[questionCharger] = new By.ByXPath(".//div[@class='accordion__item']//div[contains(text(),'Вы привозите зарядку')]");        // Вы привозите зараядку
        this.userQuestions[questionOrderCancel] = new By.ByXPath(".//div[@class='accordion__item']//div[contains(text(),'Можно ли отменить')]");    // Можно ли отменить
        this.userQuestions[questionMKAD] = new By.ByXPath(".//div[@class='accordion__item']//div[contains(text(),'за МКАДом')]");           // За МКАДОМ

        this.userAnswers[questionCostPayment] = new By.ByXPath(".//div[@class='accordion__item']//p[contains(text(),'Сутки — ')]");     // Сколько это стоит
        this.userAnswers[questionSamokatNumber] = new By.ByXPath(".//div[@class='accordion__item']//p[contains(text(),'один заказ — один самокат.')]");   // Хочу сразу несколько
        this.userAnswers[questionRentTimeCalc] = new By.ByXPath(".//div[@class='accordion__item']//p[contains(text(),'Мы привозим самокат')]");   // Как рассчитать время
        this.userAnswers[questionSamokatToday] = new By.ByXPath(".//div[@class='accordion__item']//p[contains(text(),'Только начиная с завтрашнего дня')]");   // Можно взять сегодня
        this.userAnswers[questionRentTimeChange] = new By.ByXPath(".//div[@class='accordion__item']//p[contains(text(),'Пока что нет')]"); // Можно ли продлить
        this.userAnswers[questionCharger] = new By.ByXPath(".//div[@class='accordion__item']//p[contains(text(),'с полной зарядкой')]");        // Вы привозите зараядку
        this.userAnswers[questionOrderCancel] = new By.ByXPath(".//div[@class='accordion__item']//p[contains(text(),'Штрафа не будет')]");    // Можно ли отменить
        this.userAnswers[questionMKAD] = new By.ByXPath(".//div[@class='accordion__item']//p[contains(text(),'И Москве, и Московской')]");           // За МКАДОМ

    }

    // Нажатие верхней кнопки создания заказа
    public void buttonOrderTopClick(){
        driver.findElement(buttonOrderTop).click();
    }
    // Нажатие нижней кнопки создания заказа
    public void buttonOrderBottomClick(){
        driver.findElement(buttonOrderBottom).click();
    }
    // Нажатие кнопки статуса заказа
    public void buttonOrderStatusClick(){
        driver.findElement(buttonOrderStatus).click();
    }
    // Проверка открытия ответа на вопрос из FAQ
    public void userQuestionClickAndCheckAnswer(int i){
        driver.findElement(userQuestions[i]).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(userAnswers[i]));
        assertTrue(driver.findElement(userAnswers[i]).isDisplayed());
    }
    // Проверка открытия ответа на каждый вопрос из FAQ на главной странице
    public void userFAQTest(){

        // Ждать до появления заголовка страницы
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(headerSamokat));

        // Скролл до вопросов
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(userQuestions[0]));

        // перебор вопросов в списке
        for(int i=0; i < numberFAQ; i++){
            userQuestionClickAndCheckAnswer(i);
        }
    }

   }
