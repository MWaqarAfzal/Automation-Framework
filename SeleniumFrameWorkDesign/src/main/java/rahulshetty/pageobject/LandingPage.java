package rahulshetty.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	
	//		Create a Constructor
	//		Whenever the class object is called constructor will be the first method to get executed and initialized the values
	
	WebDriver driver;		//		Create a local variable
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy (css = "[class*='flyInOut']")
	WebElement errorMessage;

	
	public CatalogPage login(String userID, String userPassword)
	{
		userEmail.sendKeys(userID);
		password.sendKeys(userPassword);
		submit.click();
		//		AFTER LOGIN, SYSTEM WILL NAVIGATE TO THE PRODUCT CATELOG PAGE THAT'S WHY CREATING AND RETURNING OBJECT INTO THE METHOD INSTEAD OF TEST CASE
		return new CatalogPage(driver);
	}
	
	public String getValidationErrorMessage()
	{
		waitForWebElementtoAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void loginPageNavigation()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
