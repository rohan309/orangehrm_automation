package com.orangehrm_automation.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseClass {
    protected WebDriver driver;
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public void launchBrowser(String browser) {
        WebDriver webDriver;

        if (browser != null && !browser.isEmpty()) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    webDriver = new ChromeDriver(setChromeOptions());
                    break;
                case "edge":
                    webDriver = new EdgeDriver(setEdgeOptions());
                    break;
                case "firefox":
                    webDriver = new FirefoxDriver(setFirefoxOptions());
                    break;
                default:
                    webDriver = new ChromeDriver(setChromeOptions());
            }
        } else {
            webDriver = new ChromeDriver(setChromeOptions());
        }

        threadLocal.set(webDriver);
        driver = webDriver;
        System.out.println("Driver launched successfully: " + browser);
    }

    public static WebDriver getDriver() {
        return threadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = threadLocal.get();
        if (driver != null) {
            driver.quit();
            threadLocal.remove();
        }
    }

    public ChromeOptions setChromeOptions() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("start-maximized", "--remote-allow-origins=*", "incognito");
        option.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-clocking"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default.directory", "c:/newfolder/download/");
        option.setExperimentalOption("prefs", prefs);
        option.setAcceptInsecureCerts(true);
        return option;
    }

    public FirefoxOptions setFirefoxOptions() {
        FirefoxOptions option = new FirefoxOptions();
        option.addArguments("start-maximized", "--remote-allow-origins=*", "incognito");
        option.setAcceptInsecureCerts(true);
        return option;
    }

    public EdgeOptions setEdgeOptions() {
        EdgeOptions option = new EdgeOptions();
        option.addArguments("start-maximized", "--remote-allow-origins=*", "incognito");
        option.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-clocking"));
        Map<String, Object> prefs = new HashMap<>();
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

    public void waitForElementTobeClickable(By by) {
        WebElement element = driver.findElement(by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForElementToRefresh(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(by)
        ));
    }

    public void scroll(int scrollBy) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + scrollBy + ")");
    }

    public void selectDate(By calIcon, By table, int day) {
        WebElement calender = driver.findElement(calIcon);
        waitForElementTobeClickable(calIcon);
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

    public void scrollToElement(By elementToScrollTo) {

        WebElement element = driver.findElement(elementToScrollTo);

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

    public void fileUpload(String filePath) {
        PropertyHandling propertyHandling = new PropertyHandling();
        String autoItScript = propertyHandling.getProperties("autoItScript");
        String file = filePath;
        try {
            Thread.sleep(2000);
            Runtime.getRuntime().exec(autoItScript + " " + file);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}



