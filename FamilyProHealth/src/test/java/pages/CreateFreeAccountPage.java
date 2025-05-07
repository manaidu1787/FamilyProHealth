package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseTest;
import utils.PropertyUtils;
import utils.RandomDataGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class CreateFreeAccountPage extends BaseTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private static final Logger logger = LogManager.getLogger(CreateFreeAccountPage.class);

    // Constructor
    public CreateFreeAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Element Locators
    private By fullName = By.name("fullName");
    private By countryCode = By.xpath("//span[text()='Select']");
    private By countryCodeValue;
    private By mobileNumber = By.name("mobileNumber");
    private By email = By.name("email");
    private By password = By.name("password");
    private By confirmPassword = By.name("confirmPassword");
    private By agreeCheckbox = By.xpath("//input[@name='privacyTerms']");
    private By createAccountButton = By.xpath("//span[contains(text(),'CREATE FREE ACCOUNT')]");

    public void enterFullName(String name) {
    	if(name.equals("RandomName"))
    	{
    		name=RandomDataGenerator.generateRandomName(10);
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
            countryCodeValue = By.xpath("//li[contains(@data-value, '"+code+"')]");
            wait.until(ExpectedConditions.elementToBeClickable(countryCodeValue)).click();
            logger.info("Selected Country Code: " + code);
            test.pass("Country Code selected: " + code);
        } catch (Exception e) {
            logger.error("Failed to select Country Code: ", e);
            test.fail("Unable to select Country Code: " + e.getMessage());
        }
    }

    public void enterMobileNumber(String env, String mobile) {
    	if(mobile.equals("RandomMobile")||!mobile.matches("[0-9]{10}"))
    	{
    		mobile=RandomDataGenerator.generateRandomMobile();
    	}
    	
        try {
        	PropertyUtils.writeOrUpdateProperty(env, "userName", mobile);
            wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumber)).sendKeys(mobile);
            logger.info("Entered Mobile Number: " + mobile);
            test.pass("Mobile Number entered: " + mobile);
        } catch (Exception e) {
            logger.error("Failed to enter Mobile Number: ", e);
            test.fail("Unable to enter Mobile Number: " + e.getMessage());
        }
    }

    public void enterEmail(String userEmail) {
    	if(userEmail.equals("RandomEmail"))
    	{
    		userEmail=RandomDataGenerator.generateRandomEmail();
    	}
    	
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(userEmail);
            logger.info("Entered Email: " + userEmail);
            test.pass("Email entered: " + userEmail);
        } catch (Exception e) {
            logger.error("Failed to enter Email: ", e);
            test.fail("Unable to enter Email: " + e.getMessage());
        }
    }

    public void enterPassword(String pwd) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pwd);
            logger.info("Entered Password.");
            test.pass("Password entered.");
        } catch (Exception e) {
            logger.error("Failed to enter Password: ", e);
            test.fail("Unable to enter Password: " + e.getMessage());
        }
    }

    public void retypePassword(String pwd) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(pwd);
            logger.info("Re-entered Password.");
            test.pass("Password re-typed.");
        } catch (Exception e) {
            logger.error("Failed to retype Password: ", e);
            test.fail("Unable to re-type Password: " + e.getMessage());
        }
    }

    public void agreeToTerms() {
        try {
         	WebElement checkbox = driver.findElement(agreeCheckbox);            
        	actions=new Actions(driver);
        	actions.click(checkbox).build().perform();     
            logger.info("Agreed to Terms and Conditions.");
            test.pass("Agreed to Terms and Conditions.");
        } catch (Exception e) {
            logger.error("Failed to check Terms and Conditions box: ", e);
            test.fail("Unable to check agreement box: " + e.getMessage());
        }
    }

    public void clickCreateAccount() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
            button.click();
            logger.info("Clicked on 'Create Free Account' button.");
            test.pass("Clicked 'Create Free Account' button.");
        } catch (Exception e) {
            logger.error("Failed to click Create Account button: ", e);
            test.fail("Unable to click 'Create Free Account' button: " + e.getMessage());
        }
    }

    public void createFreeAccount(String env, String name, String code, String mobile, String emailVal, String pwd) {
        logger.info("Starting account creation process.");
        enterFullName(name);
        selectCountryCode(code);
        enterMobileNumber(env, mobile);
        enterEmail(emailVal);
        enterPassword(pwd);
        retypePassword(pwd);
        agreeToTerms();
        clickCreateAccount();
        logger.info("Finished account creation process.");
    }
}
