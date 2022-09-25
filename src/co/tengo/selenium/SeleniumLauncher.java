package co.tengo.selenium;

import java.util.Timer;

import co.tengo.selenium.dms.example.AccountTest;

public class SeleniumLauncher {

	public static String username;
	public static String password;
	public static String loginAddress;
	public static String contextPath;
	
	public static void main(String[] args) throws InterruptedException {
		
		SeleniumLauncher.initializeParameters(args);
		AccountTest.main();
		
	}
	
	public static void initializeParameters(String[] args) {
		
		SeleniumLauncher.startReporter();
		if (SeleniumLauncher.validateParameters(args)) {
			SeleniumLauncher.username = args[0];
			SeleniumLauncher.password = args[1];
			SeleniumLauncher.loginAddress = args[2];
			SeleniumLauncher.contextPath = args[3];
		} else {
			SeleniumLauncher.setDefaultParameters();
		}
		
		SeleniumLauncher.setChromeDriverProperty();
		
	}

	public static boolean validateParameters(String[] args) {
		
		boolean validity;
		
		if (args != null && args.length > 0) {
			if (!args[0].equals("${user}") && !args[1].equals("${password}") && !args[2].equals("${address}")) {
				validity = true;
			} else {
				validity = false;
			}
		} else {
			validity = false;
		}
		
		
		return validity;
		
	}

	public static void setChromeDriverProperty() {	
		
		String path = SeleniumLauncher.contextPath;		
		String osName = System.getProperty("os.name").toLowerCase();
		
		if (osName.contains("windows")) {
			System.setProperty("webdriver.chrome.driver", path.replace("\\", "/") + "/libraries/chromedriver_win32/chromedriver.exe");
		} else if (osName.contains("mac")) {
			System.setProperty("webdriver.chrome.driver", path + "/libraries/chromedriver_mac64/chromedriver");
		} else if (osName.contains("linux")) {
			System.setProperty("webdriver.chrome.driver", path + "/libraries/chromedriver_linux64/chromedriver");
		}
		
	}
	
	public static void startReporter() {
		Timer timer = new Timer(true);
		timer.schedule(new SeleniumReporter(), 0, 2000);
	}
	
	public static void setDefaultParameters() {
		
		String path = System.getProperty("user.dir");
		SeleniumLauncher.username = "emmanuel.martinez@tengo.co.dms.emdev2";
		SeleniumLauncher.password = "brunito1119981";
		SeleniumLauncher.loginAddress = "https://test.salesforce.com";
		SeleniumLauncher.contextPath = path.substring(2, path.length());
		
	}
	
}
