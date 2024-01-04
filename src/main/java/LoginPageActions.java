import org.openqa.selenium.WebDriver;

import javax.swing.*;

public class LoginPageActions {
    public void LoggingIn (WebDriver driver, LoginPageWebElements login , String username , String password ) throws InterruptedException {
        //Entering Credentials
        login.usernameElement(driver).clear();
        login.usernameElement(driver).sendKeys(username);
        login.passwordElement(driver).clear();
        login.passwordElement(driver).sendKeys(password);

        //Logging in
        login.passwordElement(driver).submit();
        Thread.sleep(3000);
    }

    public void  SiteNavigation (WebDriver driver,String site) throws InterruptedException {
        //Navigate to Google website
        driver.navigate().to(site);
        driver.manage().window().maximize(); //Maximize
        Thread.sleep(1000); //Wait for 3 secs
    }
}
