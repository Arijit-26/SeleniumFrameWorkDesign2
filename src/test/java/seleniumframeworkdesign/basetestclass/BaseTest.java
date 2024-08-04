package seleniumframeworkdesign.basetestclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumframeworkdesign.pageobject.LoginPage;

public class BaseTest {
	
     public WebDriver driver; 
     public LoginPage newLoginPage;   
      
	public WebDriver initializeDriver() throws IOException {
		String browserName;
		Properties prop = new Properties();                   
		FileInputStream file = new FileInputStream("C:\\Users\\Arijit\\eclipse-workspace\\seleniumframeworkdesign2\\src\\main\\java\\seleniumframeworkdesign\\globalproperty\\GlobalData.properties"); 
		prop.load(file);                        
		if(System.getProperty("browser")!=null)
		{  
			browserName=System.getProperty("browser");
		}         
		else
		{ 
			browserName = prop.getProperty("browser");
		}           
		System.out.println(browserName);
		
		
		if(browserName.contains("chrome"))
		{
			//If the browserName contains "headless" in addition to containing "chrome", the following codes will be executed to run the tests in a headless mode 
			if(browserName.contains("headless"))
			{
				ChromeOptions options = new ChromeOptions();        //For running a test in headless mode, we need the ChromeOptions class
				options.addArguments("headless");
				this.driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900));
			}
			else 
			{
				this.driver = new ChromeDriver();
		
			}
		}		
		else if(browserName.contains("firefox"))
		{
			this.driver = new FirefoxDriver();
		}
		
		else if(browserName.contains("edge"))
		{
			this.driver = new EdgeDriver();
		}
		
		this.driver.manage().window().maximize(); 
		return this.driver;                       
	}
	
	
	
	public List<HashMap<String,String>> readJsonFile(String filePath) throws IOException {  
		
		File file1 = new File(filePath);
		String jsContent= FileUtils.readFileToString(file1,StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();       
		List<HashMap<String,String>> data= mapper.readValue(jsContent, new TypeReference<List<HashMap<String,String>>>(){}); 
		return data;   
		
	}
	
	
	public String getScreenShot(String testName,WebDriver driver) throws IOException {
		TakesScreenshot ss= (TakesScreenshot) driver;                 
		File source =ss.getScreenshotAs(OutputType.FILE);             
		File destinationPath = new File(System.getProperty("user.dir")+"//ScreenShots//"+testName+".png");  
		FileUtils.copyFile(source, destinationPath);                     
		return System.getProperty("user.dir")+"//ScreenShots//"+testName+".png";   
	} 
	
	
	@BeforeMethod(alwaysRun=true)                             
	public void launchBrowser() throws IOException {                
		
		WebDriver driver1= initializeDriver();
		newLoginPage = new LoginPage(driver1);
		newLoginPage.goTo();
		
	}	
	
	@AfterMethod(alwaysRun=true)                            
	public void tearDown() {
		driver.close();
	}
}