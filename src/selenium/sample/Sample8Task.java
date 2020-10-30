package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/po");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void styleChecks() throws Exception {
//         TODO:
//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);
    WebElement headerPink = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div"));
        assertEquals("rgba(255, 221, 221, 1)", headerPink.getCssValue("background-color"));
    WebElement headerYellow = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div"));
        assertEquals("rgba(255, 255, 204, 1)", headerYellow.getCssValue("background-color"));
//        check h2 elements font-size 30px
    WebElement h2P = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/a"));
       assertEquals("30px", h2P.getCssValue("font-size"));
    WebElement h2Y = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/h2"));
        assertEquals("30px", h2Y.getCssValue("font-size"));
//        check h1 element font-size 64px
    WebElement h1 = driver.findElement(By.xpath("//h1"));
         assertEquals("64px", h1.getCssValue("font-size"));
    }
}
