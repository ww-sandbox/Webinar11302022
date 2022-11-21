import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HomePageTests {
    @Test
    public static void openHomePage(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://sampleshop.inqa.pl");

        driver.close();
    }
}
