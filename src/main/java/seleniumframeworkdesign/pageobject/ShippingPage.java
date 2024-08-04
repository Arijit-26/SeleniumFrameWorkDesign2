package seleniumframeworkdesign.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import seleniumframeworkdesign.reusablecodes.ReusableComponents;

public class ShippingPage extends ReusableComponents{

	WebDriver driver;
	
	public ShippingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@name='company']")
	WebElement company;
	
	@FindBy(xpath="//input[@name='street[0]']")
	WebElement addressLine1;
	
	@FindBy(xpath="//input[@name='street[1]']")
	WebElement adressLine2;
	
	@FindBy(xpath="//input[@name='street[2]']")
	WebElement adressLine3;
	
	@FindBy(xpath="//input[@name='city']")
	WebElement city;
	
	@FindBy(xpath="//select[@name='region_id']")
	WebElement region;
	
	@FindBy(xpath="//input[@name='postcode']")
	WebElement postcode;
	
	@FindBy(xpath="//select[@name='country_id']")
	WebElement country;
	
	@FindBy(xpath="//input[@name='telephone']")
	WebElement telephone;
	
	@FindBy(xpath="//input[@name='ko_unique_1']")
	WebElement checkBox;
	
	@FindBy(css=".continue")
	WebElement continueButton;

	String URL="https://magento.softwaretestingboard.com/checkout/#shipping";

	//Here I am not using page factory, as there are too many web elements
	
	public PlaceOrderPage ship() throws InterruptedException {
		waitForURLToMatch(URL);
		Thread.sleep(5000);
		try 
		{company.sendKeys("NewCompany");
		addressLine1.sendKeys("123");
		adressLine2.sendKeys("456");
		adressLine3.sendKeys("789");
		city.sendKeys("Kolkata");
		Select regionDropdown = new Select(region);
		regionDropdown.selectByVisibleText("Washington");
		postcode.sendKeys("700034");
		
		Select countryDropdown= new Select(country);
		countryDropdown.selectByVisibleText("United States");
		telephone.sendKeys("8740234561");
		checkBox.click();
		Thread.sleep(5000);
		continueButton.click();
		}
		catch(Exception e) {
			checkBox.click();
			Thread.sleep(5000);
			continueButton.click();
		}
		
		PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
		return placeOrderPage;
	}
	
}
