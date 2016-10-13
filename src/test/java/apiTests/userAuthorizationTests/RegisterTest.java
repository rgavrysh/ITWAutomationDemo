package apiTests.userAuthorizationTests;

import apiTests.BaseRestAssuredTest;
import apiTests.util.JsonUtil;
import apiTests.util.RequestUtil;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static apiTests.util.RequestUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by rgavrysh on 10/12/2016.
 */
public class RegisterTest extends BaseRestAssuredTest {

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
                {"email", "admin2@admin.com"},
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
                {"email", "admin1@admin.com"},
                {"login_type", "Facebook"},
                {"avatar_url", ""}};
        Response response = RequestUtil.requestWithBody(Method.POST, "/register/", JsonUtil.buildJson(inputData).toString(),
                CONTENT_TYPE_APPLICATION_JSON_HEADER, MAGIC_PASSPHRASE_HEADER);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("status"), equalTo("User registered successfully"));
    }

    @AfterClass
    public void tearDownClass() {
        //todo: remove all registered users
    }
}
