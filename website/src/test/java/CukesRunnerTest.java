import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty", "html:target/cucumber-html-report"})
public class CukesRunnerTest extends AbstractTestNGCucumberTests {
}
