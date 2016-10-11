package scenario;

import junit.framework.Assert;
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
    }
}
