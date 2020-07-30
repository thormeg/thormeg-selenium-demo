package thormegseleniumdemo.test;

import java.text.DecimalFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import thormegseleniumdemo.pageobjectmodels.AccountPage;
import thormegseleniumdemo.pageobjectmodels.Homepage;
import thormegseleniumdemo.pageobjectmodels.LoginPage;
import thormegseleniumdemo.pageobjectmodels.Product;
import thormegseleniumdemo.pageobjectmodels.ShopActions;
import thormegseleniumdemo.utils.LocatorUtils;

public class ShopUITests extends SeleniumBaseTests {

	//Stick some of these in a config file.
	private String USERNAME = "test@test.com";
	private String PASSWORD = "test";
	private String BASE_URL = "http://www.automationpractice.com";
	private String LOGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
	private Homepage hp;
	private Product prod;
	private ShopActions sa;
	private LoginPage la;
	private AccountPage ap;

	@Test(priority=1)//(enabled = false)
	public void scenarioOne() throws InterruptedException {
		System.out.println("Scenario one");
		// Navigate to page and set up required classes
		la = new LoginPage(driver);
		hp = new Homepage(driver);
		prod = new Product(driver);
		sa = new ShopActions(driver);

		driver.get(LOGIN_URL);
		la.doLogin(USERNAME, PASSWORD);
		driver.get(BASE_URL);
		driver.manage().window().maximize();

		// Get an item, we're just going to use name here - hardcoding names seems
		// like a bad idea long term, could just get nth item on list.
		action.moveToElement(hp.getProductByCSS("Blouse")).click().build().perform();
		// tricky frame bit here
		driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
		prod.selectSize("L");

		double firstPrice = stringToDouble(sa.getPrice());
		sa.addToCart();
		LocatorUtils.shortWait();
		action.moveToElement(sa.continueShopping()).click().build().perform();
		LocatorUtils.shortWait();

		// Getting nth item this time
		driver.switchTo().defaultContent();
		LocatorUtils.shortWait();
		action.moveToElement(hp.getNthProduct("3")).click().build().perform();
		driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
		double secondPrice = stringToDouble(sa.getPrice());
		
		//Begin checkout process
		sa.addToCart();
		sa.checkoutOrder();
		LocatorUtils.shortWait();
		double shipping = stringToDouble(sa.getShipping());
		double totalCost = firstPrice + secondPrice + shipping;
		DecimalFormat df = new DecimalFormat("#.00");
		Assert.assertEquals(sa.getTotalPriceInSummary(), "$" + df.format(totalCost));
		sa.checkoutSummary();
		sa.addressConfirmation();
		sa.chooseDeliveryType();
		sa.choosePaymentType();
		sa.finaliseOrder();
		Assert.assertEquals(driver.getTitle(), "Order confirmation - My Store");
		la.doLogout();
	}

	@Test(priority=2)//(enabled = false)
	public void scenarioTwo() throws Exception {
		System.out.println("Scenario two");
		driver.get(LOGIN_URL);
		driver.manage().window().maximize();

		la = new LoginPage(driver);
		hp = new Homepage(driver);
		prod = new Product(driver);
		sa = new ShopActions(driver);
		ap = new AccountPage(driver);

		la.doLogin(USERNAME, PASSWORD);

		action.moveToElement(ap.viewOrderHistory()).click().build().perform();
		action.moveToElement(ap.returnLatestOrder()).click().perform();
		ap.addComment("new comment with date " + new Date().getTime());
		la.doLogout();
	}

	@Test(priority=3)//(enabled = false)
	public void scenarioThreeFirstFail() throws Exception {
		System.out.println("Scenario three, first");
		driver.get(BASE_URL);
		driver.manage().window().maximize();

		la = new LoginPage(driver);
		hp = new Homepage(driver);
		prod = new Product(driver);
		sa = new ShopActions(driver);
		ap = new AccountPage(driver);

		action.moveToElement(hp.getNthProduct("1")).click().perform();
		driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
		Assert.assertEquals(prod.getSize(), "M");
		la.doLogout();
	}

	@Test(priority=4)//(enabled=false)
	public void scenarioThreeSecondFail() throws Exception {
		System.out.println("Scenario three, second");
		driver.get(BASE_URL);
		driver.manage().window().maximize();

		la = new LoginPage(driver);
		hp = new Homepage(driver);
		prod = new Product(driver);
		sa = new ShopActions(driver);
		ap = new AccountPage(driver);

		action.moveToElement(hp.getNthProduct("3")).click().perform();
		driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
		Assert.assertEquals("My Title", driver.getTitle());
	}
	
	private double stringToDouble(String priceString) {
		return Double.parseDouble(priceString.substring(1));
	}
}
