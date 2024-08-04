package seleniumframeworkdesign.testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClass {

	@Test
	public static void main() throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement emailField = driver.findElement(By.id("userEmail"));
		emailField.sendKeys("underwhelming@gmail.com");
		WebElement pwdField = driver.findElement(By.id("userPassword"));
		pwdField.sendKeys("Passcode1998!");
		driver.findElement(By.id("login")).click();
		driver.manage().window().maximize();
		w.until(ExpectedConditions.urlMatches("https://rahulshettyacademy.com/client/dashboard/dash"));
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
		List<WebElement> addToCartButtons = driver.findElements(By.xpath("//div[@class='card-body']/button[2]"));
		
		//The following is implementing streams to add required items to cart
		
		//The following is implementing for loop to add the required item to the cart
		
		for(int i=0;i<products.size();i++)
		{
			if(products.get(i).getText().equals("IPHONE 13 PRO"))
			{
				addToCartButtons.get(i).click();
			}
		}
		
		
		
		
		 
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))); //This waits for the green toast at the bottom right corner of the page , which confirms that the item has been added to the cart
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));  //This waits for the invisibility of the loading button. After the loading icon disappears, then only the green toast appears
		
		//If we place the wait for the invisibilty of loading icon before the wait for the visibility of the toast, Selelnium while first wait for the loading icon to disappear.And by the time it disappears, the toast has already appeared and disappeared. So selenium will keep on waiting for the toast and will ultimately fail the taste.
		Thread.sleep(4000);
		//w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));	
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		w.until(ExpectedConditions.urlMatches("https://rahulshettyacademy.com/client/dashboard/cart"));
		
		List<WebElement> cartProduct= driver.findElements(By.xpath(".//div[@class='cartSection']/h3"));
		Boolean flag = cartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase("IPHONE 13 PRO"));
		Assert.assertTrue(flag);
		driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']//button")).click();
		
		w.until(ExpectedConditions.urlContains("https://rahulshettyacademy.com/client/dashboard/order?"));
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		//w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']"))));
		Thread.sleep(4000);
		List<WebElement> options = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
		
		for(int i = 0;i<options.size();i++)
		{
			options = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
			String requiredName = options.get(i).getText();
			if(requiredName.equalsIgnoreCase("India"))
			{
				options.get(i).click();
			}
		}
		
		//WebElement countryButton = options.stream().filter(s->s.findElement(By.tagName("span")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		//countryButton.click();
		driver.findElement(By.xpath("//button[@class='action__submit ']")).click();
		
	}
}
