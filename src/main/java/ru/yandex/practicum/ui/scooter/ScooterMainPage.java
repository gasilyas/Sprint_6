package ru.yandex.practicum.ui.scooter;

import org.openqa.selenium.*;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterMainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Кнопка закрытия инф.панели о куках
    private final By acceptCookieButton = By.id("rcc-confirm-button");

    //Лого Яндекс
    private final By yandexLogo = By.xpath("//img[@alt='Yandex' and contains(@src, 'ya.svg')]");

    //Лого самоката
    private final By scooterLogo = By.xpath("//img[@alt='Scooter' and contains(@src, 'scooter.svg')]");

    //Верхняя кнопка создания заказа
    private final By topCreateOrderButton = By.xpath("//div[contains(@class, 'Header_Nav')]//button[contains(@class, 'Button_Button')]");

    //Нижняя кнопка создания заказа
    private final By bottomCreateOrderButton = By.xpath("//div[contains(@class, 'Home_FinishButton')]//button | //button[contains(@class, 'Button_Middle') and text()='Заказать']");

    //Кнопка статуса заказа
    private final By checkStatusButton = By.xpath("//div[contains(@class, 'Header_Nav')]//button[contains(@class, 'Header_Link')]");

    //Поле ввода номера заказа
    private final By orderInput = By.cssSelector("input[placeholder='Введите номер заказа']");

    //Кнопка Go! (поиск заказа)
    private final By goButton = By.xpath("//div[contains(@class, 'Header_SearchInput')]//button[text()='Go!']");

    //Конструктор
    public ScooterMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    //Метод закрытия инф.панели о куках (если есть)
    public void acceptCookiesIfVisible() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        } catch (TimeoutException e) {
            System.out.println("Плашка отсутствует или была закрыта ранее");
        }
    }

    //Клик по логотипу Яндекса
    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    //Клик по логотипу самоката
    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    //Клик по кнопке создания заказа, вверху страницы
    public void clickTopCreateOrderButton() {
        driver.findElement(topCreateOrderButton).click();
    }

    //Клик по кнопке создания заказа, внизу страницы
    public void clickBottomCreateOrderButton() {

        WebElement bottomButton = wait.until(ExpectedConditions.visibilityOfElementLocated(bottomCreateOrderButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", bottomButton);

        wait.until(ExpectedConditions.elementToBeClickable(bottomButton)).click();
    }

    //Нажать кнопку проверки статуса заказа
    public void clickCheckStatusButton() {
        driver.findElement(checkStatusButton).click();
    }

    //Заполнение номера заказа (поиска статуса заказа)
    public void fillOrderNumber(String orderNumber) {
        WebElement orderInputElement = wait.until(ExpectedConditions.elementToBeClickable(orderInput));

        orderInputElement.clear();

        orderInputElement.sendKeys(orderNumber);
    }

    //Клик по кнопке Go (поиска статуса заказа)
    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    //Получение локатора вопроса по тексту
    private By getQuestionLocator(String questionText) {
        return By.xpath("//div[@data-accordion-component='AccordionItemButton' and text()='" + questionText + "']");
    }

    //Получение локатора ответа по тексту
    private By getAnswerLocator (String questionText) {
        return By.xpath("//div[@data-accordion-component='AccordionItemButton' and text()='" + questionText + "']" +
                "/ancestor::div[@data-accordion-component='AccordionItem']" +
                "//div[@data-accordion-component='AccordionItemPanel']/p");
    }

    //Клик на карточку вопроса с нужным текстом
    public void clickQuestion(String questionText) {
        WebElement questionElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(getQuestionLocator(questionText))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", questionElement);

        wait.until(ExpectedConditions.elementToBeClickable(questionElement));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", questionElement);
    }

    //Получение текста ответа в карточке искомого вопроса
    public String getAnswerText(String questionText) {
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(getAnswerLocator(questionText)));
        return answerElement.getText();
    }


}


