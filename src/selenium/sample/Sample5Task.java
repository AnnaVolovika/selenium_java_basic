package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Sample5Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/alerts_popups";
    String alerted_url = "https://kristinek.github.io/site/examples/alerted_page";

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
    public void goToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        WebElement firstButton = driver.findElement(By.className("w3-blue"));
        firstButton.click();
//        switch to alert
        Alert alert1 = driver.switchTo().alert();
//        click ok
        alert1.accept();
//        switch to second alert
        Alert alert2 = driver.switchTo().alert();
//        verify alert text
        assertEquals("Booooooooo!", alert2.getText());
//       click ok on second alert
        alert2.accept();
//        verify that the correct page is opened
        assertTrue(driver.getCurrentUrl().equals(alerted_url));
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        WebElement firstButton = driver.findElement(By.className("w3-blue"));
        firstButton.click();
//        switch to alert
        Alert alert1 = driver.switchTo().alert();
//        click cancel
        alert1.dismiss();
//        verify the text on page
        String expected = "So you desided to say? Good!";
        String actual = driver.findElement(By.id("textForAlerts")).getText();
        assertEquals(expected, actual);
    }
}
