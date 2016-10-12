package apiTests.eventsTests;

import apiTests.ApiDataProvider;
import apiTests.BaseRestAssuredTest;
import apiTests.util.JsonUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static apiTests.util.RequestUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by rgavrysh on 10/11/2016.
 */
public class LeaveEvent extends BaseRestAssuredTest {

    @BeforeClass
    public void setUpClass() {
        JoinEvent joinEvent = new JoinEvent();
        joinEvent.joinEventTest((int) ApiDataProvider.getEventID()[0][0]);
    }

    @Test(dataProvider = "eventID", dataProviderClass = ApiDataProvider.class)
    public void leaveEvent(int eventID) {
        String[][] inputDataParameters = {{"event_id", String.valueOf(eventID)}};
        Response response = authorizedReqeustWithBody(Method.POST, "/leave-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("status"), equalTo("User has just left this event"));
        assertThat(isUserJoinedEvent(eventID), is(false));
    }

    @Test
    public void leaveUnJoinedEvent() {
        int eventID = 3;
        String[][] inputDataParameters = {{"event_id", String.valueOf(eventID)}};
        Response response = authorizedReqeustWithBody(Method.POST, "/leave-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(404));
        assertThat(normalizeJSON(response).get("status"), equalTo("User did not join to this event"));
        assertThat(isUserJoinedEvent(eventID), is(false));
    }
}
