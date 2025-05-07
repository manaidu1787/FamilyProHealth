package base;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;
import utils.Screenshot;

public class BaseTest {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties config = new Properties();
	public static Properties loc = new Properties();

	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;

	@BeforeTest
	@Parameters("env")
	public void StartTest(String env) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "FPH-Test-Report-" + timeStamp + ".html";
		String reportPath = System.getProperty("user.dir") + "/src/test/resources/reports/" + reportName;
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			spark = new ExtentSparkReporter(reportPath);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("FPH Automation Report");
			spark.config().setReportName("FPH "+env+" Environment Automation Test Report");
			extent.attachReporter(spark);

		}

	}

	@Parameters("env")
	@BeforeMethod
	public void setUp(@Optional("dev") String env) {
		ConfigReader.loadProperties(env);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get(ConfigReader.get("url"));
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			// To add it in the extent report
			String pathScreenshot = Screenshot.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(pathScreenshot);
			test.fail("Test Method Failed is " + result.getMethod());
			test.fail("Error in Test Case Failed is " + result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Case Failed is " + result.getName());
		}

		driver.quit();
		test.pass("Browser Closed");
	}

	@AfterTest
	public static void endTest() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}

	}

}
