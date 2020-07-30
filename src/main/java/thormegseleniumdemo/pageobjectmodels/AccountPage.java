package thormegseleniumdemo.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import thormegseleniumdemo.utils.LocatorUtils;

public class AccountPage {
	private WebDriver d;	

	private By msgBoxLocator = By.name("msgText");
	private By submitMessageLocator = By.xpath("//*[@id='sendOrderMessage']/div/button");
	
	public AccountPage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d, this);
	}
	
	public WebElement viewOrderHistory() {
		return LocatorUtils.waitForElementVisible(d, By.cssSelector(
				"#center_column > div > div:nth-child(1) > ul > li:nth-child(1) > a > span"));
	}
	
	public WebElement returnLatestOrder() {
		return LocatorUtils.waitForElementVisible(d, 
				By.xpath("//*[@id='order-list']/tbody/tr[1]/td[7]/a"));
	}
	
	public void addComment(String comment) {
		d.findElement(msgBoxLocator).sendKeys(comment);
		d.findElement(submitMessageLocator).submit();
	}
}
