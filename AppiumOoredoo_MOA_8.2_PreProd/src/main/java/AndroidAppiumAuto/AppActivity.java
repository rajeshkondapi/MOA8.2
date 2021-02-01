package AndroidAppiumAuto;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import AndroidAppiumAuto.exceptions.CustomException;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;



public class AppActivity extends SupportMethods {
	
	AndroidDriver<AndroidElement> dr;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
	static SimpleDateFormat sdffolder = new SimpleDateFormat("yyyy_MM_dd");
	public static String getDate() {
		return sdf.format(new java.util.Date());
	}
	public static String getDatefolder() {
		return sdffolder.format(new java.util.Date());
	}

	
	
	@Test(description = "Login Pages with NoMsisdn", priority = 1, enabled =  true)
	//@org.testng.annotations.Parameters(value={"msisdn"})
	public void NoMsisdn() throws Exception {
		logger = extent.startTest("Login Page With No Msisdn Scenarios", "Description to Login Page With No Msisdn");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		propertyelements();
		withoutmsisdn();
	}
	
	@Test(description = "Login Pages InvalidMs", priority = 2, enabled =  true)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void InvalidMsisdn(String msisdn) throws Exception {
		logger = extent.startTest("Login Page Invalid Msisdn Scenarios", "Description to Login Page Invalid Msisdn");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		propertyelements();
		invalidnumber();
	}
		
	
	@Test(description = "Login Pages Correct", priority = 3, enabled =  true)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void LoginPage(String msisdn) throws Exception {
		logger = extent.startTest("Login Page Success Scenario", "Description to LoginPage Success Scenario");
		propertyelements();
		loginpage(msisdn);			
	}
	
	@Test(description = "OTP Page Validation", priority = 4, enabled =  true)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void OTPPageValidation(String msisdn) throws Exception {
		logger = extent.startTest("OTP Page Validation", "Description to OTP Page Validation");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				
		propertyelements();
		OTPValidation(msisdn);
	}
	
	
	@Test(description = "Resend & Fetch OTP", priority = 5, enabled =  true)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void ResendFetchOTP(String msisdn) throws Exception {
	logger = extent.startTest("Resend & Fetch OTP from DB", "Description to Resend & Fetch OTP from DB");	
	propertyelements();
	resendandgetotp();
	}
	
	
	@Test(description =  "Contextual Popup", priority = 6, enabled =  true)
	public void contextpopup() throws Exception  {
		logger = extent.startTest("Context Popup", "Description to contextul  popup");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		contextmethod();
	}
	
	
	@Test(description =  "Banners Details", priority = 7, enabled =  true)
	public void BannerDetails() throws Exception  {
		logger = extent.startTest("Banner Details(Manual Verification Required to Check Banners Displayed on the App)", "Description to BannerDetails (Manual Verification Required to Check Banners Displayed on the App)");
		AutoBannerheader();
	}
	
	
	@Test(description = "Top Up - Voucher", priority = 8, enabled =  true)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void TopUpVoucher(String msisdn) throws JSONException, InterruptedException, Exception {
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		ClickEvents("homebtton");
		//logger = extent.startTest("TopUpVoucher - BParty Number", "Description to TopUpVoucher");		
		//TopUpApp("TopUpText", "Voucher", "TopUp_Enter_VoucherCode", "Valid", "TopUp_ConfirmButton", "Voucher","9971357267");
		//ClickEvents("homebtton");
		
		logger = extent.startTest("TopUpVoucher", "Description to TopUpVoucher");
		//ScrollUp();
		//Thread.sleep(10000);
		TopUpApp("TopUpText", "Voucher", "TopUp_Enter_VoucherCode", "Valid", "TopUp_ConfirmButton", "Voucher",msisdn);
		ClickEvents("homebtton");
	
	}
	

	// log done
	@Test(description = "Top Up - MPitesan", priority = 9, enabled =  true)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void TopUpMPitesan(String msisdnxml) throws Exception {
		// System.out.println("MPite init()");
		
		logger = extent.startTest("TopUpMPitesan - Own Number", "Description to TopUpMPitesan");
		//Thread.sleep(10000);
		ClickEvents("homebtton");
		TopUpApp("TopUpText", "Mpitesan", "AmountPitesan", "Valid", "topupsubmit", "MPite",msisdnxml);
		
		ClickEvents("homebtton");		
		//logger = extent.startTest("TopUpMPitesan - BParty Number", "Description to TopUpMPitesan");
		//TopUpApp("TopUpText", "Mpitesan", "AmountPitesan", "Valid", "topupsubmit", "MPite","9971357267");
		
		}
	
	
	/*@Test(description =  "QuickLinks", priority = 10, enabled =false)
	public void QuickLinks() throws Exception  {
		logger = extent.startTest("QuickLinks Redirections", "Description to QuickLinks  Redirections");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		QuickLinksPlus();
	}*/
	
	@Test(description =  "Notifications", priority = 11, enabled =  true)
	public void Notifications() throws Exception  {
		logger = extent.startTest("Bell Icon Notifications Redirection", "Description to Notifications  Redirections");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		bellnotification();		
	}	
	
	@Test(description = "VIP MyBenefits", priority = 12, enabled =  true)
	public void VIPMyBenefits() throws Exception {
		logger = extent.startTest("VIPMyBenefits", "Description to MyBenefits");	
		//Thread.sleep(5000);
		ClickEvents("homebtton");
		MyBenefits();		
		}
	
	@Test(description = "VIP", priority = 13, enabled =  true)
	public void VIP_Validation() throws Exception {
		logger = extent.startTest("VIP User Details", "Description to VIP User Details");
		vipvaliddb();
	}
	
	
	@Test(description =  "Usage History", priority = 14, enabled =  true)
	public void UsageHistory() throws Exception  {
		logger = extent.startTest("User Usage History", "Description to Usage History");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		usage();		
	}
	
	
	
