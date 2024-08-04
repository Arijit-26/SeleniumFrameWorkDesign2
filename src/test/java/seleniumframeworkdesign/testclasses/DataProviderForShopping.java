package seleniumframeworkdesign.testclasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumframeworkdesign.basetestclass.BaseTest;
import seleniumframeworkdesign.pageobject.CartPage;
import seleniumframeworkdesign.pageobject.PlaceOrderPage;
import seleniumframeworkdesign.pageobject.ProductCatalogue;
import seleniumframeworkdesign.pageobject.ProductCatalogueMen;
import seleniumframeworkdesign.pageobject.ShippingPage;
import seleniumframeworkdesign.pageobject.SuccessfulOrderPage;

public class DataProviderForShopping extends BaseTest {

	@Test(dataProvider="getData")                //The two sets of data are fed to the test method using (dataProvider) attribute. So the test method will run 2 times
	
	public void shopping(String email,String password,String product) throws InterruptedException, IOException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(email,password);
		ProductCatalogueMen products = productCataloguePage.clickon("https://magento.softwaretestingboard.com/");
		CartPage cartPage = products.clickOnRequiredProduct(product);
		cartPage.selectFeaturesAndAddToCart();
		Assert.assertTrue(cartPage.checkInCart(product));
		ShippingPage shippingPage = cartPage.checkOut();
		PlaceOrderPage placeOrderPage = shippingPage.ship();
		SuccessfulOrderPage successfulOrderPage = placeOrderPage.placeOrder();
		Assert.assertTrue(successfulOrderPage.successMessageCheck("Thank you for your purchase!"));
}
	@DataProvider                               //Using this method with @DataProvider attribute, we feed sets of data into the test method
	public Object[][] getData() {
		
		return new Object[][] {{"Overwhelming@gmail.com","Password@123","Hero Hoodie"},{"Underwhelming@gmail.com","Passcode@123","Hero Hoodie"}};
		
		//Each set contains three data-> email , password, product we want to buy
		//So there are 2 sets and 3 data in each sets , so the data matrix will be a 2x3 matrix.
		//The data type is 'Object'. This is a primitive data type.Our data can be a mixture of int, string. So we cannot declare the data matrix as int or string. The data type for the matrix is 'Object', so that one data in a set can be int , another data in the same set can be string, like that
				
	}
}
