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
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        this.sendTestClassResult(iTestContext);
    }

    public void sendTestClassResult(ITestContext iTestContext) {
        Point point = Point.measurement("testclass")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("name", iTestContext.getAllTestMethods()[0].getTestClass().getName())
                .tag("device", iTestContext.getCurrentXmlTest().getParameter("deviceName"))
                .tag("android version", iTestContext.getCurrentXmlTest().getParameter("platformVersion"))
                .tag("number of passed tests", String.valueOf(iTestContext.getPassedTests().size()))
                .tag("number of failed tests", String.valueOf(iTestContext.getFailedTests().size()))
                .addField("duration", (iTestContext.getEndDate().getTime() - iTestContext.getStartDate().getTime()))
                .build();
        Database.save(point);
    }
}
