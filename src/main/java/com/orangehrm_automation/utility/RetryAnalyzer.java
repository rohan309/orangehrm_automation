package com.orangehrm_automation.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int minCount = 0;
    static final int maxCount = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (minCount < maxCount) {
            minCount++;
            return true;
        }
        return false;
    }
}
