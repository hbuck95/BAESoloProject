package com.bae.tests.frontend;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
	public void test() {
		driver.get("https://www.google.co.uk/");
		assertEquals("https://www.google.co.uk/", driver.getCurrentUrl());
	}
}
