package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utility.Utilities;


public class TopMenuTest extends Utilities {
    @Before
    public void browserSetUp() {
        openBrowser("Chrome", "https://tutorialsninja.com/demo/index.php");
    }

    //1.1 and 1.2 Creating a method with one parameter
    public void selectMenu(String menu) {
        if (menu.equalsIgnoreCase("Show All Desktops")) {
            clickOnElement(By.linkText("Show AllDesktops"));
        } else if (menu.equalsIgnoreCase("Show All Laptops & Notebooks")) {
            clickOnElement(By.linkText("Show AllLaptops & Notebooks"));
        } else if (menu.equalsIgnoreCase("Show All Components")) {
            clickOnElement(By.linkText("Show AllComponents"));
        }
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() throws InterruptedException {
        Thread.sleep(1000);
//        1.1 Mouse hover on “Desktops” Tab and click
        mouseHoverOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]"));

//        1.2 call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show All Desktops");
        verifyText(By.xpath("//h2[contains(text(),'Desktops')]"), "Desktops");

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() throws InterruptedException {
        Thread.sleep(1000);
//        2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[2]"));

//        2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("Show All Laptops & Notebooks");
//        2.3 Verify the text ‘Laptops & Notebooks’
        verifyText(By.xpath("//h2[contains(text(),'Laptops & Notebooks')]"), "Laptops & Notebooks");

    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() throws InterruptedException {
        Thread.sleep(1000);
//        3.1 Mouse hover on “Components” Tab and click
        mouseHoverOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[3]"));

//        3.2 call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show All Components");
//        3.3 Verify the text ‘Components’
        verifyText(By.linkText("Components"), "Components");
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}