package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import GuruPages.LoginPage;

public class T_LoginPage extends TestBase{
	
	@Test
	@Parameters({"username","password"})
	public static void loginTest(@Optional("as198184@gmail.com") String username, @Optional("Guru@2019") String password) {
		PageFactory.initElements(driver, GuruPages.LoginPage.class);
		LoginPage.txtUserName.sendKeys(username);
		LoginPage.txtPassword.sendKeys(password);
		LoginPage.btnLogin.click();	
		}
}
