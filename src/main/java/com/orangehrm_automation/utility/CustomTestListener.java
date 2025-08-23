package com.orangehrm_automation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomTestListener implements ITestListener {
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        extentReports = new ExtentReports();
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss"));
        String reportDirectory = System.getProperty("user.dir") + "/test-output/ExtentReports_" + timestamp;
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportDirectory);
        extentReports.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.fail("Test failed");
        captureScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    private void captureScreenshot(String testName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String screenshotName = testName + ".png";
            File destinationFile = new File("screenshots/" + screenshotName);
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // You can implement other methods as needed
}
