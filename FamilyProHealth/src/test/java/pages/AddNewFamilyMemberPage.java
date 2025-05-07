package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import base.BaseTest;
import utils.RandomDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class AddNewFamilyMemberPage extends BaseTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(AddNewFamilyMemberPage.class);

    // Constructor
    public AddNewFamilyMemberPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Element Locators
    private By fullName = By.xpath("//input[@name='fullName']");
    private By countryCode = By.xpath("//span[text()='Select']");
    private By countryCodeValue;
    private By mobileNumber = By.xpath("//input[@name='mobileNumber']");
    private By beneficiaryDropdown = By.xpath("//div[@id='mui-component-select-text']");
    private By beneficiaryOption;
    private By selectBeneficiariesDropdown = By.xpath("(//*[@data-testid=\"ArrowDropDownIcon\"])[3]");
    private By saveButton = By.xpath("//button/span[text()='SAVE']");
    private By cancelButton = By.xpath("//button/span[text()='CANCEL']");

    public void enterFullName(String name) {
        if (name.equals("RandomName")) {
            name = RandomDataGenerator.generateRandomName(10);
        }
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(fullName)).sendKeys(name);
            logger.info("Entered Full Name: " + name);
            test.pass("Full Name entered: " + name);
        } catch (Exception e) {
            logger.error("Failed to enter Full Name: ", e);
            test.fail("Unable to enter Full Name: " + e.getMessage());
        }
    }

    public void selectCountryCode(String code) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(countryCode)).click();
            countryCodeValue = By.xpath("//li[contains(@data-value, '" + code + "')]");
            wait.until(ExpectedConditions.elementToBeClickable(countryCodeValue)).click();
            logger.info("Selected Country Code: " + code);
            test.pass("Country Code selected: " + code);
        } catch (Exception e) {
            logger.error("Failed to select Country Code: ", e);
            test.fail("Unable to select Country Code: " + e.getMessage());
        }
    }

    public void enterMobileNumber(String mobile) {
        if (mobile.equals("RandomMobile")||!mobile.matches("[0-9]{10}")) {
            mobile = RandomDataGenerator.generateRandomMobile();
        }
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumber)).sendKeys(mobile);
            logger.info("Entered Mobile Number: " + mobile);
            test.pass("Mobile Number entered: " + mobile);
        } catch (Exception e) {
            logger.error("Failed to enter Mobile Number: ", e);
            test.fail("Unable to enter Mobile Number: " + e.getMessage());
        }
    }

    public void selectBeneficiary(String beneficiaryName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(beneficiaryDropdown)).click();
            beneficiaryOption = By.xpath("//span[contains(text(), '" + beneficiaryName + "')]");          
            wait.until(ExpectedConditions.elementToBeClickable(beneficiaryOption)).click();
          
         //   wait.until(ExpectedConditions.elementToBeClickable(beneficiaryDropdown)).submit();//to close the dropdown 
            logger.info("Selected Beneficiary: " + beneficiaryName);
            test.pass("Beneficiary selected: " + beneficiaryName);
        } catch (Exception e) {
            logger.error("Failed to select Beneficiary: ", e);
            test.fail("Unable to select Beneficiary: " + e.getMessage());
        }
    }

    public void clickSave() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).submit();
            logger.info("Clicked on 'Save' button.");
            test.pass("Clicked 'Save' button.");
        } catch (Exception e) {
            logger.error("Failed to click Save button: ", e);
            test.fail("Unable to click 'Save' button: " + e.getMessage());
        }
    }

    public void clickCancel() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
            logger.info("Clicked on 'Cancel' button.");
            test.pass("Clicked 'Cancel' button.");
        } catch (Exception e) {
            logger.error("Failed to click Cancel button: ", e);
            test.fail("Unable to click 'Cancel' button: " + e.getMessage());
        }
    }

    public void addNewFamilyMember(String name, String code, String mobile, String beneficiary) {
        logger.info("Starting add new family member process.");
        enterFullName(name);
        selectCountryCode(code);
        enterMobileNumber(mobile);
        selectBeneficiary(beneficiary);
        clickSave();
        logger.info("Finished add new family member process.");
    }
}

