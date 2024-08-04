package seleniumframeworkdesign.testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumframeworkdesign.basetestclass.BaseTest;
import seleniumframeworkdesign.pageobject.LoginPage;

public class WrongCredentialsLogin extends BaseTest {

	@Test(groups= {"Wrong"})
	public void loginUsingWrongPassword() {
		
		String error = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		newLoginPage.loginAction("Overwhelming@gmail.com","password@123");
		String message = newLoginPage.wrongCredentials();
		Assert.assertTrue(message.contains(error));
	}

	@Test(groups={"Wrong"})
	public void loginUsingWrongEmail() {
		
		String error = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		newLoginPage.loginAction("Underwhelming@gmail.com","password@123");
		String message = newLoginPage.wrongCredentials();
		Assert.assertTrue(message.contains(error));
	}	
	
}

