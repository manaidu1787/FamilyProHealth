package pages;

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

	private WebElement usernameField = driver.findElement(By.name("userName"));
	private WebElement passwordField = driver.findElement(By.name("password"));
	private WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
	private WebElement createFreeAccount = driver.findElement(By.xpath("//span[contains(text(), 'Create Free Account')]"));


	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	public void clickCreateFreeAccount() {
		// boolean value=this.enterText(usernameField, username);
		wait.until(ExpectedConditions.elementToBeClickable(createFreeAccount));		
		try {
			Assert.assertTrue(createFreeAccount.isDisplayed());
			createFreeAccount.click();
			// System.out.println(element.getAttribute("value")+ ": "+text);
			test.pass("Create Free Account button clicked ");
		} catch (AssertionError e) {
			test.fail("Create Free Account button is not clickble ");
		}
	}
	
	public void enterUsername(String username) {
		// boolean value=this.enterText(usernameField, username);
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.sendKeys(username);
		try {
			Assert.assertTrue(username.equals(usernameField.getAttribute("value")));
			// System.out.println(element.getAttribute("value")+ ": "+text);
			test.pass("Entered Username: " + username);
		} catch (AssertionError e) {
			test.fail("Unable to enter username" + username);
		}

	}

	public void enterPassword(String password) {
		// this.enterText(passwordField, password);
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		passwordField.sendKeys(password);
		try {
			Assert.assertTrue(password.equals(passwordField.getAttribute("value")));
			// System.out.println(element.getAttribute("value")+ ": "+text);
			test.pass("Entered Password: " + password);
		} catch (AssertionError e) {
			test.fail("Unable to enter password" + password);
		}
	}

	public void clickSignInButton() {
		// this.click(signInButton);

		wait.until(ExpectedConditions.visibilityOf(signInButton));
		try {
			Assert.assertTrue(signInButton.isDisplayed());
			// System.out.println(element.getAttribute("value")+ ": "+text);
			test.pass("Sign In Button Clicked  ");
		} catch (AssertionError e) {
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
