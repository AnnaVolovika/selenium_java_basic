package selenium.sample;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        WebDriver driver = new ChromeDriver();

//         go to https://kristinek.github.io/site/index2.html
         driver.get("https://google.com");
         driver.get("https://kristinek.github.io/site/index2.html");
//
//         get title of page
        System.out.println(driver.getTitle());

//         get URL of current page
        System.out.println(driver.getCurrentUrl());


//         close browser
        driver.quit();

        //open test homepage

 //       System.out.println(driver.findElement(By.id("h1")).getText());
    }
}

