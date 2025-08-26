package com.orangehrm_automation;

import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
    LoginPage loginPage;
    PropertyHandling properties;

    @BeforeClass
    public void beforeClass() {

        properties = new PropertyHandling();
        String browser = properties.getProperties("browser");
        launchBrowser(browser);
        loginPage = new LoginPage(driver);
        String url = properties.getProperties("orangeHrmUrl");
        driver.get(url);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test(priority = 1)
    public void loginWithValidCreds() {
        String username = properties.getProperties("orangeUserName");
        String password = properties.getProperties("orangeHrmPassword");
        waitForElementToBeVisible(loginPage.username);
        loginPage.login(username, password);
        waitForElementToBeVisible(loginPage.clientBanner);
        WebElement clientBaner = driver.findElement(loginPage.clientBanner);
        if (clientBaner.isDisplayed()) {
            System.out.println("Landed on home page and signing out");
            loginPage.logOut();
            System.out.println("Title is :" + driver.getTitle());
        }
    }

    @DataProvider
    public Object[][] getLoginData() {
        Object[][] data = new Object[][]{
                {"admin", "admin124"},
                {"admin123", "admin123"},
                {"", ""},
                {"", "admin123"},
                {"admin", ""}
        };
        return data;
    }

    @Test(dataProvider = "getLoginData",priority = 3)
    public void verifyLoginWithInvalidCreds(String username, String password) {
        loginPage = new LoginPage(driver);
        System.out.println("UN :" + username + ", " + " PWD :" + password);
        waitForElementToBeVisible(loginPage.username);
        loginPage.login(username, password);
        if (username == "" || password == "") {
            if (username == "") {
                waitForElementToBeVisible(loginPage.reqMsg);
                Assert.assertTrue(driver.findElement(loginPage.reqMsg).isDisplayed());
            } else if (password == "") {
                waitForElementToBeVisible(loginPage.reqMsg);
                Assert.assertTrue(driver.findElement(loginPage.reqMsg).isDisplayed());
            } else {
                waitForElementToBeVisible(loginPage.reqMsg);
                Assert.assertTrue(driver.findElement(loginPage.reqMsg).isDisplayed());
            }
           /* waitForElementTobeClickable(driver.findElement(loginPage.loginButton));

            Assert.assertTrue(driver.findElement(loginPage.loginButton).isDisplayed());*/
        } else {
            waitForElementToBeVisible(loginPage.errMsgForLogin);
            Assert.assertTrue(driver.findElement(loginPage.errMsgForLogin).isDisplayed());

        }
        driver.navigate().refresh();


        /*waitForElementToBeVisible(loginPage.clientBanner);
        WebElement clientBaner = driver.findElement(loginPage.clientBanner);
        if (clientBaner.isDisplayed()) {
            System.out.println("Landed on home page and signing out");
            loginPage.logOut();
            System.out.println("Title is :" + driver.getTitle());
        }*/
    }


    @Test(dataProvider = "socialLinks",priority = 2)
    public void verifyHypLinks(By hypLink, By actionElement, String expectedUrl) {
        String actualUrl = loginPage.handleHypLink(hypLink, actionElement);
        Assert.assertEquals(actualUrl, expectedUrl, "URL mismatch for: " + expectedUrl);
    }

    @DataProvider(name = "socialLinks")
    public Object[][] socialLinks() {
        return new Object[][]{
                {loginPage.facebookHyp, By.xpath("//div[@aria-label='Close']/i"), "https://www.facebook.com/OrangeHRM/"},
                {loginPage.linkedInHyp, null, "https://www.linkedin.com/company/orangehrm"},
                {loginPage.youTubeHyp, null, "https://www.youtube.com/c/OrangeHRMInc"},
                {loginPage.twitterHyp, null, "https://x.com/orangehrm?lang=en"}
        };
    }

    @Test(priority = 4)
    public void forgetPassword() {
        waitForElementToBeVisible(loginPage.forgetPassword);
        click(loginPage.forgetPassword);
        waitForElementToBeVisible(loginPage.username);
        String username = properties.getProperties("orangeUserName");
        enterText(loginPage.username, username);
        click(loginPage.loginButton);
//        String expectedNote="If the email does not arrive, please contact your OrangeHRM Administrator.";
        String expectedNote = "Note to fail the test case.";

        waitForElementToBeVisible(loginPage.note);
        String actualNote = driver.findElement(loginPage.note).getText();
        Assert.assertEquals(expectedNote, actualNote, "Unable to catch note.");
    }


}
