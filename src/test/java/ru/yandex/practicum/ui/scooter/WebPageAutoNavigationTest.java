package ru.yandex.practicum.ui.scooter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class WebPageAutoNavigationTest extends BasicTestSeleniumConfiguration {

    @Test
    public void testScooterLogoRedirectsToMainPage() {
        ScooterMainPage mainPage = new ScooterMainPage(driver);

        // Сначала уйдем на страницу заказа, чтобы проверить возврат
        mainPage.clickTopCreateOrderButton();

        // Кликаем по логотипу Самоката
        mainPage.clickScooterLogo();

        // Проверяем, что вернулись на главную
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://qa-scooter.praktikum-services.ru/", currentUrl);
    }

    @Test
    public void testYandexLogoOpensYandexInNewWindow() {
        ScooterMainPage mainPage = new ScooterMainPage(driver);

        String originalWindow = driver.getWindowHandle();

        mainPage.clickYandexLogo();

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("yandex") || currentUrl.contains("dzen"),
                "Новая вкладка не ведет на ресурсы Яндекса! Текущий URL: " + currentUrl);

        driver.close();
        driver.switchTo().window(originalWindow);
    }
}