import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.locators.RelativeLocator.with;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class SwitchToTests {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void switchToIframeTest(){
        driver.get("https://testautomationpractice.blogspot.com/");
//        driver.findElement(By.cssSelector("input#RESULT_TextField-1"));
        driver.switchTo().frame("frame-one1434677811");
        WebElement label = driver.findElement(By.xpath("//label[text()='First Name']"));
        driver.findElement(with(By.cssSelector("input"))
                .below(label))
                .sendKeys("Wojtek");

        driver.switchTo().parentFrame();
    }

    @Test
    public void switchToAlert(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[contains(., 'JS Alert')]")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
        WebElement message = driver.findElement(By.cssSelector("#result"));
        Assert.assertEquals(message.getText(),"You successfully clicked an alert");
    }

    @Test
    public void switchToTab(){
        driver.get("https://the-internet.herokuapp.com/windows");
        String originalWindow = driver.getWindowHandle();

        Assert.assertEquals(driver.getWindowHandles().size(), 1);
        Assert.assertEquals(driver.getTitle(), "The Internet");

        driver.findElement(By.linkText("Click Here")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.titleIs("New Window"));

        driver.close();
        driver.switchTo().window(originalWindow);
        Assert.assertEquals(driver.getTitle(), "The Internet");

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
