package com.orangehrm_automation;

import com.orangehrm_automation.pages.AdminPage;
import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.pages.PIMPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class Admin extends BaseClass {
    PropertyHandling propertyHandling;
    AdminPage adminPage;
    WebDriver driver;

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

    @Test(enabled = false)
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

    @Test(enabled = false)
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

    @Test(enabled = false)
    public void verifyJob() {
        click(adminPage.adminModule);
        dropDown(adminPage.jobSubModule, adminPage.jobValues, propertyHandling.getProperties("jobTitles"));
        List<WebElement> jobs = driver.findElements(adminPage.jobTable);
        for (WebElement ele : jobs) {
            String str = ele.getText();
            Assert.assertEquals(propertyHandling.getProperties("job"), str);
        }
    }
    @Test
    public void skills() {
        click(adminPage.adminModule);
        click(adminPage.qualification);

        List<WebElement> qualificationDropDown=driver.findElements(adminPage.qulificationDropDown);
        for (WebElement ele:qualificationDropDown) {
            System.out.println(ele.getText());
            if (ele.getText().equalsIgnoreCase(propertyHandling.getProperties("Skills"))){
                ele.click();
                break;
            }
        }

        waitForElementToBeVisible(adminPage.addBtn);
        click(adminPage.addBtn);
        enterText(adminPage.skillName, propertyHandling.getProperties("skillName"));
        click(adminPage.saveBtn);
        waitForElementToBeVisible(adminPage.successMsg);
        Assert.assertTrue(driver.findElement(adminPage.successMsg).isDisplayed(),"Skill not added successfully...");
    }
    @Test
    public void verifySkills() {
        PIMPage pimPage=new PIMPage(driver);
        click(pimPage.pimModule);
        click(pimPage.addBtn);
        waitForElementToBeVisible(pimPage.saveBtn);
        enterText(pimPage.empFirstName, propertyHandling.getProperties("empFirstName"));
        enterText(pimPage.empLastName, propertyHandling.getProperties("empLastName"));
        click(pimPage.saveBtn);
        waitForElementToBeVisible(pimPage.successMsg);
        Assert.assertTrue(driver.findElement(pimPage.successMsg).isDisplayed());

        waitForElementToBeVisible(pimPage.empImage);
        scrollToElement(driver,pimPage.salaryTab);
        Assert.assertTrue(driver.findElement(pimPage.salaryTab).isDisplayed());
        waitForElementToBeVisible(pimPage.qualificationTab);
        click(pimPage.qualificationTab);
        scrollToElement(driver,pimPage.dependents);
        click(pimPage.addSkill);
        click(pimPage.skillsTextField);
        Assert.assertTrue(driver.findElement(pimPage.skillsTextField).isDisplayed());
        waitForElementToBeVisible(pimPage.skillsDropDown);
        List<WebElement> elements=driver.findElements(pimPage.skillsDropDown);
        System.out.println("Elements are "+elements+" Size is : "+elements.size());
        for (int i =0; i<elements.size();i++) {
            if (elements.get(i).getText().equalsIgnoreCase(propertyHandling.getProperties("skillName"))) {
                System.out.println("Skill is displayed : "+elements.get(i).getText());
                Assert.assertEquals(propertyHandling.getProperties("skillName"), elements.get(i).getText());
                break;
            }
        }

    }
}
