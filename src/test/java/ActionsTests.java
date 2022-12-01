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
        WebElement secondAvatar = driver.findElement(By.cssSelector(".figure:nth-of-type(2)"));
        //        WebElement secondAvatar = driver.findElement(By.xpath("//div[@class='figure'][2]"));
//        dwie linijki które pozwalają zlokalizować ten sam element - 'kontener'(div) konkretnego awatara na stronie

        WebElement secondAvatarDesc = secondAvatar.findElement(By.xpath("./div[@class='figcaption']"));
//        wyszukując już w ramach konkretnego awatara wyszukujemy elementu z jego opisem

//        secondAvatarDesc.findElement(By.tagName("a")).click();

        Actions action = new Actions(driver);
//        tworzymy obiekt łańcucha akcji

        action.moveToElement(secondAvatar).perform();
//        przesuwamy mysz nad wybrany avatar aby pojawił się jego opis

//        WebElement secondAvatarDesc = secondAvatar.findElement(By.xpath("./div[@class='figcaption']"));
        Assert.assertTrue(secondAvatarDesc.isDisplayed());
//        Sprawdzamy czy opis jest wyświetlony

        Assert.assertEquals(secondAvatarDesc.findElement(By.cssSelector("h5")).getText(), "name: user2");
//        secondAvatarDesc.findElement(By.tagName("a")).click();
        action.pause(Duration.ofSeconds(3)).perform();
    }

    @Test
    public void menuTest(){
        driver.get("https://demoqa.com/menu");
        WebElement menuElement = driver.findElement(By.cssSelector("ul#nav"));

        WebElement menuItem2 = menuElement.findElement(By.xpath(".//a[text()='Main Item 2']"));
        WebElement subMenuItem = menuElement.findElement(By.xpath(".//a[text()='SUB SUB LIST »']"));
        WebElement subMenuSection = menuElement.findElement(By.xpath("//ul[li/a[text()='Sub Sub Item 1']]"));
//        deklarujemy poszczególne elementy menu niezbędne podczas testu. Dzięki temu, że są one od początku dostępne
//        w kodzie możemy je zdefiniować wcześniej, a później na nich przeprowadzać test. Gdyby elementy nie istniały
//        w DOM, a były tworzone po interakcjach, należałoby wyszukiwać elementy w trakcie akcji. Wcześniej moglibyśmy
//        zadeklarować jedynie lokalizatory - obiekty typu By

        Actions menuAction = new Actions(driver);
        menuAction.moveToElement(menuItem2).pause(Duration.ofMillis(500)).moveToElement(subMenuItem)
                .pause(Duration.ofMillis(500)).perform();

        Assert.assertTrue(subMenuSection.isDisplayed());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
