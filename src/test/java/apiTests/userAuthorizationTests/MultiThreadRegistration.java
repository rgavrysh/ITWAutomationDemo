package apiTests.userAuthorizationTests;

import apiTests.ApiDataProvider;
import apiTests.BaseRestAssuredTest;
import apiTests.util.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static apiTests.util.RequestUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by rgavrysh on 10/13/2016.
 */
public class MultiThreadRegistration extends BaseRestAssuredTest {

    private ArrayList<String> registeredUserTokens = new ArrayList<>();

    private void registerMultipleUsers() {
        for (int i = 0; i < 3; i++) {
            String[][] inputData = {{"first_name", "TestName"},
                    {"surname", "TestSurname"},
                    {"email", "TESTAdmin" + Calendar.getInstance().getTimeInMillis() + "@admin.com"},
                    {"login_type", "Facebook"},
                    {"avatar_url", ""}};
            RestAssured.baseURI = "http://195.160.232.127";
            RestAssured.basePath = "/api";
            Response response = RestAssured.given().header(CONTENT_TYPE_APPLICATION_JSON_HEADER).header(MAGIC_PASSPHRASE_HEADER)
                    .body(JsonUtil.buildJson(inputData).toString())
                    .when().post("/register/");
            assertThat(response.statusCode(), equalTo(200));
            registeredUserTokens.add("Token " + normalizeJSON(response).get("token"));
        }
    }

    private class CreateUserThread extends Thread {
        @Override
        public void run() {
            registerMultipleUsers();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //todo: for performance purpose usage
    @Test
    public void multipleRegistration() throws InterruptedException {
        CreateUserThread u1 = new CreateUserThread();
        CreateUserThread u2 = new CreateUserThread();
        CreateUserThread u3 = new CreateUserThread();

        u1.start();
        Thread.sleep(20);
        u2.start();
        Thread.sleep(20);
        u3.start();
        u1.join(3000);
        u2.join(3000);
        u3.join(3000);
    }

    @Test(dependsOnMethods = "multipleRegistration", dataProvider = "eventWithLimitation", dataProviderClass = ApiDataProvider.class)
    public void joinNewlyCreatedUsersToEvent(String eventID) {
        String[][] inputDataParameters = {{"event_id", eventID}, {"join_type", "User"}};
        for (String userToken : registeredUserTokens) {
            requestWithoutBody(Method.POST, "/me/accept-tos/", CONTENT_TYPE_APPLICATION_JSON_HEADER, new Header("Authorization", userToken));
            Response response = requestWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString(),
                    CONTENT_TYPE_APPLICATION_JSON_HEADER, new Header("Authorization", userToken));
            assertThat(response.statusCode(), equalTo(200));
            System.out.println(userToken + " -> joined.");
        }
    }

    @Test(dependsOnMethods = "joinNewlyCreatedUsersToEvent", dataProvider = "eventWithLimitation", dataProviderClass = ApiDataProvider.class)
    public void joinToFullEvent(String eventID) {
        String[][] inputDataParameters = {{"event_id", eventID}, {"join_type", "User"}};
        Response response = authorizedRequestWithBody(Method.POST, "/join-event/", JsonUtil.buildJson(inputDataParameters).toString());
        assertThat(response.statusCode(), equalTo(409));
        assertThat(normalizeJSON(response).get("status"), equalTo("Event has reached participants limit"));
    }

    @AfterClass
    public void multipleUnJoin() {
        String[][] inputDataParameters = {{"event_id", (String) ApiDataProvider.getEventIDWithLimitation()[0][0]}};
        for (String userToken : registeredUserTokens) {
            Response response = requestWithBody(Method.POST, "/leave-event/", JsonUtil.buildJson(inputDataParameters).toString(),
                    CONTENT_TYPE_APPLICATION_JSON_HEADER, new Header("Authorization", userToken));
            assertThat(response.statusCode(), equalTo(200));
            System.out.println(userToken + " -> left event.");
        }
    }
}
