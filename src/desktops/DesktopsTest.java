package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utilities {
    @Before
    public void browserSetUp() {
        openBrowser("Chrome", "https://tutorialsninja.com/demo/index.php");
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {

//        1.1 Mouse hover on Desktops Tab.and click
        mouseHoverOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]"));
//        1.2 Click on “Show All Desktops”
        clickOnElement(By.linkText("Show AllDesktops"));

        List<WebElement> listOfProducts = driver.findElements(By.xpath("//div[@class='col-sm-9']/div[4]/div/div/div[2]/div/h4/a"));
        ArrayList<String> listBeforeSorting = new ArrayList<>();
        for (WebElement e: listOfProducts) {
            listBeforeSorting.add(e.getText());
        }
        System.out.println(listBeforeSorting);
        Collections.reverse(listBeforeSorting);
        System.out.println(listBeforeSorting);


//        1.3 Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (Z - A)");
        Thread.sleep(500);
        listOfProducts = driver.findElements(By.xpath("//div[@class='col-sm-9']/div[4]/div/div/div[2]/div/h4/a"));
        ArrayList<String> listAfterSorting = new ArrayList<>();
        for (WebElement e: listOfProducts) {
            listAfterSorting.add(e.getText());
        }

        System.out.println(listAfterSorting);

//        1.4 Verify the Product will arrange in Descending order.
        Assert.assertEquals("List is not sorted in Z-A", listBeforeSorting, listAfterSorting);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        Thread.sleep(1000);

//        2.1 Mouse hover on Desktops Tab. and click
        mouseClickOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]"));
//        2.2 Click on “Show All Desktops”
        clickOnElement(By.linkText("Show AllDesktops"));

//        2.3 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");

//        2.4 Select product “HP LP3065”
        clickOnElement(By.xpath("//a[contains(text(),'HP LP3065')]"));

//        2.5 Verify the Text "HP LP3065"
        verifyText(By.xpath("//h1[normalize-space()='HP LP3065']"), "HP LP3065");
//        2.6 Select Delivery Date "2022-11-30"
        String year = "2022";
        String month = "November";
        String date = "30";
        clickOnElement(By.xpath("//body/div[@id='product-product']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/span[1]/button[1]/i[1]"));

        while (true) {
            String monthYear = driver.findElement(By.xpath("//div[@class='datepicker']/div[1]/table/thead/tr[1]/th[2]")).getText();

            //Splitting year and month Nov 2022
            String arr[] = monthYear.split(" ");
            //Actual dates
            String mon = arr[0];
            String yer = arr[1];

            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker']/div[1]/table/thead/tr[1]/th[3]"));
            }

        }
        //Select Date
        List<WebElement> allDates = driver.findElements(By.xpath("//div[@class='datepicker']//div//table//td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }
//        2.7.Enter Qty "1” using Select class.
        // selectByVisibleTextFromDropDown(By.name("quantity"),"value");

//        2.8 Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));


//        2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Thread.sleep(1000);
        verifyText(By.xpath("//div[@class='alert alert-success alert-dismissible']"), "Success: You have added HP LP3065 to your shopping cart!\n" +
                "×");
//        2.10 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

//        2.11 Verify the text "Shopping Cart"
        verifyText(By.xpath("//h1[contains(text(),'Shopping Cart')]"), "Shopping Cart  (1.00kg)");

//        2.12 Verify the Product name "HP LP3065"
        verifyText(By.linkText("HP LP3065"), "HP LP3065");

//        2.13 Verify the Delivery Date "2022-11-30"
        verifyText(By.xpath("//small[normalize-space()='Delivery Date:2022-11-30']"), "Delivery Date:2022-11-30");

//        2.14 Verify the Model "Product21"
        verifyText(By.xpath("//td[normalize-space()='Product 21']"), "Product 21");

//        2.15 Verify the Total "£74.73" convert to pounds from dollars

        clickOnElement(By.xpath("//span[normalize-space()='Currency']"));
        Thread.sleep(1000);
        clickOnElement(By.xpath("(//button[@class='currency-select btn btn-link btn-block'])[2]"));
        //verify total £74.73
        verifyText(By.xpath("//tbody//tr//td[6]"), "£74.73");
    }

    @After
    public void browserClose() {
        closeBrowser();

    }
}