	@Test(description =  "UserRegisterStatus", priority = 15, enabled =  true)
	public void UserRegisterStatus() throws Exception  {
		logger = extent.startTest("User SIM Registration Status", "Description to User Registration Status");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		registerstatus();
	}
	
	
	@Test(description =  "UserRegisterDetails", priority = 16, enabled =  true)
	public void UserRegisterDetails() throws Exception  {
		logger = extent.startTest("User SIM Registration Details", "Description to User Registration Details");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		registerdetails();
		}
	
	

//  context pop up check header related	
		
		/*dbdetails("regsimdropdown", "regsimstatusquery");
		WebElement status = Webdr.findElement(sesiontoken);
		String userstatus = status.getText();
		System.out.println(userstatus);	
		
		//driver.resetApp();
		ETLiveTransApi("chkHeader", "Authentication", "1589867941209359", obj.getProperty("apiversion"), "popups",
				obj.getProperty("apiuri"), "order", "","data");	
		
		//System.out.println("Total pop up from API = "+popuporders.keySet().size());
		Map<String,String> ordername =  new HashMap<>();
		int count = 1;
		for(String key : popuporders.keySet()) {
           String[] arr = key.split("_");
           String ord = arr[0];	
           List<Schedule> li = popuporders.get(key);
           System.out.println("Key: " + key);
           System.out.println("---------------------");
           for(int orderwise = 1; orderwise<=li.size();orderwise++) {            
            for(Schedule schedule : li) {
            	if(Integer.valueOf(ord) == orderwise && schedule.getUserstatus().contains(userstatus)) {
            		System.out.println("scheduleName = "+schedule.getName() + "status = "+ord);
            		ordername.put(ord, schedule.getName());
            		count++;
            		
            		//
            	}
                System.out.println("scheduleName: " + schedule.getName() + " userStatus: " + schedule.getUserstatus());
            }
            System.out.println("---------------------");
           }
		}
		
		System.out.println(ordername);
		
		 */
		
		//driver.closeApp();
//		Key: 1_Status popup
//		---------------------
//		scheduleName: Sim Registration userStatus: [500,2,3]
//		scheduleName: Sim Registration Approved userStatus: [0]		
		/*ETLiveTransApi("GetPopupInfo", "contextual", "1600231037439", obj.getProperty("apiversion"), "vasdetails",
				obj.getProperty("apiuri"), "title", "");*/		
		//http://ecareapp.ooredoo.com.mm/SelfcareAPI7.2/api//
		//Thread.sleep(5000);		
		/*String ActivateName = driver.currentActivity();
		String curentpackage = driver.getCurrentPackage();
		System.out.println("ActivityName = "+ActivateName);	
		System.out.println("PackageName = "+curentpackage);		
		//driver.runAppInBackground(Duration.ofSeconds(5));		
		driver.terminateApp(curentpackage);		
		//driver.closeApp();
		//driver.launchApp();		
		Thread.sleep(3000);
		((AndroidDriver) driver).startActivity(new Activity(curentpackage, curentpackage+ActivateName));
		*/
	
	@Test(description = "Data Voice SMS Details", priority = 17, enabled =  false)
	public void QueryBalanceApi() throws Exception {
		logger = extent.startTest("User Packs Details", "Description to Packs Details");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		querydataapi();
	}
	
	
	@Test(description =  "VIP HomePage", priority = 18, enabled =  false)
	public void VIPHomePage() throws Exception, InterruptedException, CustomException {
		logger = extent.startTest("VIP HomePage", "Description to VIPHomePage");
		contextmap.clear();
		ClickEvents("homebtton");
		vipnewhomepages();		
	}	
	
	@Test(description = "VIP PageLoad Time", priority = 19, enabled =  false)
	public void PageloadTest() throws Exception {
		logger = extent.startTest("VIP Page Load Time", "Description to page load");	
		ClickEvents("homebtton");	
		vipclickupdate();
		long starttime = System.currentTimeMillis();
		waituntillfound("onlyfor");
		if(AppValidation("onlyfor").equals("Only For You")) {
		long EndTime = System.currentTimeMillis();
		getResult1("pass");
		long totaltime = EndTime - starttime;
		logger.log(LogStatus.INFO, " VIP Page Load Time : " +totaltime + " milliseconds");
		//ClickEvents("homebtton");
			} 
		}
	
	@Test(description =  "VIPHomePageShortCutIcons", priority = 20, enabled =  false)
	public void VIPHomePageShortCutIcons() throws Exception, InterruptedException, CustomException {
		logger = extent.startTest("VIP HomePage ShortCut Icons Navigations", "Description to VIPHomePageShortCutIcons"); 
		vipshort();
	}	
	
	
	@Test(description = "VIP Spin The Wheel", priority = 21, enabled =  false)
	public void VipSpinWheel() throws Exception {
		logger = extent.startTest("VIP Spin The Wheel", "Description to VIP Spin The Wheel");		
		spinwheel();		
		}


