package my.FrameWork.Test;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import my.FrameWork.Base.Base;
import my.FrameWork.Pages.LogInPage;
import my.FrameWork.utils.AllureReport;

@Listeners({AllureReport.class })
public class FBLoginTest extends Base {

	// @Test(dataProvider="inputData")//Hashtable<String, String> data
	// @Test(dataProvider="inputData")

	/*@DataProvider(name = "inputData1")
	public Object[][] getTestDataData() {
		Object[][] data = getData("SampleTest");
		return data;
	}*/

	/*
	 * @Test(dataProvider="inputData",enabled=false) public void
	 * logInValidation(Hashtable<String, String> data) throws IOException {
	 * System.err.println(data.get("UserName")); driver.get(url); //LogInPage
	 * logger.info("Launched URL : " +url); LogInPage lp=new LogInPage(driver);
	 * 
	 * 
	 * lp.enterUserName(data.get("UserName")); logger.info("Enterd User name");
	 * lp.enterPasword(data.get("PassWord")); logger.info("Enterd Password");
	 * capturedScreenshot(driver, "test"); lp.clickOnLogInButton();
	 * logger.info("Clicked On Login button"); Thread.sleep(10000);
	 * if(!driver.getTitle().equals("Raju")) { capturedScreenshot(driver,
	 * "logInValidation"); Assert.assertTrue(false); } }
	 */
	//@Test(dataProvider = "inputdata")
	@Test(dataProvider = "inputdata",priority = 1, description = "verifying login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify login page title test on Login Page")
	@Story("Story Name: To check login page title")
	public void logInValidation(Hashtable<String, String> data) throws InterruptedException {
		System.err.println("******************************");

		// driver.get(url);
		// LogInPage
		logger.info("Launched URL : " + url);
		LogInPage lp = new LogInPage(driver);

		lp.enterUserName(data.get("UserName"));
		logger.info("Enterd User name");
		lp.enterPasword(data.get("PassWord"));
		logger.info("Enterd Password");
		// capturedScreenshot(driver, "test");
		lp.clickOnLogInButton();
		logger.info("Clicked On Login button");
		System.out.println("*****************************************");
		Thread.sleep(6000);
		lp.verifyTitle("Parvathy Raju");
	}
	
	/*@Test(dataProvider="inputdata")
	@Severity(SeverityLevel.NORMAL)
	@Description("Login Test")
	@Story("To check Login Page title")
	public void logInValidation(Hashtable<String, String> data)
	{
		LogInPage lp = new LogInPage(driver);

		lp.enterUserName(data.get("UserName"));
		logger.info("Enterd User name");
		lp.enterPasword(data.get("PassWord"));
		logger.info("Enterd Password");
		// capturedScreenshot(driver, "test");
		lp.clickOnLogInButton();
		logger.info("Clicked On Login button");
		System.out.println("*****************************************");
		Assert.assertTrue(false);
		
	}
	*/
}
