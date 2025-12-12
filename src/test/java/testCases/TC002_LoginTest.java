package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups = {"Sanity","Master"})
	public void verify_login() {
		logger.info("....Started TC002_LoginTest..........");

		try {
			//Homepage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			Thread.sleep(1000); 
			hp.clickMyLogin();


			//loginpage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPasssword(p.getProperty("password"));
			lp.clickBtn();

			//My Accountpage
			MyAccountPage my=new MyAccountPage(driver);
			boolean target=my.isMyAccountPageExists();
			Assert.assertEquals(target,true,"Login Failed");
		 	//Assert.assertTrue(target);


		}catch (Exception e) {
			 e.printStackTrace();   // <-- shows actual error in console
			    logger.error("Test failed: ", e);
			Assert.fail();
		}
		logger.info("....Completed TC002_LoginTest..........");

	}

}
