import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidUITest {

    private static final String URL = "http://127.0.0.1:4444/wd/hub";
    private AppiumDriver<WebElement> driver;

    @Before
    public void prepare() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "ce11160bbaee9c3b05");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/opt/sample_apk_debug.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        driver = new AndroidDriver<>(new URL(URL), desiredCapabilities);
    }

    @Given("^calculator app$")
    public void calculatorApp() throws InterruptedException {
        driver.findElementByClassName("android.widget.Button").isDisplayed();
    }

    @When("^user sum up (\\d+) and (\\d+)$")
    public void sumUpBetweenTwoNumbers(int firstNumber, int secondNumber) {
        driver.findElementById("first_input").sendKeys(Integer.toString(firstNumber));
        driver.findElementById("second_input").sendKeys(Integer.toString(secondNumber));
        driver.findElementById("btn_calculate").click();
    }

    @Then("^the result must be (.*)$")
    public void assertResult(String expectedResult) {
        WebElement resultField = driver.findElementById("result");
        Assert.assertEquals(resultField.getText(), expectedResult);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
