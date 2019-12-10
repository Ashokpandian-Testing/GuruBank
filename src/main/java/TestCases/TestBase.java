package TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestBase {
	
	public static WebDriver driver;
	
	@BeforeTest
	public static void initBrowserDriver() {	
		System.out.println(System.getProperty("user.dir")+"\\chromedriver.exe");
		System.setProperty("driver.chrome.webdriver", System.getProperty("user.dir")+"\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.get("http://demo.guru99.com/insurance/v1/index.php");		
	}
	
	@BeforeTest(dependsOnMethods="initBrowserDriver")
	public static void GuruLogin() {
		driver.get("http://demo.guru99.com/insurance/v1/index.php");
		
	}
	
	
	@AfterTest
	public static void KillBrowser() {
		driver.quit();
	}
	
	
	
}
