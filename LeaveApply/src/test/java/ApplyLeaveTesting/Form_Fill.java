package ApplyLeaveTesting;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Form_Fill implements ITestListener {

	WebDriver driver;
	WebDriverWait driverWait;
	JavascriptExecutor executor;
	@BeforeSuite
	public void establish() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 executor = (JavascriptExecutor) driver;
//		WebElement locator = driver.findElement(By.xpath(""));
//		executor.executeScript("arguments[0].style.background='yellow'", locator);
//		executor.executeScript("arguments[0].setAttributes('style',border=2px solid red;background:yellow)", locator);
		
	}
	@Test
	public void test() {
		executor.executeScript("arguments[0].setAttribute('style',border=2px solid red; background:yellow)", null);
	}
	@Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }
	@Test
	public void name() {
		
	}
	
	@AfterSuite
	public void dismiss() {
		
	}
}
