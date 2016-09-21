package scenario;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by vitalii on 9/15/16.
 */
public class AndroidAutomationTest {

    private AndroidDriver <MobileElement> androidDriver;

    private AppiumDriverLocalService service;

    public AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        /*protected String getApp(String appFile) {
            File projectRoot = new File(System.getProperty("user.dir"));
            File appDir = new File(projectRoot, "apps");
            File app = new File(appDir, appFile);
            return app.toString();
        }*/

        File path = new File("/Users/vitalii/ITWAutomation/apps/itweekend_201609192.apk");

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

        /*capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "LGD8025b18f838");
        capabilities.setCapability("platformVersion", "5.0");
        capabilities.setCapability("version", "5.0");
        capabilities.setCapability("app", path.getAbsolutePath());
        capabilities.setCapability("browserName", "Chrome");*/

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "LGD8025b18f838");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/vitalii/ITWAutomation/apps/itweekend_201609192.apk");
//        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");



        androidDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
/*    public void getAppiumStatus() {
        androidDriver.navigate().to("http://www.baidu.com");
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        androidDriver.findElementByName("wd").sendKeys("appium desired capability");
        androidDriver.findElement(By.name("wd")).clear();
    }*/

    /*public void falseLoginTest() throws InterruptedException {
        new LoginPage(driver).invalidLogin();
    }*/

    @AfterClass
    public void tearDown() {
        androidDriver.quit();
        service.stop();
    }




}
