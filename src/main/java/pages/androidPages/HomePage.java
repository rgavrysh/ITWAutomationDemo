package pages.androidPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by rgavrysh on 10/6/2016.
 */
public class HomePage extends BasePage {
    @FindBy(className = "android.widget.TextView")
    private WebElement headerTextView;

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getHeaderTextView() {
        return headerTextView.getText();
    }
}
