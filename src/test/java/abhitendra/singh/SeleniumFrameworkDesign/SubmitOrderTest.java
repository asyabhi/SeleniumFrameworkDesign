package abhitendra.singh.SeleniumFrameworkDesign;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import abhitendra.singh.pageObjects.CartPage;
import abhitendra.singh.pageObjects.ConfirmationPage;
import abhitendra.singh.pageObjects.LandingPage;
import abhitendra.singh.pageObjects.PaymentsPage;
import abhitendra.singh.pageObjects.ProductCatalogPage;

public class SubmitOrderTest {
	public static void main(String[] args) {
		String productName = "ADIDAS ORIGINAL";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogPage productCatalog = landingPage.loginApplication("randomschitz@gmail.com", "Abhi@1234");

		productCatalog.addProductToCart(productName);
		CartPage checkout = productCatalog.goToCart();

		Boolean match = checkout.verifyCartItem(productName);
		Assert.assertTrue(match);
		PaymentsPage payments = checkout.clickCheckoutBtn();

		ConfirmationPage confirm = payments.placeOrder("India");

		String finalText = " Thankyou for the order. ";
		Boolean finalMatch = confirm.comparePageHeaderText(finalText);
		Assert.assertTrue(finalMatch);
		driver.quit();

	}
}
