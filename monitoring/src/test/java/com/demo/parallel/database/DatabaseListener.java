package com.demo.parallel.database;

import org.influxdb.dto.Point;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DatabaseListener implements ITestListener {
    public static final String BUILD_NUMBER = System.getenv("BUILD_NUMBER");

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
        XmlTest currentXmlTest = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

        Point content = Point.measurement("result")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("build-nr", this.BUILD_NUMBER)
                .tag("platform", currentXmlTest.getParameter("browser"))
                .tag("device", currentXmlTest.getParameter("avd"))
                .tag("emulator", currentXmlTest.getParameter("emulator"))
                .tag("version", currentXmlTest.getParameter("version"))
                .tag("test-class", iTestResult.getTestClass().getName())
                .tag("test-name", iTestResult.getName())
                .tag("description", getDescription(iTestResult))
                .tag("status", status)
                .addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis())).build();
        Database.save(content);
    }

    private String getDescription(ITestResult iTestResult) {
        String description = "";
        ArrayList<String> tNum = new ArrayList<>();

        for (Object o : iTestResult.getParameters()){
            tNum.add(o.toString());
        }

        try {
            description += tNum.get(0) + " + " + tNum.get(1) + " = " + tNum.get(2);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return description;
    }
}
