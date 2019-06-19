package com.bae.tests.frontend;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrontendUseCaseTest {

	private static WebDriver driver;
	private static final String ROOT_LOCATION = "http://34.77.62.121:8888/BAESoloProject/";
	private static final String INDEX_LOCATION = ROOT_LOCATION + "index.html";

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

	@Before
	public void init() {
		driver.get(INDEX_LOCATION);
	}

	@Test
	public void useCase1() {// View all champions
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[2]")).click();
		assertEquals(ROOT_LOCATION + "champions.html", driver.getCurrentUrl());
		body();
	}

	@Test
	public void useCase2() {// View all playable character classes (roles)
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[4]")).click();
		assertEquals(ROOT_LOCATION + "roles.html", driver.getCurrentUrl());
		body();

	}

	@Test
	public void useCase3() {// View included pantheons
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[5]")).click();
		assertEquals(ROOT_LOCATION + "pantheons.html", driver.getCurrentUrl());
		body();

	}

	@Test
	public void useCase4() {// View game modes
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[6]")).click();
		assertEquals(ROOT_LOCATION + "gamemodes.html", driver.getCurrentUrl());
		body();

	}

	@Test
	public void useCase5() {// View all recorded stats
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[3]")).click();
		assertEquals(ROOT_LOCATION + "stats.html", driver.getCurrentUrl());
		body();
	}

	@Test
	public void useCase6() { // Add a new champions details
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[2]")).click();
		assertEquals(ROOT_LOCATION + "champions.html", driver.getCurrentUrl());

		WebElement ele;
		String name = "Fred";
		String pantheon = "Roman";
		String role = "Mage";
		String damageType = "Physical";
		String hp = "380";
		String damage = "32";

		// Wait until the page has loaded
		ele = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("new-btn")));
		ele.click();

		// Wait until the modal has loaded
		ele = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-submit-btn")));

		// Send the values for the new champion
		ele = driver.findElement(By.id("new-name"));
		ele.sendKeys(name);
		assertEquals(name, ele.getAttribute("value"));

		ele = driver.findElement(By.id("new-role-selection"));
		ele.click();
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		assertEquals(role, ele.getAttribute("value"));

		ele = driver.findElement(By.id("new-pantheon-selection"));
		ele.click();
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		assertEquals(pantheon, ele.getAttribute("value"));

		ele = driver.findElement(By.id("new-damage-selection"));
		ele.click();
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		assertEquals(damageType, ele.getAttribute("value"));

		ele = driver.findElement(By.id("new-hp"));
		ele.sendKeys(hp);
		assertEquals(hp, ele.getAttribute("value"));

		ele = driver.findElement(By.id("new-damage"));
		ele.click();
		ele.sendKeys(damage);
		assertEquals(damage, ele.getAttribute("value"));

		driver.findElement(By.id("new-submit-btn")).click();

		new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());

		assertEquals("Champion successfully created!", driver.switchTo().alert().getText());

		driver.switchTo().alert().accept();

		// Wait for the element to load
		ele = (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOfElementLocated(By.id("104")));

		// Check that it was created correctly
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[104]/td[2]"));
		assertEquals(name, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[104]/td[3]"));
		assertEquals(role, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[104]/td[4]"));
		assertEquals(pantheon, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[104]/td[5]"));
		assertEquals(damageType, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[104]/td[6]"));
		assertEquals(hp, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[104]/td[7]"));
		assertEquals(damage, ele.getText());

		// body();
	}

	public void body() {

		// The rendered table body.
		// This isn't present until the js generates and populate the table
		WebElement ele = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tbl\"]/tbody")));

		assertEquals(true, ele.isDisplayed());

		// The edit button for the first record generated by the js
		ele = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("1")));

		System.out.println(driver.getCurrentUrl());
		assertEquals(true, ele.getText().contains("Edit"));
	}
}
