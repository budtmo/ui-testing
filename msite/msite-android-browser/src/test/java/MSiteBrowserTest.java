import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MSiteBrowserTest {

    private static final String URL = "http://127.0.0.1:4444/wd/hub";
    private AppiumDriver <WebElement> driver;

    @Before
    public void prepare() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
        //desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "browser");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        driver = new AndroidDriver<>(new URL(URL), desiredCapabilities);
    }

    @Given("^my personal page$")
    public void personalPage() throws InterruptedException {
        driver.get("https://budtmo.github.io");
    }

    @When("^user press (.*)$")
    public void visitProfilePage(String btnName) {
        WebElement aboutBtn = driver.findElement(By.linkText(btnName));
        aboutBtn.click();
    }

    @Then("^user should see my profile page$")
    public void assertResultPage() {
        driver.findElement(By.xpath("/html/body/div/div/p[2]")).isDisplayed();
        driver.findElement(By.linkText("Home")).isDisplayed();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
