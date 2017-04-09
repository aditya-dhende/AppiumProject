package groupon;

import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;

public class Test2 {

	private static AndroidDriver driver;

	@Test
	public void funNavigation() throws InterruptedException {
		Thread.sleep(2000);
		String msgExpResult, msgActualResult;
		msgExpResult = "Sign up now to get the most out of Groupon";
		if (driver.findElement(By.id("com.groupon:id/title")).isDisplayed()) {

			msgActualResult = driver.findElement(By.id("com.groupon:id/title")).getText();
			if (msgExpResult.equals(msgActualResult)) {
				System.out.println("\n Correct message is displayed.");
			} else {
				System.out.println("\n Incorrect message is displayed.");
			}

		} else {
			System.out.println("\nTitle is not present.");
		}
		if (driver.findElement(By.id("com.groupon:id/done")).isDisplayed()) {
			System.out.println("\n Sign Up button is present.");
			driver.findElement(By.id("com.groupon:id/done")).click();
			if (driver.findElement(By.id("com.groupon:id/fragment_log_in_sign_up_groupon_button")).isDisplayed()) {
				System.out.println("\n Login in with Groupon button is present.");

			} else {
				System.out.println("\n Login in with Groupon button is not present.");
			}

			if (driver.findElement(By.id("com.groupon:id/fragment_log_in_sign_up_facebook_button")).isDisplayed()) {
				System.out.println("\n Login in with Facebook button is present.");

			} else {
				System.out.println("\n Login in with Facebook button is not present.");
			}

			if (driver.findElement(By.id("com.groupon:id/fragment_log_in_sign_up_google_button")).isDisplayed()) {
				System.out.println("\n Login in with Google button is present.");
				
				System.out.println("\n Test Case 2 Passed.");

			} else {
				System.out.println("\n Login in with Google button is not present.");
			}
			
			

		} else {
			System.out.println("\nSign Up button is not present.");
			
			System.out.println("\n Test Case 2 Failed.");
			
		}

	}

	@BeforeMethod
	@Parameters({ "deviceName_", "UDID_", "platformVersion_", "URL_" })
	public void beforeMethod(String deviceName_, String UDID_, String platformVersion_, String URL_)
			throws InterruptedException, MalformedURLException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/Apk");
		File app = new File(appDir, "com.groupon.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", deviceName_);
		capabilities.setCapability("udid", UDID_);
		capabilities.setCapability("platformVersion", platformVersion_);
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.groupon");
		capabilities.setCapability("appActivity", "com.groupon.home.main.activities.Carousel");

		driver = new AndroidDriver<WebElement>(new URL("http://" + URL_), capabilities);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
