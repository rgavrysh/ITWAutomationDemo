package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by rgavrysh on 10/6/2016.
 */
public abstract class BasePage {

    protected BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
