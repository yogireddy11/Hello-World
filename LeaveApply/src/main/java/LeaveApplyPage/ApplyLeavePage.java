package LeaveApplyPage;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

public class ApplyLeavePage {
	
	WebDriver driver;
	JavascriptExecutor executor;
	WebDriverWait driverWait;
	
//	 Properties obj = new Properties();
//     FileInputStream objFile = null;
	
	@FindBy(xpath = "//b[text()='My Leaves']")
	public WebElement mainTitleLogo;
	
	@FindBy(xpath = "//b[text()=' Leave Balance']")
	public WebElement pageTitle;
	
	@FindBy(xpath = "(//label[@class='label-subheader'])[1]")
	public WebElement casualLeave;
	
	@FindBy(xpath = "(//label[@class='label-subheader'])[2]")
	public WebElement sickLeave;
	
	@FindBy(xpath = "(//label[@class='label-subheader'])[3]")
	public WebElement covidLeave;
	
	@FindBy(xpath = "(//label[@class='label-subheader'])[4]")
	public WebElement paternityleaves;
	@FindBy(xpath = "(//label[@class='label-subheader'])[5]")
	public WebElement lossOfPay;
	
	@FindBy(xpath = "//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-applyleave/ion-content/ion-grid/ion-row/ion-col/ion-card/div[3]/div[2]/label")
	public WebElement compensatoryLeave;
	
	@FindBy(xpath = "//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-applyleave/ion-content/ion-grid/ion-row/ion-col/ion-card/div[3]/div[3]/label")
	public WebElement marriageLeave;
	
	@FindBy(xpath = "//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-applyleave/ion-content/ion-grid/ion-row/ion-col/ion-card/div[3]/div[4]/label")
	public WebElement bereavementLeave;
	
	@FindBy(xpath = "//b[contains(text(),'Apply Leave ')]")
	public WebElement applyLeaveTitle;
	
	@FindBy(xpath = "//label[contains(text(),'Leave Type *')]/parent::div/child::ion-list/child::div/child::ion-select[@name=\"leave_type\"]")
	public WebElement leaveTypeDropdown;
	
	//type of leaves
	@FindBy(xpath = "//*[@role='list']/child::ion-radio-group/ion-item[1]")
	public WebElement casualOptn;
	@FindBy(xpath = "//*[@role='list']/child::ion-radio-group/ion-item[2]")
	public WebElement sicOptn;
	@FindBy(xpath = "//*[@role='list']/child::ion-radio-group/ion-item[3]")
	public WebElement covidOptn;
	@FindBy(xpath = "//*[@role='list']/child::ion-radio-group/ion-item[4]")
	public WebElement matOrPat;
	@FindBy(xpath = "//div[contains(@class,\"selecteddaysborder d-flex align-center\")]/ion-label[text()='From date :']/following::ion-label[1]")
	WebElement fromDate;
	
	@FindBy(xpath="//div[contains(@class,\"selecteddaysborder d-flex align-center\")]/ion-label[text()='From date :']/following::ion-label[3]")
	WebElement toDate;
	@FindBy(xpath = "//*[@role='list']/child::ion-radio-group/ion-item[5]")
	public WebElement lopOtpn;
	@FindBy(xpath = "//*[@role='list']/child::ion-radio-group/ion-item[6]")
	public WebElement compOptn;
	@FindBy(xpath = "//*[@role='list']/child::ion-radio-group/ion-item[7]")
	public WebElement marriageOptn;
	@FindBy(xpath = "//*[@role='list']/child::ion-radio-group/ion-item[8]")
	public WebElement bareavementOptn;
	
	
	@FindBy(xpath = "//label[text()='Select the date *']/parent::div/child::ion-img")
	public WebElement calender;
	
	@FindBy(xpath = "//*[@aria-label=\"May 08, 2024\"]")
	public WebElement selectDate1;
	
	@FindBy(xpath = "//*[@aria-label=\"May 10, 2024\"]")
	public WebElement selectDate2;
	
	@FindBy(xpath = "//ion-button[@type=\"button\"]/child::span[text()='Save']")
	public WebElement dateSave;
	
