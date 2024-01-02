import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;

public class OpenBrowser {
    public static void main(String[] args) throws InterruptedException {

        //1- Bridge between test scripts and browser
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Browsers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromePath);

        //2- New Object of WebDriver
        WebDriver driver = new ChromeDriver();
        
        //3- Navigate to Google website
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize(); //Maximize
        Thread.sleep(1000); //Wait for 3 secs

        //3.2- Actions
        //Entering Credentials
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");

        Thread.sleep(1000); //Wait for 3 secs

        //Logging in
        driver.findElement(By.name("password")).submit();

        Thread.sleep(1000); //Wait for 3 secs

        //4- Close Driver
        driver.quit();

    }
}
