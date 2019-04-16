import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@CucumberOptions(strict = true, plugin = "json:target/cucumber-report-feature-composite.json")
public class CukesRunnerTest {
    private TestNGCucumberRunner testNGCucumberRunner;

    public static String os, device, version;

    @Parameters({"browserName", "deviceName", "platformVersion"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass (@Optional("android") String browserName, @Optional("ce11160bbaee9c3b05") String deviceName,
                            @Optional("7.0") String platformVersion) throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        os = browserName;
        device = deviceName;
        version = platformVersion;
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}