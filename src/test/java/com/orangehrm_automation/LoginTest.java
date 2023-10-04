package com.orangehrm_automation;

import com.google.common.annotations.VisibleForTesting;
import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class LoginTest extends BaseClass {
    LoginPage loginPage;
    PropertyHandling properties;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        properties = new PropertyHandling();
        String browser = properties.getProperties("browser");
        String url = properties.getProperties("orangeHrmUrl");
        String username = properties.getProperties("orangeUserName");
        String password = properties.getProperties("orangeHrmPassword");
        launchBrowser(browser);
        driver.get(url);
//        loginPage.login(username, password);

    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @DataProvider
    public Object[][] getLoginData() {
        Object[][] data = new Object[][]{
                {"Admin", "admin123"},
                {"admin", "admin124"},
                {"admin123", "admin123"},
                {"", ""},
                {"", "admin123"},
                {"admin", ""}
        };
        return data;
    }

    @Test(dataProvider = "getLoginData")
    public void verifyLogin(String username, String password) throws InterruptedException {
        loginPage = new LoginPage(driver);
        System.out.println("UN :" + username + ", " + " PWD :" + password);
        loginPage.login(username, password);
        Thread.sleep(2000);
        WebElement clientBaner = driver.findElement(By.xpath("//img[@alt='client brand banner']"));
        if (clientBaner.isDisplayed()) {
            System.out.println("Landed on home page and signing out");
            loginPage.logOut();
        } else {
            throw new RuntimeException("Login page is displayed");

        }
        System.out.println("Title is :" + driver.getTitle());
    }

    @Test
    public void verifyHypLinks(){
        loginPage = new LoginPage(driver);
        properties = new PropertyHandling();
        /*verifyLink(loginPage.linkedInHyp);
        verifyLink(loginPage.youTubeHyp);
        verifyLink(loginPage.facebookHyp);
        verifyLink(loginPage.twitterHyp);*/
    }
    @Test
    public void handle(){
        click(loginPage.facebookHyp);
        click(By.xpath("//div[@aria-label='Close']/i"));
        System.out.println("Current url is "+driver.getCurrentUrl());
    }
   /* public Object[][] hypLinkData() {
        Object[][] data = new Object[][]{
                {"Admin", "admin123"},
                {"admin", "admin124"},
                {"admin123", "admin123"},
                {"", ""},
                {"", "admin123"},
                {"admin", ""}
        };
        return data;
    }*/


}
