package LeaveApplyPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeaveRequestPage {
	
	WebDriver driver;
	WebDriverWait driverWait;
	JavascriptExecutor executor;
	 By beforeApplyLeave = By.xpath("//label[@class=\"label-header mt-20\"]/b");
	 By leaveReq = By.xpath("//ion-tab-button[@id=\"tab-button-requests\"]");
	 By name = By.xpath("//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-leaverequests/ion-content/ion-grid/div[1]/div/ion-card/div[1]/div[1]/label[2]");
	 By noOfDays = By.xpath("//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-leaverequests/ion-content/ion-grid/div[1]/div/ion-card/div[1]/div[2]/label[2]");
	 By leavetype = By.xpath("//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-leaverequests/ion-content/ion-grid/div[1]/div/ion-card/div[1]/div[3]/label[2]");
	 By reason = By.xpath("//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-leaverequests/ion-content/ion-grid/div[1]/div/ion-card/div[2]/div/div/div/label");
	 By approveBtn = By.xpath("//ion-card[@class=\"p-20 md hydrated\"]/div/following::div/following::div/child::div/child::ion-button[1]");
	 By declineBtn = By.xpath("//ion-card[@class=\"p-20 md hydrated\"]/div/following::div/following::div/child::div/following-sibling::div/child::ion-button");
	 By alertPopupText = By.xpath("//div[@class=\"alert-head sc-ion-alert-md\"]/h2/following-sibling::h2");
	 By alertPopupOk = By.xpath("//div[@class=\"alert-button-group sc-ion-alert-md\"]/button/span");
	 By status = By.xpath("//label[@class=\"label-subcolortitle mt-10\"]/b");
	 By viewDetails = By.xpath("//label[@class=\"label-subcolortitle ml-5\"]/u[text()='View Details'][1]");
	 By viewDetailsHead = By.xpath("//ion-label[contains(@class,\"selecteddaysborder sc-ion-label-md-h \")]/b");
	 By viewDetailsDate = By.xpath("//ion-item[contains(@class,\"item md item-lines-full item-fill-none hydrated item-label\")][1]/ion-label[1]");
	 By viewDetailsDaytype = By.xpath("//ion-item[contains(@class,\"item md item-lines-full item-fill-none hydrated item-label\")][1]/ion-label[2]");
	 
	 
	
	public LeaveRequestPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		executor = ((JavascriptExecutor)driver);
		PageFactory.initElements(driver, this);
	}
	
	public void navigateLeaveReq() {
		WebElement leaveReqPage = driver.findElement(leaveReq);
		leaveReqPage.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getViewDetails() {
	    WebElement viewDetailsTxt = driver.findElement(viewDetailsHead);
	    String viewDetailsText = viewDetailsTxt.getText();
	    
	    WebElement viewDetailsDateTxt = driver.findElement(viewDetailsDate);
	    String viewDetailsDateText = viewDetailsDateTxt.getText();
	    
	    WebElement viewDetailsDayTypeTxt = driver.findElement(viewDetailsDaytype);
	    String viewDetailsDayTypeText = viewDetailsDayTypeTxt.getText();
	    
	    return viewDetailsText + ", " + viewDetailsDateText + ", " + viewDetailsDayTypeText;
	}
	
	public String getName() {
		return driver.findElement(name).getText();	 
	}
	public String getDays() {
		return driver.findElement(noOfDays).getText();
	}	public String getLeaveType() {
		return driver.findElement(leavetype).getText();
	}
	public String getReason() {
		return driver.findElement(reason).getText();	
	}

	public void clickOnApprove() {
		WebElement approve = driver.findElement(approveBtn);
		approve.click();
	}
	public void clickOnDecline() {
		WebElement decline = driver.findElement(declineBtn);
		decline.click();
	}
	public void handleAlertpopup() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement alertPopText = driver.findElement(alertPopupText);
		if (alertPopText.getText().equals("Leave succesfully Approved")) {
			System.out.println(alertPopText+" Applied leave approved by lead!!");
		}else if (alertPopText.getText().equals("Leave succesfully Declined")) {
			System.out.println(alertPopText+" Applied Leave declined by lead!!");
		}else {
			System.out.println("No alert popup is displayed on screen");
		}
		WebElement alertPopupOkOptn = driver.findElement(alertPopupOk); 
		alertPopupOkOptn.click();	
	}
	public void verifyLeaveStatus() {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(status));
		WebElement statusText = driver.findElement(status);
		String statusGetText = statusText.getText();
		if (statusGetText.equals("Approved")) {
			System.out.println(statusGetText+" -> Lead approved the applied leave");
		}else if (statusGetText.equals("Declined")) {
			System.out.println(statusGetText+"-> Lead declined the applied leave");
		}else {
			System.out.println("Leave status is not displayed in Leave Request page!!! ");
		}
	}
}
