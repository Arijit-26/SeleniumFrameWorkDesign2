package seleniumframeworkdesign.testclasses;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import seleniumframeworkdesign.basetestclass.BaseTest;

public class ImplementingTestWithRetry extends BaseTest {

	@Test(groups= {"Wrong"}, retryAnalyzer =seleniumframeworkdesign.basetestclass.Retry.class)        //We cannot add the 'Retry' class in the xml file for the rerun to happen. We need to add it as the attribute 'retryAnalyzer=packagename.classname.class' to the method that is likely to fail.
	public void loginUsingWrongPassword() {
		
		String error = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		newLoginPage.loginAction("Overwhelming@gmail.com","password@123");
		String message = newLoginPage.wrongCredentials();
		Assert.assertFalse(message.contains(error));
	}

	@Test(groups={"Wrong"})
	public void loginUsingWrongEmail() {
		
		String error = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		newLoginPage.loginAction("Underwhelming@gmail.com","password@123");
		String message = newLoginPage.wrongCredentials();
		Assert.assertTrue(message.contains(error));
	}	
}
