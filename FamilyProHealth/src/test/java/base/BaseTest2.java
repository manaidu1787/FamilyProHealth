package base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.ConfigReader;

public class BaseTest2 {
    protected WebDriver driver;
    

	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;

	@BeforeTest
	public void StartTest() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "LINK-Test-Report-" +timeStamp+ ".html";
		String reportPath = System.getProperty("user.dir") + "/src/test/resources/reports/" + reportName;
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			spark = new ExtentSparkReporter(reportPath);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("LINK Automation Report");
			spark.config().setReportName("LINK DEV Environment Automation Test Report");
			extent.attachReporter(spark);

		}

	}
	

	@AfterTest
	public static void endTest() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}

	}

    @Parameters("env")
    @BeforeMethod
    public void setUp(@Optional("dev") String env) {
        ConfigReader.loadProperties(env);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
