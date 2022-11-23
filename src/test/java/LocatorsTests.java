import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class LocatorsTests {
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    public static void cssSelectors(){
        driver.findElement(By.cssSelector("div.widget"));

        driver.quit();
    }

    @Test
    public static void selectPersonFromTable(){
        driver.findElement(By.xpath("//td[text()='fbach@yahoo.com']"));

        driver.quit();
    }

    @Test
    public static void selectPersonRowFromTable(){
        String selector = "//tr[td[contains(., 'fbach')]]";
        driver.findElement(By.xpath(selector));

        driver.quit();
    }
}
