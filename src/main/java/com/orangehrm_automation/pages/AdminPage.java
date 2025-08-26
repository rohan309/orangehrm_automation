package com.orangehrm_automation.pages;

import com.orangehrm_automation.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage extends BaseClass {
    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public By adminModule = By.xpath("//span[text()='Admin']");
    public By corporateBranding = By.xpath("//a[text()='Corporate Branding']");
    public By primaryColor = By.xpath("//label[text()='Primary Color']/following-sibling::div");
    public By secondaryColor = By.xpath("//label[text()='Secondary Color']/following-sibling::div");
    public By primaryGradient1 = By.xpath("//label[text()='Primary Gradient Color 1']/following-sibling::div");
    public By primaryGradient2 = By.xpath("//label[text()='Primary Gradient Color 2']/following-sibling::div");
    public By pointer = By.xpath("//canvas[@class='oxd-color-picker-palette']//preceding-sibling::div");
    public By corporateBrndText = By.xpath("//div[@class='orangehrm-card-container']/h6");
    public By logoBrowse = By.xpath("//label[text()='Client Logo']/parent::div/following-sibling::div//div[@class='oxd-file-button']");
    public By bannerBrowse = By.xpath("//label[text()='Login Banner']/parent::div/following-sibling::div//div[@class='oxd-file-button']");
    public By publishBtn = By.xpath("//div[@class='orangehrm-actions-group']/button[3]");
    public By successMsg = By.xpath("//p[text()='Successfully Saved']");
    public By jobSubModule = By.xpath("//aside[@class='oxd-sidepanel']/following-sibling::header/div[2]//li[2]");
    public By jobValues = By.xpath("//ul[@class='oxd-dropdown-menu']/li/a");
    public By addBtn = By.xpath("//div[@class='orangehrm-header-container']//button/i");
    public By jobTitle = By.xpath("//form[@class='oxd-form']/div[1]//input");
    public By saveBtn = By.xpath("//div[@class='oxd-form-actions']//button[2]");
    public By jobTable = By.xpath("//div[@class='oxd-table-body']/div/div/div[2]/div");
    public By qualification = By.xpath("//header[@class='oxd-topbar']//nav//li[4]//i");
    public By qulificationDropDown = By.xpath("//header[@class='oxd-topbar']/div[2]/nav/ul/li[4]/ul/li/a");
    public By skillName = By.xpath("//form[@class='oxd-form']//input");
    public By resetDefault = By.xpath("//button[text()=' Reset to Default ']");

}
