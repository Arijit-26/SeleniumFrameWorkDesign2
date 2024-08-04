package seleniumframeworkdesign.testclasses;

import java.io.IOException;
import java.util.HashMap;

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

public class DataProviderUsingHashMap extends BaseTest {

@Test(dataProvider="getData")
public void shopping(HashMap<String,String> hmap) throws InterruptedException, IOException {
//Since the data matrix now contains hashmaps, so in each set , instead of passsing three data to the test method , we will pass one hashmap.
//For the first set , the 'hmap' will recieve the HashMap 'map' and from there we will retrieve the values needed
//For the second set, the 'hmap' will recieve the HashMap 'map1'
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(hmap.get("email"),hmap.get("password"));                           //hmap.get(key) will return the value associated with the key.
		ProductCatalogueMen products = productCataloguePage.clickon("https://magento.softwaretestingboard.com/");
		CartPage cartPage = products.clickOnRequiredProduct(hmap.get("product"));
		cartPage.selectFeaturesAndAddToCart();
		Assert.assertTrue(cartPage.checkInCart(hmap.get("product")));
		ShippingPage shippingPage = cartPage.checkOut();
		PlaceOrderPage placeOrderPage = shippingPage.ship();
		SuccessfulOrderPage successfulOrderPage = placeOrderPage.placeOrder();
		Assert.assertTrue(successfulOrderPage.successMessageCheck("Thank you for your purchase!"));
}

@DataProvider                               
public Object[][] getData() {
	
	//We will store each row/set of data in a hashmap and then put it in the data matrix.
	
	HashMap<String,String> map = new HashMap<String,String>();                       //This hashmap contains the first set of data
	map.put("email", "Overwhelming@gmail.com");										 // We enter data using map.put(key,value). The login email adress is stored in the HashMap associated with the key 'email'
	map.put("password", "Password@123");
	map.put("product", "Hero Hoodie");
	
	HashMap<String,String> map1 = new HashMap<String,String>();						//This hashmap contains the second set of data
	map1.put("email", "Underwhelming@gmail.com");
	map1.put("password", "Passcode@123");
	map1.put("product", "Hero Hoodie");
	
	return new Object[][] {{map},{map1}};                                            //This is how we pass the hashmaps into the data matrix
	
	}
}
