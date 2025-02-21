package abhitendrasingh.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhitendrasingh.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutBtn;
	
	
	public Boolean verifyCartItem(String productName){
		Boolean match = cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentsPage clickCheckoutBtn() {
		checkoutBtn.click();
		PaymentsPage payments = new PaymentsPage(driver);
		return payments;
	}

}
