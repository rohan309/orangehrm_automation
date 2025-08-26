package com.orangehrm_automation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListners implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        System.out.println("This is onStart of ISuite from TestListners");
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
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("This is onTestStart of ITestResult from TestListners");
        String methodName = result.getMethod().getMethodName();
        ExtentTestManager.createTest(methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("This is onTestSuccess of ITestResult from TestListners");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("This is onTestFailure of ITestResult from TestListeners");
        ExtentTest test = ExtentTestManager.getTest();
        test.fail(result.getThrowable());

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseClass) testClass).driver;   // public driver access
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));

        if (driver != null) {
            /*// ✅ Fixed screenshot folder
            String reportPath = System.getProperty("user.dir") + "/reports/screenshots/";
            File dir = new File(reportPath);
            if (!dir.exists()) dir.mkdirs();

            // Filename with timestamp
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));
            String fileName = result.getMethod().getMethodName() + "_" + timeStamp + ".jpg";

            String screenShotPath = reportPath + fileName;

            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
                File destinationFile = new File(screenShotPath);
                FileUtils.copyFile(sourceFile, destinationFile);

                System.out.println("Screenshot captured: " + screenShotPath);

                // ✅ Relative path for HTML report
                String relativePath = "screenshots/" + fileName;

                // 🔹 Attach screenshot in report
                test.fail("Failed Step Screenshot:")
                        .addScreenCaptureFromPath(screenShotPath);*/

            // Put screenshots in the same folder as the HTML report
            String reportDir = ExtentReportManager.reportPath;
            String screenShotDir = reportDir + "/screenshots/";

// Ensure folder exists
            File dir = new File(screenShotDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = result.getMethod().getMethodName() + "_" + timeStamp + ".jpg";
            String screenShotPath = screenShotDir + fileName;

// Save screenshot
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenShotPath);
            try {
                FileUtils.copyFile(sourceFile, destinationFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

// Attach using relative path (works since inside same report folder)
            test.fail("Failed Step Screenshot:").addScreenCaptureFromPath("screenshots/" + fileName);



    } else

    {
        System.out.println("Driver is null, screenshot not captured!");
    }
}


@Override
public void onTestSkipped(ITestResult result) {
    System.out.println("This is onTestSkipped of ITestResult from TestListners");
}
}
