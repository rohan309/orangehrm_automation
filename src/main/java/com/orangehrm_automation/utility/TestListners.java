package com.orangehrm_automation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListners extends BaseClass implements ITestListener, ISuiteListener {
    String reportPath;
    ExtentReports extentReporter;
    ExtentTest log;

    @Override
    public void onStart(ISuite suite) {
        System.out.println("This is onStart of ISuite from TestListners");
        LocalDateTime dateTime = LocalDateTime.now();
        String currentDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss_SSS"));
        reportPath = System.getProperty("user.dir") + "/reports/" + "report_" + currentDateTime;
        System.out.println("ReportPath is " + reportPath);
        File file = new File(reportPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String htmlReport = reportPath + "/AutomationReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(htmlReport);
        extentReporter = new ExtentReports();
        extentReporter.attachReporter(sparkReporter);

        extentReporter.setSystemInfo("user", "Rohan More");
        extentReporter.setSystemInfo("os", "windows");
        extentReporter.setSystemInfo("environment", "QA");
        String suiteName = suite.getName();
        System.out.println(suiteName);

    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("This is onFinish of ISuite from TestListners");
        extentReporter.flush();

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("This is onStart of ITestContext from TestListners");

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("This is onFinish of ITestContest from TestListners");


    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("This is onTestStart of ITestResult from TestListners");
        String methodName = result.getMethod().getMethodName();
//		String methodName = result.getName();
        log = extentReporter.createTest(methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        /*System.out.println("This is onTestSuccess of ITestResult from TestListners");
        System.out.println("ReportPath is " + reportPath);
        String screenShot = reportPath + "/" + result.getMethod().getMethodName() + ".jpg";
        System.out.println("ScreenShot file name " + screenShot);

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        System.out.println("Take screenShot " + takesScreenshot);
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenShot);

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
            // throw new RuntimeException(e);
        }*/
        System.out.println("Test pass : " + result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case fail : " + result.getName());

        /*log.fail(result.getThrowable().getMessage());
        String screenShotPath = reportPath + "/" + result.getMethod().getMethodName() + ".jpg";
        log.addScreenCaptureFromPath(screenShotPath, "Failed screenshot");
//        takeScreenshot(result.getName());
        captureScreenshot(result, driver, reportPath);*/
        /*String screenShot = reportPath + "/" + result.getMethod().getMethodName() + ".jpg";
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenShot);

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            // e.printStackTrace(); throw new RuntimeException(e);
        }*/

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String screenshotName = result.getName() + "_failure.png";
            File destinationFile = new File(reportPath+"/screenshots/" + screenshotName);
            FileUtils.copyFile(sourceFile, destinationFile);
            System.out.println("Screenshot captured and saved at: " + destinationFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("This is onTestSkipped of ITestResult from TestListners");


    }


}
