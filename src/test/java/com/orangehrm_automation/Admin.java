package com.orangehrm_automation;

import com.orangehrm_automation.pages.AdminPage;
import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.pages.PIMPage;
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
    String job;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browser) {
        adminPage = new AdminPage(driver);
        propertyHandling = new PropertyHandling();
//        String browser=propertyHandling.getProperties("browser");
        launchBrowser(browser);
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
//        driver.close();
    }

    @Test
    public void corporateBranding() throws InterruptedException {

        waitForElementToBeVisible(adminPage.adminModule);
        click(adminPage.adminModule);

        waitForElementToBeVisible(adminPage.corporateBranding);
        click(adminPage.corporateBranding);

        waitForElementToBeVisible(adminPage.bannerBrowse);

        scrollToElement(adminPage.resetDefault);
        waitForElementToBeVisible(adminPage.resetDefault);

//        System.out.println(driver.findElement(adminPage.resetDefault).isDisplayed() ? "Reset button displayed" : "Reset button is NOT displayed");

        if (driver.findElement(adminPage.resetDefault).isDisplayed()) {
            System.out.println("Reset button displayed");
        } else {
            throw new RuntimeException("Reset button is NOT displayed");
        }

        click(adminPage.resetDefault);
        System.out.println("Reset action finished");

//        Thread.sleep(3000);
        driver.navigate().refresh();

        click(adminPage.adminModule);

        waitForElementToBeVisible(adminPage.corporateBranding);
        click(adminPage.corporateBranding);
        System.out.println("Clicked on corporate branding");


        waitForElementToBeVisible(adminPage.primaryColor);
        click(adminPage.primaryColor);

        Actions actions = new Actions(driver);

        WebElement pointer = driver.findElement(adminPage.pointer);
        int width = pointer.getSize().getWidth();
        int height = pointer.getSize().getHeight();
        int safeOffsetX = Math.max(-width / 2, -10);
        int safeOffsetY = Math.max(height / 2, 10);

        WebElement src = driver.findElement(adminPage.pointer);
//        actions.clickAndHold(src).moveByOffset(-250, 250).release().build().perform();
        actions.clickAndHold(src).moveByOffset(safeOffsetX, safeOffsetY).release().build().perform();

        click(adminPage.secondaryColor);

        waitForElementToRefresh(adminPage.pointer);
        WebElement src1 = driver.findElement(adminPage.pointer);
//        actions.clickAndHold(src1).moveByOffset(-250, 250).release().build().perform();
        actions.clickAndHold(src1).moveByOffset(safeOffsetX, safeOffsetY).release().build().perform();

        click(adminPage.primaryGradient1);

        waitForElementToRefresh(adminPage.pointer);
        WebElement src2 = driver.findElement(adminPage.pointer);
//        actions.clickAndHold(src2).moveByOffset(-250, 250).release().build().perform();
        actions.clickAndHold(src2).moveByOffset(safeOffsetX, safeOffsetY).release().build().perform();

        click(adminPage.primaryGradient2);

        waitForElementToRefresh(adminPage.pointer);
        WebElement src3 = driver.findElement(adminPage.pointer);
//        actions.clickAndHold(src3).moveByOffset(-250, 250).release().build().perform();
        actions.clickAndHold(src3).moveByOffset(safeOffsetX, safeOffsetY).release().build().perform();

        System.out.println("Color reset actions performed");
        LoginPage loginPage=new LoginPage(driver);
//        click(loginPage.profileName);
//        Thread.sleep(5000);
        scrollToElement(adminPage.logoBrowse);
//        Thread.sleep(3000);

        waitForElementToBeVisible(adminPage.logoBrowse);
        click(adminPage.logoBrowse);
        fileUpload(propertyHandling.getProperties("clientLogo"));

//        waitForElementToBeVisible(adminPage.bannerBrowse);
        click(adminPage.bannerBrowse);
        fileUpload(propertyHandling.getProperties("loginBanner"));

        scrollToElement(adminPage.publishBtn);
        click(adminPage.publishBtn);

        waitForElementToBeVisible(adminPage.successMsg);
        Assert.assertTrue(driver.findElement(adminPage.successMsg).isDisplayed());
        System.out.println("Corporate Branding applied successfully");
    }

    @Test(priority = 2)
    public void createJob() {
        click(adminPage.adminModule);
        job = propertyHandling.getProperties("jobTitles");
        dropDown(adminPage.jobSubModule, adminPage.jobValues, job);
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

    @Test(dependsOnMethods = "createJob")
    public void verifyJob() {
        click(adminPage.adminModule);
        dropDown(adminPage.jobSubModule, adminPage.jobValues, propertyHandling.getProperties("jobTitles"));
        List<WebElement> jobs = driver.findElements(adminPage.jobTable);
        List<String> jobList = new ArrayList<>();
        for (WebElement ele : jobs) {
            String str = ele.getText();
            jobList.add(str);
        }
        Assert.assertTrue(jobList.contains(job));
    }

    @Test
    public void skills() {
        click(adminPage.adminModule);
        click(adminPage.qualification);

        List<WebElement> qualificationDropDown = driver.findElements(adminPage.qulificationDropDown);
        for (WebElement ele : qualificationDropDown) {
            System.out.println(ele.getText());
            if (ele.getText().equalsIgnoreCase(propertyHandling.getProperties("Skills"))) {
                ele.click();
                break;
            }
        }

        waitForElementToBeVisible(adminPage.addBtn);
        click(adminPage.addBtn);
        enterText(adminPage.skillName, propertyHandling.getProperties("skillName"));
        click(adminPage.saveBtn);
        waitForElementToBeVisible(adminPage.successMsg);
        Assert.assertTrue(driver.findElement(adminPage.successMsg).isDisplayed(), "Skill not added successfully...");
    }

    @Test(dependsOnMethods = "skills")
    public void verifySkills() {
        PIMPage pimPage = new PIMPage(driver);
        click(pimPage.pimModule);
        click(pimPage.addBtn);
        waitForElementToBeVisible(pimPage.saveBtn);
        enterText(pimPage.empFirstName, propertyHandling.getProperties("empFirstName"));
        enterText(pimPage.empLastName, propertyHandling.getProperties("empLastName"));
        click(pimPage.saveBtn);
        waitForElementToBeVisible(pimPage.successMsg);
        Assert.assertTrue(driver.findElement(pimPage.successMsg).isDisplayed());

        waitForElementToBeVisible(pimPage.empImage);
        scrollToElement(pimPage.salaryTab);
        Assert.assertTrue(driver.findElement(pimPage.salaryTab).isDisplayed());
        waitForElementToBeVisible(pimPage.qualificationTab);
        click(pimPage.qualificationTab);
        scrollToElement(pimPage.dependents);
        click(pimPage.addSkill);
        click(pimPage.skillsTextField);
        Assert.assertTrue(driver.findElement(pimPage.skillsTextField).isDisplayed());
        waitForElementToBeVisible(pimPage.skillsDropDown);
        List<WebElement> elements = driver.findElements(pimPage.skillsDropDown);
        System.out.println("Elements are " + elements + " Size is : " + elements.size());
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equalsIgnoreCase(propertyHandling.getProperties("skillName"))) {
                System.out.println("Skill is displayed : " + elements.get(i).getText());
                Assert.assertEquals(propertyHandling.getProperties("skillName"), elements.get(i).getText());
                break;
            }
        }

    }
}
