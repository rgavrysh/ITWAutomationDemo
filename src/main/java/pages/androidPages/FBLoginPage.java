package pages.androidPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by vitalii on 10/11/16.
 */
public class FBLoginPage extends BasePage {

    @FindBy(id = "com.facebook.katana:id/login_username")
    private WebElement fbLoginInput;

    @FindBy(id = "com.facebook.katana:id/login_password")
    private WebElement fbPasswordInput;

    @FindBy(id = "com.facebook.katana:id/login_login")
    private WebElement fbLoginButon;

    @FindBy(id = "com.facebook.katana:id/login_bottom_first_link")
    private WebElement logIntoAnotherAccountButton;

    private WebDriver driver;

    public FBLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage loginViaFB(String fbLogin, String fbPassword) {
         if (logIntoAnotherAccountButton.isDisplayed()) {
            // check if FB page advice user to log in with existing account(s)
            logIntoAnotherAccountButton.click();
        }
        fbLoginInput.sendKeys(fbLogin);
        driver.navigate().back();    //step to avoid e-mail pop up hint overlapping with password field
        fbPasswordInput.sendKeys(fbPassword);
        fbLoginButon.click();
        return new HomePage(driver);
    }
}
