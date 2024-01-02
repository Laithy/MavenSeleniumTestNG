import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.security.Key;

public class OpenBrowser
{


    WebDriver driver = null;
    @BeforeTest
    public void StartBrowser() throws InterruptedException {
        //Bridge between test scripts and browser
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Browsers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromePath);
        //New Object of WebDriver
        driver = new ChromeDriver();
        //Navigate to Google website
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize(); //Maximize
        Thread.sleep(1000); //Wait for 3 secs
    }

    @Test
    public void Test01_ValidData () {
        //Entering Credentials
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Logging in
        driver.findElement(By.name("password")).submit();
    }

    @Test
    public void Test02_InvalidData() {
        //Entering Credentials
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("TomBs");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("SuperSecretPasswor!");
        //Logging in
        driver.findElement(By.name("password")).submit();
    }

    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        //Close Driver
        Thread.sleep(1000); //Wait for 3 secs
        driver.quit();
    }


}
