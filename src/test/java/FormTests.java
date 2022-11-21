import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormTests {
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://testautomationpractice.blogspot.com/");
    }
    @Test
    public static void inputExample(){
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Wojtek");

        mySleep();
    }

    @Test
    public static void chooseDate(){
        WebElement dateInput = driver.findElement(By.cssSelector("input#datepicker"));
        dateInput.sendKeys("11/23/2022");
        dateInput.sendKeys(Keys.ESCAPE);

        mySleep();
    }

    @Test
    public static void selectSpeed(){
        WebElement selectElement = driver.findElement(By.id("speed"));
        Select selectSpeed = new Select(selectElement);

//        selectSpeed.selectByVisibleText("Slow");
        selectSpeed.selectByIndex(3);
        mySleep();
    }

    @Test
    public void sendFile(){
        driver.get("https://demoqa.com/automation-practice-form");
        System.out.println(System.getProperty("user.dir"));
        WebElement uploadElement = driver.findElement(By.id("uploadPicture"));

        uploadElement.sendKeys(System.getProperty("user.dir") + "\\myFile.txt");

        mySleep();
    }

    public static void mySleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.close();
    }
}
