package TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlSuite;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import java.io.FileInputStream;
import java.io.IOException;

public class TestBase {
	
	public static WebDriver driver;
	
	@BeforeTest
	public static void initBrowserDriver() throws IOException {	
		if(getPropertyValue("browser").equalsIgnoreCase("chrome")) {
			System.out.println(System.getProperty("user.dir")+"\\chromedriver.exe");
			System.setProperty("driver.chrome.webdriver", System.getProperty("user.dir")+"\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("http://demo.guru99.com/insurance/v1/index.php");		
	}
	
	@BeforeTest(dependsOnMethods="initBrowserDriver")
	public static void GuruLogin() throws IOException {
		driver.get(getPropertyValue("apploginurl"));		
	}
	
	
	@AfterTest
	public static void KillBrowser() {
		driver.quit();
	}
	
	
	public static String getPropertyValue(String key) throws IOException {
		FileInputStream Fis = new FileInputStream("config.properties");
		Properties prop = new Properties();
		prop.load(Fis);
		String value = prop.getProperty(key);				
		return value;
	}
	
	public static void main(String args[]) throws IOException {
		TestNG testng = new TestNG();
						
		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();
		 
		// Add xml file which you have to execute
		suitefiles.add("testng.xml");
		 
		// now set xml file for execution
		testng.setTestSuites(suitefiles);

		testng.run();
	}
	
}
