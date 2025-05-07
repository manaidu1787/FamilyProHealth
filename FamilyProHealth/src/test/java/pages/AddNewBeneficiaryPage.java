package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import base.BaseTest;
import utils.RandomDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class AddNewBeneficiaryPage extends BaseTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(AddNewBeneficiaryPage.class);

    // Constructor
    public AddNewBeneficiaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Element Locators
   // private By selectPlan = By.xpath("//div[@role='button' and contains(text(),'Free Plan')]");
    private By fullName = By.xpath("//input[@name='fullName']");
    private By countryCode = By.xpath("//span[text()='Select']");
    private By countryCodeValue;
    private By mobileNumber = By.xpath("//input[@name='mobileNumber']");
   // private By dateOfBirth = By.xpath("//input[@placeholder='DD/MM/YYYY']");
    private By genderDropdown = By.xpath("//div/span[text()='Select Gender']");
    private By genderOption;
  //  private By familyMemberDropdown = By.xpath("//div[contains(text(),'Select Family Member') and @role='button']");
  //  private By familyMemberOption;
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

//    public void enterDateOfBirth(String dob) {
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfBirth)).sendKeys(dob);
//            logger.info("Entered Date of Birth: " + dob);
//            test.pass("Date of Birth entered: " + dob);
//        } catch (Exception e) {
//            logger.error("Failed to enter DOB: ", e);
//            test.fail("Unable to enter Date of Birth: " + e.getMessage());
//        }
//    }

    public void selectGender(String gender) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(genderDropdown)).click();
            genderOption = By.xpath("//li[contains(text(),'" + gender + "')]");
            wait.until(ExpectedConditions.elementToBeClickable(genderOption)).click();
            logger.info("Selected Gender: " + gender);
            test.pass("Gender selected: " + gender);
        } catch (Exception e) {
            logger.error("Failed to select Gender: ", e);
            test.fail("Unable to select Gender: " + e.getMessage());
        }
    }

//    public void selectFamilyMember(String member) {
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(familyMemberDropdown)).click();
//            familyMemberOption = By.xpath("//li[contains(text(),'" + member + "')]");
//            wait.until(ExpectedConditions.elementToBeClickable(familyMemberOption)).click();
//            logger.info("Selected Family Member: " + member);
//            test.pass("Family Member selected: " + member);
//        } catch (Exception e) {
//            logger.error("Failed to select Family Member: ", e);
//            test.fail("Unable to select Family Member: " + e.getMessage());
//        }
//    }

    public void clickSave() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
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

    public void addNewBeneficiary(String name, String code, String mobile, String gender) {
        logger.info("Starting add new beneficiary process.");
        enterFullName(name);
        selectCountryCode(code);
        enterMobileNumber(mobile);
       // enterDateOfBirth(dob);
        selectGender(gender);
      //  selectFamilyMember(familyMember);
        clickSave();
        logger.info("Finished add new beneficiary process.");
    }
}
