package seleniumframeworkdesign.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframeworkdesign.reusablecodes.ReusableComponents;

public class MyOrderPage extends ReusableComponents {
	WebDriver driver;
	String URL = "https://magento.softwaretestingboard.com/sales/order/history/";
	public MyOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//td[@data-th='Actions']/a[@class='action view']")
	List<WebElement> orderRows;
	
	public Order checkOrder() {
		
		waitForURLToMatch(URL);
		orderRows.get(1).click();
		Order order = new Order(driver);
		return order;
		
	}
	
}