	@Test(description =  "DDD Packs", priority = 22, enabled =  false)
	public void DDDPacks() throws Exception  {
		logger = extent.startTest("Don Don Don Packs", "Description to DDD PAcks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);					
		ddbanners();		
	}
	

	@Test(description =  "Pyaw Pyaw Yu", priority = 23, enabled =  false)
	public void PyawPyawYu() throws Exception, InterruptedException, CustomException {
		logger = extent.startTest("Pyaw Pyaw Yu Pack Purchase", "Description to PyawPyawYu");		
		pyawpyawTestCase();		
	}	
	
	
	@Test(description = "VIP RedeemPoints", priority = 24, enabled =  false)
	public void VIPRedeemPoints() throws Exception {
		logger = extent.startTest("VIP Redeem Points", "Description to VIP RedeemPoints");	
		ClickEvents("homebtton");
		RedeemPoints();		
		}
	
	
	@Test(description = "VIP MyDiscounts", priority = 25, enabled =  false)
	public void VIPMyDiscounts() throws Exception {
		logger = extent.startTest("VIP MyDiscounts", "Description to VIP MyDiscounts");	
		//Thread.sleep(2000);
		ClickEvents("homebtton");
		vipclickupdate();
		MyDiscounts();		
		}
	
	
	@Test(description = "VIP MyPoints", priority = 26, enabled =  false)
	public void VIPMyPoints() throws Exception {
		logger = extent.startTest("VIP MyPoints", "Description to VIP MyPoints");	
		Thread.sleep(2000);
		ClickEvents("homebtton");
		vipclickupdate();	
		MyPoints();
		}
	
	
	@Test(description =  "UpdateLocation", priority = 27, enabled =  false)
	public void UpdateLocation() throws Exception, InterruptedException, CustomException {
		logger = extent.startTest("Update New Location", "Description to UpdateLocation");
		propertyelements();	
		updatelocation();	
		//ClickEvents("homebtton");
	}
	
	
	// 8.2
	
	@Test(description = "ProfileUpdatePending", priority = 28, enabled =  false)
	public void PendingProfileUpdate() throws Exception {
		logger = extent.startTest("Update Pending Profile If Not Updated", "Pending Task");
		Thread.sleep(10000);
		ClickEvents("homebtton");
		//ClickEvents("MoreIcon");
		ClickEvents("AddOption");
		//vipclickupdate();
		waituntillfound("PendingTaskTitle");
		getResult1("pass");
		if(driver.findElements(By.xpath(obj.getProperty("PendingTaskTitle"))).size()!=0)  {
			//logger.log(LogStatus.PASS, AppValidation("PendingTaskTitle"));
			logger.log(LogStatus.PASS, AppValidation("CompleteProfileTitle"));
			logger.log(LogStatus.PASS, AppValidation("UpdateInfoDesc"));
			logger.log(LogStatus.PASS, AppValidation("updatelocationbutton"));			
			ClickEvents("updatelocationbutton");
			if(driver.findElements(By.xpath(obj.getProperty("ProfileEmail"))).size()!=0 && driver.findElements(By.xpath(obj.getProperty("ProfileAlternateNum"))).size()!=0) {
				logger.log(LogStatus.PASS, "Redirected to Profile Update Page");
				getResult1("pass");
			} else {
				logger.log(LogStatus.FAIL, "Redirection failed");
			}
		}
		
	}
	
	// 8.2
	
	@Test(description = "More My Details", priority = 29, enabled =  false)
	public void MoreMyDetails() throws Exception {
		logger = extent.startTest("My Details", "My Details");
		propertyelements();
		//Thread.sleep(5000);
		ClickEvents("homebtton");
		ClickEvents("AddOption");
		getResult1("pass");
		waituntillfound("More_Setting");
		ClickEvents("More_Setting");
		waituntillfound("mydetails");
		if(AppValidation("mydetails").equals("MY DETAILS"))  {
			ClickEvents("mydetails");	
			getResult1("pass");
			logger.log(LogStatus.PASS, AppValidationList("Lable") + " : "+AppValidationList("LableValue"));
			Thread.sleep(1000);
			ClickEvents("MyDetailsEditText");
			if(driver.findElements(By.xpath(obj.getProperty("ProfileEmail"))).size()!=0 && driver.findElements(By.xpath(obj.getProperty("ProfileAlternateNum"))).size()!=0) {
				logger.log(LogStatus.PASS, "Redirected to Profile Update Page");
				getResult1("pass");
				driver.navigate().back();
				
				if(driver.findElements(By.xpath(obj.getProperty("VerifyEmailMyDetails"))).size()!=0) {
					ClickEvents("VerifyEmailMyDetails");
					waituntillfound("subscribe_confirm_pop_up1");
					if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
						logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
						getResult1("pass");
						ClickEvents("Ok_button");
					} else {
						logger.log(LogStatus.FAIL, "Verify Email Popup not displayed");
					}
					
				} else {
					logger.log(LogStatus.INFO, "Verify link text is not available");
				}
			} else {
				logger.log(LogStatus.FAIL, "Redirection failed");
			}
			if(driver.findElements(By.xpath(obj.getProperty("PromotionBanner"))).size()!=0) {
				logger.log(LogStatus.INFO, "Promotional Banner Available");
				ClickEvents("PromotionBanner");
				Thread.sleep(3000);
				getResult1("pass");
			} else  {
				logger.log(LogStatus.INFO, "Promotional Banner Not Available");
			}
			
		}
		
	}
	
	// 8.2
		
	
	@Test(description = "ProfileUpdate", priority = 30, enabled =  false)
	public void VipProfileUpdate() throws Exception {
		logger = extent.startTest("Profile Update", "Description to ProfileUpdate");	
		Thread.sleep(10000);
		propertyelements();
		ClickEvents("homebtton");
		ProfileUpdate();		
		}
	
		
	@Test(description = "Entertainment section redirection", priority = 31, enabled =  false)
	public void BannerDet() throws Exception {
		logger = extent.startTest("Entertainment section redirection", "Description to Entertainment section redirection");
		propertyelements();
		ClickEvents("homebtton");
		scrolltill("Entertainment", "Entertainment");		
		Swipe(3, "ETbanners",65);		
		logger.log(LogStatus.PASS, "Entertainment banners ");		
		getResult1("pass");
		ClickEvents("ETbanners");
		Thread.sleep(10000);
		getResult1("pass");
		logger.log(LogStatus.PASS, "Navigated to External Page");
		driver.navigate().back();
		}

