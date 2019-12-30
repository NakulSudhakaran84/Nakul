package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	public WebDriverWait wait;
	public WebDriver driver;
	public LoginPage(WebDriver ldriver)
	{
		
		this.driver=ldriver;
		
	}
	
	@FindBy(name="username") WebElement uname;
	@FindBy(name="pwd") WebElement pass;
	@FindBy(xpath="//*[@id=\"loginButton\"]/div") WebElement loginbutton;

	
	public void loginToActiTime(String usernsme,String password)
	{
	
			uname.sendKeys(usernsme);
			pass.sendKeys(password);
			loginbutton.click();
		
	}

}
