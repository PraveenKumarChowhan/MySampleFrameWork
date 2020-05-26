package my.FrameWork.utils;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import my.FrameWork.Base.*;

import com.aventstack.extentreports.ExtentTest;

import io.qameta.allure.Attachment;
import my.FrameWork.Base.*;

public class AllureReport implements ITestListener {

	/*
	 * WebDriver driver; public ExtentTest logger; AllureReport(WebDriver driver) {
	 * this.driver=driver; }
	 */

	private static String getMethodName(ITestResult iTest) {
		return iTest.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment
	public byte[] saveFailurScreenSot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/pain")
	public static String saveTexLog(String message) {
		return message;
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test method name : " + getMethodName(result));
		Object obj = result.getInstance();
		WebDriver driver = Base.getDriver();
		if (driver instanceof WebDriver) {
			System.out.println("Screen shot captured for the test case :" + getMethodName(result));
			saveFailurScreenSot(driver);
		}
		saveTexLog(getMethodName(result) + "failed and screen shot taken");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println(context.getName() + "execution is started...");
		context.setAttribute("WebDriver", Base.getDriver());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println(context.getName() + " execution is finished...");
	}

}
