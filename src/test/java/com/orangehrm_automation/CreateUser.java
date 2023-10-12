package com.orangehrm_automation;

import com.orangehrm_automation.utility.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateUser extends BaseClass {
    @Test
    public void createUser(){
        launchBrowser("chrome");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        enterText(By.name("username"),"Admin");
        enterText(By.name("password"),"admin123");
        click(By.xpath("//button[@type='submit']"));
        click(By.xpath("//a[@class='oxd-main-menu-item active']"));
        click(By.xpath("//div[@class='orangehrm-paper-container']/div/button/i"));
        enterText(By.name("firstName"), "JP");
        enterText(By.name("lastName"), "Morgan");
        click(By.xpath("//input[@type='checkbox']"));
        scroll(500);
        enterText(By.name("firstName"),"JP");
        enterText(By.name("lastName"),"Morgan");
        click(By.xpath("//input[@type='checkbox']"));
        driver.findElement(By.xpath("//div[@class='orangehrm-employee-form']/div[3]/div/div[1]/div/div[2]/input")).clear();
        enterText(By.xpath("//div[@class='orangehrm-employee-form']/div[3]/div/div[1]/div/div[2]/input"),"JPMorgan");  //username
        driver.findElement(By.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input")).clear();
        enterText(By.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input"),"JPMorgan@123"); //password
        enterText(By.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input"),"JPMorgan@123");
        click(By.xpath("//button[@type='submit']"));


    }
}
