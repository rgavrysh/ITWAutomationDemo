package apiTests.userAuthorizationTests;

import apiTests.BaseRestAssuredTest;
import apiTests.util.JsonUtil;
import apiTests.util.RequestUtil;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apiTests.util.RequestUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by rgavrysh on 10/11/2016.
 */
public class Login extends BaseRestAssuredTest {

    @Test
    public void invalidMagicPassphraseRegularLogin() {
        String[][] inputDataParameters = {{"email", "admin@admin.com"}, {"password", "!tweekend123"}, {"login_type", "Regular"}};
        Response response = RequestUtil.requestWithBody(Method.POST, "/login/", JsonUtil.buildJson(inputDataParameters).toString(),
                CONTENT_TYPE_APPLICATION_JSON_HEADER, INVALID_MAGIC_PASSPHRASE_HEADER);
        assertThat(response.statusCode(), equalTo(401));
        assertThat(normalizeJSON(response).get("status"), equalTo("Magic passphrase is not correct"));
    }

    @Test
    public void invalidInputLogin() {
        String[][] inputDataParameters = {{"email", "admin@admin.com"}, {"password", "!tweekend12"}, {"login_type", "Regular"}};
        Response response = RequestUtil.requestWithBody(Method.POST, "/login/", JsonUtil.buildJson(inputDataParameters).toString(),
                CONTENT_TYPE_APPLICATION_JSON_HEADER, MAGIC_PASSPHRASE_HEADER);
        assertThat(response.statusCode(), equalTo(401));
        assertThat(normalizeJSON(response).get("status"), equalTo("Email or password are incorrect."));
    }

    @Test
    public void loginViaFacebook() {
        // loginFacebook token = "Token 4969b3132922923ff1610a4239bdb9e488e0d6da"
        String[][] inputDataParameters = {{"email", "FacebookLogin@admin.com"}, {"login_type", "Facebook"}};
        Response response = RequestUtil.requestWithBody(Method.POST, "/login/", JsonUtil.buildJson(inputDataParameters).toString(),
                CONTENT_TYPE_APPLICATION_JSON_HEADER, MAGIC_PASSPHRASE_HEADER);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(normalizeJSON(response).get("token"), equalTo("4969b3132922923ff1610a4239bdb9e488e0d6da"));
    }
}