	@FindBy(xpath = "(//label[contains(@class,'label-subheader mb-0')])[2]")
	public WebElement noofDays;
	
	@FindBy(xpath = "//ion-radio[@tabindex=\"0\"]")
	public WebElement fullDay;
	
	
	@FindBy(xpath = "//ion-radio[@tabindex=\"-1\"]")
	public WebElement halfDay;
	
	@FindBy(xpath = "//label[text()='Applying to : ']/following-sibling::label/child::b")
	public WebElement reportingManager;
	
	@FindBy(xpath = "//input[@name=\"ccmail\"]")
	public WebElement ccMail;
	
	@FindBy(xpath = "//input[@name=\"reason\"]")
	public WebElement reasonField;
	
	@FindBy(xpath = "//*[@id=\"main-content\"]/app-leave/ion-content/ion-tabs/div/ion-router-outlet/app-applyleave/ion-content/ion-grid/ion-row/ion-col/ion-card/div[9]/div/ion-button")
	public WebElement subtBtn;
	
	//Alert Popups 
	@FindBy(xpath = "//div[@class=\"alert-head sc-ion-alert-md\"]/h2/following-sibling::h2")
	public WebElement pleaseEnterReqFields;
	@FindBy(xpath = "//div[@class=\"alert-button-group sc-ion-alert-md\"]/button/child::span['OK']")
	public WebElement alertOKOption;
	

	public String leaveTyp = "";
	public String resultAlertPopup = "";

