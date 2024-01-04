import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.ref.SoftReference;

public class OpenBrowser
{

    WebDriver driver = null;
    LoginPageWebElements loginElements = new LoginPageWebElements();
    LoginPageActions LoginAction = new LoginPageActions();
    SoftAssert soft = new SoftAssert();


    @BeforeTest
    public void Initialization() throws InterruptedException {
        //Bridge between test scripts and browser
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Browsers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromePath);

        //New Object of WebDriver
        driver = new ChromeDriver();
    }

    @Test
    public void Test01_ValidData () throws InterruptedException {
        //Navigate to test website
        LoginAction.SiteNavigation(driver,"https://the-internet.herokuapp.com/login");

        //Entering Credentials and submitting
        LoginAction.LoggingIn(driver,loginElements,"tomsmith","SuperSecretPassword!");

        //First Assertion
        String expectedResult = "You logged into a secure area!";
        soft.assertTrue(LoginPageWebElements.FlashMSG(driver).contains(expectedResult),"First assertion failed");
        System.out.println("Positive Scenario Passed");

        //Finalizing Assertion
        soft.assertAll();
    }

    @Test
    public void Test02_InvalidData() throws InterruptedException {
        //Navigate to test website
        LoginAction.SiteNavigation(driver,"https://the-internet.herokuapp.com/login");

        //Entering Credentials and submitting
        LoginAction.LoggingIn(driver,loginElements,"tombs","Supe");

        //First Assertion
        String expectedResult = "Your username is invalid!";
        soft.assertTrue(LoginPageWebElements.FlashMSG(driver).contains(expectedResult),"First assertion failed");
        System.out.println("Negative Scenario Passed");

        //Finalizing Assertion
        soft.assertAll();
    }

    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        //Close Driver
        Thread.sleep(1000); //Wait for 3 secs
        driver.quit();
    }

}