package scenario;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.ios.IOSDriver;



public class iPhoneAutomationTest {

    private IOSDriver<MobileElement> iosDriver;

    private AppiumDriverLocalService service;


    @BeforeClass
    public void setUp() throws Exception {

        service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(
                        new File(
                                "/usr/local/lib/node_modules/appium/build/lib/appium.js"))
                .withLogFile(new File("/Users/vitalii/Desktop/log.txt"))
                .withIPAddress("127.0.0.1").usingPort(4723));

        service.start();



        DesiredCapabilities capabilities = DesiredCapabilities.iphone();
        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iPhone 5");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("browserName", "Safari");

        iosDriver = new IOSDriver<MobileElement>(service.getUrl(), capabilities);
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void getAppiumStatus() {
        iosDriver.navigate().to("http://www.baidu.com");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByName("wd").sendKeys("appium desired capability");
        iosDriver.findElement(By.name("wd")).clear();
    }

    @AfterClass
    public void tearDown() {
        iosDriver.quit();
        service.stop();
    }
}