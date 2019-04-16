import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty", "html:target/cucumber-html-report"})
public class CukesRunnerTest extends AbstractTestNGCucumberTests {
    // For parallel test -> https://github.com/cucumber/cucumber-jvm/tree/master/testng
    public static String os, device, version;

    @Parameters({"browserName", "deviceName", "platformVersion"})
    @BeforeClass
    public void beforeClass(@Optional("android") String browserName, @Optional("ce11160bbaee9c3b05") String deviceName,
                            @Optional("7.0") String platformVersion) throws IOException {
        os = browserName;
        device = deviceName;
        version = platformVersion;
    }
}