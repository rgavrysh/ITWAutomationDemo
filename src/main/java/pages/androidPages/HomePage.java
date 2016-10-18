package pages.androidPages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rgavrysh on 10/6/2016.
 */
//todo: decouple to AllEvents/MyEvents
public class HomePage extends BasePage {
    @AndroidFindBy(className = "android.widget.TextView")
    private WebElement headerTextView;

    @AndroidFindBy(id = "com.softserve.wroc.itweekend:id/textViewEventTitle")
    private List<MobileElement> eventList;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[1]")
    private WebElement mainMenuIcon;

    @AndroidFindBy(id = "com.softserve.wroc.itweekend:id/imageViewDP")
    private WebElement userMenuImage;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='My Events']")
    protected MobileElement myEventsButton;

    private MobileDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = (MobileDriver) driver;
    }

    public String getHeaderTextView() {
        return headerTextView.getText();
    }

    public HomePage scrollToEvent(String eventName) {
        HashSet<String> events = new HashSet<>();
        while (events.addAll(eventList.stream().map(RemoteWebElement::getText).collect(Collectors.toCollection(ArrayList::new)))) {
            for (MobileElement event : eventList) {
                if (event.getText().equalsIgnoreCase(eventName)) {
                    return this;
                }
            }
            verticalSwipe();
        }
        throw new NoSuchElementException("Event: " + eventName + " couldn't be found.");
    }

    public ItEvent openEvent(String eventName) {
        for (MobileElement event : eventList) {
            if (event.getText().equalsIgnoreCase(eventName)) {
                event.click();
                return new ItEvent(driver);
            }
        }
        throw new NoSuchElementException("Even: " + eventName + " could't be found.");
    }
    public UserProfilePage getUserProfile() {
        mainMenuIcon.click();
        userMenuImage.click();
        return new UserProfilePage(driver);
    }

    public LoginPage userLogout() {
        getUserProfile().getUserSettings().userLogOut();
        return new LoginPage(driver);
    }

    public MyEvents navigateToMyEvents() {
        mainMenuIcon.click();
        myEventsButton.click();
        return new MyEvents(driver);
    }
}