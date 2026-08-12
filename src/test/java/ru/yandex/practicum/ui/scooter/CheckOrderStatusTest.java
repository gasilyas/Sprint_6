package ru.yandex.practicum.ui.scooter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckOrderStatusTest extends BasicTestSeleniumConfiguration {

    @Test
    public void testNonExistentOrderShowsErrorMessage() {
        ScooterMainPage mainPage = new ScooterMainPage(driver);

        mainPage.clickCheckStatusButton();
        mainPage.fillOrderNumber("9999999999999");
        mainPage.clickGoButton();

        ScooterOrderStatusPage statusPage = new ScooterOrderStatusPage(driver);

        Assertions.assertTrue(statusPage.isOrderNotFoundMessageDisplayed(),
                "Сообщение 'Такого заказа нет' не появилось для некорректного номера!");
    }
}