package com.orangehrm_automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ScreenShotPractice {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        File dest=new File(System.getProperty("user.dir")+"/orangehrm_automation/SS/screenshot.png");
        Thread.sleep(3000);
        TakesScreenshot screenshot=(TakesScreenshot) driver;
        File src=screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,dest);
    }
}
