package tests;

import base.BaseTest;

import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.AddNewBeneficiaryPage;
import pages.AddNewFamilyMemberPage;
import pages.CreateFreeAccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SanityTest extends BaseTest {
	private static final Logger logger = LogManager.getLogger(SanityTest.class);
	public LoginPage loginPage;
	public HomePage homePage;
	public CreateFreeAccountPage createFreeAccountPage;
	public AddNewBeneficiaryPage addNewBeneficiaryPage;
	public AddNewFamilyMemberPage addNewFamilyMemberPage;

	@Test(priority = 2)
	@Parameters("env")
	public void testValidLoginAndLogOut(String env) {
		logger.info("Starting login test in environment: " + env);
		test = extent.createTest("Verify the subscribed user should be able to login and logout successfully")
				.assignCategory("Regression").assignCategory("Login");
		loginPage = new LoginPage(driver);
		String username = ConfigReader.get("userName");
		String password = ConfigReader.get("password");
		loginPage.login(username, password);
		homePage = new HomePage(driver);
		homePage.isFPHLogoDisplayed();
		homePage.clickSignOutButton();
	}

	@Test(priority = 1)
	@Parameters("env")
	public void testFreeSubscription(String env) {
		logger.info("Starting login test in environment: " + env);
		test = extent.createTest("Verify user should be able to take free Subscription ").assignCategory("Create");
		loginPage = new LoginPage(driver);
		loginPage.clickCreateFreeAccount();
		createFreeAccountPage = new CreateFreeAccountPage(driver);
		createFreeAccountPage.createFreeAccount(env, "RandomName", "91", "RandomMobile", "RandomEmail", "Kaara@123");
		homePage = new HomePage(driver);
		homePage.clickOKButtonOnCreateAccountSuccessPopup();
		homePage.isFPHLogoDisplayed();
		homePage.clickSignOutButton();
	}

	@Test(priority = 3)
	@Parameters("env")
	public void testAddNewBeneficiary(String env) {
		logger.info("Starting login test in environment: " + env);
		test = extent.createTest("Verify user should be able to add a New Beneficiary ").assignCategory("Create");
		loginPage = new LoginPage(driver);
		String username = ConfigReader.get("userName");
		String password = ConfigReader.get("password");
		loginPage.login(username, password);
		homePage = new HomePage(driver);
		homePage.clickAddNewBeneficiaryButton();
		addNewBeneficiaryPage = new AddNewBeneficiaryPage(driver);
		addNewBeneficiaryPage.addNewBeneficiary("RandomName", "91", "RandomMobile", "Male");
		homePage.isBeneficiaryAddedSuccessMessageDisplayed();
		homePage.clickSignOutButton();
	}

	@Test(priority = 4)
	@Parameters("env")
	public void testAddNewFamilyMember(String env) {
		logger.info("Starting login test in environment: " + env);
		test = extent.createTest("Verify user should be able to add a New Family Member ").assignCategory("Create");
		loginPage = new LoginPage(driver);
		String username = ConfigReader.get("userName");
		String password = ConfigReader.get("password");
		loginPage.login(username, password);
		homePage = new HomePage(driver);
		homePage.clickAddNewFamilyMemberButton();
		addNewFamilyMemberPage = new AddNewFamilyMemberPage(driver);
		addNewFamilyMemberPage.addNewFamilyMember("RandomName", "91", "RandomMobile",
				ConfigReader.get("beneficiaryName"));
		homePage.isFamilyMemberAddedSuccessMessageDisplayed();
		homePage.clickSignOutButton();
	}
}
