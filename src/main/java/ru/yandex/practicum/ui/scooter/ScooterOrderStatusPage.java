package ru.yandex.practicum.ui.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScooterOrderStatusPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Сообщение о ненайденном заказе
    private By orderNotFoundMessage = By.xpath("//*[contains(text(), 'Такого заказа нет')]");


    public ScooterOrderStatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public Boolean isOrderNotFoundMessageDisplayed() {

        try {
            WebElement message = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(orderNotFoundMessage)
            );
            return message.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
