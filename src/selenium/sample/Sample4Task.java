package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";
    String link1_url = "https://kristinek.github.io/site/examples/link1";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        driver.findElement(By.id("link1")).click();
        //        check that current url is not base_url
       assertFalse(driver.getCurrentUrl().equals(base_url));
//        verify that current url is homepage
       assertEquals(link1_url, driver.getCurrentUrl());
//  I would consider the homepqge being:
        // String home_url = "https://kristinek.github.io/site/"
        // assertEquals(home_url, driver.getCurrentUrl());
// in this case test fails, because link1 page is not really the homepage
    }

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number"
       WebElement number = driver.findElement(By.id("number"));
       String newNumber = "4";
       number.sendKeys(newNumber);
//        check that button is not clickable "Clear Result"
       WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
       assertFalse(clearButton.isEnabled());
//        check that text is not displayed
       WebElement resultNumber = driver.findElement(By.id("result_number"));
       assertFalse (resultNumber.isDisplayed());
//        click on "Result" button
       WebElement resultButton = driver.findElement(By.id("result_button_number"));
       resultButton.click();
//        check that text is displayed
// we already defined, WebElement resultNumber = driver.findElement(By.id("result_number"));
       assertTrue (resultNumber.isDisplayed());
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
       String secondNumber = "45";
       assertTrue(resultNumber.getText().contains(secondNumber));
//        check that the button "Clear Result" is clickable now
// was already defined previously WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
       assertTrue(clearButton.isEnabled());
//        click on "Clear Result"
       clearButton.click();
//        check that the text is still (""), but it is not displayed
       String expected = "";
       String actual = resultNumber.getText();
       assertEquals(expected, actual);
       assertFalse(resultNumber.isDisplayed());
    }



}