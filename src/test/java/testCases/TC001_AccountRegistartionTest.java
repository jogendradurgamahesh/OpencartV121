package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistartionPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistartionTest extends BaseClass{



	//	public String randomEmail() {
	//		return "user" + System.currentTimeMillis() + "@gmail.com";
	//	}


	@Test(groups = {"Regression","Master"})
	public	void verify_acc_reg() {

		logger.info(".....started TC001_AccountRegistartionTest.......");

		try {


			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("...Clicked on my _account link..");
			hp.clickMyRegister();
			logger.info("...Clicked on reg link..");

			AccountRegistartionPage regpage=new AccountRegistartionPage(driver);

			logger.info("...Providing customer info ...");

			//  regpage.setFirstname("Mahi");
			regpage.setFirstname(randomString().toUpperCase());
			//  regpage.setLastname("Prince");
			regpage.setLastname(randomString().toUpperCase());
			// regpage.setEmail("mahesh18@gmail.com");
			regpage.setEmail(randomString()+"@gmail.com");
			//regpage.setTelephone("995176");
			regpage.setTelephone(randomNumber());


			String password=randomAlphaNumber();
			// regpage.setPassword("prince143");
			regpage.setPassword(password);
			//    regpage.conformPassword("prince143");
			regpage.conformPassword(password);

			regpage.setPrivacyPolicy();
			regpage.setContinue();

			logger.info("...validating exp msg ...");

			String confirmMsg=  regpage.getConfirmationMsg();
			System.out.println(confirmMsg);

			if(confirmMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else {
				logger.error("Test failed");
				logger.debug("debug logs");
				Assert.assertTrue(false);
			}
	//		Assert.assertEquals(confirmMsg, "Your Account Has Been Created!!");
		}
		catch (Exception e) {
			
			Assert.fail();
		}
		logger.info(".....finished TC001_AccountRegistartionTest.......");
	}



}
