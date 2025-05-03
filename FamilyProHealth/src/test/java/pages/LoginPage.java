package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;
import base.BaseTest;

import java.time.Duration;
import java.util.Properties;

public class LoginPage extends BaseTest {
	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	private WebElement usernameField = driver.findElement(By.name("userName"));
	private WebElement passwordField = driver.findElement(By.name("password"));
	private WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
	private WebElement createFreeAccount = driver
			.findElement(By.xpath("//span[contains(text(), 'Create Free Account')]"));

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void clickCreateFreeAccount() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(createFreeAccount));
			Assert.assertTrue(createFreeAccount.isDisplayed());
			createFreeAccount.click();
			test.pass("Create Free Account button clicked ");
		} catch (AssertionError e) {
			test.fail("Create Free Account button is not clickble ");
		}
	}

	public void enterUsername(String username) {
		try {
			wait.until(ExpectedConditions.visibilityOf(usernameField));
			usernameField.sendKeys(username);
			Assert.assertTrue(username.equals(usernameField.getAttribute("value")));
			logger.info("Entered Username: " + username);
			test.pass("Entered Username: " + username);
		} catch (AssertionError e) {
			logger.info("Unable to enter username" + username);
			test.fail("Unable to enter username" + username);
		}

	}

	public void enterPassword(String password) {
		try {
			wait.until(ExpectedConditions.visibilityOf(passwordField));
			passwordField.sendKeys(password);
			Assert.assertTrue(password.equals(passwordField.getAttribute("value")));
			logger.info("Entered the Password: ");
			test.pass("Entered the Password: ");
		} catch (AssertionError e) {
			logger.info("Unable to enter password: ");
			test.fail("Unable to enter password:");
		}
	}

	public void clickSignInButton() {
		try {
			wait.until(ExpectedConditions.visibilityOf(signInButton));
			Assert.assertTrue(signInButton.isDisplayed());
			logger.info("Sign In Button Clicked  ");
			test.pass("Sign In Button Clicked  ");
		} catch (AssertionError e) {
			logger.info("Unable to click on Sign In Button ");
			test.fail("Unable to click on Sign In Button");
		}
		signInButton.click();
	}

	public void login(String email, String password) {
		enterUsername(email);
		enterPassword(password);
		clickSignInButton();
	}
}
