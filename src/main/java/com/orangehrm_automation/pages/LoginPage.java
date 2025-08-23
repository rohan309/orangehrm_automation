package com.orangehrm_automation.pages;

import com.orangehrm_automation.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void login(String username, String password) {
        enterText(this.username, username);
        enterText(this.password, password);
        click(loginButton);
    }

    public void logOut() {
        click(this.profileName);
        click(this.logOut);
    }
    public void clickHypLink(By by){
        click(by);
        switch (driver.getTitle()){
           /* case "":
                System.out.println("Linkedin page is visible.");
                break;
            case "":
                System.out.println("Facebook page is visible.");
                break;
            case "":
                System.out.println("Twitter page is visible.");
                break;
            case "":
                System.out.println("YouTube page is visible.");
                break;
            default:
                System.out.println("No one page is visible");*/
        }
    }
}
