package co.tengo.selenium.dms.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	protected WebDriver driver;
	private By usernameInput = By.id("username");
	private By passwordInput = By.id("password");
	private By loginButton = By.id("Login");
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public void enterUsername(String username) {
		
		driver.findElement(usernameInput).sendKeys(username);
		
	}
	
	public void enterPassword(String password) {
		
		driver.findElement(passwordInput).sendKeys(password);
		
	}
	
	public void clickOnLogin() {
		
		driver.findElement(loginButton).click();
		
	}
	
}
