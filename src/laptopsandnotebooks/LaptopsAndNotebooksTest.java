package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utility.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utilities {
    @Before
    public void browserSetUp() {
        openBrowser("Chrome", "https://tutorialsninja.com/demo/index.php");
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
//        1.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[2]"));

//        1.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));

//        1.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");

//        1.4 Verify the Product price will arrange in High to Low order.
        List<WebElement> listOrder = driver.findElements(By.xpath("//div[@id='content']"));
        ArrayList<String> originalPriceList = new ArrayList<>();
        for (WebElement e : listOrder) {
            originalPriceList.add(e.getText());
        }
        System.out.println(originalPriceList);
        Collections.sort(originalPriceList);
        System.out.println(originalPriceList);

//         *Verify the products price display in
//        Low to High
        listOrder = driver.findElements(By.xpath("//div[@id='content']"));
        ArrayList<String> listAfterPriceSort = new ArrayList<>();
        for (WebElement e : listOrder) {
            listAfterPriceSort.add(e.getText());
        }
        System.out.println(listAfterPriceSort);

        Assert.assertEquals("Price is not in Low to high", originalPriceList, listAfterPriceSort);
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {

//        2.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[2]"));

//        2.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));

//        2.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");

//        2.4 Select Product “MacBook”
        clickOnElement(By.xpath("//a[normalize-space()='MacBook']"));

//        2.5 Verify the text “MacBook”
        verifyText(By.linkText("MacBook"), "MacBook");

//        2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

//        2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        verifyText(By.xpath("//div[@class='alert alert-success alert-dismissible']"), "Success: You have added MacBook to your shopping cart!\n" +
                "×");
//        2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

//        2.9 Verify the text "Shopping Cart"
        verifyText(By.xpath("//h1[contains(text(),'Shopping Cart')]"), "Shopping Cart  (0.00kg)");
//        2.10 Verify the Product name "MacBook"
        verifyText(By.linkText("MacBook"), "MacBook");

//        2.11 Change Quantity "2"
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[4]/div/input")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[4]/div/input")).sendKeys("2");

//        2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//button[@type='submit']"));

//        2.13 Verify the message “Success: You have modified your shopping cart!”
        verifyText(By.xpath("//div[@class='alert alert-success alert-dismissible']"), "Success: You have modified your shopping cart!\n" +
                "×");
//        2.14 Verify the Total £737.45
        //change the currency
        clickOnElement(By.xpath("//span[normalize-space()='Currency']"));
        Thread.sleep(1000);
        clickOnElement(By.xpath("(//button[@class='currency-select btn btn-link btn-block'])[2]"));
        verifyText(By.xpath("//tbody//tr//td[6]"), "£737.45");

//        2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));

//        2.16 Verify the text “Checkout”
        verifyText(By.linkText("Checkout"), "Checkout");

//        2.17 Verify the Text “New Customer”
        Thread.sleep(1000);
        verifyText(By.xpath("//h2[contains(text(),'New Customer')]"), "New Customer");

//        2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));

//        2.19 Click on “Continue” tab
        clickOnElement(By.id("button-account"));

//        2.20 Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='input-payment-firstname']"), "Mike");
        sendTextToElement(By.xpath("//input[@id='input-payment-lastname']"), "Potato");
        sendTextToElement(By.xpath("//input[@id='input-payment-email']"), "mikep@gmail.com");
        sendTextToElement(By.xpath("//input[@id='input-payment-telephone']"), "020835676");
        sendTextToElement(By.xpath("//input[@id='input-payment-address-1']"), "10 Angel road");
        sendTextToElement(By.xpath("//input[@id='input-payment-city']"), "London");
        sendTextToElement(By.xpath("//input[@id='input-payment-postcode']"), "HA4 5TH");
        clickOnElement(By.xpath("//option[contains(text(),'United Kingdom')]"));
        clickOnElement(By.xpath("//option[contains(text(),'Greater London')]"));

//        2.21 Click on “Continue” Button
        clickOnElement(By.xpath("//input[@id='button-guest']"));
//        2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//div[@class='panel-group']/div[3]/div[2]/div[1]/p[2]/textarea[1]"), "Please Delivery it Safely");
//        2.23 Check the Terms & Conditions check box
        clickOnElement(By.name("agree"));
//        2.24 Click on “Continue” button
        clickOnElement(By.id("button-payment-method"));

//        2.25 Verify the message “Warning: Payment method required!”
        verifyText(By.xpath("//div[@class='alert alert-danger alert-dismissible']"), "Warning: Payment method required!\n" +
                "×");
    }

    @After
    public void browserdown() {
        closeBrowser();

    }

}

