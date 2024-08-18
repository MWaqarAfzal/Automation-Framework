package rahulshetty.tests;

import rahulshetty.BaseTestComponents.BaseTest;
import rahulshetty.pageobject.AddToCart;
import rahulshetty.pageobject.CatalogPage;
import rahulshetty.pageobject.CheckOut;
import rahulshetty.pageobject.OrderPage;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrder extends BaseTest{

	String itemName = "ADIDAS ORIGINAL";
	
	@Test (dataProvider="getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String countryName = "Ind";
		
		CatalogPage productCatalog = landing.login(input.get("email"),input.get("password"));
		
		List<WebElement> productList = productCatalog.getProductList();
		AddToCart cart = productCatalog.addToCard(input.get("productName"));
		
		cart.goToCartPage();
		Boolean matched = cart.verifyCartProducts(input.get("productName"));
		
//		ASSERTION SHOULD ONLY BE WRITTERN IN THE TEST CASE FILE NOT IN THE PAGE OBJECT FILE
		Assert.assertTrue(matched);
		CheckOut checkout = cart.goToCheckOut();

		checkout.selectCountry(countryName);
		checkout.countriesList();
		String confrimMessage = checkout.getConfirmationMessage();
		Assert.assertEquals(confrimMessage, "THANKYOU FOR THE ORDER.");

		}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistory()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		CatalogPage productCatalog = landing.login("waqarafzal@yopmail.com","Selenium123");
		OrderPage orderPage = productCatalog.goToOrderPage();
		Boolean match = orderPage.verifyOrderProducts(itemName);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshetty\\Data\\PurchaseOrder.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}};
		
		
		/*
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "waqarafzal@yopmail.com");
		map.put("password", "Selenium123");
		map.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName", "IPHONE 13 PRO");
		
		return new Object [] []  {{map}, {map1}};
		*/
		//return new Object [] []  {{"waqarafzal@yopmail.com", "Selenium123", "ADIDAS ORIGINAL"}, {"shetty@gmail.com", "Iamking@000", "IPHONE 13 PRO"}};
	}
	
}