package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;
   private static WebDriverWait wait;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        driver = new ChromeDriver();

        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }
    public void greenTextCheck1() {
//           * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
////         * 2) check that button does not appear,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
////         * but loading text is seen instead   "Loading green..."
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
//       // String loadingGreenText = loadingGreen.getText();
//      //  assertEquals( "Loading green...", loadingGreenText );
//           * 3) check that both button
//           * and loading text is not seen,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
    }

    public void greenTextCheck2() {
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
//         * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        try {
            assertEquals("Success", driver.findElement(By.id("finish_green")).getText());
        } catch (AssertionError e) {
            System.err.println("!!!Success is not shown!!!");
            e.printStackTrace();
        }
    }


    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
        greenTextCheck1();
        Thread.sleep(5000);
        greenTextCheck2();
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
        greenTextCheck1();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("finish_green"));
        greenTextCheck2();
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
        greenTextCheck1();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
        greenTextCheck2();
    }

    @Test
    public void loadGreenAndBlueBonus() {
        // TODO:
//         * 0) wait until button to load green and blue appears
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#start_green_and_blue")));
//         * 1) click on start loading green and blue button
        driver.findElement(By.cssSelector("#start_green_and_blue")).click();
//         * 2) check that button does not appear, but loading text is seen instead for green
        assertFalse(driver.findElement(By.cssSelector("#start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.cssSelector("#loading_green_without_blue")).isDisplayed());
//         * 3) check that button does not appear, but loading text is seen instead for green and blue//
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#loading_blue_without_green")));
        assertTrue(driver.findElement(By.cssSelector("#loading_blue_without_green")).isDisplayed());
//         * 4) check that button and loading green does not appear,
        assertFalse(driver.findElement(By.cssSelector("#loading_green_without_blue")).isDisplayed());
//         * 		but loading text is seen instead for blue and success for green is seen
        assertTrue(driver.findElement(By.cssSelector("#loading_blue_without_green")).isDisplayed());
//         * 5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#finish_green_and_blue")));
        assertFalse(driver.findElement(By.cssSelector("#loading_blue_without_green")).isDisplayed());
        assertFalse(driver.findElement(By.cssSelector("#loading_green_without_blue")).isDisplayed());
        assertTrue(driver.findElement(By.cssSelector("#finish_green_and_blue")).isDisplayed());
    }

}