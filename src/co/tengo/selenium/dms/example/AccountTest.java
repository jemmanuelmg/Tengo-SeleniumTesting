package co.tengo.selenium.dms.example;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import co.tengo.selenium.SeleniumLauncher;

public class AccountTest {

	WebDriver driver;
	LoginPage loginPage;
	AccountListPage accountListPage;
	
	public AccountTest() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		this.driver = new ChromeDriver();
		
		this.accountListPage = new AccountListPage(this.driver);
		this.loginPage = new LoginPage(this.driver);
		
	}
	
	@Test
	public static void main() throws InterruptedException {
		
		AccountTest accountTest = new AccountTest();
		accountTest.login();
		accountTest.createAccount();
		accountTest.driver.quit();
		
	}
	
	@Test
	public void login() {

		driver.get(SeleniumLauncher.loginAddress);
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains("Login"));			
				
		loginPage.enterUsername(SeleniumLauncher.username);
		loginPage.enterPassword(SeleniumLauncher.password);
		loginPage.clickOnLogin();
		
		wait.until(ExpectedConditions.titleContains("Home"));
		String pageTitle = driver.getTitle();
		
		Assert.assertEquals("Expected to be on the Home Page", true, pageTitle.contains("Home"));
		
	}
		
	@Test
	public void createAccount() throws InterruptedException {	

		driver.get(getBaseUrl() + "/lightning/o/Account/list?filterName=Recent");
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains("Accounts"));
		
		accountListPage.clickNewAccountLink();
		
		accountListPage.setAccountNameInputAs("RST Fund Services LTD");
		
		accountListPage.setPhoneInputAs(2345328);
		
		accountListPage.setRegionPicklistAs("Europe");
		
		accountListPage.setPrimaryContactLookup();
		
		accountListPage.checkForeignRegulator();
		
		accountListPage.setDescriptionTextAreaAs("Test description");
		
		ArrayList<String> options = new ArrayList<String>(); 
		options.add("(CVM) Securities and Exchange Commission of Brazil");
		options.add("(AMF) Autorité des Marchés Financier");
		options.add("(BaFin) Federal Financial Supervisory Authority Germany");
		accountListPage.setRegulatorMultiPicklistAs(options);
		
		accountListPage.clickOnSave();
		
		wait.until(ExpectedConditions.titleContains("RST Fund"));
		String pageTitle = driver.getTitle();
		
		Assert.assertEquals("Expected to be on the detail page of newly created Account", true, pageTitle.contains("RST Fund"));
		
	}
	
	public String getBaseUrl() {
		String baseUrl = driver.getCurrentUrl().substring(0, driver.getCurrentUrl().indexOf("/", 10));
		return baseUrl;
	}
	
}




