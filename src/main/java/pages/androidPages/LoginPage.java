package pages.androidPages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Created by rgavrysh on 10/6/2016.
 */
public class LoginPage extends BasePage {

    @AndroidFindBy(id = "com.softserve.wroc.itweekend:id/email")
    @iOSFindBy(id = "io.path.to.element")
    private MobileElement emailInput;

    @AndroidFindBy(id = "com.softserve.wroc.itweekend:id/password")
    private MobileElement passwordInput;

    @AndroidFindBy(id = "com.softserve.wroc.itweekend:id/loginButton")
    private MobileElement signInButton;

    private MobileDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = (MobileDriver) driver;
    }


    public HomePage regularLogin(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        driver.hideKeyboard();
        signInButton.click();
        return new HomePage(driver);
    }
}
