package appium.android.parallel.testcases;

import appium.android.parallel.AndroidTestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class AndroidAppTest extends AndroidTestBase {

    @Test
    public void searchForAppiumConf(){
        driver.findElementByClassName("android.widget.Button").isDisplayed();

        driver.findElementById("first_input").sendKeys(Integer.toString(3));
        driver.findElementById("second_input").sendKeys(Integer.toString(7));
        driver.findElementById("btn_calculate").click();

        WebElement resultField = driver.findElementById("result");
        Assert.assertEquals(resultField.getText(), "10");
    }
}
