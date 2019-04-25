import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import gherkin.events.PickleEvent;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@CucumberOptions(strict = true)
public class CalculatorTestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
    private static String os, device, emulator, version, currentFeature, currentScenario;

    @Parameters({"browser", "avd", "emulator", "version"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass (@Optional("android") String browserName,
                             @Optional("nexus_5") String avdName,
                             @Optional("ce11160bbaee9c3b05") String emulatorName,
                             @Optional("7.0") String platformVersion) throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        os = browserName;
        device = avdName;
        emulator = emulatorName;
        version = platformVersion;
        Configuration.load();
    }

    @Test(dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        PickleEvent event = pickleEvent.getPickleEvent();
        currentFeature = cucumberFeature.toString();
        currentScenario = event.pickle.getName();
        testNGCucumberRunner.runScenario(event);
    }

    @DataProvider(name = "scenarios")
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

    public static String getOS() {
        return os;
    }

    public static String getDevice() {
        return device;
    }

    public static String getEmulator() {return emulator;}

    public static String getVersion() {
        return version;
    }

    public static String getCurrentFeature() {
        return currentFeature;
    }

    public static String getCurrentScenario() {
        return currentScenario;
    }
}