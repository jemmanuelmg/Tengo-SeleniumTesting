package co.tengo.selenium.dms.example;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountListPage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions action;
	private By newAccountLink = By.cssSelector("a[title='New'");
	private By accountNameInput = By.xpath("//label/span[text()='Account Name']/../../input");
	private By phoneInput = By.xpath("//label/span[text()='Phone']/../../input");
	private By regionPicklist = By.xpath("//span[contains(concat(' ',normalize-space(@class),' '),'label')]/span[text()='Region']/../../div[contains(concat(' ',normalize-space(@class),' '),'uiMenu')]/div/div/div/a");
	private By primaryContactLookup = By.xpath("//label[contains(concat(' ',normalize-space(@class),' '),'label')]/span[text()='Account Primary Contact']/../../div/div/div/div/input");
	private By regulatorRightButton = By.xpath("//div[text()='Regulator']/../../div/div/div/div[4]/lightning-button-icon/button");
	private By foreignRegulatorCheckbox = By.xpath("//span[text()='Registered with Foreign Regulator']/../../input[@type='checkbox']");
	private By descriptionTextArea = By.xpath("//label[contains(concat(' ',normalize-space(@class),' '),'label')]/span[text()='Description']/../../textarea");
	private By saveButton = By.xpath("//div[contains(concat(' ',normalize-space(@class),' '),'button-container')]/button/span[text()='Save']/..");
	
	public AccountListPage(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 60);
		this.action = new Actions(driver);
		
	}
	
	
	public void clickNewAccountLink() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.newAccountLink));
		driver.findElement(this.newAccountLink).click();
		
	}
	
	public void setAccountNameInputAs(String value) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.accountNameInput));
		WebElement accountNameInput = driver.findElement(this.accountNameInput);
		accountNameInput.sendKeys(value);
		
	}
	
	public void setPhoneInputAs(int value) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.phoneInput));
		WebElement phoneInput = driver.findElement(this.phoneInput);
		phoneInput.sendKeys(String.valueOf(value));
		
	}
	
	public void setRegionPicklistAs(String value) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.regionPicklist));
		WebElement regionPicklist = driver.findElement(this.regionPicklist);
		regionPicklist.click();
		
		WebElement option = driver.findElement(By.xpath("//li/a[@title='" + value + "'][@role='menuitemradio']"));
		action.moveToElement(option).click().build().perform();		
		
	}
	
	public void setRegulatorMultiPicklistAs(ArrayList<String> options) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.regulatorRightButton));
		WebElement regulatorRightButton = driver.findElement(this.regulatorRightButton);
		
		for (String optionLabel : options) {
			
			String optionSelector = "//span/span[text()='" + optionLabel + "']/../..";
			WebElement option = driver.findElement(By.xpath(optionSelector));
			action.moveToElement(option).click().build().perform();
			action.moveToElement(regulatorRightButton).click().build().perform();
			
		}
		
	}
	
	public void setPrimaryContactLookup() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.primaryContactLookup));
		WebElement primaryContactLookup = driver.findElement(this.primaryContactLookup);
		primaryContactLookup.click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		primaryContactLookup.sendKeys(Keys.ARROW_DOWN);
		primaryContactLookup.sendKeys(Keys.ENTER);
		
	}
	
	public void checkForeignRegulator() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.foreignRegulatorCheckbox));
		WebElement foreignRegulatorCheckbox = driver.findElement(this.foreignRegulatorCheckbox);
		foreignRegulatorCheckbox.click();
		
	}
	
	public void setDescriptionTextAreaAs(String value) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.descriptionTextArea));
		WebElement descriptionTextArea = driver.findElement(this.descriptionTextArea);
		descriptionTextArea.click();
		descriptionTextArea.sendKeys(value);
		
	}
	
	public void clickOnSave() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.saveButton));
		WebElement saveButton = driver.findElement(this.saveButton);
		saveButton.click();
		
	}
	
}
