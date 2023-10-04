package com.orangehrm_automation;

import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.pages.RecruitmentPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import org.testng.Assert;
import org.testng.annotations.*;

public class Recruitment extends BaseClass {
    PropertyHandling propertyHandling;

    @BeforeClass
    public void beforeClass() {
        propertyHandling = new PropertyHandling();
        String browser = propertyHandling.getProperties("browser");
        launchBrowser(browser);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(propertyHandling.getProperties("orangeHrmUrl"));
        new LoginPage(driver).login(propertyHandling.getProperties("orangeUserName"), propertyHandling.getProperties("orangeHrmPassword"));

    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    public void test() {
        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);
        click(recruitmentPage.recruitmentModule);
        click(recruitmentPage.addButton);

        enterText(recruitmentPage.firstName, propertyHandling.getProperties("firstName"));
        enterText(recruitmentPage.lastName, propertyHandling.getProperties("lastName"));
        click(recruitmentPage.dropDown);
        selectFromDropDown(recruitmentPage.valuesInDropDown);
        enterText(recruitmentPage.emailTextField, propertyHandling.getProperties("emailID"));
        click(recruitmentPage.resumeUpload);
        fileUpload(propertyHandling.getProperties("autoItScript"), propertyHandling.getProperties("resumeFile"));
        scrollToElement(driver, recruitmentPage.saveButton);
        click(recruitmentPage.saveButton);

        waitForElementToBeVisible(recruitmentPage.savedMsg);
        String text=driver.findElement(recruitmentPage.savedMsg).getText();
        Assert.assertEquals(text, propertyHandling.getProperties("savedMsg"), "Assertion failed: Test case didn't pass.");


    }

}
