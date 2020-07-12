package tests;

import java.nio.charset.Charset;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationParallelTesting extends TestBase2 {
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 

	String email = "test" + generateRandomString() +"@gmail.com";
	String fname = generateRandomString();
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() 
	{
		homeObject = new HomePage(getDriver()); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(getDriver()); 
		registerObject.userRegistration(fname, "Nabil", email, "12345678");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	
	@Test(dependsOnMethods= {"UserCanRegisterSuccssfully"})
	public void RegisteredUserCanLogout() 
	{
		registerObject.userLogout();
	}
	
	@Test(dependsOnMethods= {"RegisteredUserCanLogout"})
	public void RegisteredUserCanLogin() 
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(getDriver()); 
		loginObject.UserLogin(email, "12345678");
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
	
	public String generateRandomString() {
	    byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	 
	    //System.out.println(generatedString);
	    return generatedString;
	}
	
}
