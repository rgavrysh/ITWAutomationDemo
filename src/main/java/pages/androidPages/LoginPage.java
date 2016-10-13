package pages.androidPages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
    @FindBy(id = "com.softserve.wroc.itweekend:id/email")
    private WebElement emailInput;

    @FindBy(id = "com.softserve.wroc.itweekend:id/password")
    private WebElement passwordInput;

    @FindBy(id = "com.softserve.wroc.itweekend:id/loginButton")
    private WebElement signInButton;

    @FindBy(id = "com.softserve.wroc.itweekend:id/loginViaFacebook")
    private WebElement fbButton;

    @FindBy(id = "com.softserve.wroc.itweekend:id/loginViaLinkedIn")
    private WebElement inButton;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage regularLogin(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInButton.click();
        return new HomePage(driver);
    }

    public HomePage fbLogin() {
        fbButton.click();
        if (driver.findElements(By.id("com.facebook.katana:id/login_bottom_first_link")).size()==0 &&
                driver.findElements(By.id("com.facebook.katana:id/login_username")).size()==0)

        {
        return new HomePage(driver);
        }
        else{
            return new FBLoginPage(driver).loginViaFB("buz59application@gmail.com", "bus1n%ss497");
        }
    }
}