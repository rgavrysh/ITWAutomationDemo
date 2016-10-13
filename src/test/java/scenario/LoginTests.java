package scenario;

import junit.framework.Assert;
import org.aspectj.lang.annotation.After;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.androidPages.HomePage;
import pages.androidPages.LoginPage;

/**
 * Created by vitalii on 9/21/16.
 */
public class LoginTests extends BaseTest {

    @Test(groups = {"Android"}, dataProvider = "loginParameters", dataProviderClass = TestDataProvider.class)
    public void emailLoginTest(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.regularLogin(email, password);
        Assert.assertTrue(homePage.getHeaderTextView().toLowerCase().equals("all events"));
    //todo: add dataprovider
   /* @Test(groups = {"Android"})
    public void emailLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.regularLogin("admin@admin.com", "!tweekend123");
        Assert.assertTrue(homePage.getHeaderTextView().toLowerCase().equals("all events"));
    }*/

    @Test(groups = {"Android"})
    public void fbLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.fbLogin();
        Assert.assertTrue(homePage.getHeaderTextView().toLowerCase().equals("all events"));
    }


 /*   @Test(groups = {"Android", "IOS"})
    public void crossPlatformTest() {
        Assert.assertEquals(true, true);
    }*/

    @AfterMethod(groups = {"Android", "IOS"})
    public void logoutTest() {
        HomePage homepage = new HomePage(driver);
        homepage.userLogout();
    }
}
