package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseTest;

public class HomePage extends BaseTest {
	private By famhealthLogo = By.xpath("//img[@class='logowithText']");
	private By signOutButton = By.xpath("//span[contains(text(), 'Sign Out')]");
	private By createAccountSuccessPopupOKButton = By.xpath("//span[text()='OK']");
	private By familyUpdatedSuccessMessage = By.xpath("//*[contains(text(),'Family updated successfully')]");
	private By familyAddedSuccessMessage = By.xpath("//*[contains(text(),'Family added successfully')]");
	private By beneficiaryUpdatedSuccessMessage = By.xpath("//*[contains(text(),'Beneficiary updated successfully')]");
	private By beneficiaryAddedSuccessMessage = By.xpath("//*[contains(text(),'Beneficiary added successfully')]");
	private By addNewBeneficiaryButton = By.xpath("//span[text()='Add New Beneficiary']");
	private By addNewFamilyMemberButton = By.xpath("//span[text()='Add New Family Member']");
	private static final Logger logger = LogManager.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void isFPHLogoDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(famhealthLogo)).isDisplayed();
			logger.info("User is on Dashboard Page ");
			test.pass("User is on Dashboard Page");
		} catch (Exception e) {
			logger.error("User not loggedin into Dashboard Page  ", e);
			test.fail("User not loggedin into Dashboard Page: " + e.getMessage());
		}
	}
	public void clickOKButtonOnCreateAccountSuccessPopup() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(createAccountSuccessPopupOKButton)).click();
            logger.info("User Clicked Ok button on Success Popup");
            test.pass("User Clicked Ok button on Success Popup");
        } catch (Exception e) {
            logger.error("User not able to Click Ok button on Success Popup: ", e);
            test.fail("User not able to Click Ok button on Success Popup: " + e.getMessage());
        }
    }	
	public void clickAddNewBeneficiaryButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addNewBeneficiaryButton)).click();
            logger.info("User Clicked Add New Beneficiary Button");
            test.pass("User Clicked Add New Beneficiary Button");
        } catch (Exception e) {
            logger.error("User not able to Click on Add New Beneficiary Button: ", e);
            test.fail("User not able to Click on Add New Beneficiary Button: " + e.getMessage());
        }
    }	
	public void clickAddNewFamilyMemberButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addNewFamilyMemberButton)).click();
            logger.info("User Clicked Add New Family Member Button");
            test.pass("User Clicked Add New Family Member Button");
        } catch (Exception e) {
            logger.error("User not able to Click on Add New Family Member Button: ", e);
            test.fail("User not able to Click on Add New Family Member Button: " + e.getMessage());
        }
    }	
	public void isBeneficiaryAddedSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(beneficiaryAddedSuccessMessage)).isDisplayed();
            logger.info("Beneficiay added success message is displayed ");
            test.pass("Beneficiay added success message is displayed ");
        } catch (Exception e) {
            logger.error("Beneficiay added success message is not displayed : ", e);
            test.fail("Beneficiay added success message is not displayed : " + e.getMessage());
        }
    }	
	
	public void isFamilyMemberAddedSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(familyAddedSuccessMessage)).isDisplayed();
            logger.info("Family Member added success message is displayed ");
            test.pass("Family Member added success message is displayed ");
        } catch (Exception e) {
            logger.error("Family Member added success message is not displayed : ", e);
            test.fail("Family Member added success message is not displayed : " + e.getMessage());
        }
    }	
}
