import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;


@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty", "html:target/cucumber-html-report"})
public class CukesRunnerTest extends AbstractTestNGCucumberTests {
    // For parallel test -> https://github.com/cucumber/cucumber-jvm/tree/master/testng
    public static String platformVersion, device, browser;

    @Parameters({"platformVersion", "deviceName", "browserName"})
    @BeforeClass
    public void beforeClass(@Optional("7.0") String platformVersion, @Optional("ce11160bbaee9c3b05") String deviceName,
                            @Optional("android") String browserName) throws IOException {
        platformVersion = platformVersion;
        device = deviceName;
        browser = browserName;
    }
}