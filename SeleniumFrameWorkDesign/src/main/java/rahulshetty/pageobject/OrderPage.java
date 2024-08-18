package rahulshetty.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshetty.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	
	//		Create a Constructor
	//		Whenever the class object is called constructor will be the first method to get executed and initialized the values
	
	WebDriver driver;		//		Create a local variable
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (css = "tr td:nth-child(3)")
	List<WebElement> orderList;
		
	public Boolean verifyOrderProducts(String productName)
	{
		//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = orderList.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		//cartProducts.stream().filter(cartProduct -> cartProduct.getText().equalsIgnoreCase(itemName));
		return match;
	}
	
}
