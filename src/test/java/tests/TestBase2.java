package tests;

import java.net.MalformedURLException;
import java.net.URL;

import utilities.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Helper;

public class TestBase2 {
	
	public static String baseURL = "http://demo.nopcommerce.com/";
	protected ThreadLocal<RemoteWebDriver> driver = null;
	
	
	
	@AfterClass
	public void stopDriver()
	{
		getDriver().quit();
		driver.remove();
	}

	
	@BeforeClass
	@Parameters(value= {"browser"})
	public void setUp(@Optional("firefox")String browser) throws MalformedURLException
	{
		driver = new ThreadLocal<>();
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", browser);
		driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps));
		getDriver().navigate().to(baseURL);
	}
	
	public WebDriver getDriver()
	{
		return driver.get();
	}
	
	@AfterMethod
	public void screenShotFailure(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE )
		{
			/* take screen shot if the status is failed */
			Helper.captureScreenShot(getDriver(), result.getName());
		}
	}
}
