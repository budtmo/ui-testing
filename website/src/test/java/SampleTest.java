import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {

    private static final String URL = "http://127.0.0.1:4444/wd/hub";
    private WebDriver driver;

    @Before
    public void prepare() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
        driver = new RemoteWebDriver(new URL(URL), desiredCapabilities);
        driver.manage().window().maximize();
    }

    @Given("^google search page$")
    public void googlePage() {
        driver.get("https://www.google.de/");
    }

    @When("^user search for selenium")
    public void searchForSelenium() {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("selenium");
        element.submit();
    }

    @Then("^user should see result page")
    public void assertResultPage() {
        driver.findElement(By.id("res")).isDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
