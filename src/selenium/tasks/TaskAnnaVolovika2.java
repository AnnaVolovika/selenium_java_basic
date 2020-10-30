package selenium.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    public void checkButtonColors() {
        WebElement yesButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
        WebElement noButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    public void thankyouText() {
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.cssSelector(".w3-panel")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
            }

    public void fillinthewholeform() {

        driver.findElement(By.xpath("//*[@id='fb_name']")).sendKeys("Alex");
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).sendKeys("39");

        driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']")).click();

        driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[1]")).click();

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByIndex(1);

        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys("I like it very much");
    }


    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }
//         "Don't know" is selected in "Genre"
        assertTrue(driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[3]")).isSelected());
//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.cssSelector(".w3-select[name='option'")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.xpath("//*[@id='fb_form']/form/button"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button")).click();
//         check fields are empty or null
        /* List<WebElement> feedbackFields = driver.findElements(By.cssSelector(".description"));
        for (WebElement webelement : feedbackFields) {
            String fieldText = webelement.getText();
            System.out.println(fieldText);
            Assert.assertNull();
            */
        WebElement nameText = driver.findElement(By.id("name"));
            assertNull(nameText.getAttribute("value"));
        WebElement ageText = driver.findElement(By.id("age"));
            assertNull(ageText.getAttribute("value"));
        WebElement languageText = driver.findElement(By.id("language"));
            assertNull(languageText.getAttribute("value"));
        WebElement genderText = driver.findElement(By.id("gender"));
            assertNull(genderText.getAttribute("value"));
        WebElement optionText = driver.findElement(By.id("option"));
            assertNull(optionText.getAttribute("value"));
        WebElement commentText = driver.findElement(By.id("comment"));
            assertNull(commentText.getAttribute("value"));
//                check button colors (green with white letter and red with white letters)
        checkButtonColors();
  }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        fillinthewholeform();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button")).click();
//         check fields are filled correctly
        WebElement nameText = driver.findElement(By.id("name"));
        assertEquals ("Alex", nameText.getText());
        WebElement ageText = driver.findElement(By.id("age"));
        assertEquals("39", ageText.getText());
        WebElement languageText = driver.findElement(By.id("language"));
        assertEquals("English", languageText.getText());
        WebElement genderText = driver.findElement(By.id("gender"));
        assertEquals("male", genderText.getText());
        WebElement optionText = driver.findElement(By.id("option"));
        assertEquals("Good", optionText.getText());
        WebElement commentText = driver.findElement(By.id("comment"));
        assertEquals("I like it very much", commentText.getText());
//         check button colors (green with white letter and red with white letters)
        checkButtonColors();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.xpath("//*[@id='fb_name']")).sendKeys("Alex");
//         click "Send"
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Alex, for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        thankyouText();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        thankyouText();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        fillinthewholeform();
//         click "Send"
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();
//         click "No"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).click();
//         check fields are filled correctly
        WebElement nameField = driver.findElement(By.xpath("//*[@id='fb_name']"));
        String nameText = nameField.getAttribute("value");
        assertEquals("Alex", nameText);

        WebElement ageField = driver.findElement(By.xpath("//*[@id='fb_age']"));
        String ageText = ageField.getAttribute("value");
        assertEquals("39", ageText);

        assertTrue(driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']")).isSelected());

        assertTrue(driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[1]")).isSelected());

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByIndex(1);
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());

        WebElement commentField = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea"));
        String commentText = commentField.getAttribute("value");
        assertEquals("I like it very much", commentText);
    }
}
