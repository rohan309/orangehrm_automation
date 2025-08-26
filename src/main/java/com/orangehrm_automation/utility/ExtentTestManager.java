package com.orangehrm_automation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> threadDetails= new HashMap<>();
    static ExtentReports extentReports= ExtentReportManager.getInstance();

    public static synchronized ExtentTest getTest() {
        int threadNum = (int)Thread.currentThread().getId();  // get the thread num
        ExtentTest test= threadDetails.get(threadNum);
        return test;

    }

    public static synchronized void endTest() {
        extentReports.flush();
    }


    public static synchronized void createTest(String methodName) {
        ExtentTest test= extentReports.createTest(methodName);
        threadDetails.put((int)Thread.currentThread().getId(), test);
    }



}
