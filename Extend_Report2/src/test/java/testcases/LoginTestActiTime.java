package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseClass;
import generic.UtilityClass;
import page.LoginPage;


public class LoginTestActiTime extends BaseClass {
	
	
	@Test(priority = 1)
	public void validLoginApp()
	{
		test=report.createTest("Valid Login to ActiTIME");
		LoginPage loginpage=PageFactory.initElements(driver, LoginPage.class);
		test.info("Starting the Application");
		String username=UtilityClass.getCellVaue(XL_PATH,"ValidLogin", 1, 0);
		String password=UtilityClass.getCellVaue(XL_PATH, "ValidLogin", 1, 1);
		loginpage.loginToActiTime(username, password);
		Assert.assertTrue(driver.getTitle().contains("Time-Track"));	
		test.pass("Logged in Successfully");	
	}
	
	@Test(priority = 1)
	public void invalidLoginApp()
	{
		test=report.createTest("Invalid Login to ActiTIME");
		LoginPage loginpage=PageFactory.initElements(driver, LoginPage.class);
		test.info("Starting the Application");
		String username=UtilityClass.getCellVaue(XL_PATH,"InvalidLogin", 1, 0);
		String password=UtilityClass.getCellVaue(XL_PATH, "InvalidLogin", 1, 1);
		loginpage.loginToActiTime(username, password);		
		test.fail("Wrong Credentials");	
		Assert.assertEquals("actiTIME",driver.getTitle());
		
		
	}
}
