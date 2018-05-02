package appium.android.parallel.testcases;

import appium.android.parallel.AndroidTestBase;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.ScreenRecordingUploadOptions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidAppTest extends AndroidTestBase {

    public static final String PATH_FILE = "/tmp/test.mp4";

    @BeforeMethod
    public void setUp(){
        ScreenRecordingUploadOptions uOpt = new ScreenRecordingUploadOptions().withRemotePath(PATH_FILE);

        AndroidStartScreenRecordingOptions rOpt = new AndroidStartScreenRecordingOptions().
                withUploadOptions(uOpt);

        driver.startRecordingScreen(rOpt);
    }

    @Test
    public void searchForAppiumConf(){
        driver.findElementByClassName("android.widget.Button").isDisplayed();

        driver.findElementById("first_input").sendKeys(Integer.toString(3));
        driver.findElementById("second_input").sendKeys(Integer.toString(7));
        driver.findElementById("btn_calculate").click();

        WebElement resultField = driver.findElementById("result");
        Assert.assertEquals(resultField.getText(), "10");
    }

    @AfterMethod
    public void tearDown(){
        String result = driver.stopRecordingScreen();
        decoder(result, PATH_FILE);
    }
}
