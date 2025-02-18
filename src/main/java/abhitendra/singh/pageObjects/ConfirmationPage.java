package abhitendra.singh.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhitendra.singh.abstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1")
	WebElement pageHeader;
	
	public boolean comparePageHeaderText(String textToCompare) {
		return pageHeader.getText().equalsIgnoreCase(textToCompare.trim());
	}
}
