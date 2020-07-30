package thormegseleniumdemo.utils;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/*
 * To be used for taking screenshots and managing filenames and paths.
 * TODO: Should probably be parameterised, rather than hard coded (YAML file)
 */
public class ScreenshotUtils {
	private WebDriver d;
	private String FILEPATH = "src\\main\\java\\screenshots\\";
	
	public ScreenshotUtils(WebDriver d) {
		this.d = d;
	}
	
	public void screenshot(String testName) throws Exception {
		TakesScreenshot sc = ((TakesScreenshot) d);
		File sf = sc.getScreenshotAs(OutputType.FILE);
		File filename = new File(FILEPATH + testName + new Date().getTime() + ".jpg");
		FileUtils.copyFile(sf, filename);
	}

}
