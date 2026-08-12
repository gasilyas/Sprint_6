package ru.yandex.practicum.ui.scooter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BasicTestSeleniumConfiguration {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {

        String browser = System.getProperty("browser", "chrome").toLowerCase();
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);

        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.get("https://qa-scooter.praktikum-services.ru/");
        ScooterMainPage mainPage = new ScooterMainPage(driver);

        //Закрываем инф.панель о куках
        mainPage.acceptCookiesIfVisible();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}