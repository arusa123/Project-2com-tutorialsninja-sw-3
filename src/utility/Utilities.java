package utility;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utilities extends BaseTest {
    /**
     * This method will click on element
     */
    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    /**
     * This method will send text to element
     */
    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    /**
     * this method will send elements
     */

    public void sendTextToElements(By by, int size) {
        driver.findElements(by).size();
    }


    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    /**
     * this method will hover the mouse on the element
     */
    public void mouseHoverOnElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();

    }

    //this method will hover and click mouse on the element
    public void mouseClickOnElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().perform();
    }

    //to verify the text element
    public void verifyText(By by, String expectedText) {
        String actualText = driver.findElement(by).getText();
        Assert.assertEquals("verify the text", expectedText, actualText);
    }


//************************* Alert Methods *****************************************************
// Homework Create 5 methods

    //this method will switch to alert
    public void switchToAlert() {
        driver.switchTo().alert();

    }

    //this method will accept alert
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    //this method will dismiss alert
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    //this method will get text from alert
    public String getTextFromAlert() {
        return driver.switchTo().alert().getText();
    }

    // this method will send text to alert
    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);

    }


    //*************************** Select Class Methods ***************************************//
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        // Select by visible text
        select.selectByVisibleText(text);
    }

    //select by value
    public void selectByValueFromDropDown(By by, String value) {
        new Select(driver.findElement(by)).selectByValue(value);

    }

    //select by index from drop down
    public void selectByIndexFromDropDown(By by, int index) {
        new Select(driver.findElement(by)).selectByIndex(index);
    }

}


