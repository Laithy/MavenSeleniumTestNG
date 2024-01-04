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
    LoginPageWebElements login = new LoginPageWebElements();

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
        //Navigate to Google website
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize(); //Maximize
        Thread.sleep(1000); //Wait for 3 secs

        //Entering Credentials
        login.usernameElement(driver).clear();
        login.usernameElement(driver).sendKeys("tomsmith");
        login.passwordElement(driver).clear();
        login.passwordElement(driver).sendKeys("SuperSecretPassword!");

        //Logging in
        login.passwordElement(driver).submit();
        Thread.sleep(3000);

        //Soft assertions setup
        SoftAssert soft = new SoftAssert();

        //First Assertion
        String expectedResult = "You logged into a secure area!";
        String actualResult = driver.findElement(By.id("flash")).getText();
        soft.assertTrue(actualResult.contains(expectedResult),"First assertion failed");
        System.out.println("First Assertion Passed");

        //Second Assertion
        soft.assertTrue(driver.findElement(By.cssSelector("a[href=\"/logout\"]")).isDisplayed(),"Second assertion failed");
        System.out.println("Second Assertion Passed");

        //Third Assertion
        soft.assertTrue(driver.getCurrentUrl().contains("the-internet.herokuapp.com/secure"),"Third assertion failed");
        System.out.println("Third Assertion Passed");

        //Finalizing Assertion
        soft.assertAll();
    }

    @Test
    public void Test02_InvalidData() throws InterruptedException {
        //Navigate to Google website
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize(); //Maximize
        Thread.sleep(1000); //Wait for 3 secs

        //Entering Credentials
        login.usernameElement(driver).clear();
        login.usernameElement(driver).sendKeys("TomBs");
        login.passwordElement(driver).clear();
        login.passwordElement(driver).sendKeys("SuperSecretPasswor!");

        //Logging in
        login.passwordElement(driver).submit();
        Thread.sleep(3000);


        //Soft Assertion Setup
        SoftAssert soft = new SoftAssert();

        //First Assertion
        String expectedResult01 = "Your username is invalid!";
        String actualResult01 = driver.findElement(By.id("flash")).getText();
        soft.assertTrue(actualResult01.contains(expectedResult01),"First assertion failed");
        System.out.println("First Assertion Passed");

        //Second Assertion
        String expectedResult02 = "Login";
        String actualResult02 = driver.findElement(By.cssSelector("i[class=\"fa fa-2x fa-sign-in\"]")).getText();
        soft.assertTrue(actualResult02.contains(expectedResult02),"Second assertion failed");
        System.out.println("Second Assertion Passed");

        //Third Assertion
        soft.assertTrue(driver.getCurrentUrl().contains("the-internet.herokuapp.com/login"),"Third assertion failed");
        System.out.println("Third Assertion Passed");

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
