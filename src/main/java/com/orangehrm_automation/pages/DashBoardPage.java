package com.orangehrm_automation.pages;

import com.orangehrm_automation.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends BaseClass {
    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
    }

    public By dashBoardModule = By.xpath("//a[@class='oxd-main-menu-item active']");
    public By dashBoardPage = By.xpath("//h6[text()='Dashboard']");
    public By username = By.name("username");
    public By password = By.name("password");
    public By loginButton = By.xpath("//button[@type='submit']");
    public By logOut = By.xpath("//a[text()='Logout']");
    public By profileName = By.xpath("//p[@class='oxd-userdropdown-name']");
    public By leaveModule = By.xpath("//span[text()='Leave']");
    public By myLeave = By.xpath("//a[text()='My Leave']");
    public By fromDateFeild = By.xpath("//div[@class='oxd-form-row']/div/div[1]//input");
    public By toDate = By.xpath("//div[@class='oxd-form-row']/div/div[2]//input");
    public By selectFromDate = By.xpath("//div[@class='oxd-calendar-dates-grid']/div/div");

    public By yearDropDown=By.xpath("//ul[@class='oxd-calendar-selector']/li[2]/div/i");
    public By allYears=By.xpath("//ul[@class='oxd-calendar-dropdown']/li");
    public By monthDropDown=By.xpath("//ul[@class='oxd-calendar-selector']/li[1]/div/i");
    public By allMonths=By.xpath("//div[@class='oxd-calendar-wrapper']//div[@class='oxd-calendar-selector-month-selected']/following::ul");
    public By goToNextMonth=By.xpath("//div[@class='oxd-calendar-header']/button[2]/i");







    public void login(String username, String password) {
        enterText(this.username, username);
        enterText(this.password, password);
        click(loginButton);
    }

    public void logOut() {
        click(this.profileName);
        click(this.logOut);
    }
}