	// Updated 31/08/20
		
		/*@Test(description = "Buy Packs - Buy", priority = 12, enabled =false)
		public void BuyPackBuy() throws Exception {

			logger = extent.startTest("BuyPackBuy", "Description to Buy Packs-Buy");
			Thread.sleep(8000);
			propertyelements();
			//ClickEvents("HomeIcon");
			
			bygft("buypackBuy", "Valid");
		}

		// Updated 31/08/20

		@Test(description = "Buy Packs - Gift", priority = 13, enabled =false)
		public void GiftPackGift() throws Exception {
			logger = extent.startTest("GiftPackGift", "Description to Gift Packs-Gift");
			Thread.sleep(3000);
			propertyelements();
			ClickEvents("homebtton");		
			Thread.sleep(2000);
			//ScrollUp();
			// -- For Gift you need to get value from Sheet -- 
			bygft("buypackGift", "Valid");
		}	
		*/
		
		@Test(description = "LiveTV Details", priority = 32, enabled =  false)
		public void LiveTVDetails() throws Exception {
			logger = extent.startTest("LiveTVDetails Redirection", "Description to LiveTVDetails");
			ClickEvents("homebtton");
			livetvdetails();
		}
		
		
		// Make false this - 08/01/21

		@Test(description = "Byop Home Page", priority = 33, enabled =  false)
		public void ByopQueryBalance() throws Exception {
			logger = extent.startTest("BYOP Home Page", "Description to BYOP Home Page");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			propertyelements();
			//ScrollUp();
			//ScrollUp();
			//ClickEvents("HomeIcon");
			Thread.sleep(2000);
			ClickEvents("homebtton");			
			String statetinekya = "Sate Tine Kya";			
			scrolltill("tinekya",statetinekya );	
			if(driver.findElements(By.xpath(obj.getProperty("tinekya"))).size()!=0) {
			//if(statetinekya.length()!=0) {				
				Map<String,List<String>> APPByop = new HashMap<String,List<String>>();			
				
				APPByop.put("Progress",AppValidationList("isb_progress"));				
				APPByop.put("RangeDetails",AppValidationList("tvEnd"));
				APPByop.put("Titles",AppValidationList("subbyoptitle"));
				System.out.println("APPByop ==> " + APPByop);
				System.out.println(APPByop.get("Progress"));
				if(APPByop.keySet().contains("Progress")) {
					int keysize = APPByop.get("Progress").size();
					boolean lowbalance = false;
					for(int u=0; u<keysize ; u++) {
						String value1 = APPByop.get("Progress").get(u).replaceAll("[^0-9]", "");
						System.out.println("after relpace = "+Integer.valueOf(value1));
						if(Integer.valueOf(value1) < 10) {	
							lowbalance =  true;					
						} 
					}
					if(lowbalance) {
						System.out.println( "Low balance");
						logger.log(LogStatus.FAIL, "Low Balance BYOP");
					} else {
						logger.log(LogStatus.PASS, "Not Low Balance BYOP");
						System.out.println( "Not a low balance");
					}
				}
				for (Entry<String, List<String>> entry : APPByop.entrySet()) {
				    int mapKeys = entry.getValue().size();
				    System.out.println(mapKeys);
				    System.out.println("entry.getValue() - "+entry.getValue());
				    
				   
				}
				
				/*if(APPByop.get("Progress") < 10 Mins || APPByop.get("Progress") < 500 MB) {
					
				}*/
				logger.log(LogStatus.PASS, "Test Case Passed is ByopQueryBalance" +APPByop);
				getResult1("pass");
				
			} else {
				ClickEvents("homebtton");
				logger.log(LogStatus.PASS, "New User . Please Select your own pack from BYOP Banner");
				swipeByElements();
				swipeByElements();
				String toastmesg = tc.ToastMessage(TakeScreenshot(),"Tine Kya");
				System.out.println("toastmesg == > "+toastmesg);				
				if(toastmesg.equals("True")) {
					getResult1("pass");
				} else {
					System.out.println("Failed to find BYOP Banner");
				}
			}			
		}
			// scrolltill("buymorebyop", "Buy More");
			// ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/
			//List<String> byopaapi = QueryBYOPBalance(obj.getProperty("apiversion"));
			//System.out.println("byopaapi ==>" + byopaapi);

