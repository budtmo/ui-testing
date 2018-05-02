package appium.android.parallel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class AndroidTestBase {
    private static final String URL = "http://127.0.0.1:4444/wd/hub";
    private static final String APK = "/opt/sample_apk_debug.apk";

    protected AndroidDriver<WebElement> driver;

    @Parameters({"platformVersion", "deviceName", "browserName"})
    @BeforeClass
    public void beforeClass(@Optional("7.0") String platformVersion, @Optional("ce11160bbaee9c3b05") String deviceName,
                            @Optional("android") String browserName) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);

        if (browserName.equals(BrowserType.ANDROID)) {
            desiredCapabilities.setCapability(MobileCapabilityType.APP, APK);
        }

        driver = new AndroidDriver<>(new URL(URL), desiredCapabilities);
    }

    public static void decoder(String base64, String pathFile) {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            byte[] ByteArray = Base64.getMimeDecoder().decode(base64);
            System.out.println(ByteArray);
            imageOutFile.write(ByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }

    @AfterClass
    public void afterClass(){
        if (driver != null) {
            driver.quit();
        }
    }
}
