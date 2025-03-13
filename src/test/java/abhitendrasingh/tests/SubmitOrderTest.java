package abhitendrasingh.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abhitendrasingh.pageObjects.CartPage;
import abhitendrasingh.pageObjects.ConfirmationPage;
import abhitendrasingh.pageObjects.OrdersPage;
import abhitendrasingh.pageObjects.PaymentsPage;
import abhitendrasingh.pageObjects.ProductCatalogPage;
import abhitendrasingh.testComponenets.BaseTest;

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

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistory() {
		ProductCatalogPage productCatalog = landingPage.loginApplication("randomschitz@gmail.com", "Abhi@1234");
		OrdersPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	public String getScreenshot(String testCaseName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
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
