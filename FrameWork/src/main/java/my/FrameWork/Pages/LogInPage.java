package my.FrameWork.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.qameta.allure.Step;
import my.FrameWork.utils.LogUtil;

public class LogInPage {

	WebDriver driver;

	public LogInPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="email")
	private WebElement ipUserName;

	@FindBy(id="pass1")
	private WebElement ipPassWord;

	@FindBy(id="loginbutton")
	private WebElement btnLogin;
	

	@Step("Enter the user : {0}")
	public void enterUserName(String userName)
	{
		ipUserName.sendKeys(userName);
		LogUtil.log1("Entered user ");
	}
	@Step("Enter the password :")
	public void enterPasword(String passWord)
	{
		ipPassWord.sendKeys(passWord);
		LogUtil.log1("Entered password");
	}
	@Step("Click on Login button")
	public void clickOnLogInButton()
	{
		btnLogin.click();
		LogUtil.log1("Clicked on Login Button");
	}
	
	@Step("verify Page title:")
	public void verifyTitle(String exp)
	{
		String act=driver.getTitle();
		System.out.println("Title of the page : "+ act);
		LogUtil.log1("Actual : "+act +" and "+exp+" should be equal...");
		Assert.assertEquals(act, exp,"Shod be equal");
		
	}



}
