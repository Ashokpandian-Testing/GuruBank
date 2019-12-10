package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import GuruPages.LoginPage;

public class T_LoginPage extends TestBase{
	
	@Test
	public static void loginTest() {
		PageFactory.initElements(driver, GuruPages.LoginPage.class);
		LoginPage.txtUserName.sendKeys("as198184@gmail.com");
		LoginPage.txtPassword.sendKeys("Guru@2019");
		LoginPage.btnLogin.click();	
		}
}
