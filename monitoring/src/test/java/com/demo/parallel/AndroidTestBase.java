package com.demo.parallel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidTestBase {
    private static final String APK = "/root/tmp/sample_apk/sample_apk_debug.apk";

    protected AndroidDriver<WebElement> driver;

    @Parameters({"browser", "avd", "device", "version", "port"})
    @BeforeClass
    public void setUpDriver(@Optional("android") String platform,
                            @Optional("nexus_5") String device,
                            @Optional("emulator-5554") String emulator,
                            @Optional("7.1.1") String version,
                            @Optional("4723") String appiumPort) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, platform);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, emulator);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);

        if (!device.equals("genymotion")) {
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.AVD, device);
        }

        if (platform.equals(BrowserType.ANDROID)) {
            desiredCapabilities.setCapability(MobileCapabilityType.APP, APK);
        }

        String appiumUrl = "http://127.0.0.1:" + appiumPort + "/wd/hub";
        driver = new AndroidDriver<>(new URL(appiumUrl), desiredCapabilities);
    }

    @AfterClass
    public void tearDownDriver(){
        if (driver != null) {
            driver.quit();
        }
    }
}