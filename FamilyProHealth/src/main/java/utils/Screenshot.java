package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static String getScreenshot(WebDriver driver, String tcName) throws IOException {
		String photo = System.getProperty("user.dir") + "/src/test/resources/screenshots/"; // "eclipse-workspace/LINK/src/test/resource/Screenshots/";
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(photo+timeStamp+"-"+tcName+".png");
		String screenshotPath = destinationFile.getAbsolutePath();
		FileUtils.copyFile(sourceFile, destinationFile);
		
		return screenshotPath;
		
	}

}