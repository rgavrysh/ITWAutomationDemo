package pages.androidPages;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by vitalii on 10/11/16.
 */
public class FBLoginPage extends BasePage {

    @FindBy(id = "com.facebook.katana:id/login_username")
    private MobileElement fbLoginInput;

    @FindBy(id = "com.facebook.katana:id/login_password")
    private MobileElement fbPasswordInput;

    @FindBy(id = "com.facebook.katana:id/login_login")
    private MobileElement fbLoginButon;

    @FindBy(id = "com.facebook.katana:id/login_bottom_first_link")
    private MobileElement logIntoAnotherAccountButton;

    private WebDriver driver;

    public FBLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage loginViaFB(String fbLogin, String fbPassword) {
        if (isElementExist(By.id("com.facebook.katana:id/login_bottom_first_link"))) {
            logIntoAnotherAccountButton.click();
        }
//        driver.findElement(By.xpath("//android.widget.EditText[@index=0]")).sendKeys(fbLogin);
        fbLoginInput.sendKeys(fbLogin);
//        driver.navigate().back();    //step to avoid e-mail pop up hint overlapping with password field
//        driver.findElement(By.xpath("//android.widget.EditText[@index=1]")).sendKeys(fbPassword);
        fbPasswordInput.sendKeys(fbPassword);
//        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Log In ']")).click();
        fbLoginButon.click();
        return new HomePage(driver);
    }
}
