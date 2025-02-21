package abhitendrasingh.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import abhitendrasingh.pageObjects.CartPage;
import abhitendrasingh.pageObjects.ConfirmationPage;
import abhitendrasingh.pageObjects.OrdersPage;
import abhitendrasingh.pageObjects.PaymentsPage;
import abhitendrasingh.pageObjects.ProductCatalogPage;
import abhitendrasingh.testComponenets.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ADIDAS ORIGINAL";
	@Test
	public void submitOrder() throws IOException {
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
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory() {
		ProductCatalogPage productCatalog = landingPage.loginApplication("randomschitz@gmail.com", "Abhi@1234");
		OrdersPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
}
