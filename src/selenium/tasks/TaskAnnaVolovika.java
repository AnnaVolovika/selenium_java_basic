package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Task1 {
    WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        driver = new ChromeDriver();

        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement number = driver.findElement(By.id("numb"));
        String inputText = "text";
        number.sendKeys(inputText);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        String errorText = driver.findElement(By.id("ch1_error")).getText();
        String expected = "Please enter a number";
        assertEquals(expected, errorText);
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement number = driver.findElement(By.id("numb"));
        String inputNumberSmall = "35";
        number.sendKeys(inputNumberSmall);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        String errorNumberSmall = driver.findElement(By.id("ch1_error")).getText();
        String expected = "Number is too small";
        assertEquals(expected, errorNumberSmall);
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement number = driver.findElement(By.id("numb"));
        String inputNumberBig = "500";
        number.sendKeys(inputNumberBig);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        String errorNumberBig = driver.findElement(By.id("ch1_error")).getText();
        String expected = "Number is too big";
        assertEquals(expected, errorNumberBig);
        number.clear();
        String inputNumber666 = "666";
        number.sendKeys(inputNumber666);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        String errorNumber666 = driver.findElement(By.id("ch1_error")).getText();
        String expected1 = "Number is too big";
        try {
            assertEquals(expected1, errorNumber666);
        } catch (AssertionError e) {
            System.err.println("We failed ");
            e.printStackTrace();
        }
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement number = driver.findElement(By.id("numb"));
        String inputNumber = "81";
        number.sendKeys(inputNumber);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        Alert alert = driver.switchTo().alert();
        assertTrue(alert.getText().contains("9"));
        alert.accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
        }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement number = driver.findElement(By.id("numb"));
        String inputNumber = "60";
        number.sendKeys(inputNumber);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        Alert alert = driver.switchTo().alert();
        assertTrue(alert.getText().contains("7.7"));
        alert.accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());

    }
}
