package ru.yandex.practicum.ui.scooter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class CreateOrderFlowTest extends BasicTestSeleniumConfiguration {

    static Stream<Arguments> orderFlowDataProvider() {
        return Stream.of(
                Arguments.of(
                        "верхняя кнопка",
                        "Александр", "Солженицын", "Московская обл., г. Жуковский, ул. Гагарина, д. 1", "Котельники", "+79166666666", // Секция "Для кого"
                        "12.12.2026", "сутки", "черный", "Не беспокоить"
                ),
                Arguments.of(
                        "нижняя кнопка", // Точка входа
                        "Дон", "Кихот", "Москва, улица Пушкина, дом Колотушкина", "Выхино", "88005553535", // Секция "Для кого"
                        "01.01.2027", "двое суток", "серый", ""
                )
        );
    }

    @ParameterizedTest(name = "Позитивный e2e заказа через кнопку: {0}")
    @MethodSource("orderFlowDataProvider")
    public void testPositiveOrderFlow(
            String entryPoint,
            String name, String surname, String address, String metroStation, String phone,
            String deliveryDate, String rentalPeriod, String color, String comment
    ) {
        ScooterMainPage mainPage = new ScooterMainPage(driver);

        // Выбираем, по какой кнопке начинать процесс
        if ("верхняя кнопка".equalsIgnoreCase(entryPoint)) {
            mainPage.clickTopCreateOrderButton();
        } else {
            mainPage.clickBottomCreateOrderButton();
        }

        ScooterCreateOrderPage orderPage = new ScooterCreateOrderPage(driver);
        orderPage.waitForFirstPageOrder();

        orderPage.fillFirstOrderPageForm(name, surname, address, metroStation, phone);
        orderPage.waitForSecondPageOrder();

        orderPage.fillSecondOrderPageForm(deliveryDate, rentalPeriod, color, comment);

        orderPage.waitForConfirmOrderModal();
        orderPage.clickConfirmYesButton();

        Assertions.assertTrue(orderPage.isOrderSuccessDisplayed(), "Модальное окно успешного заказа не появилось!");
    }

    @Test
    public void testOrderFormFieldsValidationErrors() {
        ScooterMainPage mainPage = new ScooterMainPage(driver);
        mainPage.clickTopCreateOrderButton();

        ScooterCreateOrderPage orderPage = new ScooterCreateOrderPage(driver);
        orderPage.waitForFirstPageOrder();

        orderPage.fillAddress("123");
        orderPage.clickContinueButton();

        Assertions.assertEquals("Введите корректное имя", orderPage.getNameErrorText());
        Assertions.assertEquals("Введите корректное фамилию", orderPage.getSurnameErrorText());
        Assertions.assertEquals("Введите корректный адрес", orderPage.getAddressErrorText());
        Assertions.assertEquals("Выберите станцию", orderPage.getMetroErrorText());
        Assertions.assertEquals("Введите корректный номер", orderPage.getPhoneErrorText());
    }
}