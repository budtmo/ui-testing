package com.demo.parallel.database;

import org.influxdb.dto.Point;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        Map<String, String> testConfig = getTestConfiguration();

        Point content = Point.measurement("result")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("platform", testConfig.get("platform"))
                .tag("device", testConfig.get("device"))
                .tag("emulator", testConfig.get("emulator"))
                .tag("version", testConfig.get("version"))
                .tag("test class", iTestResult.getTestClass().getName())
                .tag("test name", iTestResult.getName())
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

    private Map<String, String> getTestConfiguration(){
        Map<String, String> config = new HashMap<>();
        ITestResult r = Reporter.getCurrentTestResult();
        XmlTest xmlTest = r.getTestContext().getCurrentXmlTest();
        config.put("platform", xmlTest.getParameter("browser"));
        config.put("device", xmlTest.getParameter("avd"));
        config.put("emulator", xmlTest.getParameter("emulator"));
        config.put("version", xmlTest.getParameter("version"));
        return config;
    }
}
