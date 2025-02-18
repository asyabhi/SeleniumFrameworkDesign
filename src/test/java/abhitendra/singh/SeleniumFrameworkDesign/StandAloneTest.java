package abhitendra.singh.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import abhitendra.singh.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ADIDAS ORIGINAL";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys("randomschitz@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abhi@1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

//		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
//		List<WebElement> countryNames = driver.findElements(By.cssSelector(".ta-item span"));
//		List<WebElement> countryselected = countryNames.stream().filter(country->country.getText().equalsIgnoreCase("India")).collect(Collectors.toList());
//		countryselected.get(0).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item"))));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();
		String finalText = " Thankyou for the order. ";
		Boolean finalMatch = driver.findElement(By.cssSelector("h1")).getText().equalsIgnoreCase(finalText.trim());
		Assert.assertTrue(finalMatch);
		driver.quit();

	}

}
