package scenario;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.AppiumDriver;




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
                                "/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withLogFile(new File("/Users/vitalii/Desktop/log.txt"))
                .withIPAddress("127.0.0.1").usingPort(4723));

        service.start();

        DesiredCapabilities capabilities = DesiredCapabilities.iphone();
        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iPhone 5");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("app", "/Users/vitalii/Desktop/IT Weekend.app");

        iosDriver = new IOSDriver<MobileElement>(service.getUrl(), capabilities);
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void successEmailLoginTest() {
        iosDriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[4]/UIATextField[1]").sendKeys("wrdrbtestest@gmail.com");
        iosDriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[5]/UIAButton[1]").click();
    //    iosDriver.findElement(By.xpath("/UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[5]")).sendKeys("wrdrbtestest");
    //    iosDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[5]/UIATextField[1]")).sendKeys("W%d%password");
   //     iosDriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[5]/UIAButton[1]").click();
   //     iosDriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[7]/UIAButton[1]").click();

    }

    @AfterClass
    public void tearDown() {
        iosDriver.quit();
        service.stop();
    }
}