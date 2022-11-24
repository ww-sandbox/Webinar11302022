import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorsExamples {
    @Test
    public void firstLocators() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.tagName("input"));
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
//        metoda która wyszukuje wszystkie elementy spełniające dany lokalizator. W tym wypadku są to wszystkie elementy
//        typu input. W odróżnieniu od metody .findElement, która zwraca tylko pierwszy element spełniający dany lokalizator
        System.out.println(inputs.size());

        driver.findElement(By.cssSelector("input"));
        driver.findElement(By.xpath("//input"));
//        powyższe dwie linijki realizują ten sam kod i odnajdują pierwszy element typu 'input'
        driver.findElement(By.cssSelector("#dateOfBirthInput"));
        driver.findElement(By.xpath("//input[@id=\"dateOfBirthInput\"]"));
//        powyższe dwie linijki realizują ten sam kod i odnajdują pierwszy element typu 'input' od id 'dateOfBirthInput

//        driver.findElement(By.xpath("//input[@class='react-datepicker-ignore-onclickoutside']"));
        driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']/input[contains(@class, 'form-control')]")).click();
        driver.findElement(By.xpath("//input[contains(@class, \"react-datepicker-ignore-onclickoutside\")]"));
//        TODO : analiza non found - podczas zajęć nie mogłem zlokalizować powyższego elementu. Po analizie odnalazłem
//        nieprawidłowość. Wyszukiwana klasa pojawia się dopiero po pierwszym kliknięciu w datepicker i rozwinięciu
//        kalendarza. Po dodaniu jednej linijki wyżej z odpowiednim klikiem wszystko działa poprawnie.

        driver.quit();

    }
}
