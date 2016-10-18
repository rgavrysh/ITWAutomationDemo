package scenario;

import apiTests.util.JsonUtil;
import apiTests.util.RequestUtil;
import io.restassured.http.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.androidPages.HomePage;
import pages.androidPages.ItEvent;
import pages.androidPages.LoginPage;
import pages.androidPages.MyEvents;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by rgavrysh on 10/17/2016.
 */
public class LeaveEventTest extends BaseTest {
    private HomePage homePage;

    @BeforeClass
    public void setUpClass() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.regularLogin("admin@admin.com", "!tweekend123");
    }

    @BeforeMethod
    public void setUpMethod() {
        String[][] inputDataParameters = {{"event_id", "25"}, {"join_type", "User"}};
        RequestUtil.authorize();
        RequestUtil.authorizedRequestWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
    }

    @Test
    public void leaveEventTest() {
        String eventName = "IT Weekend Ukraine 2016";
        homePage = new HomePage(driver);
        MyEvents myEvents = homePage.navigateToMyEvents();
        ItEvent selectedEvent = myEvents.openEvent(eventName);
        selectedEvent.leaveEvent();
        assertThat(selectedEvent.joinEventButtonExist(), is(true));
        selectedEvent.navigateBack();
        assertThat(myEvents.browseEventsDisplayed(), is(true));
    }

    @AfterClass
    public void tearDownMethod() {
        homePage.userLogout();
        String[][] inputDataParameters = {{"event_id", "25"}};
        RequestUtil.authorizedRequestWithBody(Method.POST, "/leave-event/", JsonUtil.buildJson(inputDataParameters).toString());
    }
}
