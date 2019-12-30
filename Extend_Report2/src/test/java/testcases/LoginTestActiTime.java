package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseClass;
import generic.UtilityClass;
import page.LoginPage;


public class LoginTestActiTime extends BaseClass {
	
	
	@Test(priority = 2)
	public void loginApp()
	{
		logger=report.createTest("Valid Login to ActiTIME");
		
		LoginPage loginpage=PageFactory.initElements(driver, LoginPage.class);
		
		logger.info("Starting the Application");
		
		String username=UtilityClass.getCellVaue(XL_PATH,"ValidLogin", 1, 0);
		String password=UtilityClass.getCellVaue(XL_PATH, "ValidLogin", 1, 1);
		
		loginpage.loginToActiTime(username, password);
		
		System.out.println("Title:"+driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Track"));
		
		logger.pass("Logged in Successfully");	
	}
	
	@Test(priority = 1)
	public void loginApp1()
	{
		logger=report.createTest("Invalid Login to ActiTIME");
		
		LoginPage loginpage=PageFactory.initElements(driver, LoginPage.class);
		
		logger.info("Starting the Application");
		
		String username=UtilityClass.getCellVaue(XL_PATH,"InvalidLogin", 1, 0);
		String password=UtilityClass.getCellVaue(XL_PATH, "InvalidLogin", 1, 1);
		
		loginpage.loginToActiTime(username, password);
		
		logger.fail("Wrong Credentials");	
		
		Assert.assertTrue(driver.getTitle().contains("Track"));
		
		
	}
}
