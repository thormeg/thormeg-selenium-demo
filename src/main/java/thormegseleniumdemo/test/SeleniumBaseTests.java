package thormegseleniumdemo.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import thormegseleniumdemo.utils.LocatorUtils;
import thormegseleniumdemo.utils.ScreenshotUtils;

/**
 * Base test class to allow placement of some common test code, such as driver
 * variables and types.
 * TODO: this should also contain a logger and results writer (to DB?)
 */
public class SeleniumBaseTests {
	public WebDriver driver;
	public ScreenshotUtils scu;
	public LocatorUtils lu;
	public Actions action;

	//TODO: This should be put in a config file
	private static String CHROME_WEBDRIVER_LOCATION = "src\\main\\java\\thormegseleniumdemo\\resources\\chromedriver.exe";

	public SeleniumBaseTests() {
		System.out.println("Starting Selenium tests...");
		System.setProperty("webdriver.chrome.driver",  CHROME_WEBDRIVER_LOCATION);
		ChromeOptions options = new ChromeOptions();

		//My laptop had some issues whereby the page would go to mobile view when I had
		//dev tools open, so I've forced a resolution size here for simplicity
		//Another candidate for config file.

		//TODO: Resolution might not work for all environments, improve this. (TechDebt)
		options.addArguments("window-size=1280,1024");
		driver = new ChromeDriver(options);	
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		action = new Actions(driver);

		scu = new ScreenshotUtils(driver);
		lu = new LocatorUtils();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		System.out.println("Test completed: " + result.getName());

		if(result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("...failed");
			scu.screenshot(result.getName());
		}
	}
}
