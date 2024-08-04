package seleniumframeworkdesign.testclasses;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumframeworkdesign.basetestclass.BaseTest;
import seleniumframeworkdesign.pageobject.CartPage;
import seleniumframeworkdesign.pageobject.LoginPage;
import seleniumframeworkdesign.pageobject.MyAccountPage;
import seleniumframeworkdesign.pageobject.MyOrderPage;
import seleniumframeworkdesign.pageobject.Order;
import seleniumframeworkdesign.pageobject.PlaceOrderPage;
import seleniumframeworkdesign.pageobject.ProductCatalogue;
import seleniumframeworkdesign.pageobject.ProductCatalogueMen;
import seleniumframeworkdesign.pageobject.ShippingPage;
import seleniumframeworkdesign.pageobject.SuccessfulOrderPage;

public class TestClass1 extends BaseTest {


	@Test
	public void shopping() throws InterruptedException, IOException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction("Overwhelming@gmail.com","Password@123");
		ProductCatalogueMen products = productCataloguePage.clickon("https://magento.softwaretestingboard.com/");
		CartPage cartPage = products.clickOnRequiredProduct("Hero Hoodie");
		cartPage.selectFeaturesAndAddToCart();
		Assert.assertTrue(cartPage.checkInCart("Hero Hoodie"));
		ShippingPage shippingPage = cartPage.checkOut();
		PlaceOrderPage placeOrderPage = shippingPage.ship();
		SuccessfulOrderPage successfulOrderPage = placeOrderPage.placeOrder();
		Assert.assertTrue(successfulOrderPage.successMessageCheck("Thank you for your purchase!"));
		
	}
	
	@Test(dependsOnMethods = { "shopping" })                                                 //The test 'checkMyOrders' depends on the method 'shopping()'. So when we run this test, first 'shopping()' will be executed and only then will 'checkMyOrders()' be executed, because 'checkMyOrders()' won't run until 'shopping()' is completed
	
	public void checkMyOrders() throws InterruptedException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction("Overwhelming@gmail.com","Password@123");
		MyAccountPage myAccountPage = productCataloguePage.clickOnMyAccount();
		MyOrderPage myOrderPage=myAccountPage.clickOnMyOrders();
		Order order = myOrderPage.checkOrder();
		String productName = order.returnProdName();
		Assert.assertTrue(productName.equalsIgnoreCase("Hero Hoodie"));
		
	}
	
	
	
}
		