package pages.androidPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by vitalii on 10/11/16.
 */
public class UserProfilePage extends BasePage {

    @FindBy(id = "com.softserve.wroc.itweekend:id/settings")
    private WebElement userProfileSettingsIcon;

    private WebDriver driver;

    public UserProfilePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public SettingsPage getUserSettings() {
        userProfileSettingsIcon.click();
        return new SettingsPage(driver);
    }
}
