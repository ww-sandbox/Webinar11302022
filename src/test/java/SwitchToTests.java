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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void switchToIframeTest(){
        driver.get("https://testautomationpractice.blogspot.com/");
//        driver.findElement(By.cssSelector("input#RESULT_TextField-1"));
//        przed przełączeniem element jest 'niewidoczny' dla drivera

        WebElement formFrame = driver.findElement(By.cssSelector("iframe#frame-one1434677811"));
        driver.switchTo().frame("frame-one1434677811");
//        driver.switchTo().frame(formFrame);
//        dwa sposobny na przełączenie do iframe'a
        WebElement label = driver.findElement(By.xpath("//label[text()='First Name']"));
        driver.findElement(with(By.cssSelector("input"))
                .below(label))
                .sendKeys("Wojtek");
//        lokalizacja elementu wewnątrz ramki z wykorzystaniem lokalizatorów relatywnych oraz wysłanie tekstu do
//        wyszukanego elementu

        driver.switchTo().parentFrame();
//        przełączenie do ramki bazowej
        driver.findElement(By.xpath("//button[text()=\"Click Me\"]"));
    }

    @Test
    public void switchToAlert(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[contains(., 'JS Alert')]")).click();

        Alert alert = driver.switchTo().alert();
//        alert.accept();
        alert.dismiss();
        WebElement message = driver.findElement(By.cssSelector("#result"));
        Assert.assertEquals(message.getText(),"You successfully clicked an alert");
    }

    @Test
    public void switchToTab(){
        driver.get("https://the-internet.herokuapp.com/windows");
        String originalWindow = driver.getWindowHandle();
//        Pobranie uchwytu (identyfikatora) okna domyślnego/bazowego

        Assert.assertEquals(driver.getWindowHandles().size(), 1);
        Assert.assertEquals(driver.getTitle(), "The Internet");
//        upewnienie sie, że otwarte jest tylko jedno okno z określonym tytułem

        driver.findElement(By.linkText("Click Here")).click();
//        kliknięcie przycisku otwierającego nowe okno

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//        oczekiwanie na otworzenie nowego okna

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
//        iteracja po kolejnych uchwytach (identyfikatorach) okien w celu odnalezienia uchwytu nowego okna oraz
//        przełączenie do niego

        wait.until(ExpectedConditions.titleIs("New Window"));
//        oczekiwanie na poprawne załadowanie tytułu nowego okna

        driver.close();
//        zamknięcie aktualnego okna (otwartego w trakcie testu)
        driver.switchTo().window(originalWindow);
//        przełączenie do pierwotnego okna - bez tego kroku pozostaniemy w zamkniętym oknie - driver będzie odpowiadał
//        błędem
        Assert.assertEquals(driver.getTitle(), "The Internet");

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
