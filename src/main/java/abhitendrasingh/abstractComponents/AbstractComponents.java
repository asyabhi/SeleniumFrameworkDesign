package abhitendrasingh.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abhitendrasingh.pageObjects.CartPage;
import abhitendrasingh.pageObjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement ordersBtn;

	public void waitForElementToAppear(By findByElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findByElement));
	}
	
	public void waitForElementToAppear(WebElement findByElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findByElement));
	}
	
	public void waitForElementToDisappear(By findByElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findByElement));
	}
	
	public void typeUsingActions(WebElement element, String textToType) {
		Actions a = new Actions(driver);
		a.sendKeys(element, textToType).build().perform();
	}
	
	public CartPage goToCart() {
		cartBtn.click();
		CartPage checkout = new CartPage(driver);
		return checkout;
	}
	
	public OrdersPage goToOrdersPage() {
		ordersBtn.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}

}
