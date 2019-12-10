package GuruPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	@FindBy(id="email")
	public static WebElement txtUserName;
	
	@FindBy(id="password")
	public static WebElement txtPassword;
	
	@FindBy(name="submit")
	public static WebElement btnLogin;

}
