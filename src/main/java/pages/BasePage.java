package pages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by rgavrysh on 10/6/2016.
 */
public abstract class BasePage {
    protected Dimension winSize;

    private WebDriver driver;

    protected BasePage(WebDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        this.winSize = driver.manage().window().getSize();
    }

    protected boolean isElementExist(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void verticalSwipe() {
        int startX = getX(20);
        int endX = getX(20);
        int startY = getY(80);
        int endY = getY(10);
        ((MobileDriver)driver).swipe(startX, startY, endX, endY, 1000);
    }

    private int getX(int x) {
        return (int) (winSize.width * x) / 100;
    }

    private int getY(int y) {
        return (int) (winSize.width * y) / 100;
    }
}
