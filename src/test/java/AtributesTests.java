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
        WebElement employe1NameElement = driver.findElement(By.xpath("//employee[@id='1']/name"));
        String elementColor = employe1NameElement.getCssValue("color");

        String convertedColor = Color.fromString(elementColor).asHex();
//        kolor jest pobierany z drivera w formacie rgb, dlatego musimy go przekonwertować do format szesnastkowego

        Assert.assertEquals(convertedColor, "#ff0000");
    }

    @Test
    public void checkFontFace(){
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement employe1Element = driver.findElement(By.cssSelector("#Text1 div.widget-content:nth-of-type(1) span"));
        String ff = employe1Element.getText();
//        sprawdz czy podstawowa czcionka elementu jest prawidłowa
        String fontFamily = employe1Element.getCssValue("font-family");
        String  selectedFontFamily = fontFamily.split(",")[1];
        Assert.assertTrue(fontFamily.startsWith("Georgia"));
//        sprawdzanie czy podstawowa czcionka (ta która jest wykorzystana w pierwszej kolejności) jest taka jak oczekujemy
        Assert.assertEquals(selectedFontFamily, "serif");
//        w linijce 43 możemy kontrolować którą z kolei czcionkę sprawdzamy
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
