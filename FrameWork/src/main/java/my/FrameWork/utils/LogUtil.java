package my.FrameWork.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import io.qameta.allure.Step;

import my.FrameWork.Base.*;

public final class LogUtil {
	public static Logger logger;
	
	private LogUtil()
	{}
	@Step("{0}")
	public static void log(final String message)
	{
		
	}
	
	@Step("{0}") 
	public static void log1(String message) 
	{ 
		logger.info("Logged to allure: " + message); 
		Reporter.log(message); 
		}

}
