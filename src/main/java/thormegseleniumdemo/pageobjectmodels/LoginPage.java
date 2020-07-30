package thormegseleniumdemo.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import thormegseleniumdemo.utils.LocatorUtils;

public class LoginPage {
	private WebDriver d;	
	private By emailField = By.name("email");
	private By passwordField = By.name("passwd");
	private By submitButton = By.id("SubmitLogin");

	public LoginPage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d, this);
	}

	public void doLogin(String user, String pass) {
		d.findElement(emailField).sendKeys(user);
		d.findElement(passwordField).sendKeys(pass);
		d.findElement(submitButton).click();
	}
	
	public void doLogout() {
		Actions action = new Actions(d);
		action.moveToElement(LocatorUtils.locateClickable(d, By.cssSelector(
				"#header > div.nav > div > div > nav > div:nth-child(2) > a"))).click().perform();
	}
}
