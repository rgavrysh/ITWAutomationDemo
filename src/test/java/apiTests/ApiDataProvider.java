package apiTests;

import org.testng.annotations.DataProvider;

/**
 * Created by rgavrysh on 10/12/2016.
 */
public class ApiDataProvider {

    @DataProvider(name = "eventID")
    public static Object[][] getEventID() {
        return new Object[][]{{24}};
    }
}
