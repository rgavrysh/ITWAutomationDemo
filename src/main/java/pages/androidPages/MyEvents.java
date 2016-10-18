package pages.androidPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by rgavrysh on 10/17/2016.
 */
public class MyEvents extends HomePage {
    public MyEvents(WebDriver driver) {
        super(driver);
    }

    public boolean browseEventsDisplayed() {
        return isElementExist(By.id("com.softserve.wroc.itweekend:id/browse_events"));
    }
}
