import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ScreenShotsTests {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void elementScreenShot(){
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName"));
        firstNameInput.sendKeys("Wojtek");

        File source = firstNameInput.getScreenshotAs(OutputType.FILE);
//        zrzut ekranu z konkretnego elementu to nowość wprowadzona w 4 wersji Selenium. Wcześniej aby uzyskać
//        taki zrzut potrzebne były dodatkowe biblioteki
        File dest = new File(System.getProperty("user.dir") +    "/target/screenshots/elementLogo.png");

        try {
            FileUtils.copyFile(source, dest);
//            FileHandler.copy(source, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void pageScreenShot(){
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName"));
        firstNameInput.sendKeys("Wojtek");

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        zapisujemy zrzut części ekranu strony w przeglądarce do pliku
        File dest = new File(System.getProperty("user.dir") + "/target/screenshots/image.png");
//        tworzymy plik wyjściowy do którego trafią dane
        try {
            FileUtils.copyFile(scrFile, dest);
//            kopiujemy zrzut do pliku wyjściowego
//            dwie poniższe linijki wykonują to samo kopiowanie. Jednak w tym przypadku musimy dodatkowo stworzyć
//            folder na plik wynikowy jeśli nie istnieje. FileHandler to część całej paczki Selenium, natomiast
//            FileUtils to część commons-io od Apache. Zawsze należy zdecydować czy chcemy ograniczyć ilość paczek
//            w projekcie, czy postawić na prostszy kod.

//            FileHandler.createDir(new File(System.getProperty("user.dir") + "/target/screenshots"));
//            FileHandler.copy(scrFile, new File(System.getProperty("user.dir") + "/target/screenshots/image.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
