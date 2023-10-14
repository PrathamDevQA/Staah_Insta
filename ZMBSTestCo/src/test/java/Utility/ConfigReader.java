package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ConfigReader {
	@Test
	public static String getConfigData(String Key) {
		try {
			FileInputStream fis=new FileInputStream("C:\\Users\\Pratham\\Downloads\\ZMBSTestCo\\ZMBSTestCo\\ConfigData\\ConfigData.properties");
			Properties prop=new Properties();
			prop.load(fis);
			return prop.getProperty(Key);
		}
		catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	@Test
	public Properties init_prop() {
		try {
		FileInputStream fis=new FileInputStream("C:\\Users\\Pratham\\Downloads\\ZMBSTestCo\\ZMBSTestCo\\ConfigData\\ConfigData.properties");
			Properties prop=new Properties();
			prop.load(fis);
			return prop;
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Test
	public void testAbove()  {	
		ConfigReader cr=new ConfigReader();
		Properties prop=new Properties();
		prop=cr.init_prop();
		//String a=prop.getProperty("URL");
		System.out.println(prop.getProperty("URL"));
		
	}
}
