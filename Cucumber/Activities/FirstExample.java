package stepDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstExample extends BaseClass{
	
	@BeforeAll
	public static void setUp() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	
	@AfterAll
	public static void tearDoen() {
		driver.quit();
	}
	
	@Given("the user is on the TS homepage")
	public void openTSHomepage() {
		driver.get("https://v1.training-support.net");
	}
	
	@When("the user clicks on the About Us link")
	public void clickAboutLink() {
		driver.findElement(By.id("about-link")).click();
	}
	
	@Then("the user is redirected to the About page")
	public void verifyAboutPage() {
		Assertions.assertEquals("About Training Support", driver.getTitle());
	}
}
