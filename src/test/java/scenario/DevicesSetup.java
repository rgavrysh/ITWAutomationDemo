package scenario;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DevicesSetup {
    protected AppiumDriver driver;



//    protected AppiumDriver driver;
    protected DesiredCapabilities capabilities = new DesiredCapabilities();
    protected String appiumServer = "http://127.0.0.1:4723/wd/hub";

    protected String getApp(String appFile) {
        File projectRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(projectRoot, "apps");
        File app = new File(appDir, appFile);
        return app.toString();
    }

    protected void preDriverInit() {
    }

    protected void postDriverInit() throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    protected AndroidDriver setUpAndroidDriver() throws Exception {
        preDriverInit();
        capabilities.setCapability("platformName", "Android");
        driver = new AndroidDriver(new URL(appiumServer), capabilities);
        postDriverInit();
        return (AndroidDriver) driver;
    }

    protected IOSDriver setUpIosDriver() throws Exception {
        preDriverInit();
        capabilities.setCapability("platformName", "iOS");
        driver = new IOSDriver(new URL(appiumServer), capabilities);
        postDriverInit();
        return (IOSDriver) driver;
    }
}