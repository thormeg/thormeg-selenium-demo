package thormegseleniumdemo.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Product {
	private WebDriver d;
	private By sizeDropdown = By.name("group_1");

	public Product(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d, this);
	}

	public void selectSize(String size) {
		new Select(d.findElement(this.sizeDropdown)).selectByVisibleText(size);
	}

	public String getSize() {
		return new Select(d.findElement(sizeDropdown)).getFirstSelectedOption().getText();
	}

}
