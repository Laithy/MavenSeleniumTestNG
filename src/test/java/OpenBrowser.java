import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenBrowser
{

    WebDriver driver = null;
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
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Logging in
        driver.findElement(By.name("password")).submit();
        //Assertion
        String expectedResult = "You logged into a secure area!";
        String actualResult = driver.findElement(By.id("flash")).getText();
//        Assert.assertEquals(actualResult.contains(expectedResult),true);
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void Test02_InvalidData() throws InterruptedException {
        //Navigate to Google website
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize(); //Maximize
        Thread.sleep(1000); //Wait for 3 secs
        //Entering Credentials
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("TomBs");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("SuperSecretPasswor!");
        //Logging in
        driver.findElement(By.name("password")).submit();
        //Assertion
        String expectedResult = "Your username is invalid!";
        String actualResult = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        //Close Driver
        Thread.sleep(1000); //Wait for 3 secs
        driver.quit();
    }

}
