package com.bae.tests.frontend;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrontendUseCaseTest {

	private static WebDriver driver;

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		driver = new ChromeDriver(options);
	}

	@AfterClass
	public static void teardown() {
		driver.quit();
	}

	@Test
	public void useCase1() {// View all champions
		driver.get("http://34.77.62.121:8888/BAESoloProject/index.html");
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[2]")).click();

		assertEquals("http://34.77.62.121:8888/BAESoloProject/champions.html", driver.getCurrentUrl());

		// The rendered table body.
		// This isn't present until the js generates and populate the table
		WebElement ele = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tbl\"]/tbody")));
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody"));
		assertEquals(true, ele.isDisplayed());

		// The edit button for the first record generated by the js
		ele = (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOfElementLocated(By.id("1")));
		ele = driver.findElement(By.id("1"));

		assertEquals(true, ele.getText().contains("Edit"));
	}

}
