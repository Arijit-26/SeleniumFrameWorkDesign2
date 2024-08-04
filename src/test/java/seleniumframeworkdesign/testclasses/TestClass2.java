package seleniumframeworkdesign.testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClass2 {
	
	@Test
	public static void main() throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/");
		driver.manage().window().maximize();
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("Overwhelming@gmail.com");
		WebElement pwdField = driver.findElement(By.id("pass"));
		pwdField.sendKeys("Password@123");
		driver.findElement(By.id("send2")).click();
		
		w.until(ExpectedConditions.urlMatches("https://magento.softwaretestingboard.com/"));
		driver.findElement(By.cssSelector("#ui-id-5")).click();
		w.until(ExpectedConditions.urlMatches("https://magento.softwaretestingboard.com/men.html"));
		WebElement prodBlock=driver.findElement(By.cssSelector(".block-products-list"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",prodBlock );
		List<WebElement> productName = driver.findElements(By.xpath("//strong[@class='product-item-name']"));
		List<WebElement> atcButtons = driver.findElements(By.cssSelector(".tocart"));
		for(int i = 0;i<productName.size();i++)
		{
			
			WebElement product =productName.get(i).findElement(By.tagName("a"));
			if(product.getText().equalsIgnoreCase("Hero hoodie"))
			{
				Actions a = new Actions(driver);
				a.moveToElement(product).build().perform();
				js.executeScript("arguments[0].click();",atcButtons.get(i));
				break;
			}
		}
		
		w.until(ExpectedConditions.urlMatches("https://magento.softwaretestingboard.com/hero-hoodie.html"));
		List<WebElement> sizes = driver.findElements(By.xpath("//div[@class='swatch-option text']"));
		for(WebElement size:sizes)
		{
			if(size.getText().equalsIgnoreCase("l"))
			{
				size.click();
				break;
			}
		}
		List<WebElement> colors = driver.findElements(By.cssSelector(".swatch-option.color"));
		for(WebElement color:colors)
		{
			if(color.getAttribute("option-label").equalsIgnoreCase("green"))
			{
				color.click();
				break;
			}
		}
		driver.findElement(By.id("product-addtocart-button")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".action.showcart")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".block-minicart")));
		String prodName = driver.findElement(By.xpath("//strong[@class='product-item-name']/a")).getText();
		Assert.assertTrue(prodName.equalsIgnoreCase("Hero hoodie"));
		driver.findElement(By.id("top-cart-btn-checkout")).click();
		w.until(ExpectedConditions.urlMatches("https://magento.softwaretestingboard.com/checkout/#shipping"));
		Thread.sleep(5000);
		try 
		{driver.findElement(By.xpath("//input[@name='company']")).sendKeys("NewCompany");
		driver.findElement(By.xpath("//input[@name='street[0]']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@name='street[1]']")).sendKeys("456");
		driver.findElement(By.xpath("//input[@name='street[2]']")).sendKeys("789");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Kolkata");
		WebElement dropdown = driver.findElement(By.xpath("//select[@name='region_id']"));
		Select regionDropdown = new Select(dropdown);
		regionDropdown.selectByVisibleText("Washington");
		driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("700034");
		WebElement dropdown2 = driver.findElement(By.xpath("//select[@name='country_id']"));
		Select countryDropdown= new Select(dropdown2);
		countryDropdown.selectByVisibleText("United States");
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("8740234561");
		driver.findElement(By.xpath("//input[@name='ko_unique_1']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".continue")).click();
		}
		catch(Exception e) {
			driver.findElement(By.xpath("//input[@name='ko_unique_1']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector(".continue")).click();
		}
		
		w.until(ExpectedConditions.urlMatches("https://magento.softwaretestingboard.com/checkout/#payment"));
		w.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".checkout")));
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".checkout")));
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));
		driver.findElement(By.cssSelector(".checkout")).click();
		w.until(ExpectedConditions.urlMatches("https://magento.softwaretestingboard.com/checkout/onepage/success/"));
		String succesMessage = driver.findElement(By.xpath("//h1/span")).getText();
		String realMessage = "Thank you for your purchase!";
		Assert.assertEquals(succesMessage, realMessage);
		driver.close();
		
}
}