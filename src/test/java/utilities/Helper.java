package utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	/*function to capture screen shots and save it a destination
	 * TestBase Class will use it to take screen shots of the failed test cases*/
	public static void captureScreenShot(WebDriver driver, String screenShotName)
	{
		Path dest = Paths.get("./screenshots/"+screenShotName+".png");
	
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Exception is caught while screenshot" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception is caught while screenshot" + e.getMessage());
		}
	}
	/*function to provide wait handle for the test cases 
	 * and every test case can customize it in class method of its own*/
	public static WebDriverWait provideExplicitWait(WebDriver driver, long timeTowait)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeTowait);
		return wait;
	}
}
