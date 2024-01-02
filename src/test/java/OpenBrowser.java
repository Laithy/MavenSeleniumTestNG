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
//        driver.findElement(By.className("radius")).click();                               //Using class name , Delete space and everything after it
//        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();            //Using css selector , ("ParentTag > Tag[attribute=\"value\"]")
//        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();               //Using xpath , ("//ParentTag//Tag[@attribute=\"value\"]")
//        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.name("password")).submit();

        Thread.sleep(1000); //Wait for 3 secs

        //Get Success Msg
        Thread.sleep(1000); //Wait for 3 secs
        String msg01 = driver.findElement(By.id("flash")).getText();
        String msg02 = driver.findElement(By.id("flash")).getAttribute("class");
        System.out.println(msg01);
        System.out.println(msg02);

        //Get Msg Color
        String bgColor = driver.findElement(By.id("flash")).getCssValue("background-color");
        String color = driver.findElement(By.id("flash")).getCssValue("color");
        System.out.println(bgColor);
        System.out.println(color);

        //4- Close Driver
        driver.quit();

    }
}
