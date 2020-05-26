package my.FrameWork.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyManager {
	Properties prop;
	
	public PropertyManager()
	{
		File f=new File("./sources/config.properties");
		try {
			FileInputStream fi=new FileInputStream(f);
			prop=new  Properties();
			prop.load(fi);
		}catch(Exception e)
		{
			System.out.println("Exception is : "+e.getMessage());
		}
	}
	
	public String getURL()
	{
		String url =prop.getProperty("URL");
		System.out.println(url);
		return url;
	}
	
	public String getUserName()
	{
		String userName =prop.getProperty("UserName");
		System.out.println(userName);
		return userName;
	}
	
	public String getPassword()
	{
		String password =prop.getProperty("Password");
		System.out.println(password);
		return password;
	}
	
	public String getBrowser()
	{
		String browser =prop.getProperty("browser");
		System.out.println(browser);
		return browser;
	}

}
