package apiTests.util;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Created by rgavrysh on 10/12/2016.
 */
public class RequestUtil {
    public static String token = "Token ";
    public static final Header CONTENT_TYPE_APPLICATION_JSON_HEADER = new Header("Content-type", "application/json");
    public static final Header MAGIC_PASSPHRASE_HEADER = new Header("Magic-passphrase", "2ak?9Mz%fBmv$9?E");
    public static final Header INVALID_MAGIC_PASSPHRASE_HEADER = new Header("Magic-passphrase", "2ak?9Mz%fBmv$9?e");
    public static Header AuthorizationHeader;

    private final static String[][] inputDataParameters = {{"email", "admin@admin.com"}, {"password", "!tweekend123"}, {"login_type", "Regular"}};

    public static void authorize() {
        RestAssured.baseURI = "http://195.160.232.127";
        RestAssured.basePath = "/api";
        Response response =
                given().contentType("application/json").header(MAGIC_PASSPHRASE_HEADER)
                        .body(JsonUtil.buildJson(inputDataParameters).toString())
                        .when().post("/login/")
                        .then()
                        .statusCode(200)
                        .extract().response();
        token = token + normalizeJSON(response).get("token");
        AuthorizationHeader = new Header("Authorization", token);
    }

    public static boolean isAuthorized() {
        return AuthorizationHeader != null;
    }

    //todo: remove method from this class
    public static JsonPath normalizeJSON(Response response) {
        return JsonUtil.normalizeJSON(response);
    }

    public static Response requestWithBody(Method method, String url, String data, Header... headers) {
        return given().headers(new Headers(headers))
                .body(data)
                .when().request(method, url);
    }

    public static Response requestWithoutBody(Method method, String url, Header... headers) {
        return given().headers(new Headers(headers))
                .when().request(method, url);
    }

    public static Response authorizedReqeustWithBody(Method method, String url, String data) {
        return requestWithBody(method, url, data, CONTENT_TYPE_APPLICATION_JSON_HEADER, AuthorizationHeader);
    }

    public static Response authorizedRequestWithoutBody(Method method, String url) {
        return requestWithoutBody(method, url, CONTENT_TYPE_APPLICATION_JSON_HEADER, AuthorizationHeader);
    }

    public static boolean isUserJoinedEvent(int eventID) {
        return given().header(CONTENT_TYPE_APPLICATION_JSON_HEADER).header(AuthorizationHeader)
                .param("event_id", eventID)
                .when().get("/is-user-joined-event/")
                .then()
                .statusCode(200)
                .extract()
                .path("status");
    }

}
