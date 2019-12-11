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
import java.io.InputStream;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class TestBase {
	
	public static WebDriver driver;
	public static Logger logger = Logger.getLogger(TestBase.class);
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports ExtentReport;
	public static ExtentTest Extentlog;
	
	
	@BeforeTest
	public static void initBrowserDriver() throws IOException {	
		if(getPropertyValue("browser").equalsIgnoreCase("chrome")) {
			System.out.println(System.getProperty("user.dir")+"\\chromedriver.exe");
			System.setProperty("driver.chrome.webdriver", System.getProperty("user.dir")+"\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		PropertyConfigurator.configure("log4j.properties");
		logger.info("'"+getPropertyValue("browser")+"' Browser is launched Successfully");
		ExtentReport = new ExtentReports();
		htmlreporter = new ExtentHtmlReporter("ExtentReport.html");
		ExtentReport.attachReporter(htmlreporter);
		htmlreporter.config().setDocumentTitle(getPropertyValue("ExtentDocumentTitle"));
		htmlreporter.config().setReportName(getPropertyValue("ExtentReportTitle"));
		htmlreporter.config().setTheme(Theme.STANDARD);
		ExtentReport.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
//		driver.get("http://demo.guru99.com/insurance/v1/index.php");		
	}
	
	@BeforeTest(dependsOnMethods="initBrowserDriver")
	public static void GuruLogin() throws IOException {
		driver.get(getPropertyValue("apploginurl"));
		logger.info("URL '"+getPropertyValue("apploginurl")+"' is launched Successfully");
	}
	
	
	@AfterTest
	public static void KillBrowser() throws IOException {
		driver.quit();
		ExtentReport.flush();
		logger.info("'"+getPropertyValue("apploginurl")+"' Browser Closed");
		
	}
	
	
	public static String getPropertyValue(String key) throws IOException {
		//FileInputStream Fis = new FileInputStream("config.properties");
		InputStream Fis = ClassLoader.getSystemResourceAsStream("config.properties");
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
		suitefiles.add(System.getProperty("user.dir")+"\\src\\main\\resources\\testng.xml");
		// now set xml file for execution
		testng.setTestSuites(suitefiles);
		testng.run();		
		
	}
	
}
