import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitTests {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        Czas przez jaki driver będzie oczekiwał podczas wyszukiwania każdego elementu zanim zwróci błąd
        driver.manage().window().maximize();
    }

    @Test
    public void implicitlyWaitTest(){
        driver.get("https://demoqa.com/dynamic-properties");
        driver.findElement(By.cssSelector("button#visibleAfter")).click();
    }

    @Test
    public void explicitWaitTest(){
        driver.get("https://demoqa.com/progress-bar");
        driver.findElement(By.cssSelector("button#startStopButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        Deklaracja waita wraz z maksymalnym czasem, który będzie on oczekiwał na spełnienie określonego warunku
//        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("div.progress-bar"), "aria-valuenow", "100"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#resetButton")));
//        deklaracja warunku na jaki będzie oczekiwał driver zanim zostanie rzucony TimeoutException. Najczęściej
//        wykorzystuje się, dedykowaną dla Selenium, bibliotekę ExpectedConditions
//        W powyższych przykładach oczekiwanie na odpowiednią wartośc atrybutu webelementu oraz na pojawienie się w DOM
//        webelementu spełniający dany lokalizator
    }

    @Test
    public void fluentWaitTest(){
        driver.get("https://demoqa.com/progress-bar");
        driver.findElement(By.cssSelector("button#startStopButton")).click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        WebElement resetButton = wait.until(driver -> {return driver.findElement(By.cssSelector("#resetButton"));});
//        Fluent wait jest najbardziej elastycznym roadzajem waita, ale jest dość skomplikowany i mniej czytelny.
//        Zdecydowaną większość przypadków można zrealizować z wykorzystaniem Explicit waita
    }

    @Test
    public void dynamicLoadingTest(){
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("#start button")).click();
//        wyszukiwanie i kliknięcie w przycis uruchamiający loader

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        deklaracja waita
        WebElement loadingBar = driver.findElement(By.cssSelector("div#loading"));
//        deklaracja elementu reprezentującego pasek ładowania
        wait.until(ExpectedConditions.visibilityOf(loadingBar));
//        oczekiwanie na widoczność paska ładowania (wcześniej obecny w DOM)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        zmiana parametrów waita
        wait.until(ExpectedConditions.invisibilityOf(loadingBar));
//        oczekiwanie na zniknięcie paska ładowania

        String finalMessage = driver.findElement(By.cssSelector("#finish h4")).getText();
        Assert.assertEquals(finalMessage, "Hello World!");
//        Sprawdzenie czy pojawia się odpowiedni tekst na koniec ładowania
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
