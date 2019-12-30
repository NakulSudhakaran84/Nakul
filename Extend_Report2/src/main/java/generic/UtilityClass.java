package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UtilityClass {
	
	public static String getProperty( String path,String key)
	{
		
		
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
		
		
}
