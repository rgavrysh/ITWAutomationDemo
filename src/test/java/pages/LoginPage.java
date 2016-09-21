package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vitalii on 8/9/16.
 */
public class LoginPage extends BasePage {
    By userId = By.xpath(" //android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]");
    By password = By.id(app_package_name + "password");
    //    By showPassword = By.id(app_package_name + "show_password");
    By login_Button = By.id(app_package_name + "loginButton");
    By loginViaFBButton= By.id(app_package_name + "loginViaFacebook");
    By loginViaLDButton = By.id(app_package_name + "loginViaLinkedIn");
    By fbUserId = By.id(fb_app_package_name + "login_username");
    By fbPassword = By.id(fb_app_package_name + "login_password");
    By fbLoginButton = By.id(fb_app_package_name + "login_login");
    By leftEventMenu = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.widget.ImageButton[1]");
    By logoutButton = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]/android.support.v7.widget.LinearLayoutCompat[9]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage invalidLogin() {
        waitForVisibilityOf(userId);
        driver.findElement(userId).sendKeys("someone@testvagrant.com");
        driver.findElement(password).sendKeys("testvagrant123");
//        driver.findElement(showPassword).click();
        driver.findElement(login_Button).click();
        return new LoginPage(driver);
    }

    public LoginPage successLogin() {
        waitForVisibilityOf(userId);
        driver.findElement(userId).sendKeys("wrdrbtestest@gmail.com");
        driver.findElement(password).sendKeys("W%d%password");
        driver.findElement(login_Button).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LoginPage(driver);
    }

    public LoginPage fbSuccessLogin() {
        waitForClickabilityOf(leftEventMenu);
        driver.findElement(leftEventMenu).click();
        waitForVisibilityOf(logoutButton);
        driver.findElement(logoutButton).click();
        waitForVisibilityOf(loginViaFBButton);
        driver.findElement(loginViaFBButton).click();
        waitForVisibilityOf(fbUserId);
        driver.findElement(fbUserId).sendKeys("buz59application@gmail.com");
        driver.findElement(fbPassword).sendKeys("bus1n%ss497");
        driver.findElement(fbLoginButton).click();
        return new LoginPage(driver);
    }
}