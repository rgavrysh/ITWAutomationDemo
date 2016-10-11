package scenario;

import junit.framework.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.androidPages.HomePage;
import pages.androidPages.ItEvent;
import pages.androidPages.LoginPage;

/**
 * Created by rgavrysh on 10/10/2016.
 */
public class JoinEventTests extends BaseTest {
    @BeforeClass
    public void setUpClass() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.regularLogin("admin@admin.com", "!tweekend123");
    }

    @Test
    public void joinEventTest() {
        String eventName = "IT Weekend Ukraine 2016";
        HomePage homePage = new HomePage(driver);
        ItEvent selectedEvent = homePage.scrollToEvent(eventName).openEvent(eventName);
        selectedEvent.joinEvent();
        Assert.assertTrue(selectedEvent.leaveEventButtonExist());
    }

    @AfterMethod
    public void tearDownMethod() {
//        leaveEvent();
    }

}
