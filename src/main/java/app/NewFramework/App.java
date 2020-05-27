package app.NewFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.utilities.DriverManager;

/**
 * Hello world!
 *
 */
public class App {
	public static void waitForPageLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				// TODO Auto-generated method stub
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	public static WebElement getWebElement(WebDriver driver, By byElement) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
	}

	@SuppressWarnings("unchecked")
	public static List<WebElement> getJavaScriptList(WebDriver driver, WebElement parentElement, String childTagname) {
		List<WebElement> childElementsList = (List<WebElement>) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].getElementsByTagName('" + childTagname + "');", parentElement);
		return childElementsList;
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World!");

		DriverManager driverManager = new DriverManager();

		WebDriver driver = driverManager.getDriver();

		driver.navigate().to("https://ss64.com/vb/sendkeys.html");

		waitForPageLoad(driver);

		WebElement parentElement = getWebElement(driver, By.xpath("//tbody"));

		List<WebElement> childElements = getJavaScriptList(driver, parentElement, "tr");

		childElements.forEach(ele -> {
			System.out.println(ele.getAttribute("innerText"));
		});

		driverManager.closeDriver();
	}
}
