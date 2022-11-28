import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitTests {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("div.progress-bar"), "aria-valuenow", "100"));
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
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
