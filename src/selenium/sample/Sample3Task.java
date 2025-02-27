package selenium.sample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.WebElementHandler;

import java.util.List;

import static org.junit.Assert.*;

public class Sample3Task {
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
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
//         check how many element with class "test" there are on page (5)
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("text")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);
//        check that value of second button is "This is also a button"
        String expected = "This is also a button";
        String actual = driver.findElement(By.name("randomButton2")).getAttribute("value");
        Assert.assertEquals(expected, actual);
    }

    @Test
        public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
          String expected = "this is Also a Button";
          String actual = driver.findElement(By.name("randomButton2")).getAttribute("value");
          Assert.assertEquals(expected, actual);
//         fail with custom error message:
//         fail("I want this test to fail, so will!");

//       assertTrue(driver.findElement(By.name("randomButton2")).getAttribute("value").equals("this is Also a Button"));
//       assertTrue(true);
        }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        assertFalse(driver.findElement(By.name("randomButton2")).getAttribute("value").equals("This is a button"));
        assertFalse(false);
        }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190

        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));

        for (WebElement elementWithClass : allElementsWithClass) {
            String element = elementWithClass.getText();
            String word = "1";
            if (element.contains(word)) {
                fail("I want this test to fail, so will!");
                break;
            }


    //assertFalse(element.contains("190"));

           // System.out.println(element);

        }
    }


}
