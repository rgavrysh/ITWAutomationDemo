package apiTests.util;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Created by rgavrysh on 10/12/2016.
 */
public class JsonUtil {

    public static JsonObject buildJson(String[][] properties){
        JsonObject jsonObject = new JsonObject();
        for (String[] prop : properties){
            jsonObject.addProperty(prop[0], prop[1]);
        }
        return jsonObject;
    }

    public static JsonPath normalizeJSON(Response response) {
        return new JsonPath(response.body().asString()
                .replace("\\\"", "\"")
                .replace("\"{", "{")
                .replace("}\"", "}"));
    }
}