			//if (byopaapi.size() > 2) {
				//Collections.sort(APPByop);
				//Collections.sort(byopaapi);
				//boolean ByopAPPByop = APPByop.equals(byopaapi);
				// Assert.asserttrue(ByopAPPByop);
				//if (ByopAPPByop) {
					//System.out.println("Passed");					
					// logger.log(LogStatus.INFO, "App Details : "+APPByop);
					// logger.log(LogStatus.INFO, "Api Details : "+byopaapi);
				/*} else {
					System.out.println("Failed");
					logger.log(LogStatus.FAIL, "MisMatch with App and Api Details : " + APPByop.removeAll(byopaapi));

					logger.log(LogStatus.FAIL, "App Details : " + APPByop);
					logger.log(LogStatus.FAIL, "Api Details : " + byopaapi);
				}
			} else {
				System.out.println("Passed");
				logger.log(LogStatus.PASS, "Banner Exists :" + byopaapi.get(0));
			}*/

	

	

	
	
	
	@Test(description = "Report an Issue", priority = 34, enabled =  false)
	public void Report_An_Issue() throws Exception {
		logger = extent.startTest("Report an Issue", "Description to Report an Issue");
		propertyelements();
		HSSFWorkbook report_an_issue_wb = Exceel();
		HSSFSheet report_an_issue_Sheet = report_an_issue_wb.getSheet("ContactUs");
		//ClickEvents("MoreIcon");
		ClickEvents("AddOption");
		Thread.sleep(1000);
		getResult1("pass");
		ClickEvents("More_Contact_US_Support");
		Thread.sleep(1000);
		getResult1("pass");
		// driver.navigate().back();
		ClickEvents("Contact_Report_An_Issue");

		Thread.sleep(5000);
		getResult1("pass");
		try {
			for (int i = 1; i <= report_an_issue_Sheet.getPhysicalNumberOfRows(); i++) {
				String Enter_Name = report_an_issue_Sheet.getRow(i).getCell(1).getStringCellValue();
				System.out.println("Enter_Name ==>" + Enter_Name);
				String Category = report_an_issue_Sheet.getRow(i).getCell(2).getStringCellValue();
				// System.out.println("Category ==>"+Category);
				String Sub_Category = report_an_issue_Sheet.getRow(i).getCell(3).getStringCellValue();
				// System.out.println("Sub_Category ==>"+Sub_Category);
				String Date_Time = report_an_issue_Sheet.getRow(i).getCell(4).getStringCellValue();
				// System.out.println("Date_Time ==>"+Date_Time);
				String Description = report_an_issue_Sheet.getRow(i).getCell(5).getStringCellValue();
				// System.out.println("Description ==>"+Description);
				String Expected_Output = report_an_issue_Sheet.getRow(i).getCell(6).getStringCellValue();
				// System.out.println("Expected_Output ==>"+Expected_Output);
				Thread.sleep(8000);
				String result = Contact_Us_Page_Report_An_Issue(Enter_Name, Category, Sub_Category, Date_Time,
						Description, Expected_Output);
				System.out.println("result ==>" + result);
				if (result != null) {
					logger.log(LogStatus.PASS, "Report an Issue passed = " + result);
					getResult1("pass");
				} else {
					logger.log(LogStatus.FAIL, "Report an Issue Failed due to  " + result);
				}

			}
		} catch (Exception ex) {
			System.out.println("Report an Issue = " + ex);
		}

	}


	@Test(description = "More Settings", priority = 35, enabled =  false)
	public void More_Settings() throws Exception {
		logger = extent.startTest("Settings (Roam, DND, Lang)", "Description to Settings");
		propertyelements();
		Settings();

	}
	
	// Whole Play Page changed 08/01/21
	
	@Test(description = "OoredooPlay", priority = 36, enabled =  false)
	public void Ooredoo_Play() throws Exception {
		logger = extent.startTest("Ooredoo Play", "Description to Ooredoo Play");
		propertyelements();
		ClickEvents("homebtton");
		Ooredoo_Play_sub();
	}
	
	@Test(description="Existing Packages", priority = 37 , enabled =  false)
	public void accountview() throws Exception {
		logger = extent.startTest("My Existing Packages", "Description to VIP Points");
		ExistingPacks();

	}
	
	@Test(description="FAQ_TermsConditions", priority = 38 , enabled =  false)
	public void FAQ_TermsConditions() throws Exception {
		logger = extent.startTest("FAQ_TermsConditions Redirections", "FAQ_TermsConditions Redirections");
		propertyelements();
		ClickEvents("homebtton");
		//ClickEvents("MoreIcon");
		ClickEvents("AddOption");
		Thread.sleep(1000);
		getResult1("pass");
		ClickEvents("More_Contact_US_Support");
		Thread.sleep(1000);
		getResult1("pass");
		ClickEvents("Contact_FAQ");
		long starttimeFAQ = System.currentTimeMillis();
		waituntillfound("FAQinFAQ");
		getResult1("pass");
		if(driver.findElements(By.xpath(obj.getProperty("FAQinFAQ"))).size()!=0) {	
			long EndTimeFAQ = System.currentTimeMillis();
			long totaltimeFAQ = EndTimeFAQ - starttimeFAQ;
			logger.log(LogStatus.INFO, " FAQ Page Load Time : " +totaltimeFAQ + " milliseconds");
			logger.log(LogStatus.PASS, "Redirected to FAQ");			
		} else  {			
			logger.log(LogStatus.FAIL, "Unable to Redirect to FAQ");
		}
		driver.navigate().back();
		ClickEvents("Contact_TC");		
		long starttimeTC = System.currentTimeMillis();		
		getResult1("pass");	
		waituntillfound("TermCondition");
		getResult1("pass");
		if(driver.findElements(By.xpath(obj.getProperty("TermCondition"))).size()!=0) {	
			long EndTimeTC = System.currentTimeMillis();
			long totaltimeTC = EndTimeTC - starttimeTC;
			logger.log(LogStatus.INFO, " T&C Page Load Time : " +totaltimeTC + " milliseconds");			
			logger.log(LogStatus.PASS, "Redirected to Terms and Condition Page");			
		} else  {			
			logger.log(LogStatus.FAIL, "Unable to Redirect to T&C");
		}
		
	}
	
	
	// Make false - 08//01/21
	
	@Test(description = "ReferFriend", priority = 39, enabled =  false)
	public void ReferFriend() throws Exception {
		logger = extent.startTest("Refer A Friend", "Refer A Friend");
		propertyelements();
		Thread.sleep(5000);
		ReferAFriend();		
	}
	
	
	@Test(description = "Nini", priority = 40, enabled =  false)
	public void Nini() throws Exception {
		logger = extent.startTest("Nini", "Nini");		
		NiniNudge();		
	}	
	
	
	@Test(description = "Special Offers Redeem", priority = 41, enabled =  false)
	public void VipRedeemPoints() throws Exception {
		logger = extent.startTest("Vip Special Offers Redeem Points", "Vip Redeem Points");
		SpecialOffers();
	}
	
	@Test(description = "Alert Messages", priority = 42, enabled =  false)
	public void LowAlert() throws Exception {
		logger = extent.startTest("Home Page Low Alert Message", "Low Alerts Points");
		Thread.sleep(5000);
		LowAlertsMessages();
	}
	
	
	// 8.2
	
