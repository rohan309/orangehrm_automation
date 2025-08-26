package com.orangehrm_automation;

import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.pages.RecruitmentPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Recruitment extends BaseClass {
    PropertyHandling propertyHandling;
    private WebDriver driver;
    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            propertyHandling = new PropertyHandling();
            launchBrowser(propertyHandling.getProperties("browser"));

        } else if (browser.equalsIgnoreCase("firefox")) {
            propertyHandling = new PropertyHandling();
            launchBrowser(propertyHandling.getProperties("fbrowser"));
        } /*else {
            propertyHandling = new PropertyHandling();
            launchBrowser(propertyHandling.getProperties("browser"));
        }*/
        driver.get(propertyHandling.getProperties("orangeHrmUrl"));
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
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void addRecruitement() {
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
        scrollToElement(recruitmentPage.saveButton);
        click(recruitmentPage.saveButton);

        waitForElementToBeVisible(recruitmentPage.savedMsg);
        String text = driver.findElement(recruitmentPage.savedMsg).getText();
        Assert.assertEquals(text, propertyHandling.getProperties("savedMsg"), "Assertion failed: Test case didn't pass.");


    }

}
