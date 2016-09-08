package scenario;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import io.appium.java_client.remote.MobileCapabilityType;
import scenario.DevicesSetup;

import java.io.File;


public class AndroidTest extends DevicesSetup {

    @BeforeClass

    public void setUp() throws Exception {
       /* AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .withAppiumJS(new File("/usr/local/bin/appium.js"))
                        .withIPAddress("0.0.0.0")
                        .usingPort(4723)
        );





        service.start();*/
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "LGD8025b18f838");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0");
        capabilities.setCapability(MobileCapabilityType.APP, getApp("itweekend201608252.apk"));
//        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".view.DragAndDropDemo");
        super.setUpAndroidDriver();

    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void falseLoginTest() throws InterruptedException {
        new LoginPage(driver).invalidLogin();
    }

    @Test
    public void successLoginTest() throws InterruptedException {
        new LoginPage(driver).successLogin();
    }

/*    @Test
    public void fbSuccessLogin() throws InterruptedException {
        new LoginPage(driver).fbSuccessLogin();
    } */
}
