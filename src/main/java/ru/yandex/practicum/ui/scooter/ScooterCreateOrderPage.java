package ru.yandex.practicum.ui.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterCreateOrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Заголовок первой страницы заполнения заказа
    private By firstPageOrderHeader = By.xpath("//div[text()='Для кого самокат']");

    //Имя
    private By customerName = By.cssSelector("input[placeholder='* Имя']");

    //Фамилия
    private By customerSurname = By.cssSelector("input[placeholder='* Фамилия']");

    //Адрес
    private By address = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");

    //Метро
    private By metroInput = By.cssSelector(".select-search__input");

    //Выпадающий список станций метро
    private By metroDropdown = By.cssSelector(".select-search__select");

    //Поле с конкретной станцией метро
    private By getMetroStationLocator(String stationName) {
        return By.xpath("//div[@class='select-search__select']//button[//div[text()='" + stationName + "']] | " +
                "//div[contains(@class, 'select-search')]//div[text()='" + stationName + "']");
    }

    //Телефон
    private By phoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее
    private By continueButton = By.xpath("//button[text()='Далее']");

    //Заголовок второй страницы заполнения заказа
    private By secondPageOrderHeader = By.xpath("//div[text()='Про аренду']");

    //Поле когда привезти заказ
    private By orderDeliveryDateInput = By.cssSelector("input[placeholder='* Когда привезти самокат']");

    //Поле срок аренды
    private By rentalPeriodDropdown = By.cssSelector(".Dropdown-control");

    //Поле с конкретным сроком аренды
    private By getRentalPeriodOption(String periodText) {
        return By.xpath("//div[@class='Dropdown-menu']/div[text()='" + periodText + "']");
    }

    //Блок выбора цвета, цвет черный
    private By blackColorCheckbox = By.id("black");

    //Блок выбора цвета, цвет серый
    private By greyColorCheckbox = By.id("grey");

    //Поле комментарий
    private By commentInput = By.cssSelector("input[placeholder='Комментарий для курьера']");

    //Кнопка заказать
    private By createOrderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");

    //Всплывающее окно хотите оформить заказ
    //Кнопка Да
    private By confirmYesButton = By.xpath("//div[contains(@class, 'Order_Modal')]//button[text()='Да']");

    //Всплывающее окно успешно созданного заказа
    //Текст в окне
    private final By orderCreatedHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");


}
