package rahulshetty.tests;

import rahulshetty.BaseTestComponents.BaseTest;
import rahulshetty.BaseTestComponents.Retry;
import rahulshetty.pageobject.AddToCart;
import rahulshetty.pageobject.CatalogPage;
import rahulshetty.pageobject.CheckOut;
import rahulshetty.pageobject.LandingPage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest{

	
	@Test (retryAnalyzer = Retry.class)
	public void submitOrder() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		landing.login("testwaqarafzal@yopmail.com","Selenium12333");
		landing.getValidationErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landing.getValidationErrorMessage());

		}
	
	@Test (groups = {"ErrorHandling"})
	public void invalidOrder() throws IOException, InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String itemName = "ADIDAS ORIGINAL";
		String countryName = "Ind";
		
		CatalogPage productCatelog = landing.login("waqarafzal@yopmail.com","Selenium123");
		
		List<WebElement> productList = productCatelog.getProductList();
		AddToCart cart = productCatelog.addToCard(itemName);
		
		cart.goToCartPage();
		Boolean matched = cart.verifyCartProducts("ADIDAS ORIGINAL 123");
		
//		ASSERTION SHOULD ONLY BE WRITTERN IN THE TEST CASE FILE NOT IN THE PAGE OBJECT FILE
		Assert.assertFalse(matched);

		}
}