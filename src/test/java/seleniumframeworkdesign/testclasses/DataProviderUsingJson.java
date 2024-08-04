package seleniumframeworkdesign.testclasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

public class DataProviderUsingJson extends BaseTest {


@Test(dataProvider="getData")
public void shopping(HashMap<String,String> hmap) throws InterruptedException, IOException {

		ProductCatalogue productCataloguePage = newLoginPage.loginAction(hmap.get("email"),hmap.get("password"));                           
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
public Object[][] getData() throws IOException {
	
	
	
	List<HashMap<String,String>> data = readJsonFile("C:\\Users\\Arijit\\eclipse-workspace\\seleniumframeworkdesign\\src\\test\\java\\seleniumframeworkdesign\\data\\credentials.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};                                           
	
	}
}
