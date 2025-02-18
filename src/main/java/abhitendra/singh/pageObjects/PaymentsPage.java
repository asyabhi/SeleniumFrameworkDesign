package abhitendra.singh.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhitendra.singh.abstractComponents.AbstractComponents;

public class PaymentsPage extends AbstractComponents{
	
	WebDriver driver;

	public PaymentsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountryTextbox;
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement countrySelect;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;
	
	
	public void selectCountry(String countryName) {
		typeUsingActions(selectCountryTextbox, countryName);
		countrySelect.click();
	}
	
	public ConfirmationPage placeOrder(String countryName) {
		selectCountry(countryName);
		placeOrderBtn.click();
		ConfirmationPage confirm = new ConfirmationPage(driver);
		return confirm;
	}

}
