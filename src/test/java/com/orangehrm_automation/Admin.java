package com.orangehrm_automation;

import com.orangehrm_automation.pages.AdminPage;
import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class Admin extends BaseClass {
    PropertyHandling propertyHandling;
    AdminPage adminPage;

    @BeforeClass
    public void beforeClass() {
        adminPage = new AdminPage(driver);
        propertyHandling = new PropertyHandling();
        launchBrowser(propertyHandling.getProperties("browser"));
        driver.get(propertyHandling.getProperties("orangeHrmUrl"));


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(propertyHandling.getProperties("orangeUserName"), propertyHandling.getProperties("orangeHrmPassword"));
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void corporateBranding() throws InterruptedException {
        click(adminPage.adminModule);
        click(adminPage.corporateBranding);
        waitForElementToBeVisible(adminPage.corporateBranding);

        click(adminPage.primaryColor);
        WebElement src = driver.findElement(adminPage.pointer);

        Actions actions = new Actions(driver);
        actions.clickAndHold(src).moveByOffset(-250, -250).release().build().perform();

        click(adminPage.secondaryColor);
        waitForElementToBeVisible(adminPage.pointer);

        WebElement src1 = driver.findElement(adminPage.pointer);
        actions.clickAndHold(src1).moveByOffset(-250, -250).release().build().perform();

        click(adminPage.primaryGradient1);
        waitForElementToBeVisible(adminPage.pointer);

        WebElement src2 = driver.findElement(adminPage.pointer);
        actions.clickAndHold(src2).moveByOffset(-250, -250).release().build().perform();

        click(adminPage.primaryGradient2);
        waitForElementToBeVisible(adminPage.pointer);
        WebElement src3 = driver.findElement(adminPage.pointer);
        actions.clickAndHold(src3).moveByOffset(-250, -250).release().build().perform();

        scrollToBottom();

        click(adminPage.logoBrowse);
        fileUpload(propertyHandling.getProperties("autoItScript"), propertyHandling.getProperties("clientLogo"));

        click(adminPage.bannerBrowse);
        fileUpload(propertyHandling.getProperties("autoItScript"), propertyHandling.getProperties("loginBanner"));

        click(adminPage.publishBtn);
        waitForElementToBeVisible(adminPage.successMsg);
        Assert.assertTrue(driver.findElement(adminPage.successMsg).isDisplayed());


    }

    @Test
    public void createJob() {
        propertyHandling = new PropertyHandling();
        click(adminPage.adminModule);
        dropDown(adminPage.jobSubModule, adminPage.jobValues, propertyHandling.getProperties("jobTitles"));
        List<WebElement> jobs = driver.findElements(adminPage.jobTable);
        List<String> list = new ArrayList<>();
        String text = null;
        for (WebElement ele : jobs) {
            text = ele.getText();
            list.add(text);
        }

        if (!list.contains(propertyHandling.getProperties("job"))) {
            click(adminPage.addBtn);

            enterText(adminPage.jobTitle, propertyHandling.getProperties("job"));
            click(adminPage.saveBtn);
            waitForElementToBeVisible(adminPage.successMsg);
            Assert.assertTrue(driver.findElement(adminPage.successMsg).isDisplayed());
        }


    }

    @Test
    public void verifyJob() {
        click(adminPage.adminModule);
        dropDown(adminPage.jobSubModule, adminPage.jobValues, propertyHandling.getProperties("jobTitles"));
        List<WebElement> jobs = driver.findElements(adminPage.jobTable);
        for (WebElement ele : jobs) {
            String str = ele.getText();
            Assert.assertEquals(propertyHandling.getProperties("job"), str);
        }
    }
}
