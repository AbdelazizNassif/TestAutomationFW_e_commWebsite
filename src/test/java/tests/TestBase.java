package tests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import utilities.Helper;

public class TestBase extends AbstractTestNGCucumberTests{
	public static WebDriver driver;
	public static String downloadPath = System.getProperty("user.dir")+"\\downloads";
	
	/*method to set up the fire fox browser options*/
	public static FirefoxOptions firefoxOpiton()
	{
		FirefoxOptions myBrowserOptions = new FirefoxOptions();
		myBrowserOptions.addPreference("browser.download.folderList", 2);
		myBrowserOptions.addPreference("browser.download.dir", downloadPath);
		myBrowserOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		myBrowserOptions.addPreference("browser.download.manager.showWhenStarting", false);
		myBrowserOptions.addPreference("pdfjs.disabled", true);
		return myBrowserOptions;
	}
	
	/*method to set up the chrome browser options--> comment*/
	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}
	
	/*will run before the suite only once to configure the browser to be used 
	 * --> considering the default browser firefox*/
	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("firefox")String browserName)
	{
		if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", 
					System.getProperty("user.dir")+"//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome-headless"))
		{
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("headless"))
		{
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY
					, System.getProperty("user.dir")+"//drivers//phantomjs.exe");
			String[] phantomCliArgs = {"--web-security=no", "--ignore-ssl-errors=yes"};
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS
					, phantomCliArgs);
			driver = new PhantomJSDriver(caps);
		}
		
		/*maximize the window screen*/
		driver.manage().window().maximize();
		/*give the driver 3 seconds before announcing failure of any web element*/
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.navigate().to("http://demo.nopcommerce.com/");
	}
	
	/*will run after the suite only once to close the browser */
	@AfterSuite
	public void stopDriver()
	{
		driver.close();
	}
	
	/*screenShotFailure will run after every test case to  check if it failed or not
	 * and if failed it will capture it */
	@AfterMethod
	public void screenShotFailure(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE )
		{
			/* take screen shot if the status is failed */
			Helper.captureScreenShot(driver, result.getName());
		}
	}
}
