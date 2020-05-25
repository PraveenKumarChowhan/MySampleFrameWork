package my.FrameWork.utils;
//Listener class used to generate Extent Report
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport extends TestListenerAdapter{
	public ExtentSparkReporter sRep;
	public ExtentReports rep;
	public ExtentTest logger;

	public void onStart(ITestContext context)
	{
		String timestamp=new SimpleDateFormat("yy.mm.dd.hh.mm.ss").format(new Date());
		String repName="Test report - "+timestamp+".html";

		sRep=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-op/"+repName);
		sRep.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");//ExtentSparkReporter(System.getProperty("user.dir")+"/extent-config.xml");
		rep=new ExtentReports();

		rep.attachReporter(sRep);
		rep.setSystemInfo("Host Name","localhost");
		rep.setSystemInfo("Environment","QA");
		rep.setSystemInfo("user","PR");

		sRep.config().setDocumentTitle("Sample Frame Work");
		sRep.config().setReportName("Sample Test");
		sRep.config().setTheme(Theme.DARK);
	}


	public void onTestSuccess(ITestResult result)
	{
		logger=rep.createTest(result.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result)
	{
		logger=rep.createTest(result.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		String screenShotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		File f=new File(screenShotPath);
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below"+logger.addScreenCaptureFromPath(screenShotPath));
			}catch(Exception e) {
				e.getMessage();
			}
		}
	}
	public void onTestSkipped(ITestResult result)
	{
		logger=rep.createTest(result.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext context)
	{
		rep.flush();
	}
}



/*private ExtentReports extent;
ExtentTest test;

public void generateReport(List<XmlSuite> xmlSuits, List<ISuite> suites,
		String outputDirectory) {

	extent=new ExtentReports(System.getProperty("user.dir")+"//TestRunReport.html", true);


	for(ISuite suite:suites){
		Map<String, ISuiteResult> result=suite.getResults();

		for(ISuiteResult r:result.values()){
			ITestContext context=r.getTestContext();

			buildTestNodes(context.getPassedTests(), LogStatus.PASS);
			buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
			buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);


		}
	}
	extent.flush();
	extent.close();
}

private void buildTestNodes(IResultMap tests, LogStatus status){


	if(tests.size() > 0){
		for(ITestResult result: tests.getAllResults()){
			test=extent.startTest(result.getMethod().getMethodName());
			test.setStartedTime(getTime(result.getStartMillis()));
			test.setEndedTime(getTime(result.getEndMillis()));

			List<String> testngReporterLogs=Reporter.getOutput(result);

			for(String testnglog:testngReporterLogs){
				test.log(LogStatus.PASS, testnglog);
			}
			if(result.getThrowable()!=null){
				test.log(status, result.getThrowable());
			}else{}
			extent.endTest(test);
		}
	}

}


private Date getTime(long millis){
	Calendar calender=Calendar.getInstance();
	calender.setTimeInMillis(millis);
	return calender.getTime();
}
 */



/*@SuppressWarnings("deprecation")
public ExtentHtmlReporter html;*/
