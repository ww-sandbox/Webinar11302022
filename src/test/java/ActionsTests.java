import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActionsTests {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }

    @Test
    public void clickAction(){
        driver.get("https://demoqa.com/tool-tips");

        Actions action = new Actions(driver);
        action.click(driver.findElement(By.cssSelector("#toolTipButton"))).perform();
//      upewnij się, ze po najechaniu wyświetlany jest tooltip (element zmienia stan)
    }

    @Test
    public void hoverAvatar(){
        driver.get("https://the-internet.herokuapp.com/hovers");
        Actions action = new Actions(driver);
        WebElement secondAvatar = driver.findElement(By.cssSelector(".figure:nth-of-type(2)"));
//        WebElement secondAvatar = driver.findElement(By.xpath("//div[@class='figure'][2]"));
        action.moveToElement(secondAvatar).perform();

        WebElement secondAvatarDesc = secondAvatar.findElement(By.xpath("./div[@class='figcaption']"));
        Assert.assertTrue(secondAvatarDesc.isDisplayed());
        Assert.assertEquals(secondAvatarDesc.findElement(By.cssSelector("h5")).getText(), "name: user2");

        action.pause(Duration.ofSeconds(3)).perform();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
