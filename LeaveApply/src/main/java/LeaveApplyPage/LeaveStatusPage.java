package LeaveApplyPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LeaveStatusPage {
	
	WebDriver driver;
	Login_Page login_Page;
	ApplyLeavePage applyLeaveTav;
	
	By leaveStatusTab = By.xpath("//ion-tab-button[@id=\"tab-button-status\"]");
	By leaveType = By.xpath("(//div[@class='w-25'])[1]");
	By noOfDays = By.xpath("(//div[@class='w-25'])[2]");
	By viewDetails = By.xpath("(//u[text()='View Details'])[1]");
	By appliedTime = By.xpath("(//div[@class='w-25'])[3]");
	By leaveStatus = By.xpath("(//div[@class='w-25'])[4]");
	By mentionedReason = By.xpath("//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-leavestatus/ion-content/div/div[1]/ion-card/ion-item/label");
	
	public LeaveStatusPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		login_Page = new Login_Page(driver);
		applyLeaveTav = new ApplyLeavePage(driver);
		
	}
	
	public void navigateToLeaveStatus() {
		WebElement leaveStatustabOpen = driver.findElement(leaveStatusTab);
		leaveStatustabOpen.click();
		System.out.println("Leave Status tab opened..."+leaveStatustabOpen.getText());	
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getLeaveType() {
		WebElement leaveTypeText = driver.findElement(leaveType); 
		return leaveTypeText.getText();
		
	}
	
	public String getNoOfDays() {
		WebElement noOfDaysText = driver.findElement(noOfDays);
		return noOfDaysText.getText();
	}
	
	public String getAppliedTime() {
		WebElement appliedTimeText = driver.findElement(appliedTime);
		return appliedTimeText.getText();
	}
	
	public String getLeaveStatus() {
		WebElement leaveStatusText = driver.findElement(leaveStatus);
		return leaveStatusText.getText();
	}
	
	public String getMentionReason() {
		WebElement reasonText = driver.findElement(mentionedReason);
		return reasonText.getText();
	}
	
	

}
