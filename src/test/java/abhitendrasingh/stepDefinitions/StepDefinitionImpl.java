package abhitendrasingh.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import abhitendrasingh.pageObjects.CartPage;
import abhitendrasingh.pageObjects.ConfirmationPage;
import abhitendrasingh.pageObjects.LandingPage;
import abhitendrasingh.pageObjects.PaymentsPage;
import abhitendrasingh.pageObjects.ProductCatalogPage;
import abhitendrasingh.testComponenets.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogPage productCatalog;
	public CartPage checkout;
	public PaymentsPage payments;
	public ConfirmationPage confirm;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^I login with username (.+) and password (.+)$")
	public void I_login_with_username_and_password(String username, String password) throws IOException {

		productCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("^I add the (.+) to cart$")
	public void I_add_the_product_to_cart(String productName) {
		productCatalog.addProductToCart(productName);
	}
	
	@When("^I checkout (.+) and submit the order$")
	public void I_checkout_product_and_submit_the_order(String productName) {
		checkout = productCatalog.goToCart();

		Boolean match = checkout.verifyCartItem(productName);
		Assert.assertTrue(match);
		payments = checkout.clickCheckoutBtn();

		confirm = payments.placeOrder("India");
	}
	
	@Then("{string} message is displayed on Confirmation Page")
	public void message_is_displayed_on_confirmation_page(String string) {
		Boolean finalMatch = confirm.comparePageHeaderText(string);
		Assert.assertTrue(finalMatch);
		driver.quit();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.quit();
	}
}
