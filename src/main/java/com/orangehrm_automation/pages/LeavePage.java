package com.orangehrm_automation.pages;

import com.orangehrm_automation.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeavePage extends BaseClass {
    public LeavePage(WebDriver driver) {
        this.driver = driver;
    }

    public By leaveModule = By.xpath("//span[text()='Leave']");
    public By profileDropDown = By.xpath("//p[@class='oxd-userdropdown-name']/following-sibling::i");
    public By logOutBtn = By.xpath("//a[text()='Logout']");
    public By applySbModule=By.xpath("//a[text()='Apply']");
    public By fromDate=By.xpath("//form[@class='oxd-form']/div[1]/div/div[1]//i");
    public By toDate=By.xpath("//form[@class='oxd-form']/div[1]/div/div[2]//i");
    public By yearDropDown=By.xpath("//ul[@class='oxd-calendar-selector']/li[2]//i");
    public By monthDropDown=By.xpath("//ul[@class='oxd-calendar-selector']/li[1]//i");
    public By dateTable=By.xpath("//div[@class='oxd-calendar-date']");
    public By successMsg = By.xpath("//p[text()='Successfully Saved']");
    public By applyBtn=By.xpath("//button[@type='submit']");
}
