package pages.androidPages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by rgavrysh on 10/10/2016.
 */
public class ItEvent extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'JOIN EVENT']")
    private MobileElement joinEventButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'LEAVE EVENT']")
    private MobileElement leaveEventButton;

    protected ItEvent(WebDriver driver) {
        super(driver);
    }

    public void joinEvent() {
        joinEventButton.click();
    }

    public boolean leaveEventButtonExist() {
        return leaveEventButton.isDisplayed();
    }
}
