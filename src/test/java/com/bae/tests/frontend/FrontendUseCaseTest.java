package com.bae.tests.frontend;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrontendUseCaseTest {

	private static WebDriver driver;
	private static final String ROOT_LOCATION = "http://35.195.164.190:8888/BAESoloProject/";
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
	public void useCase01() {// View all champions
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[2]")).click();
		assertEquals(ROOT_LOCATION + "champions.html", driver.getCurrentUrl());
		body();
	}

	@Test
	public void useCase02() {// View all playable character classes (roles)
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[4]")).click();
		assertEquals(ROOT_LOCATION + "roles.html", driver.getCurrentUrl());
		body();

	}

	@Test
	public void useCase03() {// View included pantheons
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[5]")).click();
		assertEquals(ROOT_LOCATION + "pantheons.html", driver.getCurrentUrl());
		body();

	}

	@Test
	public void useCase04() {// View game modes
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[6]")).click();
		assertEquals(ROOT_LOCATION + "gamemodes.html", driver.getCurrentUrl());
		body();

	}

	@Test
	public void useCase05() {// View all recorded stats
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[3]")).click();
		assertEquals(ROOT_LOCATION + "stats.html", driver.getCurrentUrl());
		body();
	}

	@Test
	public void useCase06() { // Add a new champions details
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
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("new-submit-btn")));

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
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("104")));

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
	}

	@Test
	public void useCase07() {// Add stats for a character
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[3]")).click();
		assertEquals(ROOT_LOCATION + "stats.html", driver.getCurrentUrl());

		WebElement ele;
		String name = "Sylvanus";
		String gamemode = "Duel";
		String win = "21.92";
		String pick = "13.67";
		String ban = "0.26";

		// Wait until the page has loaded
		ele = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("new-btn")));
		ele.click();

		// Wait until the modal has loaded
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("new-submit-btn")));

		// Select and enter the values
		ele = driver.findElement(By.id("new-champion-selection"));
		Select selector = new Select(ele);
		selector.selectByVisibleText(name);

		ele = driver.findElement(By.id("new-gamemode-selection"));
		selector = new Select(ele);
		selector.selectByVisibleText(gamemode);

		ele = driver.findElement(By.id("newpickrate"));
		ele.sendKeys(pick);

		ele = driver.findElement(By.id("newbanrate"));
		ele.sendKeys(ban);

		ele = driver.findElement(By.id("newwinrate"));
		ele.sendKeys(win);

		// Submit the details
		driver.findElement(By.id("new-submit-btn")).click();

		new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());

		// Confirm that the alert message is success
		assertEquals("New stats successfully added!", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

		// Wait for the element to load
		ele = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tbl\"]/tbody/tr[310]/td[2]")));

		// Check that it was created correctly
		assertEquals(name, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[310]/td[3]"));
		assertEquals(gamemode, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[310]/td[4]"));
		assertEquals(win, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[310]/td[5]"));
		assertEquals(pick, ele.getText());
		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[310]/td[6]"));
		assertEquals(ban, ele.getText());
	}

	@Test
	public void useCase08() {// create a new game mode
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[6]")).click();
		assertEquals(ROOT_LOCATION + "gamemodes.html", driver.getCurrentUrl());

		WebElement ele;
		String newModeName = "Assault";

		// Wait until the page has loaded
		ele = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("new-btn")));
		ele.click();

		// Wait until the modal has loaded
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("new-submit-btn")));

		// Select and enter the values
		ele = driver.findElement(By.id("new-gamemode-name"));
		ele.sendKeys(newModeName);

		// Submit the details
		driver.findElement(By.id("new-submit-btn")).click();

		new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());

		// Confirm that the alert message is success
		assertEquals("Game mode successfully created!", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

		ele = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tbl\"]/tbody/tr[4]/td[2]")));
		assertEquals(newModeName, ele.getText());

	}

	@Test
	public void useCase09() { // Update a characters details when they are changed
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[2]")).click();
		assertEquals(ROOT_LOCATION + "champions.html", driver.getCurrentUrl());

		WebElement ele;
		String pantheon = "Polynesian";
		String role = "Assassin";
		String damageType = "Magical";
		String hp = "320";
		String damage = "39";

		ele = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"96\"]")));
		ele.click();

		// Select and enter the values
		// Wait until the modal has loaded
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submit-btn\"]")));

		// Allow the dropdown selectors to populate
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		ele = driver.findElement(By.id("role-selection"));

		Select selector = new Select(ele);
		selector.selectByVisibleText(role);

		// Select and enter the values
		ele = driver.findElement(By.id("pantheon-selection"));
		selector = new Select(ele);
		selector.selectByVisibleText(pantheon);

		// Select and enter the values
		ele = driver.findElement(By.id("damage-selection"));
		selector = new Select(ele);
		selector.selectByVisibleText(damageType);

		ele = driver.findElement(By.id("hp"));
		ele.clear();
		ele.sendKeys(hp);

		ele = driver.findElement(By.id("damage"));
		ele.clear();
		ele.sendKeys(damage);

		driver.findElement(By.id("submit-btn")).click();

		new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());

		// Are you sure you want to update this record?
		driver.switchTo().alert().accept();// yes

		new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());

		// Confirm that the alert message is success
		assertEquals("The specified champion has successfuly been updated", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

		(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"96\"]")));

		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[96]/td[3]"));
		assertEquals(role, ele.getText());

		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[96]/td[4]"));
		assertEquals(pantheon, ele.getText());

		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[96]/td[5]"));
		assertEquals(damageType, ele.getText());

		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[96]/td[6]"));
		assertEquals(hp, ele.getText());

		ele = driver.findElement(By.xpath("//*[@id=\"tbl\"]/tbody/tr[96]/td[7]"));
		assertEquals(damage, ele.getText());

	}

	@Test
	public void useCase10() { // Delete a character and their stats from the database
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[3]")).click();
		assertEquals(ROOT_LOCATION + "stats.html", driver.getCurrentUrl());

		WebElement ele = null;

		// Delete all 3 records (one record for each game mode)
		for (int i = 0; i < 3; i++) {
			// Wait until the delete button has loaded and then click it
			ele = (new WebDriverWait(driver, 15)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id=\"tbl\"]/tbody/tr[1]/td[7]/button[2]")));
			ele.click();
			deleteRecord();
			// Confirm that the alert message is success
			assertEquals("The specified stats have successfully been deleted", driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
		}

		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/a[2]")).click();
		assertEquals(ROOT_LOCATION + "champions.html", driver.getCurrentUrl());

		// Get the champion
		ele = (new WebDriverWait(driver, 15)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tbl\"]/tbody/tr[1]/td[8]/button[2]")));
		ele.click();

		// Delete them
		deleteRecord();

		// Confirm the delete message
		assertEquals("The specified champion has successfully been deleted", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();// confirm

	}

	public void deleteRecord() {
		// Wait until the confirmation alert shows up
		new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());

		// Are you sure you want to delete this record?
		driver.switchTo().alert().accept();// yes

		new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());
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
