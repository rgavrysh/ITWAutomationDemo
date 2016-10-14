package apiTests.eventsTests;

import apiTests.ApiDataProvider;
import apiTests.BaseRestAssuredTest;
import apiTests.util.JsonUtil;
import apiTests.util.RequestUtil;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static apiTests.util.RequestUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by rgavrysh on 10/11/2016.
 */
public class JoinEvent extends BaseRestAssuredTest {

    @BeforeClass
    public void setUpClass() {
        String[][] inputDataParameters = {{"event_id", ""}};
        ArrayList<Integer> eventIDs = normalizeJSON(authorizedRequestWithoutBody(Method.GET, "/get-user-events/")).get("event_list");
        if (eventIDs != null) {
            for (Integer id : eventIDs) {
                inputDataParameters[0][1] = String.valueOf(id);
                authorizedRequestWithBody(Method.POST, "/leave-event/", JsonUtil.buildJson(inputDataParameters).toString());
            }
        }
    }

    @AfterClass
    public void tearDownClass() {
        String[][] inputDataParameters = {{"event_id", ""}};
        ArrayList<Integer> eventIDs = normalizeJSON(authorizedRequestWithoutBody(Method.GET, "/get-user-events/")).get("event_list");
        if (eventIDs != null) {
            for (Integer id : eventIDs) {
                inputDataParameters[0][1] = String.valueOf(id);
                authorizedRequestWithBody(Method.POST, "/leave-event/", JsonUtil.buildJson(inputDataParameters).toString());
            }
        }
    }

    @Test(dataProvider = "eventID", dataProviderClass = ApiDataProvider.class)
    public void joinEventTest(int eventID) {
        String[][] inputDataParameters = {{"event_id", String.valueOf(eventID)}, {"join_type", "User"}};
        Response response = RequestUtil.authorizedRequestWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("status"), equalTo("User has joined the event"));

    }

    @Test(dependsOnMethods = "joinEventTest", dataProvider = "eventID", dataProviderClass = ApiDataProvider.class)
    public void joinToAlreadyJoinedEventTest(int eventID) {
        String[][] inputDataParameters = {{"event_id", String.valueOf(eventID)}, {"join_type", "User"}};
        Response response = authorizedRequestWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(409));
        assertThat(normalizeJSON(response).get("status"), equalTo("Cannot join. User already joined the event"));
        assertThat(isUserJoinedEvent(eventID), is(true));
    }

    @Test
    public void joinToNonExistedEventTest() {
        int eventID = 1;
        String[][] inputDataParameters = {{"event_id", String.valueOf(eventID)}, {"join_type", "User"}};
        Response response = authorizedRequestWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(404));
        assertThat(normalizeJSON(response).get("status"), equalTo("Event does not exist"));
        assertThat(isUserJoinedEvent(eventID), is(false));
    }

    @Test(dependsOnMethods = "joinEventTest", dataProvider = "eventID", dataProviderClass = ApiDataProvider.class)
    public void getUserEventsTest(int eventID) {
        Response response = authorizedRequestWithoutBody(Method.GET, "/get-user-events/");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("event_list"), hasItems(eventID));
    }

    @Test
    public void joinToAllEvents() {
        //todo: extract to separate method
        /* EXTRACT */
        ArrayList<Integer> eventIDs = authorizedRequestWithoutBody(Method.GET, "/events/").then().extract().path("results.id");
        for (Integer id : eventIDs) {
            String[][] inputDataParameters = {{"event_id", String.valueOf(id)}, {"join_type", "User"}};
            authorizedRequestWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
        }
        /* EXTRACT */
        ArrayList<Integer> ids = normalizeJSON(authorizedRequestWithoutBody(Method.GET, "/get-user-events/")).get("event_list");
        Assert.assertEqualsNoOrder(ids.toArray(), eventIDs.toArray());
    }
}
