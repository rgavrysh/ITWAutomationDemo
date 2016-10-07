package pages.androidPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by rgavrysh on 10/6/2016.
 */
public class LoginPage extends BasePage {
    @FindBy(id = "com.softserve.wroc.itweekend:id/email")
    private WebElement emailInput;

    @FindBy(id = "com.softserve.wroc.itweekend:id/password")
    private WebElement passwordInput;

    @FindBy(id = "com.softserve.wroc.itweekend:id/loginButton")
    private WebElement signInButton;

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
}
