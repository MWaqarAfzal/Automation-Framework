package rahulshetty.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshetty.pageobject.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement addToCartButton;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderButton;
	
	
	public void waitForElementtoAppear(By findElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findElement));
	}
	
	public void waitForWebElementtoAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementtoDisappear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void goToCartPage()
	{
		click(addToCartButton);
	}
	
	public OrderPage goToOrderPage()
	{
		click(orderButton);
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;
	}
	
	public void click(WebElement element)
	{
		element.click();
	}

}
