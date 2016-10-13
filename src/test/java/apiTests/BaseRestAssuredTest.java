package apiTests;

import apiTests.util.RequestUtil;
import org.testng.annotations.BeforeTest;

/**
 * Created by rgavrysh on 10/11/2016.
 */
public class BaseRestAssuredTest {

    @BeforeTest
    public void setUpTest() {
        RequestUtil.authorize();
    }
}
