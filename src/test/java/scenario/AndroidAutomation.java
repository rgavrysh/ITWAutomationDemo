package scenario;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by vitalii on 9/21/16.
 */
public class AndroidAutomation {
    private AndroidDriver<MobileElement> androidDriver;

    private AppiumDriverLocalService service;

    @BeforeClass
    public void setUp() throws Exception {

        service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File("/usr/local/bin/node"))
                        .withAppiumJS(
                                new File(
                                        "/usr/local/lib/node_modules/appium/build/lib/main.js"))
                        .withLogFile(new File("/Users/vitalii/Desktop/log.txt"))
                        .withIPAddress("127.0.0.1").usingPort(4723));

        service.start();

        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "LGD8025b18f838");
        capabilities.setCapability("platformVersion", "5.0");
        capabilities.setCapability("app", "/Users/vitalii/ITWAutomation/apps/itweekend_201609192.apk");

        androidDriver = new AndroidDriver<MobileElement>(service.getUrl(), capabilities);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void successEmailLoginTest() {
        androidDriver.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.ImageButton[1");

    }

    @AfterClass
    public void tearDown() {
        androidDriver.quit();
        service.stop();
    }
}
