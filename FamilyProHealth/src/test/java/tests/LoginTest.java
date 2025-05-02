package tests;

import base.BaseTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.CreateFreeAccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {
	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	public LoginPage loginPage;
	public HomePage homePage;
	public CreateFreeAccountPage createFreeAccountPage;
	
	@Test
	@Parameters("env")
	public void testValidLogin(String env) {
		logger.info("Starting login test in environment: " + env);
		test = extent.createTest("Verify FPH subscribed user login functionality").assignCategory("Regression")
				.assignCategory("Login");
		loginPage = new LoginPage(driver);
		String username = ConfigReader.get("userName");
		String password = ConfigReader.get("password");
		loginPage.login(username, password);
		homePage=new HomePage(driver);
		homePage.isFPHLogoDisplayed();
	}
	

//	@Test
//	@Parameters("env")
//	public void testFreeSubscription(String env) {
//		logger.info("Starting login test in environment: " + env);
//		test = extent.createTest("Verify user should be able to take free Subscription ").assignCategory("Regression")
//				.assignCategory("Create");
//		loginPage = new LoginPage(driver);		
//		loginPage.clickCreateFreeAccount();
//		createFreeAccountPage = new CreateFreeAccountPage(driver);
//		createFreeAccountPage.createFreeAccount("RandomName", "91", "RandomMobile", "RandomEmail", "Kaara@123");
//		homePage=new HomePage(driver);
//		homePage.clickOKButtonOnCreateAccountSuccessPopup();
//		homePage.isFPHLogoDisplayed();
//	}
}
