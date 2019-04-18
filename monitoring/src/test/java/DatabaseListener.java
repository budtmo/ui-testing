import org.influxdb.dto.Point;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class DatabaseListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        sendTestResult(iTestResult, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        sendTestResult(iTestResult, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        sendTestResult(iTestResult, "SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    public void sendTestResult(ITestResult iTestResult, String status) {
        Point content = Point.measurement("result")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("feature", CalculatorTestRunner.getCurrentFeature())
                .tag("scenario", CalculatorTestRunner.getCurrentScenario())
                .tag("os", CalculatorTestRunner.getOS())
                .tag("device", CalculatorTestRunner.getDevice())
                .tag("android version", CalculatorTestRunner.getVersion())
                .tag("status", status)
                .addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis())).build();
        Database.save(content);
    }
}
