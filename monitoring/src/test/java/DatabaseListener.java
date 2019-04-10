import org.influxdb.dto.Point;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

import java.util.concurrent.TimeUnit;

public class DatabaseListener implements ITestListener {
    static XmlTest config;

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
        config = iTestContext.getCurrentXmlTest();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    public void sendTestResult(ITestResult iTestResult, String status) {
        Point content = Point.measurement("result")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("testclass", iTestResult.getTestClass().getName())
                .tag("name", iTestResult.getName())
                .tag("os", config.getParameter("browserName"))
                .tag("device", config.getParameter("deviceName"))
                .tag("android version", config.getParameter("platformVersion"))
                .tag("status", status)
                .addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis())).build();

        Database.save(content);
    }
}
