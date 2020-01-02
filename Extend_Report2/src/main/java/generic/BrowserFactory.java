package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory implements IAutoConst {
	
	
	public static WebDriver startApplication(WebDriver driver,String browsername,String appURL) 
	{
		if(browsername.equals("chrome"))
		{
			System.setProperty(CHROME_KEY, CHROME_VALUE);
			driver=new ChromeDriver();	
		}
		
		else if(browsername.equals("firefox"))
		{
			System.setProperty(FIREFOX_KEY, FIREFOX_VALUE);
			driver=new FirefoxDriver();
		}
		
		else
		{
			System.out.println("we do not support this browser");
		}
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return driver;

	}

	
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
		
	}
}
