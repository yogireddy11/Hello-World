package LeaveApplyPage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {
	
	public  WebDriver driver;
	public JavascriptExecutor executor;
	@FindBy(css = "input[name=\"testemail\"]")
	WebElement emailInput;
	
	@FindBy(xpath = "//*[@id=\"main\"]/ion-grid/ion-row/ion-col[1]/div/ion-card/div/div/div[2]/ion-button")
	WebElement logBtn;
	
	@FindBy(xpath = "//ion-label[text()='Leaves']")
	WebElement leaves;
	
	public String enteredEmail;
	
	public Login_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		executor = ((JavascriptExecutor)driver);
	}
	public void navigateToMainPage(String pageUrl) {
		driver.get(pageUrl);
	}
	
	public void setEmail(String email) {
		emailInput.clear();
		emailInput.sendKeys(email);
		String userMail = emailInput.getAttribute("value");
		enteredEmail = userMail;
	}
	
	public void clickLoginBtn() {
		logBtn.click();
	}
	
	public void goToLeavesPage() {
		 	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	executor.executeScript("arguments[0].scrollIntoView();", leaves);
	        leaves.click();
	        String navigateUrl = driver.getCurrentUrl();
	        System.out.println("Open Page is : " + navigateUrl);
	}
}
