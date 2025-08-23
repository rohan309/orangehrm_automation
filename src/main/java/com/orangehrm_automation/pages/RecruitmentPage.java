package com.orangehrm_automation.pages;

import com.orangehrm_automation.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecruitmentPage extends BaseClass {
    public RecruitmentPage(WebDriver driver){
        this.driver=driver;
    }
    public By recruitmentModule=By.xpath("//span[text()='Recruitment']");
    public By addButton=By.xpath("//div[@class='orangehrm-header-container']/button[@type='button']");

    public By saveButton=By.xpath("//div[@class='oxd-form-actions']/button[@type='submit']");
    public By firstName=By.name("firstName");
    public By lastName=By.name("lastName");
    public By dropDown=By.xpath("//div[@class='oxd-select-text--after']/i");
    public By valuesInDropDown=By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']//span");
    public By emailTextField=By.xpath("//form[@class='oxd-form']/div[3]/div/div[1]/div//input");
    public By resumeUpload=By.xpath("//div[@class='oxd-file-button']/following::i[1]");
    public By savedMsg=By.xpath("//p[text()='Successfully Saved']");






}
