import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageWebElements {

    public WebElement usernameElement (WebDriver driver){
        By username01 = By.id("username");
        WebElement usernameElement = driver.findElement(username01);
        return usernameElement;
    }

    public WebElement passwordElement (WebDriver driver){
        By password01 = By.id("password");
        WebElement passwordElement = driver.findElement(password01);
        return passwordElement;
    }
}
