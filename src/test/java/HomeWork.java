import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomeWork {

    @Test
    public void hwYahoo(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://testautomationpractice.blogspot.com/");

        WebElement browsersSelect = driver.findElement(By.id("products"));
        Select selectProducts = new Select(browsersSelect);

        List<WebElement> values = selectProducts.getOptions();
        boolean result = false;
        for (WebElement el: values) {
            System.out.println("Aktualny tekst to " + el.getText());
            if(el.getText().equals(("Yahoo"))) {
                result = true;
            }
        }
        Assert.assertTrue(result);
        driver.quit();
    }
}
