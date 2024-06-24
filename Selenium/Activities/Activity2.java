package activites;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Activity2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();

		driver.get("https://v1.training-support.net/selenium/login-form");
		System.out.println("Home page title: " + driver.getTitle());

		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.name("Password")).sendKeys("password");

		driver.findElement(By.xpath("//button[text()='Log in']")).click();

		String message = driver.findElement(By.id("action-confirmation")).getText();
		System.out.println("Login message: " + message);

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File savedScreenshot = new File("src/test/resources/screenshot.jpg");
		FileUtils.copyFile(screenshot, savedScreenshot);

		driver.quit();

	}

}
