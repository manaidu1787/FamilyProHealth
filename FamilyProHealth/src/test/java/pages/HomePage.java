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
	
}
