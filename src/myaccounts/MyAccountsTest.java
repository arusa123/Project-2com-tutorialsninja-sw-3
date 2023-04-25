package myaccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utility.Utilities;

public class MyAccountsTest extends Utilities {
    @Before
    public void browserSetUp() {
        openBrowser("Chrome", "https://tutorialsninja.com/demo/index.php");
    }

    public void selectMyAccountOptions(String option) {
        if (option.equalsIgnoreCase("Register")) {
            clickOnElement(By.xpath("//li[@class='dropdown open']/ul/li[1]"));
        } else if (option.equalsIgnoreCase("Login")) {
            clickOnElement(By.xpath("//li[@class='dropdown open']/ul/li[2]"));
        } else if (option.equalsIgnoreCase("Logout")) {
            clickOnElement(By.xpath("//div[@id='top-links']/ul/li[2]/ul/li[5]"));
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
//        1.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
//        1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");

//        1.3 Verify the text “Register Account”.
        verifyText(By.xpath("//h1[contains(text(),'Register Account')]"), "Register Account");
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
//        2.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
//        2.2 Call the method “selectMyAccountOptions” method and pass the parameter“Login”
        selectMyAccountOptions("Login");

//        2.3 Verify the text “Returning Customer”.
        verifyText(By.xpath("//h2[normalize-space()='Returning Customer']"), "Returning Customer");
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
//        3.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
//        3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
//        // 3.3 Enter First Name
        sendTextToElement(By.id("input-firstname"), "Prime");
//        //3.4 Enter Last Name
        sendTextToElement(By.id("input-lastname"), "Testing");
//        //3.5 Enter Email
        sendTextToElement(By.id("input-email"), "prime1004@gmail.com");
//        //3.6 Enter Telephone
        sendTextToElement(By.id("input-telephone"), "02012345337");
//        //3.7 Enter Password
        sendTextToElement(By.id("input-password"), "Prime");
//        //3.8 Enter Password Confirm
        sendTextToElement(By.id("input-confirm"), "Prime");

//        3.9 Select Subscribe Yes radio button
        clickOnElement(By.xpath("//div[@id='content']/form/fieldset[3]/div/div/label[1]/input"));

//        3.10 Click on Privacy Policy check box
        clickOnElement(By.name("agree"));
//        3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@class='btn btn-primary']"));

//        3.12 Verify the message “Your Account Has Been Created!”
        verifyText(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]"), "Your Account Has Been Created!");
//        3.13 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
//        3.14 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
//        3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
//        3.16 Verify the text “Account Logout”
        verifyText(By.xpath("//h1[contains(text(),'Account Logout')]"), "Account Logout");

//        3.17 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }
    @Test

    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws InterruptedException {
        Thread.sleep(1000);
//        4.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
//        4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");

//        4.3 Enter Email address
        sendTextToElement(By.id("input-email"), "prime22@gmail.com");

//        4.5 Enter Password
        sendTextToElement(By.id("input-password"), "Prime123");

//        4.6 Click on Login button
        clickOnElement(By.xpath("//input[@class='btn btn-primary']"));
//        4.7 Verify text “My Account”
        verifyText(By.xpath("//h2[contains(text(),'My Account')]"), "My Account");
//        4.8 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
//        4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
//        4.10 Verify the text “Account Logout”
        verifyText(By.xpath("//h1[contains(text(),'Account Logout')]"), "Account Logout");
//        4.11 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }

    @After
    public void closeTheBrowser() {
        closeBrowser();
    }
}