	public ApplyLeavePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		executor = ((JavascriptExecutor)driver);
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		try {
//			objFile = new FileInputStream("C:\\Users\\MusumYogireddy\\eclipse-workspace\\LeaveApply\\Object_Repo.properties");
//			obj.load(objFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
	
	
	public void checkAvailableLeaves() {
		
		String mainTitle = mainTitleLogo.getText();
		System.out.println("Page main Title is -> "+mainTitle);
		
		String pageHeadTitle = pageTitle.getText(); 
		System.out.println("Page main Title is -> "+pageHeadTitle);
		
		String casualLeaveText = casualLeave.getText();
		System.out.println("Page main Title is -> "+casualLeaveText);
		
		String sick = sickLeave.getText();
		System.out.println("sick leave available bal is -> "+sick);
		
		String covid = covidLeave.getText();
		System.out.println("covid leave available bal is -> "+covid);
		
		String paternity = paternityleaves.getText();
		System.out.println("Paternity leave available bal is -> "+paternity);
		
		String lop = lossOfPay.getText();
		System.out.println("lop leave available bal is -> "+lop);
		
		String compensatory = compensatoryLeave.getText();
		System.out.println("Compensatory leave available bal is -> "+compensatory);
		
		String marriage = marriageLeave.getText();
		System.out.println("Marriage leave available bal is -> "+marriage);
		
		String bereavement = bereavementLeave.getText();
		System.out.println("Bereavement leave available bal is -> "+bereavement);
		
	}
	
	public void applyLeavePageTitle() {
		String applyingTitle = applyLeaveTitle.getText();
		System.out.println("Apply leave page title is -> "+applyingTitle);
	}
	
	
	public void openLeaveTypeDropdown() {
		 leaveTypeDropdown.click(); 
		   driverWait.until(ExpectedConditions.visibilityOf(compOptn)).click();
	}
	
	public void selectLeaveType() {
		 String selectedLeaveoptnTxt = compOptn.getText();
		 System.out.println(selectedLeaveoptnTxt+" leave selected");
		 leaveTyp = selectedLeaveoptnTxt;  
	}
	
	public void selectLeaveDates() {
		calender.click();
		driverWait.until(ExpectedConditions.visibilityOf(selectDate1));
		executor.executeScript("arguments[0].scrollIntoView();", selectDate1);
		selectDate1.click();
		selectDate2.click();
		String getSelectDate1 = selectDate1.getText();
		String getSelectDate2 = selectDate1.getText();

		System.out.println(getSelectDate1+" date is selected");
		System.out.println(getSelectDate2+" date is selected");

		if (dateSave.isDisplayed() && dateSave.isEnabled()) {
		
			dateSave.click();
			System.out.println("Calaender date selected and save");
		} else {
			System.err.println("Save button is not enabled or displayed");
		}
		try {
			
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void noOfDays() {
		String countSelectDays = noofDays.getText();
		System.out.println("selected days for applying leave -> "+countSelectDays);
	}
	
	public void handleHalfAndFullDays() {
		if (!halfDay.isSelected()){
			System.out.println("full day is selected...!!!");
		}
		else {
			System.err.println("ull day is not selected...!!");
		}
	}
	public void calMatOrPatBtwLeaves() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fromDateTxt = fromDate.getText();
		String toDateTxt = toDate.getText();
		LocalDate from = LocalDate.parse(fromDateTxt,dateTimeFormatter);
		LocalDate to = LocalDate.parse(toDateTxt,dateTimeFormatter);
		long btwDays = ChronoUnit.DAYS.between(from, to);
		System.out.println(btwDays);
		if (btwDays == 182 || btwDays == 181) {
			System.out.println("Maternity leave From and To days are matched -> "+btwDays);
		}else if (btwDays == 7) {
			System.out.println("Paternity leave From and To days are matched -> "+btwDays);
		}else {
			System.err.println("Between day does not match with maternity or paternity!!!");
		}
	}
	
	public void mentionCCAndReason(String cc, String reason) {
		String rm = reportingManager.getText();
		System.out.println("Reporting manager -> "+rm);
		
		ccMail.sendKeys(cc);
		System.out.println("Given cc mail is "+ccMail.getAttribute("value"));
		reasonField.sendKeys(reason); 
		System.out.println("Mention reason is "+reasonField.getAttribute("value"));
			
	}
	
	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDateFormatter = dateFormat.format(date);
		System.out.println(currentDateFormatter);
		return currentDateFormatter;
	}
	
	public String verifyBalReduceOrNot() {
		return paternityleaves.getText();
	}
	
	public void verifyAlertPopups() {
		
		if(leaveTypeDropdown.getText().equals("Select Type") || noofDays.getText().equals("0") || reasonField.getText().isEmpty()) {
				subtBtn.click();
				driverWait.until(ExpectedConditions.visibilityOf(pleaseEnterReqFields));
				System.out.println(pleaseEnterReqFields.getText());
				driverWait.until(ExpectedConditions.visibilityOf(alertOKOption));

				alertOKOption.click();
		}else {
			System.out.println("All Fields are selected and enterend!!");
		}
		
		
	}
	
	public void checkCalendarAlertPopup() {
		if (leaveTypeDropdown.getText().equals("Select Type")) {
			calender.click();
			WebElement calAlertPopup = driver.findElement(By.xpath("//h2[text()='Please select Leave type']"));
			driverWait.until(ExpectedConditions.visibilityOf(calAlertPopup));
		 	System.out.println(pleaseEnterReqFields.getText());
			WebElement calAlertOkOptn = driver.findElement(By.xpath("//button[@tabindex=\"0\"]/span"));
			calAlertOkOptn.click();
		}
	}
	
	public void verifyAlertPopupSubmitedAfterAllFields() {
		if(!leaveTypeDropdown.getText().equals("Select Type") && !noofDays.getText().equals("0")  && !reasonField.getAttribute("value").isEmpty()){
			subtBtn.click();
			System.out.println("Submit button is clicked!!");
			driverWait.until(ExpectedConditions.visibilityOf(pleaseEnterReqFields));
			String alertPopUpText = pleaseEnterReqFields.getText();
			if (alertPopUpText.equalsIgnoreCase("Already applied the leave with selected date's: 30-04-2024")) {
	    		System.out.println(alertPopUpText+"=-->");
				Assert.fail("you already applied leave with these days");
				throw new SkipException("Already applied leave with these dates");
			} else {
				System.out.println("Leave applied sucessfully!!");
			}
			
			System.out.println(alertPopUpText);
			getCurrentTime();
			alertOKOption.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	public void handleAlertPopText() {
		driverWait.until(ExpectedConditions.visibilityOf(pleaseEnterReqFields));
		String alertPopUpText = pleaseEnterReqFields.getText();
		resultAlertPopup = alertPopUpText;
		System.out.println(alertPopUpText);
		getCurrentTime();
		alertOKOption.click();
	}
	

}
