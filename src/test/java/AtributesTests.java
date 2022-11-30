import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AtributesTests {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }

    @Test
    public void checkFontColor(){
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement employe1Element = driver.findElement(By.xpath("//employee[@id='1']/name"));
        String elementColor = employe1Element.getCssValue("color");

        String convertedColor = Color.fromString(elementColor).asHex();

        Assert.assertEquals(convertedColor, "#ff0000");
    }

    @Test
    public void checkFontFace(){
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement employe1Element = driver.findElement(By.xpath("//employee[@id='1']/name"));
//        sprawdz czy podstawowa czcionka elementu jest prawidłowa
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
