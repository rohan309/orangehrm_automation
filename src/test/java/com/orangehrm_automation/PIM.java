package com.orangehrm_automation;

import com.beust.jcommander.Parameter;
import com.opencsv.CSVReader;
import com.orangehrm_automation.pages.LeavePage;
import com.orangehrm_automation.pages.LoginPage;
import com.orangehrm_automation.pages.PIMPage;
import com.orangehrm_automation.utility.BaseClass;
import com.orangehrm_automation.utility.PropertyHandling;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PIM extends BaseClass {
    PropertyHandling propertyHandling;
    LoginPage loginPage;
    PIMPage pimPage;
    private WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            propertyHandling = new PropertyHandling();
            launchBrowser(propertyHandling.getProperties("browser"));
        } else if (browser.equalsIgnoreCase("firefox")) {
            propertyHandling = new PropertyHandling();
            launchBrowser(propertyHandling.getProperties("fbrowser"));
        }/*else {
            propertyHandling = new PropertyHandling();
            launchBrowser(propertyHandling.getProperties("browser"));
        }*/
        driver.get(propertyHandling.getProperties("orangeHrmUrl"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.login(propertyHandling.getProperties("orangeUserName"), propertyHandling.getProperties("orangeHrmPassword"));
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
//        driver.close();
    }

    @Test
    public void uploadListOfEmp() {
        pimPage = new PIMPage(driver);
        click(pimPage.pimModule);
        click(pimPage.configuration);
        click(pimPage.dataImport);
        click(pimPage.browseBtn);
        String csvFile = propertyHandling.getProperties("csvFile");
        fileUpload(propertyHandling.getProperties("autoItScript"), csvFile);
        click(pimPage.uploadBtn);
        click(pimPage.okBtn);
    }

    @Test(enabled = false)
    public void verifyAddedEmp() throws InterruptedException {
        pimPage = new PIMPage(driver);
        click(pimPage.pimModule);
        scrollToBottom();
        try {
            waitForElementToBeVisible(pimPage.paginationNumbers);
        } catch (Exception ignored) {

        }

        Map<String, String> names = new HashMap<>();

        List<WebElement> paginationElements = driver.findElements(pimPage.paginationNumbers);


        int totalPages = paginationElements.size();
        System.out.println(totalPages);

        for (int pageNumber = 1; pageNumber <= totalPages; pageNumber++) {
            paginationElements.get(pageNumber - 1).click();

            List<WebElement> firstNames = driver.findElements(pimPage.firstName);
            List<WebElement> lastNames = driver.findElements(pimPage.lastName);

            for (int j = 0; j < firstNames.size(); j++) {
                String fN = firstNames.get(j).getText();
                String lN = lastNames.get(j).getText();
                names.put(fN, lN);

            }

            Thread.sleep(1000);
        }

        for (Map.Entry<String, String> listOfName : names.entrySet()) {
            System.out.println(listOfName.getKey() + " " + listOfName.getValue());
        }

        propertyHandling = new PropertyHandling();
        String filePath = propertyHandling.getProperties("csvFile");
        try {
            FileReader fileReader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                String first_name = nextRecord[0];
                String last_name = nextRecord[2];
                String gender = nextRecord[7];
                String date_of_birth = nextRecord[10];

                System.out.println(first_name + "   " + last_name + "   " + gender + "  " + date_of_birth);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test(enabled = false)
    public void uploadInappropriateFile() {
        pimPage = new PIMPage(driver);
        propertyHandling = new PropertyHandling();
        click(pimPage.pimModule);
        click(pimPage.configuration);
        click(pimPage.dataImport);
        click(pimPage.browseBtn);

        fileUpload(propertyHandling.getProperties("autoItScript"), propertyHandling.getProperties("screenShot"));
        WebElement errMsg = driver.findElement(pimPage.fileUpErrMsg);
        Assert.assertTrue(errMsg.isDisplayed());

    }

    @Test(enabled = false)
    public void optionalFields1() throws InterruptedException {
        pimPage = new PIMPage(driver);
        propertyHandling = new PropertyHandling();
        click(pimPage.pimModule);
        click(pimPage.configuration);
        click(pimPage.optionalFields);

        waitForElementToBeVisible(pimPage.checkButtons);
        List<WebElement> checkButtons = driver.findElements(pimPage.checkButtons);

        for (WebElement button : checkButtons) {
            if (!button.isSelected())
                button.click();

        }

        click(pimPage.empList);
        click(pimPage.addBtn);
        enterText(pimPage.empFirstName, propertyHandling.getProperties("firstName"));
        enterText(pimPage.empLastName, propertyHandling.getProperties("lastName"));
        click(pimPage.saveBtn);
        waitForElementToBeVisible(pimPage.successMsg);
        Assert.assertTrue(driver.findElement(pimPage.successMsg).isDisplayed());
        scrollToBottom();

        List<WebElement> allFeilds = driver.findElements(pimPage.allFeilds);

        List<WebElement> fields = new ArrayList<>();

        int[] indicesToStore = {5, 10, 11, 18, 19,};

        for (int index : indicesToStore) {
            if (index >= 0 && index < allFeilds.size()) {
                fields.add(allFeilds.get(index));
            }

        }

        for (WebElement ele : fields) {
            Assert.assertTrue(ele.isDisplayed());

        }

    }

    @Test(enabled = false)
    public void optionalFields2() throws InterruptedException {
        pimPage = new PIMPage(driver);
        propertyHandling = new PropertyHandling();
        click(pimPage.pimModule);
        click(pimPage.configuration);
        click(pimPage.optionalFields);

        waitForElementToBeVisible(pimPage.checkButtons);
        List<WebElement> checkButtons = driver.findElements(pimPage.checkButtons);
//        System.out.println(checkButtons.size());

        for (WebElement button : checkButtons) {
//            System.out.println(button.getTagName());
            if (button.isSelected())

                button.click();
//            System.out.println("Clicked");
        }
//        Thread.sleep(5000);

        click(pimPage.empList);
        click(pimPage.addBtn);
        enterText(pimPage.empFirstName, propertyHandling.getProperties("firstName"));
        enterText(pimPage.empLastName, propertyHandling.getProperties("lastName"));
        click(pimPage.saveBtn);
        waitForElementToBeVisible(pimPage.successMsg);
        Assert.assertTrue(driver.findElement(pimPage.successMsg).isDisplayed());
        scrollToBottom();

        List<WebElement> allFeilds = driver.findElements(pimPage.allFeilds);

        List<WebElement> fields = new ArrayList<>();

        int[] indicesToStore = {5, 10, 11, 18, 19,};

        for (int index : indicesToStore) {
            if (index >= 0 && index < allFeilds.size()) {
                fields.add(allFeilds.get(index));
            }


        }

        for (WebElement ele : fields) {
            Assert.assertTrue(ele.isDisplayed());

        }

    }

    @Test(enabled = false)
    public void addReports() {
        pimPage = new PIMPage(driver);
        propertyHandling = new PropertyHandling();
        click(pimPage.pimModule);
        click(pimPage.reportsSubModule);
        click(pimPage.addBtn);
        enterText(pimPage.reportName, propertyHandling.getProperties("reportName"));
        dropDown(pimPage.selectionCriteriaDropDown, pimPage.valuesInDropDown, propertyHandling.getProperties("reportCriterion"));
        dropDown(pimPage.includeDropdown, pimPage.valuesInInclude, propertyHandling.getProperties("includeCriterion"));
        dropDown(pimPage.fieldGroup, pimPage.valuesInDropDown, propertyHandling.getProperties("fieldGroup"));
        dropDown(pimPage.displayField, pimPage.valuesInDropDown, propertyHandling.getProperties("empId"));
        click(pimPage.addField);
        dropDown(pimPage.displayField, pimPage.valuesInDropDown, propertyHandling.getProperties("empFirstName"));
        click(pimPage.addField);
        dropDown(pimPage.displayField, pimPage.valuesInDropDown, propertyHandling.getProperties("empLastName"));
        click(pimPage.addField);
        click(pimPage.saveBtn);
        waitForElementToBeVisible(pimPage.successMsg);
        Assert.assertTrue(driver.findElement(pimPage.successMsg).isDisplayed());
        waitForElementToBeVisible(pimPage.toggleIcon);

    }

    @Test(enabled = false,dependsOnMethods = "addReports")
    public void verifyReport() throws InterruptedException {
        pimPage = new PIMPage(driver);
        propertyHandling = new PropertyHandling();
        click(pimPage.pimModule);
        click(pimPage.reportsSubModule);
        enterText(pimPage.searchReport, propertyHandling.getProperties("reportName"));
        Thread.sleep(1000);

        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(pimPage.searchReport), Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

        click(pimPage.searchBtn);

        scrollToBottom();
        String report = driver.findElement(pimPage.reoprtFound).getText();
        System.out.println(report);
        Assert.assertEquals(report, propertyHandling.getProperties("reportName"));
    }


}