	@Test(description = "Multiple Account", priority = 43, enabled =  false)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void MultipleAccount(String samemsisdn) throws Exception {
		logger = extent.startTest("MultipleAccount", "MultipleAccount");
		//Thread.sleep(10000);
		ClickEvents("homebtton");
		ClickEvents("AddOption");
		waituntillfound("More_AddManageAccount");
		getResult1("pass");
		ClickEvents("More_AddManageAccount");
		Thread.sleep(1000);
		getResult1("pass");
		if(driver.findElements(By.xpath(obj.getProperty("EnterNameTitle"))).size()!=0) {
			logger.log(LogStatus.PASS, "User has no previous accounts added - Please add accounts");
			ClickEvents("ProceedButton");			
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Enter Name Error Popup not displayed");
			}
			Thread.sleep(1000);
			logger.log(LogStatus.PASS, AppValidation("EnterNameTitle"));
			SendEvent("EnterAccountName", "TestAccountName1");
			ClickEvents("ProceedButton");			
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Enter Mobile Number Error Popup not displayed");
			}
			waituntillfound("EnterNumberTitle");
			logger.log(LogStatus.PASS, AppValidation("EnterNumberTitle"));
			SendEvent("EnterAccountNumber", samemsisdn);
			ClickEvents("ProceedButton");
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Cannot add same Number Error Popup not displayed");
			}
			waituntillfound("EnterNumberTitle");
			SendEvent("EnterAccountNumber", obj.getProperty("othermsisdn"));
			ClickEvents("ProceedButton");
			
			getResult1("pass");					
			String Altnumotp = otprecive("Dropdown1","Altnumquery");
			logger.log(LogStatus.PASS, "Account Number verification pin :"+Altnumotp);
			SendEvent("OTPfield", Altnumotp);
			ClickEvents("OtpSubmit");
			waituntillfound("ManageAccountName");
			getResult1("pass");
			ManageAcountsList();
						
		} else {
			logger.log(LogStatus.PASS, "Below are the list of accounts added");
			ManageAcountsList();
		}
		
		
	}
	
	
	// 8.2
	
	
	@Test(description = "DeleteAccount", priority = 44, enabled =  false)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void DeleteAccount(String samemsisdn) throws Exception {
		logger = extent.startTest("DeleteAccount", "DeleteAccount");
		//Thread.sleep(10000);
		ClickEvents("homebtton");
		ClickEvents("AddOption");
		getResult1("pass");
		/*ClickEvents("Setting_AddMultiAccount");		
		waituntillfound("Refer_Titile");
		if(AppValidation("Refer_Titile").equals("Manage Accounts")) {
			logger.log(LogStatus.PASS, "Redirected Back to Manage Accounts Page");
			getResult1("pass");
			ClickEvents("backtohome");
			waituntillfound("Setting_AddMultiAccount");
			if(AppValidation("Setting_AddMultiAccount").equals("Add/Manage Accounts >")) {
				logger.log(LogStatus.PASS, "Redirected Back to More Settings Page");
				getResult1("pass");
			} else  {
				logger.log(LogStatus.FAIL, "Redirection Back to More page failed");
			}
		} else {
			logger.log(LogStatus.FAIL, "Redirection Failed");
		}*/
		ClickEvents("More_Setting");		
		waituntillfound("MoreSettings_Location");
		ClickEvents("MoreSettings_Location");
		if(driver.findElements(By.xpath(obj.getProperty("MoreSettings_MY_NUMBERS"))).size()!=0) {
		ClickEvents("MoreSettings_MY_NUMBERS");
		getResult1("pass");
		if(driver.findElements(By.xpath(obj.getProperty("More_MyNumbers_Edit"))).size()!=0)  {
			logger.log(LogStatus.PASS, "List of Accounts are listed below");
			String BeforeName = AppValidation("ManageAccountName");
			logger.log(LogStatus.PASS, "Account Name : "+AppValidationList("ManageAccountName"));
			logger.log(LogStatus.PASS, "Account Mobile Number : "+AppValidationList("ManageAccountMobileNumber"));
			logger.log(LogStatus.PASS, "Account Type : "+AppValidationList("ManageAccountType"));
			logger.log(LogStatus.PASS, "Account Type : "+AppValidationList("More_MyNumbers_Edit"));	
			ClickEvents("More_MyNumbers_Edit");
			waituntillfound("EnterAccountName");
			getResult1("pass");
			SendEvent("EnterAccountName", "TestAccountNameChange");
			if(driver.findElement(By.xpath(obj.getProperty("EnterAccountNumber"))).isEnabled()) {
				logger.log(LogStatus.FAIL, "Mobile number able to edit");
			} else {
				logger.log(LogStatus.PASS, "Mobile number is disabled");
			}
			ClickEvents("Mynumber_Update");
			waituntillfound("subscribe_confirm_pop_up1");
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Cannot add same Number Error Popup not displayed");
			}
			waituntillfound("ManageAccountName");
			if(AppValidation("ManageAccountName").equals(BeforeName)) {
				logger.log(LogStatus.FAIL, "Name Not Updated");
			} else {
				logger.log(LogStatus.PASS, "Name Changed Succesfully");
			}
			
			ClickEvents("More_MyNumbers_Delete");
			waituntillfound("Mynumber_removeAccontpopup");
			getResult1("pass");
			ClickEvents("Mynumber_removeYesbtn");
			waituntillfound("subscribe_confirm_pop_up1");
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("Mynumber_ThanksText"));
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));			
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Deleted Confirmation Popup not displayed");
				}
			}
			
		} else {
			logger.log(LogStatus.PASS, "My Numbers Section is not available");
			getResult1("pass");
			/*getResult1("pass");
			AppValidation("MyNumber_NoNumbersAvailable");
			ClickEvents("More_MyNumbers_AddButton");
			waituntillfound("EnterNameTitle");
			if(driver.findElements(By.xpath(obj.getProperty("EnterNameTitle"))).size()!=0) {
				getResult1("pass");
				logger.log(LogStatus.PASS, "Pop Up displayed");
				ClickEvents("closeregister");
			}  else {
				logger.log(LogStatus.PASS, "Failed to display Pop Up");
				
			}*/
		}
	}
	
	
	// 8.2
	
	@Test(description = "SuperNetMini Account", priority = 45, enabled =  false)
	@org.testng.annotations.Parameters(value={"msisdn"})
	public void SuperNetMini(String samemsisdn) throws Exception {
		logger = extent.startTest("SuperNetMini", "SuperNetMini Account");
		//Thread.sleep(10000);
		ClickEvents("homebtton");
		ClickEvents("AddOption");
		waituntillfound("More_AddManageAccount");
		getResult1("pass");
		ClickEvents("More_AddManageAccount");
		Thread.sleep(1000);
		getResult1("pass");
		if(driver.findElements(By.xpath(obj.getProperty("EnterNameTitle"))).size()!=0) {
			logger.log(LogStatus.PASS, "User has no previous accounts added - Please add accounts");
			ClickEvents("SupernetRadioDial");
			Thread.sleep(1000);
			ClickEvents("ProceedButton");			
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Enter Name Error Popup not displayed");
			}
			Thread.sleep(1000);
			logger.log(LogStatus.PASS, AppValidation("EnterNameTitle"));
			SendEvent("EnterAccountName", "TestSupernetAccount");
			ClickEvents("ProceedButton");
			waituntillfound("subscribe_confirm_pop_up1");
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Enter Mobile Number Error Popup not displayed");
			}
			waituntillfound("EnterNumberTitle");
			logger.log(LogStatus.PASS, AppValidation("EnterNumberTitle"));
			SendEvent("EnterAccountNumber", samemsisdn);
			ClickEvents("ProceedButton");
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Cannot add same Number Error Popup not displayed");
			}
			
			waituntillfound("EnterNumberTitle");
			SendEvent("EnterAccountNumber", "9957123");
			ClickEvents("ProceedButton");
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Valid supernet number Error Popup not displayed");
			}
						
			
			waituntillfound("EnterNumberTitle");
			SendEvent("EnterAccountNumber", obj.getProperty("othersupermsisdn"));
			ClickEvents("ProceedButton");			
			getResult1("pass");					
			String Altnumotp = otprecive("Dropdown1","Supernetquery");
			logger.log(LogStatus.PASS, "Account Number verification pin :"+Altnumotp);
			SendEvent("OTPfield", Altnumotp);
			ClickEvents("OtpSubmit");
			waituntillfound("ManageAccountName");
			getResult1("pass");
			ManageAcountsList();
						
		} else {
			logger.log(LogStatus.PASS, "Below are the list of accounts added");
			ManageAcountsList();
		}
		
		
	}
	
	// 8.2
	
	@Test(description = "MaxAccountVerify", priority = 46, enabled =  false)
	public void MaxAccountVerify() throws Exception {
		logger = extent.startTest("MaxAccountVerify", "MaxAccountVerify");
		propertyelements();
		//Thread.sleep(10000);
		ClickEvents("homebtton");
		ClickEvents("AddOption");
		waituntillfound("More_AddManageAccount");
		getResult1("pass");
		ClickEvents("More_AddManageAccount");
		waituntillfound("ManageAccount_FullBanner");		
		getResult1("pass");
		
		for(int accountnum = 1; accountnum<12; accountnum++) {
			ClickEvents("ManageAccount_FullBanner");
			Thread.sleep(2000);
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
				//waituntillfound("More_MyNumbers_Edit");
				if(driver.findElements(By.xpath(obj.getProperty("More_MyNumbers_Edit"))).size()!=0)  {
					logger.log(LogStatus.PASS, "Redirected to My Numbers");
					getResult1("pass");
				} else  {
					logger.log(LogStatus.PASS, "Redirection failed");
				}
				
				return;				
					
				} 
			 else {
				 waituntillfound("EnterAccountName");
					SendEvent("EnterAccountName", "TestAccount"+accountnum);
					logger.log(LogStatus.PASS, "Account Name :"+"TestAccount"+accountnum);
					SendEvent("EnterAccountNumber", obj.getProperty("AccountNumber"+accountnum));
					logger.log(LogStatus.PASS, "Account Number :"+obj.getProperty("AccountNumber"+accountnum));
					ClickEvents("ProceedButton");			
					getResult1("pass");		
					
					String numotp = otprecive("Dropdown1","AccountQuery"+accountnum);
					logger.log(LogStatus.PASS, "Account Number verification pin :"+numotp);
					SendEvent("OTPfield", numotp);
					ClickEvents("OtpSubmit");
					//if(driver.findElements(By.xpath(obj.getProperty("exceedlimit"))).size()!=0) {
					Thread.sleep(2000);
				waituntillfound("ManageAccountName");
				getResult1("pass");
			}			
			
		}
		
		
	}
	
	
	// Makethis false 08/01/21
	
	@Test(description = "EmailVerification", priority = 47, enabled =  false)
	public void EmailVerification() throws Exception {
		logger = extent.startTest("Email Verification", "logout Functoinality");
		propertyelements(); 
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rajesh.k\\Desktop\\OoredooCode\\chrome87browser\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        WebDriver Chrdriver =  new ChromeDriver(options); 
        Chrdriver.get("https://accounts.google.com/signin");
        new WebDriverWait(Chrdriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='identifierId']"))).sendKeys("ooredoomynmar@gmail.com");
        Chrdriver.findElement(By.id("identifierNext")).click();
        new WebDriverWait(Chrdriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']"))).sendKeys("MOA@1234");
        Chrdriver.findElement(By.id("passwordNext")).click();
	}
	
	
	
	@Test(description = "Log Out", priority = 48, enabled =  false)
	public void LogOut() throws Exception {
		logger = extent.startTest("Logout", "logout Functoinality");
		propertyelements();
		logout();		
	}

	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = capture(result.getName());
			logger.log(LogStatus.FAIL, "Snapshot below: " + logger.addBase64ScreenShot((screenShotPath)));
		}
		extent.endTest(logger);
	}

	public String capture(String screenShotName) throws IOException {

		String base64Image = null;
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src = ts1.getScreenshotAs(OutputType.FILE);
		System.out.println("Successfully captured a screenshot");
		// File source = new File(src);
		File destination = new File(System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenShotName + ".png");
		FileUtils.copyFile(src, destination);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(destination);
			byte[] bytes = new byte[(int) destination.length()];
			fileInputStream.read(bytes);
			base64Image = new String(Base64.getEncoder().encodeToString(bytes));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return "data:image/png;base64,"+base64Image;
	}
	
	@AfterTest
	public void crashdetails(ITestResult resultcrash) {
		logger = extent.startTest("Crash Details", "Description to Crash");
		String activity = driver.currentActivity();
		if (!activity.equals("com.myapp.myactivity")){
			logger.log(LogStatus.FAIL, "App Crashed" +resultcrash.getName());
			logger.log(LogStatus.FAIL, "App Crashed" +resultcrash.getStatus());
			logger.log(LogStatus.FAIL, "App Crashed" +resultcrash.getTestName());
		}
	}

	@BeforeClass
	//@org.testng.annotations.Parameters(value={"config","environment","msisdn"})
	@org.testng.annotations.Parameters(value={"config","msisdn"})
	public void launchapp(String config_file,String msisdn) throws Exception {
	//public void launchapp(String config_file,String environment,String msisdn) throws Exception {
	//@org.testng.annotations.Parameters(value={"config"}) 
	//public void launchapp(String config_file) throws Exception {
		
		replacemsisdn(msisdn);
		//replacemsisdn("9971357267");	
		System.out.println("after = "+obj.getProperty("queryotp"));
		
		propertyelements();	
		
		Iterator it;
		//System.out.println(config_file);
		//System.out.println("environment = "+environment);
		
		System.out.println("Getting ready to Launch the APP ! ");			
		JSONParser parser = new JSONParser();
		Object config = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\ConfigFiles\\"+config_file));
		
		
		   System.out.println("config = "+config);	    
		  org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject)config;	
		  
		/*  
		  Map<String,String> envs = (Map<String, String>) jsonObject.get("environment");
	      System.out.println(envs.size());
	      System.out.println("envs = "+envs);	      
	      Map<String, String> environ = new HashMap<String, String>();
	      
		try {
			environ = (Map<String, String>) ((org.json.simple.JSONObject) envs).get(environment);
			System.out.println("envCapabilities = "+environ);
		       it = environ.entrySet().iterator();
		      while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        cap.setCapability(pair.getKey().toString(), pair.getValue().toString());
		      }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */
		
		
	      
	      try {
			Map<String,String> caps = (Map<String, String>) jsonObject.get("capabilities");
			  
			  it = caps.entrySet().iterator();

			    while (it.hasNext()) {

			        Map.Entry pair = (Map.Entry)it.next();

			        if(cap.getCapability(pair.getKey().toString()) == null){

			            cap.setCapability(pair.getKey().toString(), pair.getValue().toString());

			        }
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	         String xy = "Rajesh";
	         char[] c = xy.toCharArray();
	       	
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		//cap.setCapability(MobileCapabilityType.UDID, "520074b4ee426523");
		//Oppo
		cap.setCapability(MobileCapabilityType.UDID, "V4QKLNTK6PBYPJCU");		
		//cap.setCapability(MobileCapabilityType.UDID, "192.168.0.103:5555");
		cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		//cap.setCapability("noReset", "true");
		System.setProperty("webdriver.http.factory", "apache");
		cap.setCapability("autoWebView", "true");
		//cap.setCapability("unicodeKeyboard", "true");
		//cap.setCapability("resetKeyboard", "true");
		driver = new AndroidDriver<MobileElement>(url, cap);
		System.out.println("Added cap = "+cap);
		/*String userName = "srikanthmuthyala3";
		String accessKey = "nnsoCESaU6obGbqeyb1x";	
		
	   driver = new AndroidDriver<MobileElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), cap);
		*/ 
		initmethods();
		//driver.startRecordingScreen();
		
		String now = getDate();
		String folderdir = getDatefolder();		
		 File f = null;
	       boolean bool = false;
	       try {
	    	  
	    	   String folderpath = System.getProperty("user.dir")+"\\"+folderdir+"_Reports";	    	   
	          f = new File(folderpath);
	          bool = f.mkdirs();	          
	          System.out.print("Directory created? "+bool);	          
	         // extent = new ExtentReports (folderpath+"\\"+now+"_"+environ.get("device") + ".html", true);
	          extent = new ExtentReports (folderpath+"\\"+now+"_MOAReport" + ".html", true);
	       } catch(Exception e) {
	          e.printStackTrace();
	       }
		extent.addSystemInfo("Host Name", "Ooredoo MOA").addSystemInfo("Environment", "Mobile Automation Testing")
		.addSystemInfo("User Name", "My Ooredoo Myanmar");
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		//logger.getRunStatus();	
		
	}	
	
	
	

	
	@AfterClass
	public void tearDown() {	
		extent.flush();
		extent.close();
		Webdr.close();
		//driver.stopRecordingScreen();
		driver.quit();
	}

}






