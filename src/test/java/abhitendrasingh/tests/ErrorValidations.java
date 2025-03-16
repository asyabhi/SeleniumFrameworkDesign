package abhitendrasingh.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import abhitendrasingh.pageObjects.CartPage;
import abhitendrasingh.pageObjects.ProductCatalogPage;
import abhitendrasingh.testComponenets.BaseTest;
import abhitendrasingh.testComponenets.Retry;

public class ErrorValidations extends BaseTest {
	@Test(retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {

		landingPage.loginApplication("randomschitz@gmail.com", "Abh@1234");
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation() throws IOException {
		String productName = "ADIDAS ORIGINAL";

		ProductCatalogPage productCatalog = landingPage.loginApplication("kiwenil128@lxheir.com", "Abhi@1234");

		productCatalog.addProductToCart(productName);
		CartPage checkout = productCatalog.goToCart();

		Boolean match = checkout.verifyCartItem("Original");
		Assert.assertFalse(match);
	}
}
