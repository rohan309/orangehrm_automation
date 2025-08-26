package com.orangehrm_automation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {
    public static String reportPath;
    public static ExtentReports extentReporter;

    //singleton pattern
    public static ExtentReports getInstance() {
        if(extentReporter == null) {
            setReportDetails();
        }
        return extentReporter;
    }


    public static void setReportDetails() {
        LocalDateTime dateTime = LocalDateTime.now();
        String currentDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd_mm_yyyy_HH_mm"));
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
    }

}
