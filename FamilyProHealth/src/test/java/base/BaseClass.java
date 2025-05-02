package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseClass extends BaseTest {
	protected WebDriverWait wait;
	protected WebDriver driver;

	protected WebElement findElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected void click(By locator) {
		WebElement element = findElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	protected void clickAnElementUsingActionsClass(By locator) {
		WebElement element = findElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
		// element.click();
	}

	protected void clickAnElementUsingJavascriptExecutor(By locator) {
		WebElement element = findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Remove aria-hidden and unselectable attributes using JavaScript
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('aria-hidden'); arguments[0].removeAttribute('unselectable');",
				element);

		// js.executeScript("arguments[0].click();", element);
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
	}

	protected void clickAnElementUsingJavascriptExecutorAndActionsClasss(By locator) {
		WebElement element = findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
	}

	protected boolean enterText(By locator, String text) {
		WebElement element = findElement(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
		if (text == element.getAttribute("value")) {
			return true;
		} else {
			return false;
		}
	}

	protected void enterTextUsingActionsClass(By locator, String text) {
		WebElement element = findElement(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		// element.sendKeys(text);
		Actions actions = new Actions(driver);
		actions.clickAndHold(element).sendKeys(text).build().perform();
	}

	protected String getText(By locator) {
		WebElement element = findElement(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element.getText();
	}

	protected boolean isLocatorDisplayed(By locator) {
		WebElement element = findElement(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element.isDisplayed();
	}
}
