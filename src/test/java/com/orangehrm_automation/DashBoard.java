package com.orangehrm_automation;

import com.orangehrm_automation.pages.DashBoardPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import com.orangehrm_automation.utility.RetryAnalyzer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class DashBoard extends BaseClass {
    PropertyHandling propertyHandling;
    DashBoard dashBoard;
    DashBoardPage dashBoardPage;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Launching browser in before class");
        dashBoardPage = new DashBoardPage(driver);
        propertyHandling = new PropertyHandling();
        String browser = propertyHandling.getProperties("browser");
        launchBrowser(browser);
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Quiting browser in after class");
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigating to application in before method");
        propertyHandling = new PropertyHandling();
        dashBoardPage = new DashBoardPage(driver);
        String url = propertyHandling.getProperties("orangeHrmUrl");
        String username = propertyHandling.getProperties("orangeUserName");
        String password = propertyHandling.getProperties("orangeHrmPassword");
        driver.get(url);
        waitForElementToBeVisible(dashBoardPage.username);
        enterText(dashBoardPage.username, username);
        enterText(dashBoardPage.password, password);
        click(dashBoardPage.loginButton);
        waitForElementToBeVisible(dashBoardPage.dashBoardPage);
        WebElement element = driver.findElement(dashBoardPage.dashBoardPage);
        if (element.isDisplayed()) {
            System.out.println("DashBoard page is displayed");
        } else {
            System.out.println("Dashboard page is not displayed");
        }
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Closing browser in after class");
//        driver.close();
    }

    //    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Test(enabled = false)
    public void leaveRequestToApprove() {
        System.out.println("Verifying leave request feature");
        click(dashBoardPage.leaveModule);
        waitForElementToBeVisible(dashBoardPage.myLeave);
        click(dashBoardPage.myLeave);
        waitForElementToBeVisible(dashBoardPage.fromDateFeild);
        selectDate(11, 06, 2023);


    }



    public void selectDate(int day, int month, int year) {
        click(dashBoardPage.fromDateFeild);
        List<WebElement> elements = driver.findElements(dashBoardPage.selectFromDate);

        while (true) {//year
            click(dashBoardPage.yearDropDown);
            List<WebElement> yearsList = driver.findElements(dashBoardPage.allYears);
            for (WebElement ReqYear : yearsList) {
                String yearToBeSelect = String.valueOf(year);
                if (ReqYear.equals(yearToBeSelect)) {
                    ReqYear.click();
                    break;
                }
            }
            while (true) {//month
                click(dashBoardPage.goToNextMonth);
                click(dashBoardPage.monthDropDown);
                List<WebElement> monthList = driver.findElements(dashBoardPage.allMonths);
                for (WebElement ReqMonth : monthList) {
                    String monthToBeSelect = String.valueOf(month);
                    if (ReqMonth.equals(monthToBeSelect)) {
                        ReqMonth.click();
                        break;
                    }
                }
                while (true) {//date
                    for (int i = 0; i < elements.size(); i++) {
                        WebElement element = elements.get(i);
                        String date = element.getText();
                        String theDay = String.valueOf(day);
                        if (date.equals(theDay)) {
                            element.click();
                            break;
                        }
                    }
                }
            }
        }
    }
}

