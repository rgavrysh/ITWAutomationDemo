package scenario;

import apiTests.eventsTests.LeaveEvent;
import apiTests.util.RequestUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.androidPages.HomePage;
import pages.androidPages.ItEvent;
import pages.androidPages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
        assertThat(selectedEvent.leaveEventButtonExist(), is(true));
    }

    @AfterMethod
    public void tearDownMethod() {
        if (!RequestUtil.isAuthorized())
            RequestUtil.authorize();
        LeaveEvent leaveEvent = new LeaveEvent();
        leaveEvent.leaveEvent(25);
    }
}
