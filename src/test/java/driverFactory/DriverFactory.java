package driverFactory;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rgavrysh on 10/6/2016.
 */
public class DriverFactory {

    private String platformName;

    private DriverFactory() {
        this.platformName = MainProperties.getProperty("platform_name");
    }

    private static final DriverFactory FACTORY_INSTANCE = new DriverFactory();

    public static DriverFactory getFactoryInstance() {
        return FACTORY_INSTANCE;
    }

    private ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>() {
        @Override
        protected AppiumDriver initialValue() {
            if (platformName.toLowerCase().equals("android")) {
                return new AndroidDriver(getAppiumRemoteAddress(), getAndroidCapabilities());
            } else if (platformName.toLowerCase().equals("ios")) {
                return new IOSDriver(getAppiumRemoteAddress(), getIOsCapabilities());
            } else
                throw new WebDriverException("Not supported driver type - " + platformName);
        }
    };

    private DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MainProperties.getProperty("platform_name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, MainProperties.getProperty("android_platform_version"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, MainProperties.getProperty("android_device_name"));
        capabilities.setCapability(MobileCapabilityType.APP, MainProperties.getProperty("android_apk_path"));
        return capabilities;
    }

    private DesiredCapabilities getIOsCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.iphone();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MainProperties.getProperty("platform_name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, MainProperties.getProperty("ios_platform_version"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, MainProperties.getProperty("ios_device_name"));
        capabilities.setCapability(MobileCapabilityType.APP, MainProperties.getProperty("ios_app_path"));
        return capabilities;
    }

    private URL getAppiumRemoteAddress() {
        URL appiumAddress = null;
        try {
            appiumAddress = new URL(MainProperties.getProperty("appium_remote_address"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return appiumAddress;
    }

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void closeDriver() {
        driver.get().closeApp();
        driver.get().quit();
    }
}
