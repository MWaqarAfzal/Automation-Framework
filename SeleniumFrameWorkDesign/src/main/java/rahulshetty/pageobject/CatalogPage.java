package rahulshetty.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshetty.abstractcomponents.AbstractComponent;

public class CatalogPage extends AbstractComponent{
	
	
	//		Create a Constructor
	//		Whenever the class object is called constructor will be the first method to get executed and initialized the values
	
	WebDriver driver;		//		Create a local WebDriver variable
	public CatalogPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement loader;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCard = By.cssSelector(".card-body button:last-of-type");
	By toast = By.id("toast-container");
	
	
	public List<WebElement> getProductList()
	{
		waitForElementtoAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String name)
	{
		WebElement productName = getProductList().stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equalsIgnoreCase(name)).findFirst().orElse(null);
		return productName;
	}
	
	public AddToCart addToCard(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCard).click();
		waitForElementtoAppear(toast);
		waitForElementtoDisappear(loader);
		//		AFTER CATALOG PAGE, SYSTEM WILL NAVIGATE TO THE ADD TO CART PAGE THAT'S WHY CREATING AND RETURNING OBJECT INTO	THE METHOD INSTEAD OF TEST CASE
		return new AddToCart(driver);
	}
	
}
