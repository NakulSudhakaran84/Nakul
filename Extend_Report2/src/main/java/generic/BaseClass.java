package generic;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass implements IAutoConst{

	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite()
	{
		//this is BeforeSuite
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(REPORT_PATH));
		report=new ExtentReports();
		report.attachReporter(extent);
	}
	
	@BeforeClass
	public void setUp()
	{
		String url=UtilityClass.getProperty(SETTINGS_PATH, "URL");
		driver=BrowserFactory.startApplication(driver, "chrome", url);	
	}
	
	@BeforeMethod
	public void pageLoad()
	{
		String strETO=UtilityClass.getProperty(SETTINGS_PATH, "ETO");
		long ETO= Long.parseLong(strETO);
		wait=new WebDriverWait(driver, ETO);
		wait.until(ExpectedConditions.titleContains("actiTIME"));		
	}
	
	@AfterMethod(alwaysRun = true)
	public void takePhoto(ITestResult res) throws IOException
	{
		String imgPath=UtilityClass.getPhoto(driver, PHOTO_PATH, res.getName());
		
		if(res.getStatus()==ITestResult.FAILURE)
		{
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
		}
		else if(res.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());		
		}
		else if(res.getStatus()==ITestResult.SKIP)
		{
			logger.skip("Test Skipped",MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());	
		}
			report.flush();
	}
	@AfterClass
	public void tearDown()
	{	
		String strITO=UtilityClass.getProperty(SETTINGS_PATH,"ITO");
	    long ITO= Long.parseLong(strITO);
		driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
		BrowserFactory.quitBrowser(driver);
	}
	
}
