package pages.androidPages;


import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    @FindBy(id = "com.softserve.wroc.itweekend:id/loginViaFacebook")
    private MobileElement fbButton;

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

    public HomePage fbLogin() {
        fbButton.click();
        if (isElementExist(By.xpath("//android.webkit.WebView[@content-desc='Welcome to Facebook']"))) {
            return new FBLoginPage(driver).loginViaFB("wrdrbtestest@gmail.com", "Fit%test.31");
        } else {
            return new HomePage(driver);
        }
    }
}