package pages.androidPages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
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

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private MobileElement navigateBackButton;

    private MobileDriver driver;
    protected ItEvent(WebDriver driver) {
        super(driver);
        this.driver = (MobileDriver) driver;
    }

    public void joinEvent() {
        joinEventButton.click();
    }

    public boolean leaveEventButtonExist() {
        return isElementExist(By.xpath("//android.widget.TextView[@text = 'LEAVE EVENT']"));// leaveEventButton.isDisplayed();
    }

    public void leaveEvent() {
        leaveEventButton.click();
    }

    public void navigateBack() {
        navigateBackButton.click();
    }

    public boolean joinEventButtonExist() {
        return isElementExist(By.xpath("//android.widget.TextView[@text = 'JOIN EVENT']"));
    }
}
