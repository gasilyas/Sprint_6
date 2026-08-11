package ru.yandex.practicum.ui.scooter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScooterMainPage {

    private WebDriver driver;

    //Лого Яндекс
    private By yandexLogo = By.xpath("//img[@alt='Yandex' and contains(@src, 'ya.svg')]");

    //Лого самоката
    private By scooterLogo = By.xpath("//img[@alt='Scooter' and contains(@src, 'scooter.svg')]");

    //Верхняя кнопка создания заказа
    private By topCreateOrderButton = By.xpath("//div[contains(@class, 'Header_Nav')]//button[contains(@class, 'Button_Button')]");

    //Нижняя кнопка создания заказа
    private By bottomCreateOrderButton = By.cssSelector("button[class*='Button_Middle']");

    //Кнопка статуса заказа
    private By checkStatusButton = By.xpath("//div[contains(@class, 'Header_Nav')]//button[contains(@class, 'Header_Link')]");

    //Поле ввода номера заказа
    private By orderInput = By.cssSelector("input[placeholder='Введите номер заказа']");

    //Кнопка Go! (поиск заказа)
    private By goButton = By.xpath("//div[contains(@class, 'Header_SearchInput')]//button[text()='Go!']");

    //Список вопросов
    private By questionList = By.cssSelector("div[data-accordion-component='AccordionItemButton']");

    //Список ответов
    private By answerList = By.cssSelector("div[data-accordion-component='AccordionItemPanel'] p");

    public ScooterMainPage(WebDriver driver) {
        this.driver = driver;
    }


}


