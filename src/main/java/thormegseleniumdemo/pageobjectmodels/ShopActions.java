package thormegseleniumdemo.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import thormegseleniumdemo.utils.LocatorUtils;

public class ShopActions {
	private WebDriver d;	

	//product fancybox
	private By addToCartButton = By.xpath("//*[@id='add_to_cart']/button");
	private By itemPrice = By.id("our_price_display");
	private By continueShopping = By.xpath("//span[contains(@title,'Continue shopping')]");
	
	//Summary page onwards
	private By checkoutOrder = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span");
	private By totalPrice = By.cssSelector("#total_price_without_tax");
	private By shippingCost = By.id("total_shipping");
	private By checkoutButton = By.xpath("//*[@id='center_column']/p[2]/a[1]/span");
	private By processAddress = By.name("processAddress");
	private By termsAgreementBox = By.xpath("//*[@id='cgv']");
	private By processCarrierLocator = By.name("processCarrier");
	private By payBankWireLocator = By.xpath("//*[@id='HOOK_PAYMENT']/div[1]/div/p/a");
	private By confirmOrderLocator = By.xpath("//*[@id='cart_navigation']/button/span");
	

	public ShopActions(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d, this);
	}

	public void addToCart() {
			LocatorUtils.locateClickable(d, addToCartButton).click();
	}

	public String getPrice() {
		return LocatorUtils.waitForElementVisible(d, itemPrice).getAttribute("innerHTML");
	}

	public WebElement continueShopping() {
			return LocatorUtils.locateClickable(d, continueShopping);
	}

	public void checkoutOrder()  throws InterruptedException {
		//Due to page load times or otherwise, this seemed to have some issues.
		LocatorUtils.shortWait();
		LocatorUtils.shortWait();
		d.findElement(checkoutOrder).click();
		LocatorUtils.shortWait();
	}
	
	public void checkoutSummary() {
		d.findElement(checkoutButton).click();
	}
	
	public void addressConfirmation() throws InterruptedException {
		d.findElement(processAddress).click();
		LocatorUtils.shortWait();
	}
	
	public void agreeTermsAndContinue() throws InterruptedException {
		d.findElement(termsAgreementBox).click();
		LocatorUtils.shortWait();
		d.findElement(processCarrierLocator).click();
		LocatorUtils.shortWait();
	}
	
	public void chooseDeliveryType() throws InterruptedException {
		//Actually does nothing at the moment because there's one valid path choice
		agreeTermsAndContinue();
	}
	
	public void finaliseOrder() throws InterruptedException {
		d.findElement(confirmOrderLocator).click();	
		LocatorUtils.shortWait();
	}
	
	/**
	 * Should be overloaded with another type if default is not desirable.
	 * @throws InterruptedException
	 */
	public void choosePaymentType() throws InterruptedException {
		d.findElement(payBankWireLocator).click();
		LocatorUtils.shortWait();
	}
	
	public String getTotalPriceInSummary() {
		return d.findElement(totalPrice).getAttribute("innerHTML");
	}
	
	public String getShipping() {
		return d.findElement(shippingCost).getAttribute("innerHTML");

	}

}
