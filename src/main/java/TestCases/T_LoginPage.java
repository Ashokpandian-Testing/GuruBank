package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import GuruPages.LoginPage;

public class T_LoginPage extends TestBase{
	
	@Test
	@Parameters({"username","password"})
	public static void loginTest(@Optional("as198184@gmail.com") String username, @Optional("Guru@2019") String password) {
		Extentlog = ExtentReport.createTest(T_LoginPage.class.getName());
		PageFactory.initElements(driver, GuruPages.LoginPage.class);
		LoginPage.txtUserName.sendKeys(username);
		Extentlog.log(Status.PASS,"User Name : '"+username+"' is Entered");
		LoginPage.txtPassword.sendKeys(password);
		Extentlog.log(Status.PASS,"User Name : '"+password+"' is Entered");
		LoginPage.btnLogin.click();	
		Extentlog.log(Status.PASS,"Login Button is Clicked");
		}
}
