package apiTests.eventsTests;

import apiTests.ApiDataProvider;
import apiTests.BaseRestAssuredTest;
import apiTests.util.JsonUtil;
import apiTests.util.RequestUtil;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static apiTests.util.RequestUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

/**
 * Created by rgavrysh on 10/11/2016.
 */
public class JoinEvent extends BaseRestAssuredTest {

    @BeforeClass
    public void setUpClass() {
        if (isUserJoinedEvent((int) ApiDataProvider.getEventID()[0][0])) {
            LeaveEvent leaveEvent = new LeaveEvent();
            leaveEvent.leaveEvent((int) ApiDataProvider.getEventID()[0][0]);
        }
    }

    @AfterClass
    public void tearDownClass() {
        LeaveEvent leaveEvent = new LeaveEvent();
        leaveEvent.leaveEvent((int) ApiDataProvider.getEventID()[0][0]);
    }

    @Test(dataProvider = "eventID", dataProviderClass = ApiDataProvider.class)
    public void joinEventTest(int eventID) {
        String[][] inputDataParameters = {{"event_id", String.valueOf(eventID)}, {"join_type", "User"}};
        Response response = RequestUtil.authorizedReqeustWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("status"), equalTo("User has joined the event"));

    }

    @Test(dependsOnMethods = "joinEventTest", dataProvider = "eventID", dataProviderClass = ApiDataProvider.class)
    public void joinToAlreadyJoinedEventTest(int eventID) {
        String[][] inputDataParameters = {{"event_id", String.valueOf(eventID)}, {"join_type", "User"}};
        Response response = authorizedReqeustWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(409));
        assertThat(normalizeJSON(response).get("status"), equalTo("Cannot join. User already joined the event"));
        assertThat(isUserJoinedEvent(eventID), is(true));
    }

    @Test
    public void joinToNonExistedEventTest() {
        int eventID = 1;
        String[][] inputDataParameters = {{"event_id", String.valueOf(eventID)}, {"join_type", "User"}};
        Response response = authorizedReqeustWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(404));
        assertThat(normalizeJSON(response).get("status"), equalTo("Event ID does not exist"));
        assertThat(isUserJoinedEvent(eventID), is(false));
    }

    @Test(dependsOnMethods = "joinEventTest", dataProvider = "eventID", dataProviderClass = ApiDataProvider.class)
    public void getUserEventsTest(int eventID) {
        Response response = authorizedRequestWithoutBody(Method.GET, "/get-user-events/");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("event_list"), hasItems(eventID));
    }
}
