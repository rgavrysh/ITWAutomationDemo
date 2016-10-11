package scenario;

import org.testng.annotations.DataProvider;

/**
 * Created by rgavrysh on 10/11/2016.
 */
public class TestDataProvider {

    @DataProvider(name = "loginParameters")
    public static Object[][] loginParameters(){
        return new Object[][] {{"admin@admin.com", "!tweekend123"}};
    }
}
