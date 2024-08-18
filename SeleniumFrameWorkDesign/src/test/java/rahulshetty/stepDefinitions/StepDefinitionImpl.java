package rahulshetty.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshetty.BaseTestComponents.BaseTest;
import rahulshetty.pageobject.AddToCart;
import rahulshetty.pageobject.CatalogPage;
import rahulshetty.pageobject.CheckOut;
import rahulshetty.pageobject.LandingPage;

public class StepDefinitionImpl extends BaseTest{
	
	
	public LandingPage landing;
	public CatalogPage CatalogPage;
	public AddToCart cart;
	public CheckOut checkout;
	
	@Given("Landed on the Ecommerce page")
	public void landed_on_the_Ecommerce_page() throws IOException
	{
		landing = launchApplication();
	}
	
	@Given("^I logged in with the username (.+) and password (.+)$")
	public void i_logged_in_with_username_and_password(String username, String password)
	{
		CatalogPage = landing.login(username, password);
	}
	
	@When("^I add the product (.+) to the Cart$")
	public void i_add_the_product_to_the_card(String productname)
	{
		List<WebElement> productList = CatalogPage.getProductList();
		cart = CatalogPage.addToCard(productname);
	}
	
	@When("^Checkout product (.+) and submit the order and search countryname (.+)$")
	public void checkout_product_and_submit_the_order(String productname, String countryName) throws InterruptedException
	{
		cart.goToCartPage();
		Boolean matched = cart.verifyCartProducts(productname);
		
		//		ASSERTION SHOULD ONLY BE WRITTERN IN THE TEST CASE FILE NOT IN THE PAGE OBJECT FILE
		Assert.assertTrue(matched);
		checkout = cart.goToCheckOut();

		checkout.selectCountry(countryName);
		checkout.countriesList();
		String confrimMessage = checkout.getConfirmationMessage();
	}
	
	@Then("{string} message is displayed on the confirmation page")
	public void message_dispalyed_confirmationpage(String string) throws InterruptedException
	{
		String confrimMessage = checkout.getConfirmationMessage();
		Assert.assertEquals(confrimMessage, string);
	}
	
	@Then("{string} validation error message is displayed")
	public void validation_error_message_displayed(String string) throws InterruptedException
	{
		Assert.assertEquals("Incorrect email or password.", string);
	}

}
