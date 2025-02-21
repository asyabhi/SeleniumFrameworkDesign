package abhitendrasingh.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhitendrasingh.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginBtn;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogPage loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		ProductCatalogPage productCatalog = new ProductCatalogPage(driver);
		return productCatalog;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
