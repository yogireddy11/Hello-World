package ApplyLeaveTesting;

import static org.testng.Assert.assertFalse;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import LeaveApplyPage.ApplyLeavePage;
import LeaveApplyPage.LeaveRequestPage;
import LeaveApplyPage.LeaveStatusPage;
import LeaveApplyPage.Login_Page;

public class ApplyLeaveTest {
	
	private WebDriver driver;
	private Login_Page login_Page;
	private ApplyLeavePage applyLeavePage;
	private LeaveStatusPage statusPage;
	private LeaveRequestPage requestPage;
    private final String baseUrl = "https://rms-dev.peopletech.com/login";



    @Parameters("browser")
    @BeforeSuite
    public void setUp(@Optional("chrome") String browser) {
        try {
        	if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
			}else if (browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}else if (browser.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			}else {
				System.out.println("Incorrect driver!!");
			}
            driver.manage().window().maximize();
            PageFactory.initElements(driver, this);
            applyLeavePage = new ApplyLeavePage(driver);
            statusPage = new LeaveStatusPage(driver);
            requestPage = new LeaveRequestPage(driver);
            System.out.println("WebDriver setup successful.");
        } catch (Exception e) {
            System.out.println("Error during WebDriver setup: " + e.getMessage());
            e.printStackTrace();
        }
    }

	    @Test (priority = 1, description = "This is a login Rms login Test method")
	public void testLoginPage() {
	    	login_Page = new Login_Page(driver);
	    	login_Page.pageOpen(baseUrl);
	        String mailId = "harika.vegi@rampgroup.com";
	        login_Page.setEmail(mailId);
	        Assert.assertEquals(login_Page.enteredEmail, mailId);
	        login_Page.clickLoginBtn();
	        login_Page.goToLeavesPage(); 
	    }
	    
	    
	    @Test(priority = 2,enabled = true)
	    public void leavePage() {
	    	applyLeavePage.checkAvailableLeaves();
	    	applyLeavePage.applyLeavePageTitle();
	    	applyLeavePage.openLeaveTypeDropdown();
	    	applyLeavePage.selectLeaveType();
	    	applyLeavePage.selectLeaveDates();
	    	applyLeavePage.noOfDays();
	    	//applyLeavePage.calMatOrPatBtwLeaves();
	    	//applyLeavePage.handleHalfAndFullDays();
	    	applyLeavePage.mentionCCAndReason("vishnu.yelapati@rampgroup.com", "Take week Off");
	    	//String initialBal = applyLeavePage.paternityleaves.getText();

	    	applyLeavePage.verifyAlertPopupSubmitedAfterAllFields();

	    	//String laterBal = applyLeavePage.verifyBalReduceOrNot();
//	    	System.out.println(initialBal+ " " +laterBal);
//	    	Assert.assertTrue(!initialBal.equals(laterBal), "Before apply and after apply balance are same");
	    }
	    
	    @Test(priority = 3,dependsOnMethods = {"leavePage"})
	    public void verifyLeaveStatus() {
	    	
	    	statusPage.navigateToLeaveStatus();
	    	String expectedLeaveType = applyLeavePage.leaveTyp;
	    	String actualLeaveType = statusPage.getLeaveType();
	    	System.out.println(actualLeaveType);
	    	Assert.assertTrue(actualLeaveType.contains(expectedLeaveType), "Expected Leave Type  and Actual Leave type is not matched....!!");
	    	
	    	String expectedNoOfDays =applyLeavePage.noofDays.getText();
	    	String actualNoOfDays = statusPage.getNoOfDays();
	       
	    	System.out.println(actualNoOfDays);
	    	Assert.assertTrue(actualNoOfDays.contains(expectedNoOfDays), "expected no of days not contains actual days");
	        
	    	String expectedAppliedTime = applyLeavePage.getCurrentTime();
	    	String actualAppliedTime = statusPage.getAppliedTime();

	    	String expectedTimeUpToMinutes = expectedAppliedTime.substring(0, expectedAppliedTime.lastIndexOf(":"));
	    	String actualTimeUpToMinutes = actualAppliedTime.substring(actualAppliedTime.indexOf("-") + 2, actualAppliedTime.lastIndexOf(":"));

	    	System.out.println("Expected Time (up to minutes): " + expectedTimeUpToMinutes);
	    	System.out.println("Actual Time (up to minutes): " + actualTimeUpToMinutes);

	    	Assert.assertEquals(actualTimeUpToMinutes, expectedTimeUpToMinutes, "Expected applied time and actual applied time (up to minutes) do not match");
	    	
	    	try {
	    		String expectedReason = applyLeavePage.reasonField.getAttribute("value");
		    	String actualReason = statusPage.getMentionReason();
		    	System.out.println("Leave Req -> "+expectedReason);
		    	System.out.println("Leave Req -> "+actualReason);
		    	Assert.assertEquals(actualReason, expectedReason,"Expected reason and actual reason is not matched!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    }
	    
	    @Test(priority = 4,dependsOnMethods = {"leavePage"})
	    public void loginWithLead() {
	    	login_Page.pageOpen(baseUrl);
	    	String mailId = "vishnu.yelapati@rampgroup.com";
	        login_Page.setEmail(mailId);
	        Assert.assertEquals(login_Page.enteredEmail, mailId);
	        login_Page.clickLoginBtn();
	        login_Page.goToLeavesPage(); 
	    }
	    
	   @Test(priority = 5,dependsOnMethods = {"leavePage"})
	   public void leaveRequests() {
		   requestPage.navigateLeaveReq();
		   Assert.assertEquals(requestPage.getName(),"Harika Vegi","Expected name and actual name is not matched");
		   Assert.assertTrue(statusPage.getNoOfDays().contains(requestPage.getDays()),"expected and actual days are not matched" );
		   try {
			   Assert.assertEquals(requestPage.getReason(), statusPage.getMentionReason(),"expected and actual reason is not matched!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		   try {
			   Assert.assertEquals(requestPage.getLeaveType(), statusPage.getLeaveType(),"expected and actual leave type is not matched!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		   requestPage.getViewDetails();
		   requestPage.clickOnApprove();
		   requestPage.handleAlertpopup();
		   requestPage.verifyLeaveStatus();		   
		   
	   }
	    
	    @AfterSuite
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit(); 
	        }
	    }


	 
}



