package rahulshetty.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshetty.abstractcomponents.AbstractComponent;

public class AddToCart extends AbstractComponent{
	
	
	//		Create a Constructor
	//		Whenever the class object is called constructor will be the first method to get executed and initialized the values
	
	WebDriver driver;		//		Create a local variable
	public AddToCart(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	@FindBy (css = ".cartSection h3")
	List<WebElement> cartProducts;
		
	public Boolean verifyCartProducts(String productName) throws InterruptedException
	{
		Thread.sleep(2000);
		//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		//cartProducts.stream().filter(cartProduct -> cartProduct.getText().equalsIgnoreCase(itemName));
		return match;
	}
	
	public CheckOut goToCheckOut()
	{
		click(checkoutButton);
		//		AFTER ADDING ITEMS INTO THE CART, SYSTEM WILL NAVIGATE TO THE CHECKOUT PAGE THAT'S WHY CREATING AND RETURNING OBJECT INTO THE METHOD INSTEAD OF TEST CASE
		return new CheckOut(driver);
	}
	
}
