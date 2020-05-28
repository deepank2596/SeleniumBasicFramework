package app.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {
	
	private ExtentHtmlReporter htmlReporter = null;
	private ExtentReports extent = null;
	private ExtentTest test = null;
	private String reportPath = ConfigManager.getInstance().getProperty("Reportpath");
	private String currentReportPath = null;
	private String testName= null;
	
	
	public ExtentReportManager(String testName) {
		this.testName = testName;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		
		currentReportPath = System.getProperty("user.dir")+reportPath+"/Report_"+dateFormat.format(date);
		
		htmlReporter = new ExtentHtmlReporter(currentReportPath+"/Test_Report_"+dateFormat.format(date)+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest(testName);
		
	}
	
	public void passed (String actual) {
		test.pass(actual);
	}
	
	public void passed (String actual,WebDriver driver) throws Exception {
		
		String screenShotLocation = getScreenhot(driver);
		
		test.pass(actual, MediaEntityBuilder.createScreenCaptureFromPath(screenShotLocation).build());
	}
	
	public void failed (String actual) {
		test.fail(actual);
	}
	
	public void failed (String actual,WebDriver driver) throws Exception {
		
		String screenShotLocation = getScreenhot(driver);
		
		test.fail(actual, MediaEntityBuilder.createScreenCaptureFromPath(screenShotLocation).build());
	}
	
	public void flushReports() {
		extent.flush();
	}
	
	public String getScreenhot(WebDriver driver) throws Exception {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = currentReportPath+ "/Screenshots/"+testName+dateName+".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }

	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		System.out.println(format.format(date));
	}

}
