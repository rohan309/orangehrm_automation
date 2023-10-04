package com.orangehrm_automation.pages;

import com.orangehrm_automation.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PIMPage extends BaseClass {
    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    public By pimModule = By.xpath("//span[text()='PIM']");
    public By configuration = By.xpath("//i[@class='oxd-icon bi-chevron-down']");
    public By dataImport = By.xpath("//a[text()='Data Import']");
    public By browseBtn = By.xpath("//div[text()='Browse']");
    public By uploadBtn = By.xpath("//div[@class='oxd-form-actions']//button");
    public By okBtn = By.xpath("//div[@class='orangehrm-modal-footer']/button");
    public By uploadMsg = By.xpath("//div[@class='orangehrm-text-center-align']/p");
    public By nextBtn = By.xpath("//i[@class='oxd-icon bi-chevron-right']");
    public By firstName = By.xpath("//div[@class='oxd-table-card']/div/div[3]/div");
    public By lastName = By.xpath("//div[@class='oxd-table-card']/div/div[4]/div");
    public By paginationNumbers = By.xpath("//ul[@class='oxd-pagination__ul']//button");
    public By bottomCont=By.xpath("//div[@class='orangehrm-bottom-container']");
    public By fileUpErrMsg=By.xpath("//span[text()='File type not allowed']");
    public By optionalFields=By.xpath("//ul[@class='oxd-dropdown-menu']/li[1]");
    public By checkButtons=By.xpath("//div[@class='oxd-switch-wrapper']");
    public By empList= By.xpath("//a[text()='Employee List']");
    public By addBtn=By.xpath("//div[@class='orangehrm-header-container']/button");
    public By empFirstName=By.name("firstName");
    public By empLastName=By.name("lastName");
    public By createLoginCredBtn=By.xpath("//div[@class='oxd-switch-wrapper']/label/span");
    public By saveBtn=By.xpath("//button[@type='submit']");
    public By successMsg=By.xpath("//p[text()='Successfully Saved']");
    public By allFeilds=By.xpath("//div[@class='oxd-input-group__label-wrapper']");
    public By reportsSubModule=By.xpath("//nav[@class='oxd-topbar-body-nav']/ul/li[4]");
    public By reportName=By.xpath("//input[@placeholder='Type here ...']");
    public By selectionCriteriaDropDown=By.xpath("//form[@class='oxd-form']/div[2]/div/div[1]/div[1]/div[2]/div/div/div[2]/i");

    //public By valueToSelect=By.xpath("//span[text()='Employee Name']");
    public By valuesInDropDown=By.xpath("//div[@role='listbox']/div");
    public By includeDropdown=By.xpath("//form[@class='oxd-form']/div[2]/div/div[2]/div[1]/div[2]/div/div/div[2]/i");
    public By valuesInInclude=By.xpath("//div[@role='listbox']/div");
    public By fieldGroup=By.xpath("//form[@class='oxd-form']/div[3]/div/div[1]/div[1]/div[2]/div/div/div[2]/i");
    public By displayField=By.xpath("//form[@class='oxd-form']/div[3]/div/div[2]/div[1]/div[2]/div/div/div[2]/i");
    public By addField=By.xpath("//form[@class='oxd-form']/div[3]/div/div[2]/div[2]//i");
    public By toggleIcon=By.xpath("//div[@class='oxd-report-table-header--toggable']/i");
    public By searchReport=By.xpath("//input[@placeholder='Type for hints...']");
    public By reoprtFound=By.xpath("//div[@class='oxd-table-card']/div/div[2]/div");
    public By searchBtn=By.xpath("//button[text()=' Search ']");




}
