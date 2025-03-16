package abhitendrasingh.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abhitendrasingh.pageObjects.CartPage;
import abhitendrasingh.pageObjects.ConfirmationPage;
import abhitendrasingh.pageObjects.OrdersPage;
import abhitendrasingh.pageObjects.PaymentsPage;
import abhitendrasingh.pageObjects.ProductCatalogPage;
import abhitendrasingh.testComponenets.BaseTest;
import abhitendrasingh.testComponenets.Retry;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
		ProductCatalogPage productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		productCatalog.addProductToCart(input.get("product"));
		CartPage checkout = productCatalog.goToCart();

		Boolean match = checkout.verifyCartItem(input.get("product"));
		Assert.assertTrue(match);
		PaymentsPage payments = checkout.clickCheckoutBtn();

		ConfirmationPage confirm = payments.placeOrder("India");

		String finalText = " Thankyou for the order. ";
		Boolean finalMatch = confirm.comparePageHeaderText(finalText);
		Assert.assertTrue(finalMatch);
	}

	@Test(dependsOnMethods = { "submitOrder" }, retryAnalyzer = Retry.class)
	public void orderHistory() {
		ProductCatalogPage productCatalog = landingPage.loginApplication("randomschitz@gmail.com", "Abhi@1234");
		OrdersPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//abhitendrasingh//data//purchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "randomschitz@gmail.com");
//		map.put("password", "Abhi@1234");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map.put("email", "kiwenil128@lxheir.com");
//		map.put("password", "Abhi@1234");
//		map.put("product", "ADIDAS ORIGINAL");
		
	}
	
//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"randomschitz@gmail.com", "Abhi@1234", "ZARA COAT 3"},{"kiwenil128@lxheir.com", "Abhi@1234", "ADIDAS ORIGINAL"}};
//	}
}
