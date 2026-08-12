package ru.yandex.practicum.ui.scooter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterMainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Лого Яндекс
    private final By yandexLogo = By.xpath("//img[@alt='Yandex' and contains(@src, 'ya.svg')]");

    //Лого самоката
    private final By scooterLogo = By.xpath("//img[@alt='Scooter' and contains(@src, 'scooter.svg')]");

    //Верхняя кнопка создания заказа
    private final By topCreateOrderButton = By.xpath("//div[contains(@class, 'Header_Nav')]//button[contains(@class, 'Button_Button')]");

    //Нижняя кнопка создания заказа
    private final By bottomCreateOrderButton = By.cssSelector("button[class*='Button_Middle']");

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
        driver.findElement(bottomCreateOrderButton).click();
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
        WebElement questionElement = driver.findElement(getQuestionLocator(questionText));

        ((JavascriptExecutor) driver).executeScript("arguments.scrollIntoView({block: 'center'});", questionElement);

        questionElement.click();
    }

    //Получение текста ответа в карточке искомого вопроса
    public String getAnswerText(String questionText) {
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(getAnswerLocator(questionText)));
        return answerElement.getText();
    }


}


