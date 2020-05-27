package app.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {
		private WebDriver driver;
		private static String driverType;
		private static String driverPath;
		
		public DriverManager() {
			driverPath= System.getProperty("user.dir")+ConfigManager.getInstance().getProperty("Drivers");
			driverType= ConfigManager.getInstance().getProperty("Browser");
		}
		
		public WebDriver getDriver() {
			if(driver == null) driver = createDriver();
			return driver;
		}
		
		private WebDriver createDriver() {
			if(driverType.equalsIgnoreCase("chrome")) {
	    		System.setProperty("webdriver.chrome.driver", driverPath+"/chromedriver.exe");
	    		driver= new ChromeDriver();
	    	}
	    	else if(driverType.equalsIgnoreCase("ie")) {
	    		System.setProperty("webdriver.ie.driver", driverPath+"/IEDriverServer.exe");
	    		driver= new InternetExplorerDriver();
	    	}else
	    	{
	    		System.out.println("Invalid browser"+driverType);
	    	}
			
			driver.manage().window().maximize();
	    	return driver;
		}
		
		public void closeDriver() {
			driver.close();
			driver.quit();
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver*");
				Runtime.getRuntime().exec("taskkill /F /IM IEDriver*");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
