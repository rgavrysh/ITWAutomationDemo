package apiTests.userAuthorizationTests;

import apiTests.BaseRestAssuredTest;
import apiTests.util.JsonUtil;
import apiTests.util.RequestUtil;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.Calendar;

import static apiTests.util.RequestUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by rgavrysh on 10/12/2016.
 */
public class RegisterTest extends BaseRestAssuredTest {

    private String registeredUserToken;

    @Test
    public void regularRegistrationExistedUser() {
        String[][] inputData = {{"first_name", "Jan"},
                {"surname", "Nowak"},
                {"email", "admin@admin.com"},
                {"password", "!tweekend123"},
                {"phone_number", ""},
                {"location_info", ""},
                {"login_type", "Regular"},
                {"avatar_url", ""}};
        Response response = RequestUtil.requestWithBody(Method.POST, "/register/", JsonUtil.buildJson(inputData).toString(),
                CONTENT_TYPE_APPLICATION_JSON_HEADER, MAGIC_PASSPHRASE_HEADER);
        assertThat(response.statusCode(), equalTo(409));
        assertThat(normalizeJSON(response).get("status"), equalTo("User with email admin@admin.com already exists in database"));
    }

    @Test
    public void regularRegistrationNewUser() {
        String[][] inputData = {{"first_name", "TestName"},
                {"surname", "TestSurname"},
                {"email", "TESTAdmin" + Calendar.getInstance().getTimeInMillis() + "@admin.com"},
                {"password", "itwknd123"},
                {"phone_number", "123456"},
                {"location_info", ""},
                {"login_type", "Regular"},
                {"avatar_url", ""}};
        Response response = RequestUtil.requestWithBody(Method.POST, "/register/", JsonUtil.buildJson(inputData).toString(),
                CONTENT_TYPE_APPLICATION_JSON_HEADER, MAGIC_PASSPHRASE_HEADER);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("status"), equalTo("Activation link has been sent, please activate"));
    }

    @Test
    public void facebookRegistrationNewUser() {
        String[][] inputData = {{"first_name", "TestName"},
                {"surname", "TestSurname"},
                {"email", "FBRegisterTest" + Calendar.getInstance().getTimeInMillis() + "@admin.com"},
                {"login_type", "Facebook"},
                {"avatar_url", ""}};
        Response response = requestWithBody(Method.POST, "/register/", JsonUtil.buildJson(inputData).toString(),
                CONTENT_TYPE_APPLICATION_JSON_HEADER, MAGIC_PASSPHRASE_HEADER);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("status"), equalTo("User registered successfully"));
        registeredUserToken = "Token " + normalizeJSON(response).get("token");
    }

    @Test(dependsOnMethods = "facebookRegistrationNewUser")
    public void updateUser() {
        String[][] inputData = {{"first_name", "ChangedTestName"},
                {"country", "Poland"},
                {"company", "SS"},
                {"phone_number", "123456"}};
        Response response = requestWithBody(Method.PUT, "/update-user-profile/", JsonUtil.buildJson(inputData).toString(),
                new Header("Content-Type", "application/json"), new Header("Authorization", registeredUserToken));
        assertThat(response.statusCode(), equalTo(200));
        response = requestWithoutBody(Method.GET, "/get-user-profile/", new Header("Authorization", registeredUserToken));
        assertThat(normalizeJSON(response).get("country"), equalTo("Poland"));
        assertThat(normalizeJSON(response).get("first_name"), equalTo("ChangedTestName"));
        assertThat(normalizeJSON(response).get("company"), equalTo("SS"));
    }

    @AfterClass
    public void tearDownClass() {
        //todo: remove all registered users
    }
}
