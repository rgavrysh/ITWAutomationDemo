package pages.androidPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by vitalii on 10/11/16.
 */
public class SettingsPage extends BasePage {

    @FindBy(id = "com.softserve.wroc.itweekend:id/log_out")
    private WebElement logOutButton;

    private WebDriver driver;

    public SettingsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage userLogOut() {
        //// TODO: 10/18/2016 scroll down to Log Out button
        verticalSwipe();
        logOutButton.click();
        return new LoginPage(driver);
    }

}
