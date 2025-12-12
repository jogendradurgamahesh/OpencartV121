package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLogin;
//	
//	 @FindBy(xpath = "//a[@title='My Account']")
//	    WebElement lnkMyAccount;
//
//	    @FindBy(linkText = "Register")
//	    WebElement lnkRegister;
//
//	    @FindBy(linkText = "Login")
//	    WebElement lnkLogin;


	   
	public void clickMyAccount() {
		lnkMyAccount.click();
//		try {
//	        Thread.sleep(1000); // wait for dropdown
//	    } catch (InterruptedException e) {
//	        e.printStackTrace();
//	    }
	}
	
	public void clickMyRegister() {
		lnkRegister.click();
	}
	
	public void clickMyLogin()  //login added in step5
	{
		lnkLogin.click();
	}
	
	
	
}
