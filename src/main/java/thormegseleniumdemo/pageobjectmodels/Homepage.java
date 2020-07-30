package thormegseleniumdemo.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import thormegseleniumdemo.utils.LocatorUtils;

public class Homepage {
	private WebDriver d;

	public Homepage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d, this);
	}

	public WebElement getNthProduct(String product) {
		return LocatorUtils.locateClickable(d, By.xpath(String.format("//*[@class=\"tab-content\"]/ul/li[%s]//a", product)));
	}

	public WebElement getProductByCSS(String name) {
		return LocatorUtils.waitForElementVisible(d, By.cssSelector(String.format("img[title='%s']", name)));
	}
}
