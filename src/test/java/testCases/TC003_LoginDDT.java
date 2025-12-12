package testCases;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass  {

	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "Datadriven")//getting data provider from diff class
	public void verify_login(String email,String pwd,String exp) throws InterruptedException {
	

		logger.info("Started TC003_LoginDDT......" );
	
		try {
			//Homepage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickMyLogin();


			//loginpage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);  
			lp.setPasssword(pwd);
			lp.clickBtn();

			//My Accountpage
			MyAccountPage my=new MyAccountPage(driver);
			boolean target=my.isMyAccountPageExists();
			
			/*
			 -Data is valid-login success-test pass-logout
			               -login failed-test fail
			 
			 -Data is invalid-login success-test fail-logout
			                 -login failed-test pass
			 */
			
			
			if(exp.equalsIgnoreCase("valid")) {
				if(target==true) {
					 my.clickLogout();
					 Assert.assertTrue(true);
					
					
				}
				else {
					Assert.assertTrue(false);
				}
				
			}
			
			if(exp.equalsIgnoreCase("invalid")) {
				if(target==true) {
					my.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
		} 
		catch (Exception e) {
			Assert.fail();
		}
			
			logger.info("Finished TC003_LoginDDT......" );
			
			

	}

	
}
