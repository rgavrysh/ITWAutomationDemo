package scenario;

import appium.AppiumController;
import driverFactory.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by rgavrysh on 10/7/2016.
 */
public abstract class BaseTest {

    protected AppiumDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException, InterruptedException {
        AppiumController.startAppiumServer();
        driver = DriverFactory.getFactoryInstance().getDriver();
<<<<<<< Updated upstream
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
=======
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
>>>>>>> Stashed changes
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        DriverFactory.getFactoryInstance().closeDriver();
        AppiumController.stopAppiumServer();
    }
}
