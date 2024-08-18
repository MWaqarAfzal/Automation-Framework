package rahulshetty.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshetty.abstractcomponents.AbstractComponent;

public class CheckOut extends AbstractComponent{
	
	
	//		Create a Constructor
	//		Whenever the class object is called constructor will be the first method to get executed and initialized the values
	
	WebDriver driver;		//		Create a local variable
	public CheckOut(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy (css = ".action__submit")
	WebElement submit;
	
	@FindBy (css = ".hero-primary")
	WebElement confirmMessage;
	
	
	By result = By.cssSelector(".ta-results");
	By suggestedList = By.cssSelector(".ta-item");
	By selectedCountry = By.cssSelector(".ng-star-inserted");
	
	public void selectCountry(String countryName)
	{
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		waitForElementtoAppear(result);
	}
	
	public void countriesList()
	{
		List<WebElement> suggestedCountries = driver.findElements(suggestedList);
		WebElement matchedCountry = suggestedCountries.stream().filter(country -> 
		country.findElement(selectedCountry).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		click(matchedCountry);
		click(submit);
	}
	
	public String getConfirmationMessage() throws InterruptedException
	{
		Thread.sleep(2000);
		String confirmationMessage = confirmMessage.getText();
		return confirmationMessage;
		
	}
}
