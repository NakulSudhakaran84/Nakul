package generic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UtilityClass {
	
	public static BufferedReader br=null;
	
	public static String getProperty( String path,String key)
	{
		//this is for taking data from properties file
		String v="";
		try {
			Properties p=new Properties();
			p.load(new FileInputStream(path));
			v=p.getProperty(key);
			}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			return v;
	}
	
		public static String getCellVaue(String path,String sheet,int r,int c)
		{
			String v="";
			try {
				new WorkbookFactory();
				Workbook wb=WorkbookFactory.create(new FileInputStream(path));
				v=wb.getSheet(sheet).getRow(r).getCell(c).toString();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return v;
		}
	

		public static String getPhoto(WebDriver driver,String folder,String name)
		{
			TakesScreenshot t=(TakesScreenshot)driver;
			File srcFile=t.getScreenshotAs(OutputType.FILE);
			String date=new Date().toString().replaceAll(":", "-");
			String path= System.getProperty("user.dir")+folder+name+" "+date+".png";
			File destFile=new File(path);
			
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return path;
		}
		
		public static String emailCredentials(String path,String checkdata) {
			
			String credentials="";
			if(checkdata=="hostname")
			{
			credentials=getProperty(path, "HOSTNAME");
			}
			else if(checkdata=="mailid")
			{
			credentials=getProperty(path, "EMAIL_AUTHENTICATOR");
			}
			else if(checkdata=="password")
			{
			credentials=getProperty(path, "AUTHENTICATOR_PWD");
			}
			else if(checkdata=="mailfrom")
			{
			credentials=getProperty(path, "EMAIL_FROM");
			}
			else if(checkdata=="mailto")
			{
			credentials=getProperty(path, "EMAIL_TO");
			}
			return credentials;
		}
		
		
		public static String readTextFile(String filepath)
		{
			String data="";
			try {
				data=new String(Files.readAllBytes(Paths.get(filepath)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return data;
		}
		
		public static String emailSendData(String filepath,String checkdata)
		{	
			String data=(checkdata=="esubject")?readTextFile(filepath):(checkdata=="emessage")?readTextFile(filepath):"Default";
			return data;
		}
		
		public static void sendEmail(WebDriver driver,String name,String path,String esubject,String emessage) throws EmailException
		{	
		Email email = new SimpleEmail();
		email.setHostName(emailCredentials(path,"hostname"));
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(emailCredentials(path,"mailid"), emailCredentials(path,"password")));
		email.setSSLOnConnect(true);
		email.setFrom(emailCredentials(path,"mailfrom"));
		email.setSubject(emailSendData(esubject,"esubject"));
		email.setMsg(emailSendData(emessage,"emessage"));
		email.addTo(emailCredentials(path,"mailto"));
		email.send();
		}
}
