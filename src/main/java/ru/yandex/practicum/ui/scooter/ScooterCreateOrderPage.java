package ru.yandex.practicum.ui.scooter;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScooterCreateOrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Заголовок первой страницы заполнения заказа
    private final By firstPageOrderHeader = By.xpath("//div[text()='Для кого самокат']");

    //Имя
    private final By customerName = By.cssSelector("input[placeholder='* Имя']");

    //Фамилия
    private final By customerSurname = By.cssSelector("input[placeholder='* Фамилия']");

    //Адрес
    private final By address = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");

    //Метро
    private final By metroInput = By.cssSelector(".select-search__input");

    //Выпадающий список станций метро
    private final By metroDropdown = By.cssSelector(".select-search__select");

    //Телефон
    private final By phoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее
    private final By continueButton = By.xpath("//button[text()='Далее']");

    //Заголовок второй страницы заполнения заказа
    private final By secondPageOrderHeader = By.xpath("//div[text()='Про аренду']");

    //Поле когда привезти заказ
    private final By orderDeliveryDateInput = By.cssSelector("input[placeholder='* Когда привезти самокат']");

    //Поле срок аренды
    private final By rentalPeriodDropdown = By.cssSelector(".Dropdown-control");

    //Блок выбора цвета, цвет черный
    private final By blackColorCheckbox = By.id("black");

    //Блок выбора цвета, цвет серый
    private final By greyColorCheckbox = By.id("grey");

    //Поле комментарий
    private final By commentInput = By.cssSelector("input[placeholder='Комментарий для курьера']");

    //Кнопка заказать
    private final By createOrderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");

    //Всплывающее окно хотите оформить заказ
    private final By confirmOrderHeader = By.xpath("//div[text()='Хотите оформить заказ?'");

    //Кнопка Да
    private final By confirmYesButton = By.xpath("//div[contains(@class, 'Order_Modal')]//button[text()='Да']");

    //Всплывающее окно успешно созданного заказа
    //Текст в окне
    private final By orderCreatedHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");

    //Конструктор
    public ScooterCreateOrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    //Поле с конкретной станцией метро
    private By getMetroStationLocator(String stationName) {
        return By.xpath("//div[@class='select-search__select']//button[//div[text()='" + stationName + "']] | " +
                "//div[contains(@class, 'select-search')]//div[text()='" + stationName + "']");
    }

    //Поле с конкретным сроком аренды
    private By getRentalPeriodOption(String periodText) {
        return By.xpath("//div[@class='Dropdown-menu']/div[text()='" + periodText + "']");
    }

    //Ожидание загрузки первой страницы создания заказа
    public void waitForFirstPageOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstPageOrderHeader));
    }

    //Заполняем имя заказчика
    public void fillCustomerName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(customerName));
        nameField.clear();
        nameField.sendKeys(name);
    }

    //Заполняем фамилию заказчика
    public void fillCustomerSurname(String surname) {
        WebElement surnameField = wait.until(ExpectedConditions.elementToBeClickable(customerSurname));
        surnameField.clear();
        surnameField.sendKeys(surname);
    }

    //Заполняем адрес доставки
    public void fillAddress(String customerAddress) {
        WebElement addressField = wait.until(ExpectedConditions.elementToBeClickable(address));
        addressField.clear();
        addressField.sendKeys(customerAddress);
    }

    //Выбираем ближайшую станцию метро
    public void selectMetroStation(String stationName) {
        WebElement metroInputField = wait.until(ExpectedConditions.elementToBeClickable(metroInput));

        // Клик по полю ввода станции
        metroInputField.click();

        // Вводим название, чтобы сузить диапазон
        metroInputField.sendKeys(stationName);

        // Ждем появления опции из списка
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroDropdown));

        // Находим искомую станцию
        WebElement stationOption = driver.findElement(getMetroStationLocator(stationName));

        // Клик по выбранной станции
        stationOption.click();
    }

    //Вводим номер телефона
    public void fillPhoneNumber(String phone) {
        WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(phoneNumber));
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    //Нажимаем кнопку Далее
    public void clickContinueButton() {
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        nextButton.click();
    }

    // Заполнение первой страницы заказа
    public void fillFirstOrderPageForm(String name, String surname, String customerAddress, String stationName, String phone) {
        fillCustomerName(name);
        fillCustomerSurname(surname);
        fillAddress(customerAddress);
        selectMetroStation(stationName);
        fillPhoneNumber(phone);
        clickContinueButton();
    }

    //Ожидание загрузки второй страницы создания заказа
    public void waitForSecondPageOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondPageOrderHeader));
    }

    //Заполняем дату доставки
    public void fillDeliveryDate(String date) {
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(orderDeliveryDateInput));
        dateField.clear();
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER); // Закрываем всплывающее окно календаря
    }

    //Заполняем срок аренды
    public void selectRentalPeriod(String period) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown));
        dropdown.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getRentalPeriodOption(period)));
        option.click();
    }

    //Выбираем цвет самоката
    public void selectScooterColor(String color) {
        if ("черный".equalsIgnoreCase(color)) {
            wait.until(ExpectedConditions.elementToBeClickable(blackColorCheckbox)).click();
        } else if ("серый".equalsIgnoreCase(color)) {
            wait.until(ExpectedConditions.elementToBeClickable(greyColorCheckbox)).click();
        }
    }

    //Заполняем комментарий
    public void fillComment(String comment) {
        WebElement commentField = wait.until(ExpectedConditions.elementToBeClickable(commentInput));
        commentField.clear();
        commentField.sendKeys(comment);
    }

    //Нажимаем кнопку заказать
    public void clickCreateOrderButton() {
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(createOrderButton));
        createButton.click();
    }

    //Заполнение второй страницы заказа
    public void fillSecondOrderPageForm(String date, String period, String color, String comment) {
        fillDeliveryDate(date);
        selectRentalPeriod(period);
        selectScooterColor(color);
        fillComment(comment);
        clickCreateOrderButton();
    }

    //Ожидание появления модального окна подтверждения заказа
    public void waitForConfirmOrderModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmOrderHeader));
    }

    //Клик по кнопке Да
    public void clickConfirmYesButton() {
        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton));
        yesButton.click();
    }

    //Проверка текста об успешно созданном заказе
    public Boolean isOrderSuccessDisplayed() {
        try {
            WebElement successMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(orderCreatedHeader)
            );
            return successMessage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}

