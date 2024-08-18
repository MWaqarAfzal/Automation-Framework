package rahulshetty.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		String itemName = "ADIDAS ORIGINAL";
		
		//		Add 10s implicitlyWait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("waqarafzal@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium123");
		driver.findElement(By.id("login")).click();
		
		//		Add 5s explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		//		Wait 5s for the list of products to be loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement productName = productList.stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equalsIgnoreCase(itemName)).findFirst().orElse(null);
		
		productName.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		//Both work same but invisibilityOfElementLocated takes time to perform the action but invisibilityOf work faster as compare
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//		Click on the Cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(itemName));
		//cartProducts.stream().filter(cartProduct -> cartProduct.getText().equalsIgnoreCase(itemName));
		Assert.assertTrue(match);
		
		//		Click on Checkout Button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//		Use Actions methods to send the value
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		// .ng-star-inserted
		List<WebElement> suggestedCountries = driver.findElements(By.cssSelector(".ta-item"));
		
		WebElement matchedCountry = suggestedCountries.stream().filter(country -> 
		country.findElement(By.cssSelector(".ng-star-inserted")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		
		matchedCountry.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmationMessage);
		
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		}
}