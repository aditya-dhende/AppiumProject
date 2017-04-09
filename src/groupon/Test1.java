package groupon;

import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;


public class Test1 {

	private static AndroidDriver driver;

	@Test
	public void funLocationAccess() {

		String msgExpResult, msgActualResult;
		msgExpResult = "Allow Groupon to access this device's location?";

		if (driver.findElement(By.id("com.android.packageinstaller:id/dialog_container")).isDisplayed()) {
			msgActualResult = driver.findElement(By.id("com.android.packageinstaller:id/permission_message")).getText();
			if (msgActualResult.equals(msgExpResult)) {
				System.out.println("\n Correct Message is displayed.");
			}else {

				System.out.println("\n Correct Message is not displayed.");
			}

			if (driver.findElement(By.id("com.android.packageinstaller:id/permission_deny_button")).isDisplayed()) {
				System.out.println("\n Deny Button is displayed.");
			} else {
				System.out.println("\n Deny Button is not displayed.");
			}

			if (driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).isDisplayed()) {
				System.out.println("\n Allow Button is displayed.");
				driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
				System.out.println("\n Test Case 1 Passed");
			} else {

				System.out.println("\n Allow Button is not displayed.");
			}

		} else {
			System.out.println("\nDialog Box doesn't exist.");
			System.out.println("\nTest Case 1 Failed");
		}

	}

	@BeforeMethod
	@Parameters({"deviceName_","UDID_","platformVersion_","URL_"})
	public void beforeMethod(String deviceName_, String UDID_,String platformVersion_, String URL_) throws InterruptedException, MalformedURLException {

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

		driver = new AndroidDriver(new URL("http://" + URL_), capabilities);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000);

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
