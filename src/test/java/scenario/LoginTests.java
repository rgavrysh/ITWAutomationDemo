package scenario;

import junit.framework.Assert;
import org.testng.annotations.Test;
import pages.androidPages.HomePage;
import pages.androidPages.LoginPage;

/**
 * Created by vitalii on 9/21/16.
 */
public class LoginTests extends BaseTest {

    //todo: add dataprovider
    @Test(groups = {"Android"})
    public void emailLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.regularLogin("admin@admin.com", "!tweekend123");
        Assert.assertTrue(homePage.getHeaderTextView().toLowerCase().equals("all events"));
    }

    @Test(groups = {"Android", "IOS"})
    public void crossPlatformTest() {
        Assert.assertEquals(true, true);
    }
}
