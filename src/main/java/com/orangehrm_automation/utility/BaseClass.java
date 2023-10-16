package com.orangehrm_automation.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseClass {
    //    public String browser;
    public WebDriver driver;

    public void launchBrowser(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(setChromeOptions());
//                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(setEdgeOptions());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(setFirefoxOptions());
                break;
            default:
                driver = new ChromeDriver(setChromeOptions());
        }
    }

    public ChromeOptions setChromeOptions() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("start-maximized");
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("incognito");
        // option.setHeadless(true);
        option.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-clocking"));
        Map<String, String> prefs = new HashMap<>();
        prefs.put("download.default.directory", "c:/newfolder/download/");
        option.setExperimentalOption("prefs", prefs);
        option.setAcceptInsecureCerts(true);
        return option;
    }

    public FirefoxOptions setFirefoxOptions() {
        FirefoxOptions option = new FirefoxOptions();
        option.addArguments("start-maximized");
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("incognito");
        // option.setHeadless(true);
        option.addPreference("excludeSwitches", Arrays.asList("disable-popup-clocking"));
        Map<String, String> prefs = new HashMap<String, String>();
        prefs.put("download.default.directory", "c:/newfolder/download/");
        option.addPreference("prefs", prefs);
        option.setAcceptInsecureCerts(true);
        return option;
    }

    public EdgeOptions setEdgeOptions() {
        EdgeOptions option = new EdgeOptions();
        option.addArguments("start-maximized");
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("incognito");
        // option.setHeadless(true);
        option.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-clocking"));
        Map<String, String> prefs = new HashMap<>();
        prefs.put("download.default.directory", "c:/newfolder/download/");
        option.setExperimentalOption("prefs", prefs);
        option.setAcceptInsecureCerts(true);
        return option;
    }


    public void enterText(By by, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            driver.findElement(by).sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            driver.findElement(by).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scroll(int scrollBy) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + scrollBy + ")");
    }

    public void selectDate(By calIcon, By table, int day) {
        WebElement calender = driver.findElement(calIcon);
        waitForElement(calender);
        calender.click();
        List<WebElement> allDates = driver.findElements(table);
        for (WebElement element : allDates) {
            String date = element.getText();
            String theDay = String.valueOf(day);
            if (date.equals(theDay)) {
                /*System.out.println(date);
                System.out.println(theDay);*/
                element.click();
                break;
            }
        }
    }

    public void scrollToElement(WebDriver driver, By elementToScrollTo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementToScrollTo));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottom() throws InterruptedException {
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

    }

    public void dropDown(By dD, By by, String textToSelect) {
        click(dD);
        waitForElementToBeVisible(by);
        List<WebElement> elements = driver.findElements(by);
        for (WebElement ele : elements) {
            if (ele.getText().equals(textToSelect)) {
                ele.click();
                break;
            } else {
                throw new RuntimeException("Expected element not found");
            }
        }

    }

    public void selectFromDropDown(By by) {
        click(by);
        List<WebElement> webElements = driver.findElements(by);
        for (WebElement ele : webElements) {
            String text = ele.getText();
            ele.click();
            break;
        }
    }

    public void fileUpload(String autoItScript, String filePath) {
        String file = filePath;
        try {
            Thread.sleep(1000);
            Runtime.getRuntime().exec(autoItScript + " " + file);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void captureScreenshot(ITestResult result, WebDriver driver,String path) {
        /*String screenshotPath = null;
        LocalDateTime dateTime = LocalDateTime.now();
        String currentDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss_SSS"));
        screenshotPath = System.getProperty("user.dir") + "/reports/" +"/screenshot_"+ currentDateTime ;

        System.out.println("Screenshot path is " + screenshotPath);

        File directory = new File(screenshotPath);
        if (!directory.exists()) {
            *//*if (directory.mkdirs()) {
                System.out.println("Report directory created.");
            } else {
                System.out.println("Failed to create report directory.");
                return;
            }*//*
            directory.mkdir();
            System.out.println("Directory created : "+screenshotPath);
        }else {
            System.out.println("Directory already exist");
        }

        screenshotPath = screenshotPath + "/" + result.getMethod().getMethodName() + ".jpg";
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenshotPath);

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        }*/
        /*LocalDateTime dateTime = LocalDateTime.now();
        String currentDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss_SSS"));
        String reportPath = System.getProperty("user.dir") + "/reports/" + "report_" + currentDateTime;
        String screenShot = path + "/" + result.getMethod().getMethodName() +".jpg";
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenShot);

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            // e.printStackTrace(); throw new RuntimeException(e);
        }*/
    }

    public void takeScreenshot(String methodName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String screenshotName = methodName + ".png";
            File destinationFile = new File("screenshots/" + screenshotName);
            FileUtils.copyFile(sourceFile, destinationFile);
            System.out.println("Screenshot captured and saved at: " + destinationFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



