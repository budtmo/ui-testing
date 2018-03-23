package appium.android.parallel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidTestBase {
    private static final String URL = "http://127.0.0.1:4444/wd/hub";
    private static final String APK = "/opt/sample_apk_debug.apk";

    protected AppiumDriver<WebElement> driver;

    @Parameters({"platformVersion", "deviceName", "browserName"})
    @BeforeClass
    public void beforTest(String platformVersion, String deviceName, String browserName) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);

        if (browserName.equals(BrowserType.ANDROID)) {
            desiredCapabilities.setCapability(MobileCapabilityType.APP, APK);
        }

        driver = new AndroidDriver<>(new URL(URL), desiredCapabilities);
    }

    @AfterClass
    public void afterTest(){
        if (driver != null) {
            driver.quit();
        }
    }
}
