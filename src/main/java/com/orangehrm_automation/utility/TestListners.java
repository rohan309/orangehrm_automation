package com.orangehrm_automation.utility;

import com.aventstack.extentreports.ExtentReports;
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

    @Override
    public void onStart(ISuite suite) {
        System.out.println("This is onStart of ISuite from TestListners");
        LocalDateTime dateTime = LocalDateTime.now();
        String currentDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd_mm_yyyy_hh_mm"));
        reportPath = System.getProperty("user.dir") + "/reports/" + "report_" + currentDateTime;
        System.out.println("ReportPath is " + reportPath);
        File file = new File(reportPath);
        if (!file.exists()) {
            file.mkdir();
        }



    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("This is onFinish of ISuite from TestListners");


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
        String methodName=result.getName();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("This is onTestSuccess of ITestResult from TestListners");
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
            // e.printStackTrace();
            // throw new RuntimeException(e);
            }

        }

        @Override
        public void onTestFailure (ITestResult result){
            System.out.println("This is onTestFailure of ITestResult from TestListners");



        }

        @Override
        public void onTestSkipped (ITestResult result){
            System.out.println("This is onTestSkipped of ITestResult from TestListners");


        }
    }
