package my.FrameWork.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class LogInPage {

	WebDriver driver;

	public LogInPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="email")
	private WebElement ipUserName;

	@FindBy(id="pass")
	private WebElement ipPassWord;

	@FindBy(id="loginbutton")
	private WebElement btnLogin;

	@Step("Enter the user : {0}")
	public void enterUserName(String userName)
	{
		ipUserName.sendKeys(userName);
	}
	@Step("Enter the password : {0}")
	public void enterPasword(String passWord)
	{
		ipPassWord.sendKeys(passWord);
	}
	@Step("Click on Login button")
	public void clickOnLogInButton()
	{
		btnLogin.click();
	}



}
