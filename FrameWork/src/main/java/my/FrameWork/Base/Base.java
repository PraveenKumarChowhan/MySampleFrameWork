package my.FrameWork.Base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import my.FrameWork.utils.ExcelManager;
import my.FrameWork.utils.PropertyManager;

public class Base {
	
	protected WebDriver driver;
	public static Logger logger;
	PropertyManager pro=new PropertyManager();
	protected String userName=pro.getUserName();
	protected String password=pro.getPassword();
	protected String url=pro.getURL();
	WebDriverManager manager;
	private ExcelManager xls= new ExcelManager();
	public static ThreadLocal<WebDriver> driver1=new ThreadLocal<>();
	@Parameters("browser")
	public WebDriver initializeDriver()
	{		
		String br=pro.getBrowser();
		logger=Logger.getLogger("Test");
		PropertyConfigurator.configure("log4j.properties");
		if(br.equals("chrome"))
		{
			manager.chromedriver().setup();
		driver=new ChromeDriver();		
		
		}else if(br.equals("firefox"))
		{
			manager.firefoxdriver().setup();
			driver=new FirefoxDriver();			
		}else if(br.equals("ie"))
		{
			manager.iedriver().setup();
			driver=new InternetExplorerDriver();			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
		driver1.set(driver);
		return getDriver();
	}
	@BeforeMethod
	public void setUp()
	{
		initializeDriver();
		getDriver().get(url);
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
	//public Object[][] getData(Method m)
	public Object[][] getData(String name)
	{
		//String mName=
		Object[][] data=null;
		//String name=m.getName();
		System.err.println(name);
		data=xls.getData(name);
		System.out.println("Hello");
		return data;
	}
	
	public static void capturedScreenshot(WebDriver driver,String testName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"\\screenshots\\"+testName+".png");
		FileUtils.copyFile(source, target);
		System.out.println("SREENSHOT TAKEN");
	}
	
	
	
public static synchronized WebDriver getDriver()
{
	return driver1.get();
	}
}
