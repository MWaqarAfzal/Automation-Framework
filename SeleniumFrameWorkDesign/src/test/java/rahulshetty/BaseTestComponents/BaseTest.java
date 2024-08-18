package rahulshetty.BaseTestComponents;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshetty.pageobject.LandingPage;

public class BaseTest {
	
	
	public WebDriver driver;
	public LandingPage landing;
	
	public WebDriver driverInitilizer() throws IOException
	{
		//	CREATE OBJECT OF PROPERTY CLASS
		Properties prop = new Properties();	
		//	SET THE PATH OF THE GLOBAL PROPERTY FILE
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshetty\\resources\\GlobalData.properties");
		//	LOAD THE BLOBAL PROPERTY FILE
		prop.load(fis);
		
		//		JAVA Ternary Operators
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		// String browserName = prop.getProperty("browser");
		
		if (browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if (browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//		Read JSON to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//		Convert String to HASH MAP by using JACKSON DATABIND Repository
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		
		return data;
	}
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = driverInitilizer();
		landing = new LandingPage(driver);
		landing.loginPageNavigation();
		return landing;
	}
	
	
	public String getScreenShots(String testCaseName, WebDriver driver) throws IOException
	{
		//		Get the Driver into SCREENSHOT mode
		TakesScreenshot ts = (TakesScreenshot)driver;
		//		Take the screenshot as FILE Output
		File source = ts.getScreenshotAs(OutputType.FILE);
		//		Set the path of file where it will store
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}

}
