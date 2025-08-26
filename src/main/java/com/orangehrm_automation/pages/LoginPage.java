package com.orangehrm_automation.pages;

import com.orangehrm_automation.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoginPage extends BaseClass {
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public By username = By.name("username");
    public By password = By.name("password");
    public By loginButton = By.xpath("//button[@type='submit']");
    public By loginFailedMsg = By.xpath("//p[text()='Invalid credentials']");
    public By dashBoard = By.xpath("//h6[text()='Dashboard']");
    public By profileName = By.xpath("//p[@class='oxd-userdropdown-name']");
    public By logOut = By.xpath("//a[text()='Logout']");
    public By linkedInHyp = By.xpath("//div[@class='orangehrm-login-footer-sm']/a[1]");
    public By facebookHyp = By.xpath("//div[@class='orangehrm-login-footer-sm']/a[2]");
    public By twitterHyp = By.xpath("//div[@class='orangehrm-login-footer-sm']/a[3]");
    public By youTubeHyp = By.xpath("//div[@class='orangehrm-login-footer-sm']/a[4]");
    public By clientBanner=By.xpath("//img[@alt='client brand banner']");
    public By errMsgForLogin=By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
    public By reqMsg=By.xpath("//span[text()='Required']");
    public By forgetPassword=By.xpath("//div[@class='orangehrm-login-forgot']/p");
    public By note=By.xpath("//p[text()='If the email does not arrive, please contact your OrangeHRM Administrator.']");

    public void login(String un, String pwd) {
        enterText(username, un);
        enterText(password, pwd);
        click(loginButton);
    }

    public void logOut() {
        click(this.profileName);
        click(this.logOut);
    }

    public String handleHypLink(By hypLink, By actionElement) {
        click(hypLink);
        System.out.println("Previous url is " + driver.getCurrentUrl());

        Set<String> allWindows = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(allWindows);

        driver.switchTo().window(windowList.get(windowList.size() - 1));

        if (actionElement != null) {
            click(actionElement);
        }

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current url is " + currentUrl);

        driver.close();
        driver.switchTo().window(windowList.get(0));

        return currentUrl;
    }
}
