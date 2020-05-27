package app.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	
	private static ConfigManager configManager = null;
	private static Properties prop = new Properties();
	
	private ConfigManager()
	{
	try {
		FileInputStream inputfile = new FileInputStream(new File("./src/main/config/config.properties"));
		prop.load(inputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConfigManager getInstance()
	{
		return (configManager==null)? configManager=new ConfigManager() : configManager;
	}
	
	public String getProperty(String key)
	{
		String value = null;
		value = prop.getProperty(key);
		return value;
		
	}
	
	

}
