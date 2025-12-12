package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AccountRegistartionPage extends BasePage {

	WebDriver driver;
	public  AccountRegistartionPage (WebDriver driver) {
		super(driver);
	}	
	
		
		@FindBy(xpath="//input[@id='input-firstname']") WebElement input_firstname;
		
		@FindBy(xpath="//input[@id='input-lastname']") WebElement input_lastname;
		@FindBy(xpath="//input[@id='input-email']") WebElement input_email;
		@FindBy(xpath="//input[@id='input-telephone']") WebElement input_telephone;
		@FindBy(xpath="//input[@id='input-password']") WebElement input_password;
		@FindBy(xpath="//input[@id='input-confirm']") WebElement input_confirm;
		@FindBy(xpath="//input[@name='agree']") WebElement input_agree;
		@FindBy(xpath="//input[@value='Continue']") WebElement input_continue;
		@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msg_confirmation;

		
		public void  setFirstname(String fname) {
			input_firstname.sendKeys(fname);
		}
		
		public void  setLastname(String lname) {
			input_lastname.sendKeys(lname);
		}
		
		public void  setEmail(String email) {
			input_email.sendKeys(email);
		}
		
		public void setTelephone(String telephone) {
			input_telephone.sendKeys(telephone);
		}

		public void  setPassword(String password) {
			input_password.sendKeys(password);
		}
		
		public void  conformPassword(String coPassword) {
			input_confirm.sendKeys(coPassword);
		}
		
		public void setPrivacyPolicy() {
			input_agree.click();
		}
		
		public void setContinue() {
			input_continue.click();
		}
		
		public String getConfirmationMsg() {
			try {
				return (msg_confirmation.getText());
			}
			catch(Exception e) {
				return (e.getMessage());
			}
		}
}
