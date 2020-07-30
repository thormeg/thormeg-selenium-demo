package thormegseleniumdemo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Handles reusable wait code, to avoid redundancy
 * TODO: Could accept a configurable max wait time from YAML file
 * TODO: Could be hooked into logger for better success/failure output
 */
public class LocatorUtils {

	public static WebElement locateClickable(WebDriver d, By by) {
		return new WebDriverWait(d, 3).until(
				ExpectedConditions.elementToBeClickable(by));
	}
	
	public static WebElement waitForElementVisible(WebDriver d, By by) {
	    return new WebDriverWait(d,6).until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public static void shortWait() throws InterruptedException {
		Thread.sleep(1000);
	}
}
