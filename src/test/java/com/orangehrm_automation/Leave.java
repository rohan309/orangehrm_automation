package com.orangehrm_automation;

import com.orangehrm_automation.pages.LeavePage;
import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class Leave extends BaseClass {
    PropertyHandling propertyHandling;
    LoginPage loginPage;
    LeavePage leavePage;

    @BeforeClass
    public void beforeClass() {
        propertyHandling = new PropertyHandling();
        launchBrowser(propertyHandling.getProperties("browser"));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(propertyHandling.getProperties("orangeHrmUrl"));
        loginPage = new LoginPage(driver);
        loginPage.login(propertyHandling.getProperties("orangeUserName"), propertyHandling.getProperties("orangeHrmPassword"));
    }

    @AfterMethod
    public void afterMethod() {
        click(leavePage.profileDropDown);
        click(leavePage.logOutBtn);
        driver.close();
    }

    @Test
    public void leave() throws InterruptedException {
        leavePage = new LeavePage(driver);
        click(leavePage.leaveModule);
        click(leavePage.applySbModule);

        selectDate(leavePage.fromDate, propertyHandling.getProperties("leaveFromDate"));

        click(leavePage.applyBtn);
        waitForElementToBeVisible(leavePage.successMsg);
        Assert.assertTrue(driver.findElement(leavePage.successMsg).isDisplayed());
    }

    public void selectDate(By by, String date) {
        click(by);
        String[] format = date.split(",");
        String day=format[0];
        String month=format[1];
        String year=format[2];
        List<WebElement> years=driver.findElements(leavePage.yearDropDown);
        for (WebElement ele:years) {

            String yr=ele.getText();
            if (yr.equals(year)){
                ele.click();
            }
        }
        click(leavePage.monthDropDown);
        List<WebElement> months=driver.findElements(leavePage.monthDropDown);
        for (WebElement ele:months) {
            String mnth=ele.getText();
            if (mnth.equals(month)){
                ele.click();
            }
        }
        List<WebElement> allDates=driver.findElements(leavePage.dateTable);
        for (WebElement days:allDates) {
                String theDay=days.getText();
                if (theDay.equals(day)){
                    days.click();
                }
        }
    }

}
