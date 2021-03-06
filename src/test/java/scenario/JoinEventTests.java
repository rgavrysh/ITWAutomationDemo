package scenario;

import apiTests.util.JsonUtil;
import apiTests.util.RequestUtil;
import io.restassured.http.Method;
import org.testng.annotations.AfterClass;
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
    private ItEvent selectedEvent;
    private HomePage homePage;

    @BeforeClass
    public void setUpClass() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.regularLogin("admin@admin.com", "!tweekend123");
    }

    @Test
    public void joinEventTest() {
        String eventName = "IT Weekend Ukraine 2016";
        homePage = new HomePage(driver);
        selectedEvent = homePage.scrollToEvent(eventName).openEvent(eventName);
        selectedEvent.joinEvent();
        assertThat(selectedEvent.leaveEventButtonExist(), is(true));
    }

    @AfterClass
    public void tearDownClass() {
        selectedEvent.navigateBack();
        homePage.userLogout();
        String[][] inputDataParameters = {{"event_id", "25"}};
        RequestUtil.authorize();
        RequestUtil.authorizedRequestWithBody(Method.POST, "/leave-event/", JsonUtil.buildJson(inputDataParameters).toString());
    }
}
