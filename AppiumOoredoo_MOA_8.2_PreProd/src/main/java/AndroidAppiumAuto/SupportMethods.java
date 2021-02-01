package AndroidAppiumAuto;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;
import AndroidAppiumAuto.exceptions.CustomException;
import ImageCaptureToast.ToastCapture;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SupportMethods extends HomePageAPI {

	List<String> bb = new ArrayList<>();	


	public boolean HomePageSlideImage() throws Exception {

		String starticon = driver.getDeviceTime();
		System.out.println(starticon);
		swiperighticons("//android.widget.ImageView[contains(@resource-id, 'com.ooredoo.selfcare:id/ivIcon')]", 630.1,820.6, 630.6);
		return true;
	}


	public List<String> AppValidationList(String element) throws Exception {			
		//propertyelements();			
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String eeelements = obj.getProperty(element);				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(eeelements)));				
		List<MobileElement> DisplayCheck = driver.findElements(By.xpath(eeelements));			
		MobileElement DisplayCh = driver.findElement(By.xpath(eeelements));			
		List<String> listdisplay = new ArrayList<>();			
		if (DisplayCh.isDisplayed()) {			
			for (MobileElement DisplayChecks : DisplayCheck) {			
				System.out.println("App Display = " + DisplayChecks.getText());
				String check = DisplayChecks.getText();
				listdisplay.add(check);			
			}			
			return listdisplay;			
		} else {
			logger.log(LogStatus.FAIL, "App Loading ... Exceeded limited time. Please try again later");
			return null;
		}			
	}			


	public int AppValidationImageList(String element) throws Exception {			
		String eeelements2 = obj.getProperty(element);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(eeelements2)));			
		List<MobileElement> DisplayCheck = driver.findElements(By.xpath(eeelements2));			
		MobileElement DisplayCh = driver.findElement(By.xpath(eeelements2));
		bb.clear();
		if (DisplayCh.isDisplayed()) {			
			for (MobileElement DisplayChecks : DisplayCheck) {			
				//System.out.println("Image AppDisplayed = " + DisplayChecks.getSize());
				Dimension check = DisplayChecks.getSize();
				int ht = check.height;
				int wd = check.width;			
				//System.out.println("(" + String.valueOf(ht) + ",(" + String.valueOf(wd) + ")");			
				bb.add("(" + String.valueOf(ht) + "," + String.valueOf(wd) + ")");			
			}
			//System.out.println("AppValidationImageList: " + bb.size());								
		}				
		else {
			logger.log(LogStatus.FAIL, "App Loading ... Exceeded limited time. Please try again later");
		}
		return bb.size();
	}


	public ArrayList<String> voicesmsBon() throws Exception {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		ArrayList<String> vsb = new ArrayList<>();

		// vsb.addAll(AppValidationList("tvtitle"));

		vsb.addAll(AppValidationList("tvDataValues"));

		System.out.println("vsb 2 ==> " + vsb.get(2) + " 3 ==>" + vsb.get(3) + "4 ==>" + vsb.get(4));

		return vsb;

	}

	public void swiperighticons(String swipeele, double anchorPercentage, double startPercentage,double finalPercentage) throws Exception {



		try {
			// Dimension size = driver.manage().window().getSize();

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty(swipeele))));

			Dimension size = driver.manage().window().getSize();

			// Dimension size = slideimages.getSize();

			System.out.println("Size of slide image ==>" + size);

			// Start from middle of the page

			int anchor = (int) (anchorPercentage);
			int startPoint = (int) (startPercentage);
			int endPoint = (int) (finalPercentage);

			TouchAction tou = new TouchAction((AndroidDriver) driver);

			if (swipeele.equalsIgnoreCase(
					"//android.widget.ImageView[contains(@resource-id,'com.ooredoo.selfcare:id/icon')]")) {

				tou.press(PointOption.point(anchor, startPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
				.moveTo(PointOption.point(endPoint, startPoint)).release().perform();

			} else if (swipeele.equalsIgnoreCase(
					"//android.widget.ImageView[contains(@resource-id,'com.ooredoo.selfcare:id/iv_eicon')]")) {

				tou.press(PointOption.point(anchor, startPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
				.moveTo(PointOption.point(endPoint, startPoint)).release().perform();

			}			

			else if (swipeele.equalsIgnoreCase(
					"//android.widget.ImageView[contains(@resource-id, 'com.ooredoo.selfcare:id/ivIcon')]")) {

				Map<Integer, Integer> apicount = BanersApi("SelfcareAPI7.0"); // changed to 7.0

				for (Integer key : apicount.keySet()) {
					for (int i = 0; i < x.length; i++) {
						if (key == x[i]) {
							for (int j = 1; j <= apicount.get(key); j++) {

								// Thread.sleep(5000);
								System.out.println("HomePage Slide Image Size " + j + " ==>" + size);
								AppValidation("BannerDetails" + i);

							}

						}
					}
				}

			}

			/*----------------Entertainment------------------*/

			else if (swipeele.equalsIgnoreCase(
					"//android.widget.ImageView[contains(@resource-id,'com.ooredoo.selfcare:id/ivBanner')]")) {
				scrolltill("Entertainment", "Entertainment");
				List<MobileElement> serviceicon = driver.findElements(serviceicons);
				System.out.println("Services Count ==>" + serviceicon.size());
				MobileElement servicon = driver.findElement(serviceicons);
				Dimension ServiceDimension = servicon.getSize();
				System.out.println("Service Icon Image Size  ==>" + ServiceDimension);


			} else if (swipeele.equalsIgnoreCase("statetine")) {

				tou.press(PointOption.point(anchor, startPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
				.moveTo(PointOption.point(endPoint, startPoint)).release().perform();
			}
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void livetvdetails() throws Exception {

		scrolltill("LiveTvTitle", "Live TV");
		getResult1("pass");
		int liveiconssize = AppValidationImageList("conticons");
		System.out.println("liveiconssize ==> " + liveiconssize);
		ClickEvents("conticons");
		Thread.sleep(5000);
		getResult1("pass");
		logger.log(LogStatus.PASS, "Navigated to Live TV Section ");
		driver.navigate().back();

		//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/

		//List<String> livdetailsAPI =ETLiveTransApi("getlivetvchannels","promotions","1571803395775",obj.getProperty("apiversion"),"vasdetails",obj.getProperty("apiuri"),"title","");


		/*if(livdetailsAPI==null || livdetailsAPI.isEmpty()){
	System.out.println("Null/Empty returned from API");
}

int apicount = livdetailsAPI.size();

if(apicount!=liveiconssize) {
	logger.log(LogStatus.FAIL, "Test Case Failed due to mismatch in livetv count from api to app : Count from API Request is = "+apicount+  "  Count from App is = "+liveiconssize);
} else {
	logger.log(LogStatus.PASS, "Test Case Passed is LiveTVDetails "+livdetailsAPI);
}*/


		/*
		 * List<MobileElement> livetviconstitles = driver.findElements( By.xpath(
		 * "//android.widget.TextView[contains(@resource-id,'com.ooredoo.selfcare:id/title')]"
		 * ));
		 * 
		 * List<String> livelist = new ArrayList<>(); List<String> livedetails =
		 * AppValidationList("LiveTvDetxp");
		 * 
		 * livelist.addAll(livedetails); //livelist.add(livedetails);
		 * 
		 * if(livetviconstitles.size() >2) { swiperighticons(
		 * "//android.widget.ImageView[contains(@resource-id,'com.ooredoo.selfcare:id/icon')]"
		 * ,840.1,1000.1,60.1); List<String> livedetailss =
		 * AppValidationList("LiveTvDetxp"); livelist.addAll(livedetailss);
		 * 
		 * }
		 * 
		 * System.out.println("livelist after swipe right  ==> "+livelist); return
		 * livelist;
		 */
	}

	/*
	 * public void beforehomeIconss() {
	 * 
	 * List<MobileElement> BeforeHomeIcons = driver.findElements( By.xpath(
	 * "//android.widget.ImageView[contains(@resource-id,'com.ooredoo.selfcare:id/icon')]"
	 * )); System.out.println("Size of BeforeHomeIcons = " +
	 * BeforeHomeIcons.size()); for (MobileElement beforeHomeIcon : BeforeHomeIcons)
	 * { if (beforeHomeIcon.isDisplayed()) {
	 * System.out.println("Size of beforeHomeIcons =  " + beforeHomeIcon.getSize());
	 * System.out.println("GetText of BeforeHomeIcons" + beforeHomeIcon.getText());
	 * System.out.println("BeforeHomeIconcs = " + beforeHomeIcon); } }
	 * 
	 * }
	 * 
	 * public void bfrhmeIconsTitles() { List<MobileElement> BeforeHomeIconsTitles =
	 * driver.findElements( By.xpath(
	 * "//android.widget.TextView[contains(@resource-id,'com.ooredoo.selfcare:id/title')]"
	 * )); System.out.println("Size of BeforeHomeIconsTitles = " +
	 * BeforeHomeIconsTitles.size()); for (MobileElement bfrHmeTitles :
	 * BeforeHomeIconsTitles) { if (bfrHmeTitles.isDisplayed()) {
	 * System.out.println(bfrHmeTitles.getText()); } } }
	 * 
	 * public void befreHmetemplate() { List<MobileElement> bfrhometemplt =
	 * driver.findElements( By.xpath(
	 * "//android.widget.ImageView[contains(@resource-id,'com.ooredoo.selfcare:id/template_icon')]"
	 * )); System.out.println("Size of BeforeTemplate = " + bfrhometemplt.size());
	 * for (MobileElement bfrhmetemplte : bfrhometemplt) { if
	 * (bfrhmetemplte.isDisplayed()) { System.out.println("BeforeHomeTemplate  = " +
	 * bfrhmetemplte.getText());
	 * 
	 * } } }
	 * 
	 * public void BeforeHomePromo1() { for (int i = 0; i < 10; i++) { MobileElement
	 * promowithtxt = driver.findElement(By.id("com.ooredoo.selfcare:id/rl_addpromo"
	 * + i)); if (promowithtxt.isDisplayed()) { System.out.println("PromoIcons = " +
	 * promowithtxt.getText()); }
	 * 
	 * MobileElement onlypromo =
	 * driver.findElement(By.id("com.ooredoo.selfcare:id/iv_addpromo" + i)); if
	 * (onlypromo.isDisplayed()) { System.out.println("PromoIcons = " +
	 * onlypromo.getText()); }
	 * 
	 * MobileElement promotxt =
	 * driver.findElement(By.id("com.ooredoo.selfcare:id/tv_addpromo" + i)); if
	 * (promotxt.isDisplayed()) { System.out.println("PromoIcons = " +
	 * promotxt.getText()); }
	 * 
	 * } }
	 * 
	 */

	public List<String> Enternt() throws Exception {

		int d = AppValidationImageList("Entertmntbanners"); // app count 

		// API calling
		List<String> ETdetailsAPI = ETLiveTransApi("GetEntertainmentService", "promotions", "1571803395757", obj.getProperty("apiversion"), "vasdetails", obj.getProperty("apiuri"), "title", "","");
		System.out.println("ETdetailsAPI ==>" + ETdetailsAPI);
		if (ETdetailsAPI == null || ETdetailsAPI.isEmpty() || ETdetailsAPI.contains("No record found") ) {
			//System.out.println("Null Response from API!");
			logger.log(LogStatus.FAIL, "Test Case Failed as api response is  "+ETdetailsAPI);
		} 
		return ETdetailsAPI;



		/*int e = ETdetailsAPI.size();// api count

if (d != e) { // app not eq to api then swipe

swiperighticons("//android.widget.ImageView[contains(@resource-id,'com.ooredoo.selfcare:id/iv_eicon')]",889.1, 1420.1, 60.1);
AppValidationImageList("Entertmntbanners");

}

System.out.println("bb size ==>" + bb.size());

if ((bb.size()) - 1 != e) {				
	System.out.println("Integer.toString(e) ==> "+Integer.toString(e));
	return Integer.toString(e);

} else {
	System.out.println("ET Count Success");
return "ET Count Success";
	}
		 */
	}

	public void VipCheckDB() throws Exception {

		dbdetails("VipDrpdwn", "VipQuery");

		WebElement vipresp = Webdr.findElement(sesiontoken);
		String vipres = vipresp.getText();
		System.out.println("String Vipres ==> " + vipres);

		JSONObject jObject = new JSONObject(vipres);

		JSONArray jArr = jObject.getJSONObject("value").getJSONArray("bonuses");
		int bal = 0;
		for (int i = 0; i < jArr.length(); i++) {
			if (new JSONObject(jArr.get(i).toString()).get("bonus").equals("OMPoints")) {

				bal = bal + Integer.parseInt((String) new JSONObject(jArr.get(i).toString()).get("qtyBalance"));

			}
		}
		System.out.println("Api Vip ==> " + bal);

		String appvipbal = AppValidation("vipbalance");

		if (!appvipbal.equalsIgnoreCase(String.valueOf(bal))) {
			Assert.fail();
		} else {
			System.out.println("Vip Matched");
		}
	}

	public static String[] convert(Set<String> setOfString) 
	{ 

		// Create String[] of size of setOfString 
		String[] arrayOfString = new String[setOfString.size()]; 

		// Copy elements from set to string array 
		// using advanced for loop 
		int index = 0; 
		for (String str : setOfString) 
			arrayOfString[index++] = str; 

		// return the formed String[] 
		return arrayOfString; 
	} 

	public String getattribute(String pathattribute, String getname) {

		String PathChatt = obj.getProperty(pathattribute);

		MobileElement Attrib = driver.findElement(By.xpath(PathChatt));
		if (Attrib.isDisplayed()) {
			return Attrib.getAttribute(getname);
		} else {
			return "Something went wrong";
		}

	}

	public void TopUpApp(String TopUpTab, String Radio, String topuppin, String TopSendPin, String TopSubmit,
			String Sheetname,String msisdnxml) throws Exception {
		//System.out.println("TopUpAPP===========" + TopSendPin + " " + Sheetname);
		//Thread.sleep(5000);
		String mainvalanceBefore =  AppValidation("Main_Balance").replaceAll("[^a-zA-Z0-9]", "");
		int Beforebalance = Integer.parseInt(mainvalanceBefore);		
		ClickEvents("TopUpIcon");
		Thread.sleep(2000);
		//imagecompare("TopUp");
	
		
		 SendEvent("TopUp_MobileNo", msisdnxml);
		 Thread.sleep(2000);
		 String msisdn = AppValidation("TopUp_MobileNo");		
		System.out.println("msisdn voucher ==>" + msisdn);
		getResult1("pass");
		logger.log(LogStatus.PASS, "Top Up MSISDN : "+msisdn);			
		Thread.sleep(3000);
		if(Sheetname.equals("Voucher")) {
		//Thread.sleep(3000);
		// ScrollUp();
		//ClickEvents(TopUpTab);
		//String msisdn = AppValidation("contact");
		Thread.sleep(3000);
		//ClickEvents(Radio);
		//SendEvent(topuppin, mpinvalue(TopSendPin, Sheetname));
		SendEvent(topuppin, mpinvalue(TopSendPin, Sheetname));
		//if (getattribute("ConfirmButton", "clickable").equalsIgnoreCase("true")) {
		Thread.sleep(3000);
		getResult1("pass");
		logger.log(LogStatus.PASS, "Voucher Entered");
			ClickEvents(TopSubmit);
			Thread.sleep(3000);
			
				
				try {	
					String confirmtxt = driver.findElement(By.xpath("//android.view.View[@text = 'Are you sure you want to Topup the number "+msisdn+"']")).getText();
					System.out.println("confirmtxt = "+confirmtxt);
					if(confirmtxt.equalsIgnoreCase("Are you sure you want to Topup the number "+msisdn)){
						getResult1("pass");
						logger.log(LogStatus.PASS, ""+confirmtxt);
						List<MobileElement> list  = driver.findElements(By.xpath(obj.getProperty("TopUp_confirmBtn")));
						for (int i=0;i<list.size();i++){
							list.get(i).click();
							}						
						Thread.sleep(3000);
						getResult1("pass");
						if(driver.findElements(By.xpath(obj.getProperty("invalidvoucher"))).size()!=0) {
							if(driver.findElement(By.xpath(obj.getProperty("invalidvoucher"))).getText().equals("Please enter a valid PIN to top up your account.")) {
								logger.log(LogStatus.PASS, "Please enter a valid PIN to top up your account.");
								//System.out.println("invalidvoucher");						
								ClickEvents("TopUp_OKButton");
							} else {
								logger.log(LogStatus.FAIL, "Please enter a valid PIN to top up your account. - Text Mismatch");
							}
						} else {
							logger.log(LogStatus.PASS, "Top Up success");
							if(driver.findElements(By.xpath(obj.getProperty("CongratSuccessTopUp"))).size()!=0) {
								if(AppValidation("CongratSuccessTopUp").equals("Congratulations! Your Top Up is successful")) {
									logger.log(LogStatus.PASS, "Congratulations! Your Top Up is successful");								
									getResult1("pass");								
										if(AppValidation("playlanterngame").equals("Play lucky lantern game and win Millions of Bonus")) {
											logger.log(LogStatus.PASS, "Play lucky lantern game and win Millions of Bonus");
											getResult1("pass");	
												if(driver.findElements(By.xpath(obj.getProperty("Playnowbutton"))).size()!=0) {
													logger.log(LogStatus.PASS, "Play now button is displayed");
														} else {
															logger.log(LogStatus.FAIL, "Failed to dislpay Play now button");
														}										
												} else {
													logger.log(LogStatus.FAIL, "Lantern game Text Message not Displayed");
													}
								//if(driver.findElements(By.xpath(obj.getProperty("CongratsOtherpacks"))).size()!=0) {
									//ClickEvents("CongratsOtherpacks");
									//Thread.sleep(3000);
									//getResult1("pass");
									//logger.log(LogStatus.PASS, "Redirected to Packs Page");
									ClickEvents("homebtton");				
									ScrollUp();
									Thread.sleep(3000);
									getResult1("pass");
									String mainvalanceAfter =  AppValidation("Main_Balance").replaceAll("[^a-zA-Z0-9]", "");
									int Afterbalance = Integer.parseInt(mainvalanceAfter);		
									int FinalBalance= Afterbalance - Beforebalance;	
										if(FinalBalance!=0) {
											logger.log(LogStatus.INFO, "Before Top Up Balance : "+Beforebalance);
											logger.log(LogStatus.INFO, "After Top Up Balance : "+Afterbalance);		
											logger.log(LogStatus.PASS, "Updated Balance : "+FinalBalance);
										} else {
											logger.log(LogStatus.FAIL, "Updated Balance : "+FinalBalance);
										}
									
								} else {
									getResult1("fail");
									logger.log(LogStatus.FAIL, "Other Packs Text Not Displayed");
									}
								  			
							} else {
								logger.log(LogStatus.FAIL, "M-Pitesan Top Up Fail / Something went wrong / No Balance to Top Up");
								}
							
						// clear	
							
							}	
						}
					
					 else {
						getResult1("fail");
						logger.log(LogStatus.FAIL, "Are you sure you want to Topup the number - Text Mismatched ");
						}
					
				
					//driver.findElement(By.xpath("//android.widget.Button[@text = 'CONFIRM']")).click();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.log(LogStatus.FAIL, "Exception occured: "+e.getMessage());
					e.printStackTrace();
				}
		}
		

		//--------------- END --------------- //
		
		
		
				
				//safeJavaScriptClick("TopUp_confirmBtn");
				//UiSelectorClick("CONFIRM");
							
			
			//System.out.println("Result ==> " + result.get(0));
			//if (result.get(0).trim().equalsIgnoreCase("Are you sure you want to Top Up this mobile number "+msisdn.trim()+"?")) {
			//if (result.get(0).trim().contains("Are you sure you want to Topup the number "+msisdn.trim())) {
				//System.out.println("============IF Inside===================");
				//ClickEvents("buynowok");
				/*if(TopSendPin.equalsIgnoreCase("Valid")) {
					String validcasemesg = AppValidation("buypkpopmesg");
					if(validcasemesg.equals("Congratulations! Your Top Up is successful")) {
						logger.log(LogStatus.PASS, "TopUp Voucher passed for "+TopSendPin+ " case : " +validcasemesg);
						ClickEvents("SuccessClose");
					} else {
						logger.log(LogStatus.FAIL, "TopUp Voucher Failed for "+TopSendPin+ " case due to : " +validcasemesg);
						getResult1("fail");
					}
				}*/

				//List<String> APITopUpDesc = ETLiveTransApi("TopUpMyNumber", "topup", "1573035302761", "SelfcareAPI7.0","status_desc", "ecarepreprod", "", TopSendPin);

				//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/
			/*	List<String> APITopUpDesc = ETLiveTransApi("TopUpMyNumber", "topup", "1573035302761", obj.getProperty("apiversion"),"status_desc", obj.getProperty("apiuri"), "", TopSendPin,"");
				System.out.println("APITopUpDesc ==> " + APITopUpDesc);
				String topupcheckvoicherapp = AppValidationList("buypkpopmesg").get(0).trim().replaceAll("[^a-zA-Z0-9_-]","");
				String topvoucherapi = APITopUpDesc.get(0).trim().replaceAll("[^a-zA-Z0-9_-]", "").toString();
				//Assert.assertEquals(topupcheckvoicher,APITopUpDesc.get(0).trim().replaceAll("[^a-zA-Z0-9_-]", ""));
				boolean vuchervalid = topupcheckvoicherapp.equalsIgnoreCase(topvoucherapi);
				if(vuchervalid) {
					logger.log(LogStatus.PASS, "TopUp Voucher functionality pass for "+TopSendPin+" case");	
					getResult1("pass");
				} else {
					logger.log(LogStatus.FAIL, "TopUp Voucher functionality Failed for "+TopSendPin+" case");	
					logger.log(LogStatus.FAIL, "APP Response = "+topupcheckvoicherapp);	
					logger.log(LogStatus.PASS, "API Response = "+topvoucherapi);	
				}
				ClickEvents("buynowok");
				driver.findElement(By.xpath(obj.getProperty("topuppin"))).clear();
				//return topupcheckvoicher;
			//}
			else if (result.get(0).trim().equalsIgnoreCase("Please enter a valid Top-up PIN to proceed")) {
				ClickEvents("buynowok");
				System.out.println("Please Enter Valid Pin");
				//return result.get(0).trim();
			}*/

			// -------------------- MPitesan --------------------

		
		//Thread.sleep(5000);
		//System.out.println("Before 1000");
		
		else if (Sheetname.equals("MPite")) {
			if(driver.findElements(By.xpath(obj.getProperty("TopUp_MPitesanOption"))).size()!=0) {
			ClickEvents("TopUp_MPitesanOption");
			logger.log(LogStatus.FAIL, "Clicked on MPitesan Button");
			Thread.sleep(3000);
			getResult1("pass");			
			if(driver.findElements(By.xpath(obj.getProperty("TopUpokbutton"))).size()!=0) {
				ClickEvents("TopUpokbutton");
			} else {
				logger.log(LogStatus.FAIL, "Please select amount to continue - PopUp not displayed");				
			}
			
		} 
		//
		//System.out.println("Not clicked and disabled");
		Thread.sleep(3000);
		SendEvent("TopUp_Enter_Amount", "51000");
		Thread.sleep(1000);
		//System.out.println("---------->>>>"+driver.getPageSource());
		if(driver.findElements(By.xpath(obj.getProperty("betweenamount"))).size()!=0) {
			if(driver.findElement(By.xpath(obj.getProperty("betweenamount"))).getText().equals("Please select Topup amount in between of 500 Ks to 50,000 Ks")) {
				logger.log(LogStatus.PASS, "Entered amount is more than 50,000 Ks");
				logger.log(LogStatus.PASS, "Please select Topup amount in between of 500 Ks to 50,000 Ks");
				getResult1("pass");
				ClickEvents("TopUpokbutton");
			} else {
				logger.log(LogStatus.FAIL, "Please select Topup amount in between of 500 Ks to 50,000 Ks - Text Mismatch");
				getResult1("fail");
			}
		}
		
		Thread.sleep(2000);		
		SendEvent("TopUp_Enter_Amount", "1000");	
		
		List<MobileElement> oCheckBox = driver.findElements(By.className("android.widget.RadioButton"));
		 int iSize = oCheckBox.size();
		 System.out.println(oCheckBox);
		 if(iSize!=0) {
		 for(int i=0; i < iSize ; i++ ){
			 String sValue = oCheckBox.get(i).getText();
			 System.out.println(sValue);
		 	} 
		 } else {
			 logger.log(LogStatus.FAIL, "Suggested Amounts are not displayed");
			 }
			
		ClickEvents("TopUp_MPitesanOption");
		Thread.sleep(3000);
		//System.out.println(driver.getPageSource());
		getResult1("pass");
		SendEvent("EnterMpitesan", "9963996666");		
		SendEvent("EnterMpitesanPassword", "7073");
		ClickEvents("confirmMpitesan");
		Thread.sleep(5000);
		
		//if(driver.findElements(By.xpath("//android.view.View[@text = 'Are you sure you want to top up the mobile number 0"+msisdnxml+" with 1,000 Ks?']")).size()!=0) {
		String validatemesg = driver.findElement(By.xpath("//android.view.View[@text = 'Are you sure you want to top up the mobile number 0"+msisdnxml+" with 1,000 Ks?']")).getText();
		if(validatemesg.equals("Are you sure you want to top up the mobile number 0"+msisdnxml+" with 1,000 Ks?"))
		{
			logger.log(LogStatus.PASS, "Are you sure you want to top up the mobile number 0"+msisdnxml+" with 1,000 Ks?");
			getResult1("pass");
			ClickEvents("TopUp_confirmBtn");
			Thread.sleep(10000);
			
			if(driver.findElements(By.xpath(obj.getProperty("CongratSuccessTopUp"))).size()!=0) {
				if(AppValidation("CongratSuccessTopUp").equals("Congratulations! Your Top Up is successful")) {
				logger.log(LogStatus.PASS, "Congratulations! Your Top Up is successful");				
				getResult1("pass");
				
				if(AppValidation("playlanterngame").equals("Play lucky lantern game and win Millions of Bonus")) {
					logger.log(LogStatus.PASS, "Play lucky lantern game and win Millions of Bonus");
					getResult1("pass");	
						if(driver.findElements(By.xpath(obj.getProperty("Playnowbutton"))).size()!=0) {
							logger.log(LogStatus.PASS, "Play now button is displayed");
								} else {
									logger.log(LogStatus.FAIL, "Failed to display Play now button");
								}										
						} else {
							logger.log(LogStatus.FAIL, "Lantern game Text Message not Displayed");
							}
				
			// Commented for 8.1 lantern game	
				 
				/*if(AppValidation("referefrndMesg").equals("Refer a friend to My Ooredoo App and get 50 VIP points.")) {
					logger.log(LogStatus.PASS, "Refer a friend to My Ooredoo App and get 50 VIP points.");
					getResult1("pass");			
				} else {
					getResult1("fail");
					logger.log(LogStatus.FAIL, "Refer a friend Text Message not Displayed / Mismatched");
				}	*/
				
				List<MobileElement> Buypacks = driver.findElements(By.xpath(obj.getProperty("CongratsBuyNow")));
				 int SizeBuypacks = Buypacks.size();
				 System.out.println(SizeBuypacks);
				 if(SizeBuypacks==2) {
					 getResult1("pass");
					 logger.log(LogStatus.PASS, "Number of Packs available :"+SizeBuypacks);	
					 for(int buy=0; buy < SizeBuypacks ; buy++ ){						 
							 Buypacks.get(buy).click();
							 break;
						 }
						  Thread.sleep(3000);
						  getResult1("pass");
						  System.out.println("-----------------------------");
						 // System.out.println(driver.getPageSource());
						  Thread.sleep(3000);
						  ClickEvents("TopUp_confirmBtn");
						  System.out.println("-----------------------------");
						  Thread.sleep(3000);
						  getResult1("pass");
						  //System.out.println(driver.getPageSource());
					 }
				 
				 } else {
					 getResult1("fail");
					 logger.log(LogStatus.FAIL, "More/Less Packs are configured on Success TopUp Page");
				 }	
				
				ClickEvents("homebtton");				
				ScrollUp();
				Thread.sleep(3000);
				getResult1("pass");
				String mainvalanceAfter =  AppValidation("Main_Balance").replaceAll("[^a-zA-Z0-9]", "");
				int Afterbalance = Integer.parseInt(mainvalanceAfter);		
				int FinalBalance= Afterbalance - Beforebalance;	
				if(FinalBalance!=0) {
					logger.log(LogStatus.INFO, "Before Top Up Balance : "+Beforebalance);
					logger.log(LogStatus.INFO, "After Top Up Balance : "+Afterbalance);		
					logger.log(LogStatus.PASS, "Updated Balance : "+FinalBalance);
				} else {
					logger.log(LogStatus.FAIL, "Updated Balance : "+FinalBalance);
				}
				
				
			}  else {
				getResult1("fail");
				logger.log(LogStatus.FAIL, "M-Pitesan Top Up Fail / Something went wrong / No Balance to Top Up");
				}			
			} else {				
				if(AppValidation("MptesanStatus").equals("Entered mpitesan number in not registered or allowed for this transaction")) {
					getResult1("pass");
					logger.log(LogStatus.PASS, "Entered mpitesan number in not registered or allowed for this transaction");
					ClickEvents("TopUpokbutton");
				} else {
					getResult1("fail");
					logger.log(LogStatus.FAIL, "Text MisMatched for Entered mpitesan number in not registered ");
				}
			}		
		} else {
			getResult1("fail");
			logger.log(LogStatus.FAIL, "Confirmation Popup Text Mismatched");
		}
		
		
		ClickEvents("homebtton");				
		ScrollUp();
		Thread.sleep(3000);
		getResult1("pass");
		String mainvalanceAfter =  AppValidation("Main_Balance").replaceAll("[^a-zA-Z0-9]", "");
		int Afterbalance = Integer.parseInt(mainvalanceAfter);		
		int FinalBalance= Afterbalance - Beforebalance;		
		logger.log(LogStatus.INFO, "Updated Balance : "+FinalBalance);
		
		
		
		/*List<String> result = AppValidationList("buypkpopmesg");
		
			if (result.get(0).trim().equalsIgnoreCase("Please enter your M-Pitesan account details to complete the top up of 1,000 Ks..")) {

				System.out.println("Entered Ptesan");
				SendEvent("enterrlnum", "9973331609");
				SendEvent("entermpin", "1312");
				ClickEvents("Proceed");

				if (AppValidationList("buypkpopmesg").get(0).trim().equalsIgnoreCase("")) {

					ClickEvents("buynowok");

					String appmpite = AppValidationList("buypkpopmesg").get(0).trim();
					//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/
					//String apimpite = ETLiveTransApi("MpitesenPayment", "payment", "1575019739507", obj.getProperty("apiversion"),"status_desc", obj.getProperty("apiuri"), "", TopSendPin).toString();
					List<String> apimpite = ETLiveTransApi("MpitesenPayment", "payment", "1575019739507", obj.getProperty("apiversion"),"status_desc", obj.getProperty("apiuri"), "", TopSendPin, "");
					//Assert.assertEquals(appmpite,apimpite);

					boolean validatempite = appmpite.equalsIgnoreCase(apimpite.get(0));

					if(validatempite) {
						logger.log(LogStatus.PASS, "TopUpMPitesan functionality pass");	
						getResult1("pass");
					} else {
						logger.log(LogStatus.FAIL, "TopUpMPitesan functionality Failed");	
						logger.log(LogStatus.FAIL, "TopUpMPitesan APP Value = "+appmpite);	
						logger.log(LogStatus.FAIL, "TopUpMPitesan API Value = "+apimpite);	
					}

					//Assert.assertEquals(AppValidationList("buypkpopmesg").get(0).trim(),
					//s	ETLiveTransApi("MpitesenPayment", "payment", "1575019739507", "SelfcareAPI7.0","status_desc", "ecarepreprod", "", TopSendPin));

					ClickEvents("Ok_button");
					//return result.get(0).trim();

				} else if (AppValidationList("buypkpopmesg").get(0).trim().equalsIgnoreCase("Please enter a valid Mobile Number")) {
					logger.log(LogStatus.FAIL, "TopUpMPitesan Failed due to : "+AppValidationList("buypkpopmesg").get(0));
					getResult1("fail");
					ClickEvents("Ok_button");
					ClickEvents("closeicon");
				}


				else {
					System.out.println("Something happend at Recharge level");
				}

			}*/

		//}

		//return "Enter Valid Details !";
		//}
	}

	public void MatchDataPacks(String mismatchdata) throws Exception {
		//logger = extent.startTest("MatchDataPacks", "Description to MatchDataPacks");

		List<String> datapacksTitle = PacksDetails("tvtitle");
		ArrayList<String> buypk = new ArrayList<String>();

		int buysize = datapacksTitle.size();
		do {

			// Title
			datapacksTitle = PacksDetails("tvtitle");
			buypk.addAll(datapacksTitle);
			// Validity
			List<String> buypkdatavalidity = PacksDetails("buypkdatavalidity");
			// Buy
			List<String> buypackBuy = PacksDetails("buypackBuy");
			// Gift
			List<String> buypackGift = PacksDetails("buypackGift");
			swipeByElements();// --uday
			//Thread.sleep(3000);
			buysize = buysize + 1;
			System.out.println("Validation in Process ==>" + buysize);
		}
		while (buysize <= 6);
		System.out.println("buypk Titiless==>"+buypk);
		Set<String> set = new HashSet<String>(buypk);
		set.addAll(buypk);
		System.out.println("Set Values after converting ==>"+set);
		Set<String> setFinal = new HashSet<>();
		//String val = "hi hello prince";
		for(String str : set){

			//setFinal.add(str.replace(" ", "").replace(",", ""));
			setFinal.add(str);
		}
		//System.out.println("setFinal =="+setFinal);


		System.out.println("Remove Duplicates ==>" + setFinal);
		// offsetnameid == db validation
		Map<String, String> offersFromDb = offsetnameid();

		System.out.println("DbDataPacks ==>" + offersFromDb.keySet());

		// ArrayList<String> list = convertSetToList(s);

		//System.out.println("Set Values ==> " + setFinal);


		boolean DataPacksName = offersFromDb.keySet().equals(setFinal);

		System.out.println("DataPacksName ==>" + DataPacksName);
		// offDb.keySet().retainAll(s);

		// System.out.println("DataPacks Retained Values
		// ==>"+offDb.keySet().retainAll(s));
		Webdr.findElement(logout).click();
		// Webdr.close();
		//setOffers();
		// System.out.println("apioffid.get(0) ==> "+apioffid.get(0));
		Set<String> cloneObj = new HashSet<>();
		cloneObj.addAll(setFinal);

		for(String key : offersFromDb.keySet()) {
			for(String key1 : setFinal) {
				if(key.equalsIgnoreCase(key1)) {
					cloneObj.remove(key1);
				}
			}
		}
		//logger.log(LogStatus.INFO, "MisMatched Data = "+cloneObj);

		if (DataPacksName) {
			// Assert.fail();
			logger.log(LogStatus.PASS, "Test Case Passed is DataPackTitle  Matched");
			getResult1("pass");

		} else {

			//System.out.println("Failed to Match Data Packs from DB Values to APP Values");
			logger.log(LogStatus.FAIL, "Failed to Match Data Packs from DB Values to APP Values for = "+mismatchdata);
			logger.log(LogStatus.FAIL, "Db Data Packs = "+offersFromDb.keySet());
			logger.log(LogStatus.FAIL, "App Data Packs = "+setFinal);
			logger.log(LogStatus.FAIL, "MisMatched Data = "+cloneObj);


		}
	}

	public boolean bygft(String buygiftoption, String SheetKey) throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String BeforeksBalance = AppValidation("HOME_USER_POINTS").replaceAll("[^0-9]", "").replaceAll("[^a-zA-Z0-9]", "");
		System.out.println("BeforeksBalance ==> " + BeforeksBalance);

		// TODO Auto-generated catch block

		//ClickEvents("Buypackicon");
		ClickEvents("PacksIcon");

		String[] bpivr = new String[] {"bpinternet","tinekya"};//{"bpinternet","tinekya", "bpvoice", "bpRoam"}; //"bpinternet","tinekya", "bpvoice", "bpRoam",
		String[] bpdcfx = new String[] {"bpdata"};//, "bpcombo", "bpfacebook"};// "bpdata", "bpcombo", "bpfacebook"
		//String[] bponanin = new String[] {"bponNetPk"};//, "bpAnyNetPk","bpInternational"};//"bponNetPk", "bpAnyNetPk","bpInternational"
		//String[] bptsiirq = new String[] {"bprmthai"};//, "bprmsinga", "bprmIndia", "bprmIndone"};//"bprmthai", "bprmsinga", "bprmIndia", "bprmIndone", "bprmRussia","bprmQatar"
		/*int bpinternetbuy = 0;
		int bpinternetgift = 0;
		int bpvoicebuy = 0;
		int bpvoicegift = 0;
		int bpRoamBuy = 0;
		int bpRoamGift = 0;
		int istinekyatopupbuy = 0;
		int istinekyatopupgift = 0;*/

		try {
			for (int pkIVR = 0; pkIVR < bpivr.length; pkIVR++) {

				ClickEvents(bpivr[pkIVR]);
				if (bpivr[pkIVR].contains("tinekya")) {
					//logger = extent.startTest("tinekya", "Description to State Tine Kya");
					Thread.sleep(1000);
					//swiperighticons("statetine", 810, 996, 180);					
					Swipe(1, "statetine1",150);					
					Thread.sleep(1000);
					//swiperighticons("statetine", 826, 1236, 180);
					Swipe(1, "statetine2",150);
					Thread.sleep(1000);
					//swiperighticons("statetine", 825, 1501, 180);
					Swipe(1, "statetine3",150);					
					//Thread.sleep(2000);
					String Validity = AppValidation("Validity");
					System.out.println("Validity ==>" + Validity);
					//Thread.sleep(2000);
					String PackPrice = AppValidation("packprice");
					System.out.println("packprice ==>" + PackPrice + " Ks");
					//Thread.sleep(2000);
					//swipeByElements();
					String SavedPrice = AppValidation("savedprice");
					System.out.println("savedprice ==>" + SavedPrice + " Ks");
					//Thread.sleep(2000);
					String SavedPercentage = AppValidation("savedperc");
					System.out.println("savedperc ==>" + SavedPercentage);
					swipeByElements();

					//ClickEvents("giftproceed");

					/*	try {
	if(AppValidation("buynowok").equalsIgnoreCase("Buy Now")) {
		ClickEvents("toppopcancel");
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}*/
					if (buygiftoption.equalsIgnoreCase("buypackBuy")) {

						ClickEvents("Jobs_Subscribe_now_button");

					} else if (buygiftoption.equalsIgnoreCase("buypackGift")) {

						ClickEvents("buypackGift");
						//ClickEvents("contactpckt");
						//ClickEvents("searchmenu");
						//SendEvent("searchsrctxt", "Test");
						//ClickEvents("contactName");
						SendEvent("bpartynum", "9953366292");
						ClickEvents("giftproceed");
						if (AppValidation("subscribe_confirm_pop_up1").equalsIgnoreCase("The number which you have selected is not valid. Please select other number")) {
							ClickEvents("Subscribe_button");
							SendEvent("bpartynum", "9953366291");
							ClickEvents("giftproceed");

						}
					}

					String Ore2Ore = AppValidation("Or2Or");					
					String Ore2OreValue = AppValidation("Or2OrValue");
					logger.log(LogStatus.INFO, Ore2Ore +"="+Ore2OreValue );

					String Ore2Oth =AppValidation("Ore2Oth");
					String Ore2OthValue =AppValidation("Ore2OthValue");
					logger.log(LogStatus.INFO, Ore2Oth +"="+Ore2OthValue );

					String Data =AppValidation("Data");
					String DataValue =AppValidation("DataValue");
					logger.log(LogStatus.INFO, Data +"="+DataValue );

					String ValidityConfirm =AppValidation("ValidityConfirm");
					String ValidityValue =AppValidation("ValidityValue");
					logger.log(LogStatus.INFO, ValidityConfirm +"="+ValidityValue );

					String Savings =AppValidation("Savings");
					String SavingsValue =AppValidation("SavingsValue");
					logger.log(LogStatus.INFO, Savings +"="+SavingsValue );


					String Total =AppValidation("Total");
					String TotalValue =AppValidation("TotalValue");
					logger.log(LogStatus.INFO, Total +"="+TotalValue );

					getResult1("pass");
					ClickEvents("Confirm");
					// String totalvalue = AppValidation("totalvalue").replaceAll("[^0-9]", "");
					Thread.sleep(5000);

					String SorryPage = AppValidation("subscribe_confirm_pop_up1");

					if (SorryPage.equalsIgnoreCase("Sorry, the request failed due to low balance. Please topup and try again")) {
						ClickEvents("buynowok");
						getResult1("pass");
					} else if(SorryPage.equalsIgnoreCase("Your request is under progressing. You will receive a confirmation message shortly.")) {
						ClickEvents("buynowok");
						getResult1("pass");
					}
					else if(SorryPage.equalsIgnoreCase("Sorry! We are unable to process your request. Please try again.")) {
						ClickEvents("buynowok");
						getResult1("pass");

					}
					//boolean topupbyop = AppValidation("TopUpText").equalsIgnoreCase("Topup");
					/*//if(topupbyop) {
	logger.log(LogStatus.PASS, "State Tine Kya Reached to Top Up Page ");
	getResult1("pass");

} else {
	logger.log(LogStatus.FAIL, "State Tine Kya Failed to Reach Top Up Page");
}*/
					//istinekyatopupbuy++;
					//ClickEvents("Buypackicon");
					ClickEvents("PacksIcon");
					ClickEvents("tinekya");
					//Assert.assertTrue(topupbyop);



					/*//
istinekyatopupgift++;								
if(istinekyatopupgift == 1) {
	logger.log(LogStatus.PASS, "State Tine Kya (Gift Functionality) Reached to Top Up Page");
}else {
	logger.log(LogStatus.FAIL, "State Tine Kya (Gift Functionality) Failed to Reach Top Up Page");
}	*/				


				}

				/* ------------------------------- Internet Packs ---------------------- */

				else if (bpivr[pkIVR].trim().contains("bpinternet")) {
					for (int intdcfx = 0; intdcfx < bpdcfx.length; intdcfx++) {
						ClickEvents(bpdcfx[intdcfx]);
						//logger = extent.startTest(bpdcfx[intdcfx], "Description to ="+bpdcfx[intdcfx]);
						dbdetails("Dropdown2", "datapakquery" + intdcfx);
						MatchDataPacks(bpdcfx[intdcfx]);							
						ScrollUp();

						if (buygiftoption.trim().equalsIgnoreCase("buypackBuy")) {

							String appbuypack = BuyGftSuccess(bpdcfx[intdcfx], bpivr[pkIVR], buygiftoption, SheetKey,BeforeksBalance);

							String buypackapi = ETLiveTransApi("SetOffer", "OfferActivation", "1571725474863",obj.getProperty("apiversion"), "status_desc", obj.getProperty("apiuri"), "", "","").get(0);						
							//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/

							boolean buysuccess = appbuypack.equalsIgnoreCase(buypackapi);
							System.out.println("bpdcfx[intdcfx] = "+bpdcfx[intdcfx]);
							System.out.println("bpivr[pkIVR] = "+bpivr[pkIVR]);
							String rslt = BuyGftSuccess(bpdcfx[intdcfx], bpivr[pkIVR], buygiftoption, "Valid",BeforeksBalance);
							if(rslt.isEmpty()) {
								logger.log(LogStatus.FAIL, "failed is Internet Buy for = " +bpdcfx[intdcfx]);
							} else {
								logger.log(LogStatus.PASS, "Passed is Internet Buy for = " +bpdcfx[intdcfx]);
							}

							/*boolean buysuccess = BuyGftSuccess(bpdcfx[intdcfx], bpivr[pkIVR], buygiftoption, "Valid")
.equalsIgnoreCase(ETLiveTransApi("SetOffer", "OfferActivation", "1571725474863",
		"SelfcareAPI7.0", "status_desc", "ecarepreprod", "", "").get(0));
							 */
							//Assert.assertTrue(buysuccess);

							if(buysuccess) {
								logger.log(LogStatus.PASS, "Passed is Internet Buy for = " +bpdcfx[intdcfx]);
								//System.out.println("Internet Buy Matched for  = "+bpdcfx[intdcfx]);
							}else {
								logger.log(LogStatus.FAIL, "Test Case FAIL is Internet Buy for =  "+bpdcfx[intdcfx]);
								logger.log(LogStatus.FAIL, "Failed due to Mismatch with API Response and APP Message");
								logger.log(LogStatus.FAIL, "API Response = "+buypackapi);
								logger.log(LogStatus.FAIL, "APP POP UP MESSAGE = "+appbuypack);

							}


							//logger.log(LogStatus.PASS, "Test Case Passed is Internet Buy" +bpinternetbuy++ +"  "+bpdcfx[intdcfx]);



							//logger.log(LogStatus.FAIL, "Test Case (Internet Buy) Status is passed");


						} else if (buygiftoption.trim().equalsIgnoreCase("buypackGift")) { 
							System.out.println("in buy gift");

							String buygftapp = BuyGftSuccess(bpdcfx[intdcfx], bpivr[pkIVR], buygiftoption, SheetKey,BeforeksBalance).trim();
							//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/
							String buygftapi = ETLiveTransApi("GiftPackOffer", "GiftPack", "1571725474863",obj.getProperty("apiversion"), "status_desc", obj.getProperty("apiuri"), "", SheetKey,"").get(0);

							boolean giftcheck = buygftapp.equalsIgnoreCase(buygftapi);
							/*
boolean giftcheck = BuyGftSuccess(bpdcfx[intdcfx], bpivr[pkIVR], buygiftoption, SheetKey)
		.trim()
		.equalsIgnoreCase(ETLiveTransApi("GiftPackOffer", "GiftPack", "1571725474863",
				"SelfcareAPI7.0", "status_desc", "ecarepreprod", "", SheetKey).get(0));*/

							//bpinternetgift++;

							if(giftcheck) {
								logger.log(LogStatus.PASS, "Internet Gift Functionality Pass for = "+bpdcfx[intdcfx]);
							} else {
								//logger.log(LogStatus.FAIL, "Internet Gift  failed");
								logger.log(LogStatus.FAIL, "Internet Gift  failed ="+bpdcfx[intdcfx] +" - "+bpivr[pkIVR]);
								logger.log(LogStatus.FAIL, "API Response = "+buygftapi);
								logger.log(LogStatus.FAIL, "APP POP UP MESSAGE = "+buygftapp);
							}

						}
					}

				}

				/* ------------------------------- Voice Packs ---------------------- */

				/*else if (bpivr[pkIVR].trim().contains("bpvoice")) {
					for (int r = 0; r < bponanin.length; r++) {
						//ClickEvents(bponanin[r-1]);
						ClickEvents(bponanin[r]);
						//dbdetails("Dropdown2", "VoicePakQuery" + r);
						//MatchDataPacks(bponanin[r]);
						//ScrollUp();

						if (buygiftoption.trim().equalsIgnoreCase("buypackBuy")) {

							String buyvoiceapp = BuyGftSuccess(bponanin[r], bpivr[pkIVR], buygiftoption,SheetKey,BeforeksBalance).trim();
							//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/
							String buyvoiceapi = ETLiveTransApi("SetOffer", "OfferActivation", "1571725474863",obj.getProperty("apiversion"), "status_desc", obj.getProperty("apiuri"), "", "","").get(0);

							boolean buyvoicecheck = buyvoiceapp.equalsIgnoreCase(buyvoiceapi);

							boolean buyvoicecheck = BuyGftSuccess(bponanin[r], bpivr[pkIVR], buygiftoption, "Valid")
.equalsIgnoreCase(ETLiveTransApi("SetOffer", "OfferActivation", "1571725474863",
		"SelfcareAPI7.0", "status_desc", "ecarepreprod", "", "").get(0));

							//Assert.assertTrue(buyvoicecheck);
							// Assert.fail();
							bpvoicebuy++;
							if(buyvoicecheck) {								

								logger.log(LogStatus.PASS, "Buy Functionality Passed is for  = "+bponanin[r]);
							} else {
								//logger.log(LogStatus.FAIL, "Test Case Failed for Voice-Buy");
								logger.log(LogStatus.FAIL, "Internet Voice Buy  failed = "+bponanin[r]+" - "+ bpivr[pkIVR]);								
								logger.log(LogStatus.FAIL, "API Response = "+buyvoiceapi);
								logger.log(LogStatus.FAIL, "APP POP UP MESSAGE = "+buyvoiceapp);


							}

						} else if (buygiftoption.trim().equalsIgnoreCase("buypackGift")) {

							String app = BuyGftSuccess(bponanin[r], bpivr[pkIVR], buygiftoption, SheetKey,BeforeksBalance).trim();

							//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/
							String api = ETLiveTransApi("GiftPackOffer", "GiftPack", "1571725474863",obj.getProperty("apiversion"), "status_desc", obj.getProperty("apiuri"), "", SheetKey,"").get(0);


							boolean giftvoicecheck = app.equalsIgnoreCase(api);

							boolean giftvoicecheck = BuyGftSuccess(bpdcfx[r], bpivr[pkIVR], buygiftoption, SheetKey)
.trim().equalsIgnoreCase(ETLiveTransApi("GiftPackOffer", "GiftPack", "1571725474863",
		"SelfcareAPI7.0", "status_desc", "ecarepreprod", "", SheetKey).get(0));
							bpvoicegift++;

							if(giftvoicecheck)
							{
								logger.log(LogStatus.PASS, "Gift Functionality Passed is for ="+bponanin[r]);
							} else {
								//logger.log(LogStatus.FAIL, "Test case Failed");
								logger.log(LogStatus.FAIL, "Internet failed for = "+bponanin[r]+" - "+ bpivr[pkIVR]);
								logger.log(LogStatus.FAIL, "API Response = "+api);
								logger.log(LogStatus.FAIL, "APP POP UP MESSAGE = "+app);
							}




						}
					}
				}

				 ------------------------------- Roaming Packs ---------------------- 

				else if (bpivr[pkIVR].contains("bpRoam")) {

					for (int t = 0; t < bptsiirq.length; t++) {
						ClickEvents(bptsiirq[t]);
						dbdetails("Dropdown2", "RoamPakQuery" + t);
						MatchDataPacks(bptsiirq[t]);
						ScrollUp();


						if (buygiftoption.trim().equalsIgnoreCase("buypackBuy")) {

							String RoamApp = BuyGftSuccess(bptsiirq[t], bpivr[pkIVR], buygiftoption, SheetKey,BeforeksBalance).trim();
							//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/
							String RoamApi = ETLiveTransApi("SetOffer", "OfferActivation", "1571725474863", obj.getProperty("apiversion"), "status_desc", obj.getProperty("apiuri"), "", "","").get(0);

							boolean roambuycheck = RoamApp.equalsIgnoreCase(RoamApi);

								boolean roambuycheck = BuyGftSuccess(bptsiirq[t], bpivr[pkIVR], buygiftoption, "Valid")
.equalsIgnoreCase(ETLiveTransApi("SetOffer", "OfferActivation", "1571725474863",
		"SelfcareAPI7.0", "status_desc", "ecarepreprod", "", "").get(0));
							bpRoamBuy++;
							if(roambuycheck) {
								logger.log(LogStatus.PASS, "Buy Functionality Passed is for = "+bptsiirq[t]);
							} else {
								logger.log(LogStatus.FAIL, "Test Case Failed for Roam-Buy");
								logger.log(LogStatus.FAIL, "Internet failed for = "+bptsiirq[t] +" - "+ bpivr[pkIVR]);
								logger.log(LogStatus.FAIL, "API Response = "+RoamApi);
								logger.log(LogStatus.FAIL, "APP POP UP MESSAGE = "+RoamApp);
							}



						} else if (buygiftoption.trim().equalsIgnoreCase("buypackGift")) {

							String RoamGiftApp = BuyGftSuccess(bptsiirq[t], bpivr[pkIVR], buygiftoption, SheetKey,BeforeksBalance).trim();
							//ecarepreprod.ooredoo.com.mm/selfcareapistg7.1/api/
							String RoamGiftApi = ETLiveTransApi("GiftPackOffer", "GiftPack", "1571725474863",obj.getProperty("apiversion"), "status_desc", obj.getProperty("apiuri"), "", SheetKey,"").get(0);

							boolean roamgiftcheck = RoamGiftApp.equalsIgnoreCase(RoamGiftApi);

							boolean roamgiftcheck = BuyGftSuccess(bpdcfx[t], bpivr[pkIVR], buygiftoption, SheetKey).trim()
.equalsIgnoreCase(ETLiveTransApi("GiftPackOffer", "GiftPack", "1571725474863",
		"SelfcareAPI7.0", "status_desc", "ecarepreprod", "", SheetKey).get(0));

							Assert.assertTrue(roamgiftcheck);
							bpRoamGift++;
							if(roamgiftcheck) {
								logger.log(LogStatus.PASS, "Gift Functionality Passed for = "+bptsiirq[t]);
							}else {
								//logger.log(LogStatus.FAIL, "Test Case Failed for Roam-Gift");
								logger.log(LogStatus.FAIL, "Internet failed for = "+bptsiirq[t] +" - "+ bpivr[pkIVR]);
								logger.log(LogStatus.FAIL, "API Response = "+RoamGiftApi);
								logger.log(LogStatus.FAIL, "APP POP UP MESSAGE = "+RoamGiftApp);
							}


						}
					}
				}

			}


			System.out.println("Internet Buy: " + bpinternetbuy + " Internet Gift: " + bpinternetgift
					+ " SateTinKya TopUp Buy: " + istinekyatopupbuy + " SateTinKya TopUp Gift: " + istinekyatopupgift
					+ "Voice Buy: " + bpvoicebuy + " Voice Gift: " + bpvoicegift + " Roaming Buy: " + bpRoamBuy
					+ " Roaming Gift: " + bpRoamGift);
			if(bpinternetbuy == 6 && bpinternetgift == 6 && istinekyatopupbuy == 1 && istinekyatopupgift == 1 && bpvoicebuy ==6 && bpvoicegift==6 && bpRoamBuy==6 && bpRoamGift==6) {
				return true;
			} */
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch blockSSS
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}





	public void scrollUpTill(String ActualApp, String Expected) throws Exception {


		for (int i = 0; i < 5; i++) {
			ScrollUp();
			System.out.println("Search attempts - "+i);
			try {

				if (AppValidation(ActualApp).equalsIgnoreCase(Expected)) {
					System.out.println(Expected +" found in " + i + " scroll");					
					break;

				}  else {
					System.out.println("No Such Details available");
					break;
				}

			} catch (CustomException e) {
				logger.log(LogStatus.INFO, " Could not find element in " + i + " attempt " + e.getMessage());
			}
		}

	}

	public void Ooredoo_Play_sub() throws Exception {
		Thread.sleep(3000);

		String MB = AppValidation("Main_Balance").replaceAll("[^0-9]", "");
		System.out.println("MB ==>" + MB);
		int MB_value = Integer.parseInt(MB);
		ClickEvents("play");
		// Appdriver.findElement(Home_Play_Button).click();
		Thread.sleep(3000);
		scrolltill("Play_Jobs_Category1", obj.getProperty("play_subscription"));
		//if (AppValidation("buypkdatavalidity").equalsIgnoreCase("IT/Software Job Alert")) {
		if (AppValidation("buypkdatavalidity").contains(obj.getProperty("play_Subscription_desc"))) {
			ClickEvents("Jobs_Subscribe_now_button");
		}
		// Appdriver.findElement(Jobs_Subscribe_now_button).click();
		Thread.sleep(2000);
		String confirm_pop_up_message1 = AppValidation("subscribe_confirm_pop_up1");
		System.out.println("confirm_pop_up_message1 ==>" + confirm_pop_up_message1);
		String Subscription_price1 = (StringUtils.abbreviate(confirm_pop_up_message1.replaceAll("[^0-9]", ""), 6))
				.replaceAll("[^0-9]", "");
		int sp_1 = Integer.parseInt(Subscription_price1);
		System.out.println("sp_1 ==>" + sp_1);
		String title = AppValidation("Subscription_title");
		System.out.println("title ==>" + title);
		getResult1("pass");
		ClickEvents("Subscribe_button");

		// Appdriver.findElement(Subscribe_button).click();
		Thread.sleep(2000);
		String confirm_pop_up_message2 = AppValidation("subscribe_confirm_pop_up2");
		String Subscription_price2 = (StringUtils.abbreviate(confirm_pop_up_message2.replaceAll("[^0-9]", ""), 6))
				.replaceAll("[^0-9]", "");
		int sp_2 = Integer.parseInt(Subscription_price2);
		System.out.println("sp_2 ==>" + sp_2);
		Thread.sleep(2000);
		getResult1("pass");
		ClickEvents("Ok_button");
		// Appdriver.findElement(Ok_button).click();
		Thread.sleep(5000);
		String sf_m = AppValidation("success_or_failure_pop_up_msg");
		Thread.sleep(1000);
		getResult1("pass");
		ClickEvents("success_or_failure_pop_up_OK_button");

		Thread.sleep(1000);

		if (MB_value >= sp_1) {
			logger.log(LogStatus.INFO, "user has sufficient balance to subscribe");
			System.out.println("user has sufficient balance to subscribe");
			getResult1("pass");

			if (sf_m.contains("confirmation")) {
				System.out.println("Pack activated successfully");
				logger.log(LogStatus.INFO, "Pack activated successfully");
				getResult1("pass");

				int final_amount = MB_value - sp_1;
				Thread.sleep(2000);
				ClickEvents("homebtton");

				// Appdriver.findElement(Home_button).click();
				Thread.sleep(3000);
				MB = AppValidation("Main_Balance").replaceAll("[^0-9]", "");
				MB_value = Integer.parseInt(MB);
				if (final_amount == MB_value) {
					getResult1("pass");
					System.out.println("Appropriate amount deducted");
				} else {
					logger.log(LogStatus.INFO, "Appropriate amound did not deducted. Instead of " + final_amount +" , "+ MB_value + "is displayed");
					getResult1("pass");
					System.out.println("Appropriate amound did not deducted. Instead of " + final_amount + ","
							+ MB_value + "is displayed");
				}
				ClickEvents("My_packages_button");
				// Appdriver.findElement(My_packages_button).click();
				Thread.sleep(2000);
				ClickEvents("Myservice_page");
				// Appdriver.findElement(Myservice_page).click();
				Thread.sleep(3000);

				List<String> API_Data_List = AppValidationList("Myservice_pack_details");

				if (API_Data_List.contains(title)) // need to pass dynamically
				{
					System.out.println("The subscribed channel is displayed");
					logger.log(LogStatus.INFO, "The subscribed channel is displayed");
					getResult1("pass");
				} else {
					System.out.println("The subscribed channel not displayed");
					logger.log(LogStatus.INFO, "The subscribed channel not displayed");
					getResult1("pass");
				}
			} else {
				System.out.println("Pack activated failed for below reason ==> " + sf_m);
				logger.log(LogStatus.INFO, "Pack activated failed for below reason ==> "+ sf_m);
			}

			if (sp_1 == sp_2) {
				System.out.println("The price displayed on the confimation and double confirmation pop-up is same");
			} else {
				System.out.println("The price displayed on the confimation and double confirmation pop-up is not same");
			}

		} else {
			logger.log(LogStatus.INFO, "Pack activated failed for below reason ==> "+ sf_m);
			System.out.println("user has in-sufficient balance to subscribe and error message is ==> " + sf_m);
			ClickEvents("homebtton");
			Thread.sleep(2000);

			String MB1 = AppValidation("Main_Balance").replaceAll("[^0-9]", "");
			int MB_value1 = Integer.parseInt(MB1);

			if (MB_value == MB_value1) {
				logger.log(LogStatus.INFO, "Balance did not deducted =>"+ MB_value1);
				getResult1("pass");
				System.out.println("Balance did not deducted =>" + MB_value1);
			} else {
				logger.log(LogStatus.INFO, "Balance deducted ==>"+ MB_value1);
				getResult1("pass");
				System.out.println("Balance deducted ==>" + MB_value1);
			}
		}
	}

	public String Contact_Us_Page_Report_An_Issue(String Enter_Name, String Category_value, String Sub_Category_value,
			String Date_Time, String Description, String Expected_Output) throws Exception {


		SendEvent("Report_Enter_Name", Enter_Name);

		// Appdriver.findElement(Report_Enter_Name).sendKeys(Enter_Name);

		Thread.sleep(2000);
		String actual_result;
		// By wait = Report_Pop_Up_message;

		if (Category_value != null)
		{
			ClickEvents("Report_Categroy");
			// Appdriver.findElement(Report_Categroy).click();
			Thread.sleep(2000);
			//
			int c_size = driver.findElementsByClassName("android.widget.TextView").size();
			System.out.println("c_size ==>" + c_size);

			if (c_size >= 1) {
				String cat_drop_down = "//android.widget.TextView[@text='" + Category_value + "']";
				System.out.println("drop_down ==>" + cat_drop_down);

				driver.findElement(By.xpath(cat_drop_down)).click();

				Thread.sleep(2000);

				if (Sub_Category_value != null || !Sub_Category_value.isEmpty()) {

					ClickEvents("Report_Sub_Category");
					// Appdriver.findElement(Report_Sub_Category).click();
					Thread.sleep(2000);
					int sub_size = driver.findElementsByClassName("android.widget.TextView").size();
					System.out.println("sub_size ==>" + sub_size);

					if (sub_size > 1) {
						String cat_sub_drop_down = "//android.widget.TextView[@text='" + Sub_Category_value + "']";
						System.out.println("cat_sub_drop_down ==>" + cat_sub_drop_down);
						driver.findElement(By.xpath(cat_sub_drop_down)).click();
						Thread.sleep(2000);

						if (Description != null || !Description.isEmpty()) {
							ClickEvents("Report_Enter_Description");
							// Appdriver.findElement(Report_Enter_Description).click();
							Thread.sleep(1000);
							int des_size = driver.findElementsByClassName("android.widget.TextView").size();
							System.out.println("des_size ==>" + des_size);

							if (des_size > 1) {
								driver.findElement(By.xpath("//android.widget.TextView[@text='I cannot make outgoing calls']")).click();
								Thread.sleep(2000);
								// wait = live_chat_back_Arrow;
							}
						}
					}
				}
			}
		}
		if (Date_Time.equals("Yes")) {

			ClickEvents("Report_Date_Time_Icon");
			// Appdriver.findElement(Report_Date_Time_Icon).click();
			Thread.sleep(2000);
			ClickEvents("Report_Calender_Ok_Button");
			// Appdriver.findElement(Report_Calender_Ok_Button).click();
			Thread.sleep(2000);
			ClickEvents("Report_Calender_Ok_Button");
			// Appdriver.findElement(Report_Calender_Ok_Button).click();
			Thread.sleep(2000);
		}
		
		swipeByElements();
		swipeByElements();
		
		ClickEvents("Report_Submit_Button");
		Thread.sleep(10000);
		try {
			if (driver.findElement(By.xpath(obj.getProperty("live_chat_back_Arrow"))).isDisplayed()) {
				System.out.println("livechat loop");
				actual_result = "Navigated to Live Chat Successfully";
				getResult1("pass");
				ClickEvents("live_chat_back_Arrow");
				// Appdriver.findElement(live_chat_back_Arrow).click();
				Thread.sleep(2000);
				//ClickEvents("Contact_Report_An_Issue");
				// Appdriver.findElement(Contact_Report_An_Issue).click();
				Thread.sleep(2000);
				logger.log(LogStatus.PASS, actual_result);
				return actual_result;
			} else {
				logger.log(LogStatus.FAIL, "No pop-up displayed");
				actual_result = "No pop-up displayed";
				return actual_result;
			}

		} catch (Exception e) {
			String act_res = AppValidation("Report_Pop_Up_message");
			if (act_res != null)
				// if (Appdriver.findElement(Report_Pop_Up_message).isDisplayed())
			{
				logger.log(LogStatus.FAIL, "error pop-up loop");
				System.out.println("error pop-up loop");
				// actual_result = Appdriver.findElement(Report_Pop_Up_message).getText();

				if (act_res.equals(Expected_Output)) {
					logger.log(LogStatus.PASS, act_res);
					return act_res + "Pass";
				} else {
					logger.log(LogStatus.FAIL, act_res);
					return act_res + "Fail";
				}
			} else {
				return "No pop-up displayed";
			}
		}
	}


	@SuppressWarnings("unused")
	public Map<String, String> getLoyaltySubscriber(String VIP_User_details_DB, String VIP_User_details_Query)
			throws Exception {
		JSONArray jArr;
		//System.out.println("VIP_User_details_DB==>" + VIP_User_details_DB);
		//System.out.println("VIP_User_details_Query==>" + VIP_User_details_Query);
		Map<String, String> API_VIP_Points_Level = new HashMap<String, String>();
		String status;
		// String Api_Response =// VIP_Details(VIP_User_details_DB,
		// VIP_User_details_Query);
		dbdetails(VIP_User_details_DB, VIP_User_details_Query);

		WebElement msgtab = Webdr.findElement(sesiontoken);

		String Api_Response = msgtab.getText();

		logger.log(LogStatus.INFO, "Vip Response = "+Api_Response);

		//System.out.println("Api_Response ==>" + Api_Response);
		JSONObject jObject = new JSONObject(Api_Response);
		String StatusCheck = jObject.getString("status");
		if(StatusCheck.equals("404")) {
			logger.log(LogStatus.INFO, "New User");			
		} else {
			logger.log(LogStatus.INFO, "Existing User");
		}
		String TypeofCustomer = jObject.getString("customer_type");
		//System.out.println("VIP_status ==>" + TypeofCustomer);
		String userpoints = jObject.getString("current_om_points");
		//System.out.println("VIP_status ==>" + userpoints);
		API_VIP_Points_Level.put("VIP_Level", TypeofCustomer);
		API_VIP_Points_Level.put("VIP_Points", userpoints);
		return API_VIP_Points_Level;
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		

/*		if (VIP_Eligible_Or_Not.equals("SUCCESS")) {
			System.out.println("User is eligible for VIP subscription");
			String VIP_Sub_Status;
			VIP_Sub_Status = jObject.getJSONObject("value").getJSONObject("subscriber").getString("loyaltyReviewClass");
			System.out.println("VIP_Sub_Status ==>" + VIP_Sub_Status);

			if (VIP_Sub_Status != null || VIP_Sub_Status != "") {
				System.out.println("User Subscribed already");

				jArr = jObject.getJSONObject("value").getJSONArray("bonuses");
				System.out.println("Jrr length == "+jArr.length());

				String User_level = jObject.getJSONObject("value").getJSONObject("loyalty").getString("loyaltyClass").toLowerCase();		


				int VIP_Points = 0;

				for (int i = 0; i < jArr.length(); i++) {
					if ((new JSONObject(jArr.get(i).toString()).get("bonus").equals("OMPoints"))) {
						VIP_Points = VIP_Points	+ Integer.parseInt((String) new JSONObject(jArr.get(i).toString()).get("qtyBalance"));
					}
				}
				String exp_info;
				if(jArr.length()>1) {
					String Expiry_date = (String) new JSONObject(jObject.getJSONObject("value").getJSONObject("subscriber").toString()).get("tierReviewDate");
					// System.out.println("Expiry_date ==>"+Expiry_date);
					String Expiry_Points = (String) new JSONObject(jArr.get(0).toString()).get("qtyBalance");
					// System.out.println("Expiry_date ==>"+Expiry_Points);

					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Expiry_date);

					SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
					Expiry_date = formatter.format(date);
					// System.out.println("Date Format with dd MMMM yyyy : "+Expiry_date);

					if(User_level.equals("tier1") || User_level.equals("tier2") ) {
	exp_info = "Earn" + Expiry_Points + "points by "+ Expiry_date + "to maintain level";					
}

else {
	exp_info = Expiry_Points + " are going to expire by " + Expiry_date + ".";
}			

				}

				else {
					exp_info = "You are at level Member";
				}

				if (User_level.equals("tier1") || VIP_Sub_Status.equals("Tier1")) {
					User_level = "PLATINUM +";
				}
				else if (User_level.equals("tier2") && VIP_Sub_Status.equals("Tier2")) {
					User_level = "PLATINUM";
				}
				else if (User_level.equals("tier3") || VIP_Sub_Status.toLowerCase().equals("tier3")) {
					User_level = "GOLD";
				}
				else if (User_level.equals("tier4") || VIP_Sub_Status.toLowerCase().equals("tier4")) {
					User_level = "SILVER";
				}
				else if (User_level.equals("tier5") || VIP_Sub_Status.toLowerCase().equals("tier5")) {
					User_level = "MEMBER";
				}



				//API_VIP_Points_Level.put("VIP_Status", VIP_Sub_Status.toUpperCase());
				API_VIP_Points_Level.put("VIP_Level", User_level.toUpperCase());
				API_VIP_Points_Level.put("VIP_Points", Integer.toString(VIP_Points));
				//API_VIP_Points_Level.put("VIP_Exp_Points", exp_info);
				logger.log(LogStatus.INFO, "API_VIP = "+API_VIP_Points_Level);
				return API_VIP_Points_Level;

			} else {
				status = "User is Eligible but not Subscribed";
				API_VIP_Points_Level.put("VIP_Status", status);
				System.out.println("User is Eligible but not Subscribed");
				logger.log(LogStatus.INFO, "API_VIP = "+API_VIP_Points_Level);
				return API_VIP_Points_Level;
			}
		} else {
			status = "User is not eligible for VIP";
			API_VIP_Points_Level.put("VIP_Status", status);
			logger.log(LogStatus.INFO, "API_VIP = "+API_VIP_Points_Level);
			System.out.println("User is not eligible for VIP");

			return API_VIP_Points_Level;
		}*/
	

	public Map<String, String> App_VIP() throws Exception {
		// ClickEvents("home_page_content");
		// Appdriver.findElement(home_page_content).click();
		Thread.sleep(5000);

		String home_user_level = AppValidation("HOME_USER_LEVEL");
		String home_user_points = AppValidation("HOME_USER_POINTS");
		// System.out.println("user_level ==>"+home_user_level);
		// System.out.println("user_points ==>"+home_user_points);

		vipclickupdate();
		// Appdriver.findElement(HOME_USER_LEVEL).click();
		Thread.sleep(3000);
		getResult1("pass");
		String vip_user_level = AppValidation("VIP_USER_LEVEL");
		String vip_user_points = AppValidation("VIP_USER_POINTS");
		//String exp_info = AppValidation("VIP_Points_Exp_Info");
		//System.out.println("exp_info app ==>" + exp_info);
		
		
		// System.out.println("vip_user_level ==>"+vip_user_level);
		// System.out.println("vip_user_points ==>"+vip_user_points);

		Map<String, String> APP_VIP_Points_Level = new HashMap<String, String>();
		if (home_user_level.equals(vip_user_level) && home_user_points.equals(vip_user_points)) {
			//APP_VIP_Points_Level.put("VIP_Status", vip_user_level.toUpperCase());
			APP_VIP_Points_Level.put("VIP_Level", home_user_level.toUpperCase()); // done
			APP_VIP_Points_Level.put("VIP_Points", home_user_points.replace(",", "")); // done
			//APP_VIP_Points_Level.put("VIP_Exp_Points", exp_info);	

			logger.log(LogStatus.PASS, "User Level and Points MOA Home Page and VIP Home Page are same  = " + APP_VIP_Points_Level);
			return APP_VIP_Points_Level;
		} else {
			APP_VIP_Points_Level.put("VIP_Level", vip_user_level);
			APP_VIP_Points_Level.put("VIP_Points", vip_user_points);
			logger.log(LogStatus.FAIL, "HOME MEMBER & VIP PAGE ARE NOT SAME  = " + APP_VIP_Points_Level);
			return APP_VIP_Points_Level;
		}
	}





	public void Settings() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		//ClickEvents("MoreIcon");
		ClickEvents("homebtton");
		ClickEvents("AddOption");
		ClickEvents("More_Setting");
		String name = AppValidation("Setting_Name");
		logger.log(LogStatus.INFO, "Name= "+name);
		//System.out.println("Name ==> " + name);
		String number = AppValidation("Setting_Msisdn");
		logger.log(LogStatus.INFO, "MSISDN = "+number);
		//System.out.println("number ==> " + number);
		ClickEvents("Settings_Setting");
		//swipeByElements();
		//String NickNameTitle = AppValidation("Settings_NickName");
		//logger.log(LogStatus.INFO, NickNameTitle);
		//System.out.println("Before Switch to Language ==>" + NickNameTitle);
		ClickEvents("Settings_language");
		Thread.sleep(5000);
		logger.log(LogStatus.INFO, "Language Change");
		getResult1("pass");
		//String AfterNickNameTitle = AppValidation("Settings_NickName");
		//logger.log(LogStatus.INFO, AfterNickNameTitle);
		//System.out.println("After Switch to Language  ==>" + AfterNickNameTitle);
		//Thread.sleep(5000);
		ClickEvents("languagechange");		
		//homebtton
		Thread.sleep(3000);
		ClickEvents("Settings_language");
		Thread.sleep(3000);
		ClickEvents("Settings_Setting");
		// DND
		//swipeByElements();
		String BeforeDNDDesc = AppValidation("DNDDesc");
		logger.log(LogStatus.INFO, BeforeDNDDesc);
		getResult1("pass");
		System.out.println("BeforeDNDDesc ==> " + BeforeDNDDesc);
		ClickEvents("Settings_DND");
		Thread.sleep(3000);
		String DNDMessage = AppValidation("subscribe_confirm_pop_up2");
		logger.log(LogStatus.INFO, DNDMessage);
		System.out.println("DNDMessage ==> " + DNDMessage);
		getResult1("pass");
		ClickEvents("Ok_button");
		Thread.sleep(3000);
		String AfterDNDDesc = AppValidation("DNDDesc");
		logger.log(LogStatus.INFO, AfterDNDDesc);
		getResult1("pass");
		System.out.println("AfterDNDDesc ==> " + AfterDNDDesc);
		// ClickEvents("Ok_button");
		ClickEvents("Settings_DND");
		ClickEvents("Ok_button");

		// Roaming
		//swipeByElements();
		String Beforeromaing_desc = AppValidation("RoamingDesc");
		logger.log(LogStatus.INFO, Beforeromaing_desc);
		getResult1("pass");
		//System.out.println("Desc Before Romaing Switched ==>" + Beforeromaing_desc);
		ClickEvents("Settings_Roaming");
		Thread.sleep(4000);
		String roaming_text = AppValidation("subscribe_confirm_pop_up1");
		logger.log(LogStatus.INFO, roaming_text);
		//getResult1("pass");
		//System.out.println("Roaming text ==>" + roaming_text);
		//ClickEvents("Ok_button");
		//String AfterRomaing_desc = AppValidation("RoamingDesc");
		//logger.log(LogStatus.INFO, AfterRomaing_desc);
		//getResult1("pass");
		//System.out.println("Desc After Roaming Switched ==>" + AfterRomaing_desc);

		// Thread.sleep(3000);
		try {
			//ClickEvents("Settings_Roaming");
			//String Roamming_On = AppValidation("subscribe_confirm_pop_up2");
			//System.out.println(" Roamming_On Text ==>" + Roamming_On);
			if (roaming_text.equalsIgnoreCase(
					"You have turned on roaming network. Buy Ooredoo roaming packs for more affordable price rates.")) {
				getResult1("pass");
				ClickEvents("Ok_button"); // buy a roaming pack
				Thread.sleep(3000);
				String RoamingPage = AppValidation("bprmsinga");
				if (RoamingPage.equalsIgnoreCase("Singapore")) {
					logger.log(LogStatus.PASS, "Redirected to Roaming Page = "+ RoamingPage);
					//System.out.println("Success");
					//System.out.println("Landed to Roaming Page");
					getResult1("pass");
				} else {
					// System.out.println("Failed to Land Roaming Page");
					logger.log(LogStatus.FAIL, "Redirection Failed");
					//System.out.println("Oops ! Landed to SomeOther Page");
				}

			} else {
				ClickEvents("Ok_button"); 
				//ClickEvents("toppopcancel");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.log(LogStatus.FAIL, "Test 1");
			//System.out.println("Test 1 ");
			e.printStackTrace();
		}

	}

	public String loan() throws Exception {

		ClickEvents("AddOption");
		ClickEvents("KyoThone");
		List<String> loantitile = AppValidationList("tvtitleloan");
		System.out.println("loantitile ==>" + loantitile);
		System.out.println(loantitile.size());
		List<String> loanDesc = AppValidationList("buypkdatavalidity");
		System.out.println("loanDesc ==>" + loanDesc);
		List<String> loanValidity = AppValidationList("loanvalidity");
		System.out.println("loanValidity ==>" + loanValidity);
		ClickEvents("takeloan");
		String LoanOk = AppValidation("buypkpopmesg");
		System.out.println("LoanOk ==>" + LoanOk);
		ClickEvents("buynowok");
		String ProcessMessage = AppValidation("buypkpopmesg");
		System.out.println("ProcessMessage ==> " + ProcessMessage);
		ClickEvents("buynowok");
		return ProcessMessage;

	}

	public void norecords() throws Exception {
		try {
			if(driver.findElements(By.xpath(obj.getProperty("NoRecordFound"))).size()!=0) {
				getResult1("pass");
				logger.log(LogStatus.PASS, "No Records Found");
			}
			else {
				logger.log(LogStatus.INFO, "Title :  " + PacksDetails("MyTitleName") + "  Validity :  " +PacksDetails("loanvalidity") +"  Description :  "+PacksDetails("buypkdatavalidity"));
				getResult1("pass");
			}	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public void contextmethod() throws Exception {
		

		HSSFWorkbook contextbook = Exceel();
		HSSFSheet contextsheet = contextbook.getSheet("contextpop");

		for(int context = 1; context <contextsheet.getPhysicalNumberOfRows(); context++) {
			String contextType = contextsheet.getRow(context).getCell(0).getStringCellValue();
			String ContextContent = contextsheet.getRow(context).getCell(1).getStringCellValue();
			contextmap.put(contextType, ContextContent);
		}
		System.out.println("ContextMap == "+contextmap);

		Thread.sleep(4000);	

		if(driver.findElements(By.xpath(obj.getProperty("UpdateLaterregister"))).size()!=0) {
			Thread.sleep(2000);
			logger.log(LogStatus.PASS, "Sim Registration Update Later");
			//String updateregdesc = AppValidation("UpdateSimRegister");
			getResult1("pass");
			/*if(updateregdesc.equalsIgnoreCase(contextmap.get("PASSPORT"))){
				logger.log(LogStatus.PASS, "Description Of New User Flow  = "+updateregdesc);
			}else {
				logger.log(LogStatus.FAIL, "Description Of New User Flow is not same when compared with given Text ");
			}*/
			ClickEvents("UpdateLater");
			Thread.sleep(3000);
			//return;
		}else {
			//logger.log(LogStatus.INFO, "Not new user");
		}
		
		if(driver.findElements(By.xpath(obj.getProperty("UpdateProfileStart"))).size()!=0) {
			Thread.sleep(2000);
			logger.log(LogStatus.PASS, "Update Profile Page -  Update Later");
			getResult1("pass");
			ClickEvents("UpdateLater");			
		} else {
			logger.log(LogStatus.INFO, "User Updated Profile");
		}

		Thread.sleep(3000);

		for(String keytype :contextmap.keySet() ) {
			String contextmessage = tc.ToastMessage(TakeScreenshot(),keytype);
			System.out.println("contextmessage == > "+contextmessage);
			if(contextmessage.equals("True")) {
				logger.log(LogStatus.PASS, "Type Of Context Pop-Up = " + keytype);					
				String contextDesc = AppValidation("ContextDescription");
				System.out.println("App =  "+contextDesc);
				System.out.println("Sheet =  "+contextmap.get(keytype));
				getResult1("pass");
				if(contextmap.get(keytype).equalsIgnoreCase(contextDesc)) {
					logger.log(LogStatus.PASS, "Description Of Context Pop-Up = " + contextDesc);
				} else {
					logger.log(LogStatus.FAIL, "Description Of Context Pop-Up is not same when compared with given Text ");
				}

				ClickEvents("ContextBuyButton");
				Thread.sleep(5000);
				getResult1("pass");

				if(driver.findElements(By.xpath(obj.getProperty("confirmbutton"))).size()!=0) {
					String PackDetails = AppValidation("UpdateSimRegister");
					if(PackDetails.equalsIgnoreCase(contextmap.get("PackDetails")))
						logger.log(LogStatus.PASS, "Description Of Pack Details = " + PackDetails);
					if(driver.findElements(By.xpath(obj.getProperty("toppopcancel"))).size()!=0)  {
						ClickEvents("toppopcancel");
					}
					
					getResult1("pass");
				} else {
					ClickEvents("backtohome");
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, "Redirected to Home Page");
					getResult1("pass");
				}


				return;
			}else {
				getResult1("pass");
				//System.out.println("No Context popup");
				logger.log(LogStatus.INFO, "No Context Popup");
			}



		}
	}

	public String datavoice() throws Exception  {
		
				Map<String, String> ApiValidate = querybalance(obj.getProperty("apiversion"), "querybalance");
				String bonus = ApiValidate.get("BONUS");
				if(bonus.contains("."))
				{			    
				    return bonus.replaceAll("[^0-9.]", "");
				} else {
					return bonus;
				}				         
	          	
	}
	
	

	public void ddbanners() throws Exception {
		ClickEvents("homebtton");
		beforebalancevipCheck();		
		String beforebonus =  datavoice();
		System.out.println("Before bonus value is ="+beforebonus);
		Thread.sleep(2000);
		if(driver.findElements(By.xpath(obj.getProperty("DDDPack"))).size()!=0) {
		ClickEvents("DDDPack");
		getResult1("pass");			
		String ksval = ksvalue();
		//Thread.sleep(3000);
		System.out.println(balanceCheck.get("BeforeBalance"));
		//System.out.println(Integer.parseInt(balanceCheck.get("BeforeBalance")));
		if(driver.findElements(By.xpath(obj.getProperty("pyawvip"))).size()!=0) {
			logger.log(LogStatus.PASS, AppValidation("pyawvip"));
		} else {
			logger.log(LogStatus.INFO, "No VIP points are allocated");
		}
		
		if (balanceCheck.get("BeforeBalance") < Integer.parseInt(ksval)) {
			logger.log(LogStatus.PASS, "Low Balance");
			ClickEvents("confirmbutton");
			waituntillfound("TopUpNow");
			getResult1("pass");
			ClickEvents("TopUpNow");
			Thread.sleep(2000);
			getResult1("pass");
			ClickEvents("homebtton");

		} else {		
			ClickEvents("confirmbutton");
			getResult1("pass");
			ClickEvents("confirmbutton");
			Thread.sleep(20000);
			getResult1("pass");
			driver.navigate().back();
					
		}				


		balancepointsCheck(balanceCheck.get("BeforeBalance"),balanceCheck.get("BeforeVIPPoints"));
		balanceCheck.clear();
		//datavoice("ddd");
		String afterbonus = datavoice();
		System.out.println("After bonus value is ="+afterbonus);
		logger.log(LogStatus.INFO, "Bonus Value After Purchase = "+afterbonus);
		
		if(afterbonus.equals(beforebonus)) {
			logger.log(LogStatus.INFO, "No Deduction from Bonus");
		} else {
			Double bonusfinalvalue = Double.parseDouble(beforebonus) - Double.parseDouble(afterbonus);
			System.out.println(bonusfinalvalue);
			logger.log(LogStatus.INFO, "Bonus Deducted After Purchase = "+bonusfinalvalue);
			}
		} else {			
			logger.log(LogStatus.FAIL, "No Such DDD Pack available");
			getResult1("fail");
		}
		
	}	


	public void beforebalancevipCheck() throws IOException, CustomException {
		if(driver.findElements(By.xpath(obj.getProperty("Main_Balance"))).size()!=0 && driver.findElements(By.xpath(obj.getProperty("HOME_USER_POINTS"))).size()!=0 )  {
			String mainvalance =  AppValidation("Main_Balance").replaceAll("[^a-zA-Z0-9]", "");
			//String BonusBefore =  AppValidation("Main_Balance").replaceAll("[^a-zA-Z0-9]", "");
			String vip = AppValidation("HOME_USER_POINTS").replaceAll("[^a-zA-Z0-9]", "");
			int Beforebalance = Integer.parseInt(mainvalance);

			int BeforeVIPPoints = Integer.parseInt(vip);
			balanceCheck.put("BeforeBalance", Beforebalance);
			balanceCheck.put("BeforeVIPPoints", Integer.valueOf(BeforeVIPPoints));		
		} else {
			logger.log(LogStatus.FAIL, "VIP Points /  Main Balance not found !");
		}
				

	}
	public void balancepointsCheck(Integer Beforebalance,Integer BeforeVIPPoints ) throws IOException, CustomException {
		Beforebalance = Integer.valueOf(balanceCheck.get("BeforeBalance").toString().replaceAll("[^a-zA-Z0-9]", ""));
		BeforeVIPPoints =  balanceCheck.get("BeforeVIPPoints");

		String AfterVIPPoints = AppValidation("HOME_USER_POINTS").replaceAll("[^a-zA-Z0-9]", "");			
		int finalvippoints = Integer.valueOf(AfterVIPPoints) - Integer.valueOf(BeforeVIPPoints);

		String Afterbalance = AppValidation("Main_Balance").replaceAll("[^a-zA-Z0-9]", "");				
		int Deductedbalance = Beforebalance - Integer.valueOf(Afterbalance);
		logger.log(LogStatus.PASS, "Updated VIP Points = "+finalvippoints);
		logger.log(LogStatus.PASS, "Deducted Balance Amount = "+Deductedbalance);
		logger.log(LogStatus.PASS, "Updated Balance Amount = "+Afterbalance);									

	}

	public void pyawpyawTestCase() throws Exception {
		ClickEvents("homebtton");
		beforebalancevipCheck();		
		String beforebonus =  datavoice();
		System.out.println("Before bonus value is ="+beforebonus);
		scrolltill("Pyaw", "Pyaw Pyaw Yu");
		getResult1("pass");
		for(int c=1;c<AppValidationList("only4UTitles").size();c++) {
			logger.log(LogStatus.INFO, "PyawPyawYu Packs Titles " +c +" = " +AppValidation("only4UTitles"));		
			logger.log(LogStatus.INFO, "PyawPyawYu Packs Validities " +c +" = "+AppValidation("only4UValidity"));
			logger.log(LogStatus.INFO, "PyawPyawYu Packs Price " +c +" = "+AppValidation("only4UPrice"));
			logger.log(LogStatus.INFO, "PyawPyawYu VIP  Points " +c +" = "+AppValidation("pyawvip"));
			}	

		ClickEvents("pyawfirst");
		Thread.sleep(3000);
		getResult1("pass");
		ClickEvents("confirmbutton");
		Thread.sleep(3000);
		getResult1("pass");
		
		if(driver.findElements(By.xpath(obj.getProperty("TopUpNow"))).size()!=0) {
			ClickEvents("TopUpNow");
			Thread.sleep(3000);
			getResult1("pass");
			ClickEvents("homebtton");
		} else {
			ClickEvents("confirmbutton");
			Thread.sleep(2000);
			getResult1("pass");
			ClickEvents("homebtton");
		}

		balancepointsCheck(balanceCheck.get("BeforeBalance"),balanceCheck.get("BeforeVIPPoints"));
		balanceCheck.clear();	
		
		String afterbonus = datavoice();
		System.out.println("After bonus value is ="+afterbonus);
		logger.log(LogStatus.INFO, "Bonus Value After Purchase = "+afterbonus);
		
		if(afterbonus.equals(beforebonus)) {
			logger.log(LogStatus.INFO, "No Deduction from Bonus");
		} else {
			Double bonusfinalvalue = Double.parseDouble(beforebonus) - Double.parseDouble(afterbonus);
			System.out.println(bonusfinalvalue);
			logger.log(LogStatus.INFO, "Bonus Deducted After Purchase = "+bonusfinalvalue);
		}
	}
	
	
	public void ExistingPacks() throws Exception {
		try {
			propertyelements();
			ClickEvents("homebtton");
			Thread.sleep(2000);
			ClickEvents("MyAccountClick");
			logger.log(LogStatus.INFO, "INTERNET");			
			norecords();	

			Thread.sleep(2000);			
			//Voice				
			ClickEvents("voicepackdetails");
			logger.log(LogStatus.INFO, "VOICE");			
			norecords();		
			Thread.sleep(2000);
			//SMS
			ClickEvents("smspacks");	
			logger.log(LogStatus.INFO, "SMS");
			norecords();
			Thread.sleep(2000);
			//My services
			ClickEvents("myservicespacks");	
			logger.log(LogStatus.INFO, "MY SERVICES");
			norecords();
			Thread.sleep(2000);
			//KYO Thone
			ClickEvents("kyothonepackdetails");
			logger.log(LogStatus.INFO, "KYO THONE");
			norecords();
			Thread.sleep(2000);
			//BONUS
			ClickEvents("bonuspackdetails");
			logger.log(LogStatus.INFO, "BONUS");
			norecords();
			Thread.sleep(2000);
			//ROAMING
			ClickEvents("roamingpackdetails");
			logger.log(LogStatus.INFO, "ROAMING");
			norecords();



		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
	
	public void intsmsvoice() throws Exception {
		ClickEvents("bpinternet");
		getResult1("pass");
		datausage();
		ClickEvents("bpvoice");
		datausage();
		getResult1("pass");
		ClickEvents("sms");
		datausage();
		getResult1("pass");
	}
	
	public void datausage() throws Exception {
		if(driver.findElements(By.xpath(obj.getProperty("UsageTitle"))).size()!=0) {
			List<String> ValuesHistory1 = AppValidationList("UsageTitle");
			List<String> ValuesHistory2 = AppValidationList("UsageInterval");
			List<String> ValuesHistory3 = AppValidationList("UsageTitleKS");
			//System.out.println(ValuesHistory1);
			if(ValuesHistory1.contains("null") || ValuesHistory2.contains("null") || ValuesHistory3.contains("null") ) {
					logger.log(LogStatus.FAIL, "ValuesHistory " +ValuesHistory1 + " " +ValuesHistory2+ " "+ValuesHistory3 );
				} else {
					logger.log(LogStatus.PASS, "ValuesHistory " +ValuesHistory1 + " " +ValuesHistory2+ " "+ValuesHistory3 );
				}
			//getResult1("pass");
		} else  {
			System.out.println(AppValidation("NoRecordFound"));
		}
	}
	
	public void OTPValidation(String msisdn) throws Exception, InterruptedException, CustomException {
		logger.log(LogStatus.INFO, "User Clicked button without entering otp");
		Thread.sleep(1000);
		if(driver.isKeyboardShown()) {		
		driver.hideKeyboard();
		System.out.println("closd keyboard");
		}
		ClickEvents("Login_Proceed");
		getResult1("pass");		
		
		logger.log(LogStatus.INFO, "User Entered Invalid OTP");
		SendEvent("OTPfield", "124543");
		if(AppValidation("success_or_failure_pop_up_msg").equals("Your PIN is not matching. Please enter correct one.")) {			
			getResult1("pass");
			logger.log(LogStatus.PASS, "Error message displayed properly = Your PIN is not matching. Please enter correct one.");
			ClickEvents("success_or_failure_pop_up_OK_button");
			}	
		else if(AppValidation("success_or_failure_pop_up_msg").contains("blocked")) {
				logger.log(LogStatus.FAIL, "Expected Error Message did not displayed ");
				ClickEvents("success_or_failure_pop_up_OK_button");
			}
		
		logger.log(LogStatus.INFO, "User Clicked on Modify Number");
		ClickEvents("ModifyNumber");
		loginpage(msisdn);
		}
	
	public void invalidnumber() throws IOException, Exception {
		logger.log(LogStatus.INFO, "User Entered Invalid MSISDN");
		String invalidnum = "1234567";
		SendEvent("msisdnenter",invalidnum );		
		ClickEvents("getotp");		
		if(AppValidation("success_or_failure_pop_up_msg").equals("Please enter a valid Mobile Number")) {			
			getResult1("pass");
			logger.log(LogStatus.INFO, "Error message displayed properly = Please enter a valid Mobile Number");
			//System.out.println(AppValidation("Please enter a valid Mobile Number"));
			ClickEvents("success_or_failure_pop_up_OK_button");
			getResult1("pass");
			Thread.sleep(500);
			/*logger.log(LogStatus.INFO, "User Entered Valid Mobile Number");
			SendEvent("msisdnenter",msisdn);
			ClickEvents("getotp");
			Thread.sleep(2000);
			getResult1("pass");
			*/
		} else {
			getResult1("fail");
			logger.log(LogStatus.FAIL, "Please enter a valid Mobile Number Error Text Message failed to display");
		}
	
	}
	
	public void withoutmsisdn() throws CustomException, IOException, InterruptedException {
		boolean requestotp = AppValidation("login").equalsIgnoreCase("Login");
		if(requestotp) {
			logger.log(LogStatus.PASS, "User in Login Page");
			} else {
				logger.log(LogStatus.FAIL, "User Not in Login Page");
			}
		
		logger.log(LogStatus.INFO, "User Clicked without entering Mobile Number");
		ClickEvents("getotp");
		if(AppValidation("success_or_failure_pop_up_msg").equals("Please enter Mobile Number")) {
			logger.log(LogStatus.PASS, "Please enter Mobile Number");
			getResult1("pass");
			ClickEvents("success_or_failure_pop_up_OK_button");
		} else {
			getResult1("fail");
			logger.log(LogStatus.FAIL, "Please enter Mobile Number text failed to display");
		}
	}
	
	public void resendandgetotp() throws Exception  {
		logger.log(LogStatus.INFO, "User Clicked on Resend OTP");
		Thread.sleep(1000);	
		if(driver.isKeyboardShown()) {		
			driver.hideKeyboard();
			System.out.println("closd keyboard");
			}
		Thread.sleep(2000);
		ClickEvents("ResendOTP");
		if(driver.findElements(By.xpath(obj.getProperty("success_or_failure_pop_up_msg"))).size()!=0) {		
			if(AppValidation("success_or_failure_pop_up_msg").equals("Sorry, you exceed the maximum number of PIN request attempts. Please try again later.")){
				getResult1("pass");
				ClickEvents("success_or_failure_pop_up_OK_button");
			}			
		} else {		
		String otp = otprecive("Dropdown1","queryotp");
		// Assert.asserttrue(Verifynum);
		if (otp != null) {
			logger.log(LogStatus.PASS, "OTP Retrived from DB = " + otp);
			getResult1("pass");
			} else {
				
			logger.log(LogStatus.FAIL, "Failed to fetch otp");
			getResult1("fail");
				}
			}
	}
	
	public void resendandgetotp2() throws Exception  {
		/*logger.log(LogStatus.INFO, "User Clicked on Resend OTP");
		Thread.sleep(1000);	
		if(driver.isKeyboardShown()) {		
			driver.hideKeyboard();
			System.out.println("closd keyboard");
			}
		ClickEvents("ResendOTP");
		if(driver.findElements(By.xpath(obj.getProperty("success_or_failure_pop_up_msg"))).size()!=0) {		
			if(AppValidation("success_or_failure_pop_up_msg").equals("Sorry, you exceed the maximum number of PIN request attempts. Please try again later.")){
				getResult1("pass");
				ClickEvents("success_or_failure_pop_up_OK_button");
			}			
		} else {	*/	
		String otp = otprecive("Dropdown1","queryotp");
		// Assert.asserttrue(Verifynum);
		if (otp != null) {
			logger.log(LogStatus.PASS, "OTP Retrived from DB = " + otp);
			getResult1("pass");
			} else {
			logger.log(LogStatus.FAIL, "Failed to fetch otp");
				}
			//}
	}
	
	public void AutoBannerheader() throws JSONException, Exception  {
		ClickEvents("homebtton");
		List<String> CountofBanners = ETLiveTransApi("getbanners", "home", "1589867941209359", obj.getProperty("apiversion"), "promobanners",
			obj.getProperty("apiuri"), "header", "","");
		if(CountofBanners!=null) {
		logger.log(LogStatus.PASS, "Total Number of banners are = " + CountofBanners.size());
		logger.log(LogStatus.PASS, "Banner Titles  = " + CountofBanners);
		System.out.println(CountofBanners.size());
		System.out.println(CountofBanners);
		} else {
		logger.log(LogStatus.FAIL, "Something went wrong at API Level");
		}
	}
	
	public void QuickLinksPlus() {
		try {
			ClickEvents("homebtton");
			ClickEvents("AddOption");
			Thread.sleep(2000);
			List<String> QuickTitles = AppValidationList("tvtitle");
			
			Actions act = new Actions(driver);		
			System.out.println(QuickTitles);
			getResult1("pass");
			int quickicons = AppValidationImageList("Quickicons");
			logger.log(LogStatus.PASS, "Total Quick Links are  = " + quickicons);	
			//logger.log(LogStatus.PASS, "Total Quick Links titles  = " + QuickTitles.size());	
			
			for(int quick=0; quick<quickicons; quick++) {
				System.out.println(QuickTitles.get(quick));
				MobileElement target = driver.findElement(By.xpath("//android.widget.TextView[@text= '"+QuickTitles.get(quick)+"']"));
				act.moveToElement(target).click().build().perform();
				
				System.out.println("quick = "+quick);
				logger.log(LogStatus.PASS, "Quicklinks Clicked is  = " + QuickTitles.get(quick));
				//System.out.println(QuickTitles.get(quick));
				//QuickTitles.get(quick).click();			
				Thread.sleep(1500);
				getResult1("pass");
				if(driver.findElements(By.xpath(obj.getProperty("success_or_failure_pop_up_msg"))).size()!=0) {
					if(AppValidation("success_or_failure_pop_up_msg").equals("To transfer your balance you must connect to Ooredoo 3G/4G only.")) {
						ClickEvents("success_or_failure_pop_up_OK_button");
					}
				}
				ClickEvents("homebtton");
				if(quick<quickicons-1) {
				ClickEvents("AddOption");
				}
				Thread.sleep(2000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void bellnotification() {
		
		try {
			ClickEvents("homebtton");
			getResult1("pass");
			ClickEvents("Bellicon");
			Thread.sleep(2000);
			getResult1("pass");
			if(driver.findElements(By.xpath(obj.getProperty("UnreadMesg"))).size()!=0 ) {
				logger.log(LogStatus.PASS, "UnRead Notifications");
				logger.log(LogStatus.PASS, "Notification Titles  = " +AppValidationList("AllTitles"));
				logger.log(LogStatus.PASS, "Notification Description  = " +AppValidationList("MyBenefitsDesc"));
				ClickEvents("DeleteNotification");
				Thread.sleep(2000);
				logger.log(LogStatus.PASS, "After Delete  = " + AppValidation("NotifiText"));
				getResult1("pass");				
				
				
			} else {
				logger.log(LogStatus.PASS, "Notifications  = " + AppValidation("NotifiText"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void usage() {
		try {
			ClickEvents("homebtton");
			ClickEvents("MyAccountClick");
			ClickEvents("UsageHistory");
			logger.log(LogStatus.PASS, "Header Title  = " + AppValidation("MyBenefitsheadertitle"));			
			logger.log(LogStatus.PASS, "Name  = " +AppValidation("Setting_Name"));
			logger.log(LogStatus.PASS, "MSISDN  = " +AppValidation("Setting_Msisdn"));
			ClickEvents("TodayUsage");
			logger.log(LogStatus.INFO, "Today Usage History");
			intsmsvoice();		
			ClickEvents("YesterdayUsage");
			logger.log(LogStatus.INFO, "Yesterday Usage History");
			intsmsvoice();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registerstatus() throws Exception {
		ClickEvents("homebtton");
		getResult1("pass");
		if(driver.findElements(By.xpath(obj.getProperty("SaluateClose"))).size()!=0) {			
		ClickEvents("SaluateClose");
		System.out.println("closed");
		Thread.sleep(2000);
		logger.log(LogStatus.PASS, "Salutation Nudge Closed");
		getResult1("pass");
		} else {			
			logger.log(LogStatus.INFO, "Salutation Nudge Closed Already/ Unavailable");
			getResult1("pass");
		}
		waituntillfound("UserStatus");
		String userstatus = AppValidation("UserStatus");
		
		if(userstatus.equals("Sim Registration Approved") || userstatus.equals("Register a friend") || userstatus.equals(" Get up to 5GB bonus each") || userstatus.equals(" Get up to 10 GB data bonus") || userstatus.equals("Register your SIM") || userstatus.equals("SIM Not Registered") || userstatus.equals("Sim Registration Rejected") || userstatus.equals("Register your SIM")) {
			logger.log(LogStatus.INFO, "New/Rejected User Sim Registration");
			for(int i=0; i<3; i++) {
			//logger.log(LogStatus.INFO, "App Status Display : "+userstatus);			
				logger.log(LogStatus.INFO, "App Status Display : "+AppValidation("UserStatus"));
				Thread.sleep(2000);				
			}
			
			ClickEvents("UserStatus");
			scrollToRedeem();
			Thread.sleep(2000);
			System.out.println("Redirected");
			waituntillfound("MyBenefitsheadertitle");
			if(driver.findElements(By.xpath(obj.getProperty("MyBenefitsheadertitle"))).size()!=0) {
				if(AppValidation("MyBenefitsheadertitle").equals("Update SIM Registration")) {
					getResult1("pass");
					logger.log(LogStatus.INFO, "Update SIM Registration");
				} 
				else if(AppValidation("MyBenefitsheadertitle").equals("SIM Registration Rejected")) {
					getResult1("pass");
					logger.log(LogStatus.INFO, "SIM Registration Rejected");
					logger.log(LogStatus.PASS, AppValidation("Setting_Name") + " : "+AppValidation("rejectdesc"));
					ClickEvents("RegisterAgain");
					waituntillfound("NewUserDIsplay");
					if(AppValidation("MyBenefitsheadertitle").equals("Update SIM Registration")) {
						logger.log(LogStatus.PASS, "Redirected to Update SIM Registration");
						getResult1("pass");						
					}					
				}
				else if(AppValidation("MyBenefitsheadertitle").equals("Register a friend/family")) {
					getResult1("pass");
					logger.log(LogStatus.INFO, "Register a friend/family");
					SendEvent("NewMSISDNRegister", "9971357267");
					ClickEvents("VerifyNumber");
					String regfrndotp = otprecive("Dropdown1","Regfrndquery");
					logger.log(LogStatus.INFO, "Register a friend/family OTP : "+regfrndotp);
					System.out.println("Register Frnd OTP" +regfrndotp);
					getResult1("pass");
					ClickEvents("VerifyOtp");
					Thread.sleep(5000);
					getResult1("pass");					
				}
			}		
		}		
		else if(userstatus.equals("Sim Registration in Process")) {
			logger.log(LogStatus.INFO, userstatus);
			getResult1("pass");
			ClickEvents("UserStatus");
			logger.log(LogStatus.INFO, "REGISTRATION DETAILS");
			logger.log(LogStatus.INFO, AppValidationList("Lable") + " : "+AppValidationList("LableValue"));
			String statusmesg = AppValidation("LabelValueStatusMsg");
			String mesg = AppValidation("LableMessage");
			if(statusmesg.equals("The registration details are under verification. You will receive a confirmation SMS soon.")) {
				logger.log(LogStatus.PASS, statusmesg);
				logger.log(LogStatus.PASS, mesg);	
				getResult1("pass");
					}	else {
						logger.log(LogStatus.FAIL, statusmesg+" -  Text Mismatch");
						getResult1("fail");
					}		
				}
		else {
			logger.log(LogStatus.FAIL, "Registration Status not available");
			}
		}
	
	public void registerdetails() {
		try {
			ClickEvents("homebtton");
			if(driver.findElements(By.xpath(obj.getProperty("SaluateClose"))).size()!=0) {			
				ClickEvents("SaluateClose");
				System.out.println("closed");
				Thread.sleep(1500);
				logger.log(LogStatus.PASS, "Salutation Nudge Closed");
				getResult1("pass");
				} else {
					logger.log(LogStatus.INFO, "Salutation Nudge already closed");
				}
			ClickEvents("My_packages_button"); // > click
			logger.log(LogStatus.INFO, "REGISTRATION DETAILS");
			//align technologies
			scrolltill("RegistrationDetails", "SIM REGISTRATION DETAILS");
			Thread.sleep(2000);
			if(AppValidationList("LableValue").contains("SIM Not Registered")) {
				System.out.println("in");
				scrolltill("RegisterFrind", "REGISTER NOW");
				System.out.println("scrolled");
			}
			logger.log(LogStatus.INFO, AppValidationList("Lable") + " : "+AppValidationList("LableValue"));
			//String statusmesg = AppValidation("LabelValueStatusMsg");
			String mesg = AppValidation("LableMessage");
			if(mesg.equals("Everytime you register a friend or family SIM with NRC, both of you will get up to 5 GB data bonus.")) {
				logger.log(LogStatus.INFO, "Status Approved. Register a Friend");			
				logger.log(LogStatus.PASS, mesg);	
				getResult1("pass");
				ClickEvents("RegisterFrind");
				waituntillfound("MyBenefitsheadertitle");
				
				if(AppValidation("MyBenefitsheadertitle").equals("Register a friend/family")) {
					logger.log(LogStatus.PASS, "Redirected to Register a friend/family");
					getResult1("pass");				
						}			
					}			
					
			else if (AppValidation("RegisterFrind").equals("REGISTER AGAIN")) {
				logger.log(LogStatus.INFO, "Sim Registration Rejected");
				logger.log(LogStatus.PASS, mesg);
				getResult1("pass");
				ClickEvents("RegisterFrind");
				waituntillfound("MyBenefitsheadertitle");
				if(AppValidation("MyBenefitsheadertitle").equals("Update SIM Registration")) {
					logger.log(LogStatus.PASS, "Redirected to Update SIM Registration");
					getResult1("pass");				
					}
			}		
			
			
			else if (AppValidation("RegisterFrind").equals("REGISTER NOW")) {
				logger.log(LogStatus.INFO, "SIM Not Register. Register Now");
				logger.log(LogStatus.PASS, mesg);
				getResult1("pass");
				imagedimesions("BannerDetails3");
				ClickEvents("RegisterFrind");
				waituntillfound("MyBenefitsheadertitle");
				if(AppValidation("MyBenefitsheadertitle").equals("Update SIM Registration")) {
					logger.log(LogStatus.PASS, "Redirected to Update SIM Registration");
					getResult1("pass");				
						}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void querydataapi() {
		try {
			ClickEvents("homebtton");
			Map<String, String> AppMap = new HashMap<String, String>();
			List<String> datavoicesmsTitle = AppValidationList("tvDataTitle");
			
			System.out.println(datavoicesmsTitle.size());
			List<String> datavoicesmsValue = AppValidationList("tvDataValues");
			
			System.out.println(datavoicesmsValue);
			
			System.out.println(datavoicesmsValue.size());
			for (int f = 0; f < datavoicesmsTitle.size(); f++) {
				AppMap.put(datavoicesmsTitle.get(f).toString(), datavoicesmsValue.get(f).toString());
				}
			
			AppMap.put("MOBILENO", AppValidation("msisdn"));
			AppMap.put("YOUR BALANCE", (AppValidation("Qbalance") + " " + AppValidation("ksextn")));
			System.out.println("App = "+AppMap);
			Map<String, String> ApiValidate = querybalance(obj.getProperty("apiversion"), "querybalance");
			System.out.println("API  : "+ApiValidate);
			if(ApiValidate.isEmpty() || ApiValidate  == null) {
				logger.log(LogStatus.FAIL, "Failed to Fetch API details");
			}
			boolean QueryBalanceisEqual = AppMap.equals(ApiValidate);
			AppMap.values().retainAll(ApiValidate.values());
			if(ApiValidate.get("YOUR BALANCE").equals(AppMap.get("YOUR BALANCE"))) {
				logger.log(LogStatus.PASS, "User Balance same with APP  and API side");			
			}
			if (QueryBalanceisEqual) {
				logger.log(LogStatus.PASS, "Test Case Passed is QueryBalance = "+ AppMap);
				logger.log(LogStatus.PASS, "App Side and API side matched succesfully");
				System.out.println("passed");
				getResult1("pass");
			} else {
				logger.log(LogStatus.FAIL, "QueryBalance Failed");
				logger.log(LogStatus.FAIL, "QueryBalance API Response = " + ApiValidate);
				logger.log(LogStatus.FAIL, "QueryBalance App Response = " + AppMap);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void vipshort() {
		try {
			ClickEvents("homebtton");
			waituntillfound("HOME_USER_POINTS");
			vipclickupdate();
			waituntillfound("VIP_USER_POINTS");
			List<AndroidElement> shortlist = AppSameList("shortcutTitles");
			//getResult1("pass");	
			Thread.sleep(2000);
			System.out.println(shortlist);
			for(int m =0; m<5; m++) {									
				logger.log(LogStatus.PASS, "Clicked "+ shortlist.get(m).getText());
				shortlist.get(m).click();
				if(m==4) {
					return;
				}
				Thread.sleep(4000);
				//waituntillfound("MyBenefitsheadertitle");
				//getResult1("pass");	
				if(driver.findElements(By.xpath(obj.getProperty("MyBenefitsheadertitle"))).size()!=0) {
					if(AppValidation("MyBenefitsheadertitle").equals("Redeem Points")) {					
						logger.log(LogStatus.PASS, "Redirection to Redeem Points");
						getResult1("pass");
						driver.navigate().back();
					}
					else if (AppValidation("MyBenefitsheadertitle").equals("My Discounts")){
						logger.log(LogStatus.PASS, "Redirection to My Discounts");
						getResult1("pass");
						driver.navigate().back();
					}
					else if(AppValidation("MyBenefitsheadertitle").equals("My Benefits")) {
						logger.log(LogStatus.PASS, "Redirection to My Benefits");
						getResult1("pass");
						driver.navigate().back();
						}
					}
				else if(AppValidation("MyPointsTitle").equals("My Points")) {
					logger.log(LogStatus.PASS, "Redirected to My Points");
					getResult1("pass");
					scrollUpTill("shortcutTitles",shortlist.get(m).getText());
				}			
				Thread.sleep(2000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void vipvaliddb() {
		try {
			ClickEvents("homebtton");
			getResult1("pass");
			Map<String, String> VIP_API = getLoyaltySubscriber("VIP_User_details_DB", "VIP_User_details_Query");
			System.out.println("VIP_API ==>" + VIP_API);
			if(VIP_API==null || VIP_API.isEmpty()) {
				logger.log(LogStatus.FAIL, "API Response Failed to fetch");
			} else {

					Map<String, String> VIP_APP = App_VIP();
					System.out.println("VIP_APP ==>" + VIP_APP);
		
					//logger.log(LogStatus.INFO, "VIP APP = " + VIP_APP);
		
					boolean vipvalidatecheck = VIP_API.toString().equalsIgnoreCase(VIP_APP.toString());
		
					if (vipvalidatecheck) {
						System.out.println("Pass");
						logger.log(LogStatus.PASS, "User Level and Points Validated Succesfully");
		
					} else {
						logger.log(LogStatus.FAIL, "VIP Points Failed");
						logger.log(LogStatus.FAIL, "VIP APP = " + VIP_APP);
						logger.log(LogStatus.FAIL, "VIP API = " + VIP_API);
						}
					} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ReferAFriend() throws Exception {
		try {
			ClickEvents("homebtton");
			//ClickEvents("MoreIcon");
			ClickEvents("AddOption");
			
			scrolltill("ReferAFrindText", "Share App");
			//waituntillfound("ReferAFrindText");
			Thread.sleep(2000);
			getResult1("pass");
			ClickEvents("ReferAFrindText");
			getResult1("pass");
			waituntillfound("Refer_Earn50Points");
			for(int i=0; i<3; i++) {
				
				if(i==0) {
			ClickEvents("Refer_Whatsapptxt");
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Clicked on Whatsapp");
			if(driver.getCurrentPackage().equals("com.whatsapp")) {
				System.out.println(driver.getCurrentPackage());
				cap.setCapability("appActivity", "com.whatsapp");
				cap.setCapability("appPackage", "com.whatsapp");
				if(driver.findElements(By.xpath(obj.getProperty("whatsappshare"))).size()!=0) {
					logger.log(LogStatus.PASS, "Redirected to Whatsapp");
					getResult1("pass");
					driver.navigate().back();
				}
				
			} 
				} else if(i==1) {
					ClickEvents("Refer_ViberTxt");
					Thread.sleep(2000);
					logger.log(LogStatus.INFO, "Clicked on Viber");	
					if(AppValidation("success_or_failure_pop_up_msg").equals("App isn't installed in your device")) {
						logger.log(LogStatus.PASS, "App isn't installed in your device");
						getResult1("pass");
						ClickEvents("success_or_failure_pop_up_OK_button");
						Thread.sleep(1500);
						}
				
				} else if(i==2) {
					ClickEvents("Refer_FaceBook");
					Thread.sleep(2000);
					logger.log(LogStatus.INFO, "Share on Facebook");
					if(driver.getCurrentPackage().equals("com.facebook.katana")) {
						System.out.println(driver.getCurrentPackage());
						cap.setCapability("appActivity", "com.facebook.katana");
						cap.setCapability("appPackage", "com.facebook.katana");
						getResult1("pass");
						logger.log(LogStatus.INFO, "Navigated to Fb");						
						Thread.sleep(1500);
						
						/*String postmessage = "Shared Link: Invite your friend's now\r\n" + 
								"						Download My Ooredoo APP now by using below link and get 50 VIP Points.";
						
						String postfb = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc ='"+postmessage+"']")).getText();
						
						if(postfb.contains("Invite your friend's now")) {
							logger.log(LogStatus.PASS, postmessage);
						} else {
							logger.log(LogStatus.FAIL, "No Post available to share in FB");
						}*/
						ClickEvents("Refer_FbPost");						
					} else {
						if(AppValidation("success_or_failure_pop_up_msg").equals("App isn't installed in your device")) {
							logger.log(LogStatus.PASS, "App isn't installed in your device");
							getResult1("pass");
							ClickEvents("success_or_failure_pop_up_OK_button");
							Thread.sleep(1500);
							}
					}
			
			
				}			
			
			}
			if(driver.findElements(By.xpath(obj.getProperty("Refer_Earn50Points"))).size()!=0) {				
				logger.log(LogStatus.PASS, AppValidation("Refer_Titile"));
				if(AppValidation("Refer_Earn50Points").equals("Refer a friend to My Ooredoo App and get 100 VIP points.")) {
					getResult1("pass");					
					imagedimesions("Refer_Image");
					logger.log(LogStatus.PASS, AppValidation("InviteFrndTxt"));
					logger.log(LogStatus.PASS, "Refer a friend to My Ooredoo App and get 100 VIP points.");					
					logger.log(LogStatus.PASS, AppValidation("WhatsappText") +" , "+ AppValidation("FbTxt") +" , "+AppValidation("SMStxt")+" , "+AppValidation("ViberTxt"));
					logger.log(LogStatus.PASS, AppValidation("MyRewards"));
					logger.log(LogStatus.PASS, AppValidation("MyRewardsDesc"));
					imagedimesions("GiftLogo");
					ClickEvents("SMStxt");
					logger.log(LogStatus.INFO, "Clicked on SMS");
					Thread.sleep(1000);
					if(driver.findElements(By.xpath(obj.getProperty("Refer_PermissionMesg"))).size()!=0) {
						logger.log(LogStatus.PASS, AppValidation("Refer_PermissionMesg"));
						ClickEvents("Refer_AllowContacts");
					}
					getResult1("pass");
					waituntillfound("Refer_FullBanner");
					imagedimesions("Refer_FullBanner");
					ClickEvents("Refer_Invite");
					getResult1("pass");
					logger.log(LogStatus.PASS, "Before Selected : "+AppValidation("Refer_InviteSelected"));			
					ClickEvents("Refer_SelectAll");
					getResult1("pass");
					logger.log(LogStatus.PASS, "After Selected : "+AppValidation("Refer_InviteSelected"));
					ClickEvents("Refer_Invite");
					waituntillfound("VipNewTitle");
					if(AppValidation("VipNewTitle").equals("Congratulations")) {
						String sendinvitetext = "We will send the invite to your friends via SMS to join My Ooredoo APP";
						String Mesgrefertext = "You will receive 100 VIP points for every friend that logs-in to the My Ooredoo App for the first time with your link. Additional 100 points on 1st pack purchase or Top-up";
						if(AppValidation("AllTitles").equals(sendinvitetext) && AppValidation("VipNewMessage").equals(Mesgrefertext)) {
							getResult1("pass");
							Dimension  uppertext = driver.findElement(By.xpath(obj.getProperty("AllTitles"))).getSize();
							Dimension  Belowtext = driver.findElement(By.xpath(obj.getProperty("VipNewMessage"))).getSize();
							logger.log(LogStatus.PASS, sendinvitetext +" => and Dimensions of text :"+uppertext);
							logger.log(LogStatus.PASS, Mesgrefertext +" => and Dimensions of text :"+Belowtext);
							ClickEvents("success_or_failure_pop_up_OK_button");
							Thread.sleep(2000);
							getResult1("pass");
						}
					}
					logger.log(LogStatus.PASS, AppValidation("HowitWorks"));
					logger.log(LogStatus.PASS, ""+AppValidationList("StepNumber"));
					logger.log(LogStatus.PASS, ""+AppValidationList("StepName"));
					logger.log(LogStatus.PASS, ""+AppValidationList("StepDesc"));
					getResult1("pass");
					
					scrolltill("FreqAskQues", "Frequently Asked Questions");
					
					ClickEvents("FreqAskQues");
					waituntillfound("FreqAskQues_External");
					if(AppValidation("FreqAskQues_External").equals("Frequently Asked Questions")) {
						logger.log(LogStatus.PASS, "Redirected to Frequently Asked questions External Page");
						getResult1("pass");
						driver.navigate().back();
					}else {
						logger.log(LogStatus.FAIL, "Redirection Failed to Frequently Asked questions External Page");
					}
					
					waituntillfound("FreqAskQues");
					ClickEvents("TermsCondition");
					waituntillfound("TermsCondition_External");
					if(AppValidation("TermsCondition_External").equals("Terms & Conditions")) {
						logger.log(LogStatus.PASS, "Redirected to Terms & Condition External Page");
						getResult1("pass");
						driver.navigate().back();
					}else {
						logger.log(LogStatus.FAIL, "Redirection Failed to Terms & Condition External Page");
					}
					
					
					ScrollUp();
					Thread.sleep(2000);
					if(AppValidation("MyRewardsTxt").equals("MY REWARDS")) {
						getResult1("pass");
						logger.log(LogStatus.INFO, "MY REWARDS Exists");
						logger.log(LogStatus.INFO, AppValidation("MyRewardsDesc"));						
						ClickEvents("MyRewardsTxt");
						waituntillfound("MyRewards_History");
						if(AppValidation("MyRewards_History").equals("My Rewards History")) {
							logger.log(LogStatus.PASS, "Redirected to Rewards History Page");
							getResult1("pass");
							if(driver.findElements(By.xpath(obj.getProperty("Refer_PhoneNumber"))).size()!=0)  {
								logger.log(LogStatus.PASS, "Phone  Number : "+AppValidationList("Refer_PhoneNumber"));
								logger.log(LogStatus.PASS, "Reward Description : "+AppValidationList("Refer_RewardDesc"));
								logger.log(LogStatus.PASS, "Reward Points : "+AppValidationList("Refer_RewardPoints"));
							}
							
							if(AppValidation("InviteNow").equals("Invite Now >")) {
								logger.log(LogStatus.PASS, "Invite Now Link is displayed");
								ClickEvents("InviteNow");
								getResult1("pass");
								
							} else {
								logger.log(LogStatus.FAIL, "Invite Now Link is not displayed");
							}
						} else {
							logger.log(LogStatus.FAIL, "Redirection Failed to History Page");
						}
					} else {
						logger.log(LogStatus.FAIL, "MY Rewards Not available");
					}
					
					
					
					
					/*ClickEvents("Refer_TC");
					waituntillfound("TermCondition");
					if(driver.findElements(By.xpath(obj.getProperty("TermCondition"))).size()!=0) {						
						logger.log(LogStatus.PASS, "Redirected to Terms and Conditions Page");
						getResult1("pass");
						driver.navigate().back();						
					} else {
						logger.log(LogStatus.FAIL, "Redirection to Terms and Conditions Page Failed");
					}
					ClickEvents("Refer_InviteButton");
					getResult1("pass");
					ClickEvents("Refer_AgreeTC");
					Thread.sleep(3000);
					ClickEvents("Refer_InviteButton");
					Thread.sleep(3000);
					getResult1("pass");
					
						} else {
							logger.log(LogStatus.FAIL, "Earn Points Text/ Banner Failed to dislay");							
						}			
					}*/
			
			/*waituntillfound("Refer_FullBanner");
			String referbanner = tc.ToastMessage(TakeScreenshot(),"50");
			System.out.println("toastmesg == > "+referbanner);
			if(referbanner.equals("True")) {
				imagedimesions("Refer_FullBanner");
				Thread.sleep(3000);
			} else {
				System.out.println("Refer a Friend Banner unavailable / Delay in Display");
			}*/		
							
					}
				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void SpecialOffers() throws Exception {
		ClickEvents("homebtton");
		getResult1("pass");
		vipclickupdate();
		waituntillfound("VIP_USER_POINTS");
		String vip_points = AppValidation("VIP_USER_POINTS").replaceAll("[^0-9]", "");
		logger.log(LogStatus.PASS, "VIP Points : "+vip_points);
		
		scrolltill("SpecialOffersTitle", "Special Offers");
		getResult1("pass");
		ClickEvents("VipSpecialOffer");
		waituntillfound("VIP_RedeemButton");
		
		if(vip_points.equals("0")) {
			logger.log(LogStatus.PASS, "VIP Points : "+vip_points);
			logger.log(LogStatus.PASS, "Low Points Text : "+AppValidation("VIP_Donthave"));
			//logger.log(LogStatus.PASS, AppValidation("VIP_Earnpointstext"));	
			getResult1("pass");
			ClickEvents("VIP_Earnpointstext");
			Thread.sleep(1500);
			if(AppValidation("EarnmorepointTitles").equals("Earn More Points")) {
				logger.log(LogStatus.PASS, "Redirected to Earn More Points ");
				getResult1("pass");
			} else  {
				logger.log(LogStatus.FAIL, "Redirection Failed to Earn More Points ");
			}
			
			
		} else {		
		getResult1("pass");
		ClickEvents("VIP_RedeemButton");
		Thread.sleep(5000);
		//System.out.println(driver.getPageSource());
		getResult1("pass");
		
		
		logger.log(LogStatus.PASS, AppValidation("VIP_Donthave"));
		if(AppValidation("VIP_PointsText")!=null || !AppValidation("VIP_PointsText").isEmpty()) {
			String popupvippoints = driver.findElement(By.xpath("//android.view.View[@text='"+vip_points+"']")).getText();
			if(vip_points.equals(popupvippoints)) {
				logger.log(LogStatus.PASS, "Available points : "+popupvippoints);
				} else {
					logger.log(LogStatus.FAIL, "No Points are displayed");
				}			
		} else {
			logger.log(LogStatus.FAIL, "Available points text missing");
		}
		
		logger.log(LogStatus.PASS, AppValidation("VIP_Earnpointstext"));
		
		//logger.log(LogStatus.INFO, AppValidation("VIP_EarnpointsArrow"));
		/*ClickEvents("VIP_Earnpointstext");
		waituntillfound("EarnmorepointsText");
		if(AppValidation("EarnmorepointsText").equalsIgnoreCase("Earn More Points")) {
			logger.log(LogStatus.PASS, "Redirected to Earn More Points");
		}*/
		
		logger.log(LogStatus.PASS, AppValidation("VIP_Buywithpointstext"));	
		logger.log(LogStatus.PASS, AppValidation("VIP_Note"));	
		logger.log(LogStatus.INFO, "Offer Redeem with 0 points");
		Thread.sleep(2000);
		ClickEvents("VIP_txtenterpoints");	
		driver.pressKeyCode(android.view.KeyEvent.KEYCODE_0);		
		Thread.sleep(2000);
		ClickEvents("VIP_ProceedButton");
		Thread.sleep(2000);
		//System.out.println(driver.getPageSource());
		if(!AppValidation("VIP_GreaterThan0").isEmpty())  {
			//System.out.println("greather 10");
			getResult1("pass");
			logger.log(LogStatus.PASS, AppValidation("VIP_GreaterThan0"));
			ClickEvents("VIP_OkButton");
			getResult1("pass");
			ClickEvents("VIP_RedeemButton");	
			logger.log(LogStatus.INFO, "Offer Redeem with More than available points");
			Thread.sleep(2000);	
			ClickEvents("VIP_txtenterpoints");
			driver.pressKeyCode(android.view.KeyEvent.KEYCODE_5);
			driver.pressKeyCode(android.view.KeyEvent.KEYCODE_0);
			driver.pressKeyCode(android.view.KeyEvent.KEYCODE_0);
			driver.pressKeyCode(android.view.KeyEvent.KEYCODE_0);
			
			ClickEvents("VIP_ProceedButton");
			Thread.sleep(2000);
			if(AppValidation("PointsGreaterText").equals("You can only use points which is the same or less than your available points"))
			{
				logger.log(LogStatus.PASS, "You can only use points which is the same or less than your available points");
				getResult1("pass");
				ClickEvents("VIP_OkButton");
			} else {				
				logger.log(LogStatus.FAIL, "Text Mismatch when entered greater than available points");
			}
			
			ClickEvents("VIP_RedeemButton");
			logger.log(LogStatus.INFO, "Offer Redeem with available points");
			Thread.sleep(2000);	
			ClickEvents("VIP_txtenterpoints");
			driver.pressKeyCode(android.view.KeyEvent.KEYCODE_1);
			ClickEvents("VIP_ProceedButton");
			Thread.sleep(2000);	
			String pointsvip;
			try {
				pointsvip = driver.findElement(By.xpath("//android.view.View[@text = 'You are redeeming 1 points for the offer of MomoPlay merchant from your balance "+ vip_points +" VIP points. For the remaining point, please pay with cash.']")).getText();

				if(pointsvip.equalsIgnoreCase("You are redeeming 1 points for the offer of MomoPlay merchant from your balance "+vip_points+" VIP points. For the remaining point, please pay with cash.")){
					logger.log(LogStatus.PASS, pointsvip);
					System.out.println(pointsvip);
					getResult1("pass");
					ClickEvents("TopUp_confirmBtn");
					Thread.sleep(2000);
						if(AppValidation("VIP_MerchntPinText").equalsIgnoreCase("Enter Merchant PIN")) {
							logger.log(LogStatus.PASS, "Enter Merchant PIN");
							getResult1("pass");
							ClickEvents("EnterMerchantPin");
							driver.pressKeyCode(android.view.KeyEvent.KEYCODE_1);
							driver.pressKeyCode(android.view.KeyEvent.KEYCODE_2);
							driver.pressKeyCode(android.view.KeyEvent.KEYCODE_4);
							driver.pressKeyCode(android.view.KeyEvent.KEYCODE_3);
							logger.log(LogStatus.PASS, AppValidation("VIP_ScanQRText"));							
							ClickEvents("VIP_ProceedButton");
							Thread.sleep(3000);
							getResult1("pass");
							if(driver.findElements(By.xpath(obj.getProperty("CorectMerchantText"))).size()!=0)  {
								logger.log(LogStatus.PASS, "Please Enter the Correct Merchant PIN");
								ClickEvents("VIP_OkButton");								
							} else {
								logger.log(LogStatus.PASS, AppValidation("SuccessRedeemed"));
								ClickEvents("BackToRewards");		
								Thread.sleep(2000);
								getResult1("pass");
								ClickEvents("homebtton");
								String vippoints_After = AppValidation("HOME_USER_POINTS").replaceAll("[^0-9]", "");
								int finalpoints = Integer.valueOf(vip_points) -  Integer.valueOf(vippoints_After);
								if(finalpoints!=0) {
									logger.log(LogStatus.PASS, "Points Deducted : "+finalpoints);
								} else {
									logger.log(LogStatus.FAIL, "No Points Deducted");
								}
								
							}	
							
							
						} else {
							logger.log(LogStatus.FAIL, "Enter Merchant PIN Text MisMatch / Not found");
							getResult1("fail");
						}
					//System.out.println(driver.getPageSource());
				} else {
					logger.log(LogStatus.FAIL, "Confirmation Popup Text Message Mismatch");
					getResult1("fail");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//
			
			//
			
		} else {
			logger.log(LogStatus.FAIL, "Error Popup Not Displayed");
			getResult1("fail");
		}
		//logger.log(LogStatus.PASS, AppValidation("VIP_Earnpointstext"));
		
		}	
		
	}
	
	protected void NiniNudge() throws Exception {
		// TODO Auto-generated method stub
		ClickEvents("homebtton");
		ClickEvents("niniopen");
		waituntillfound("nini_cards");		
		int ninibanners = driver.findElements(By.xpath(obj.getProperty("ETbanners"))).size();
		int ninicards = driver.findElements(By.xpath(obj.getProperty("nini_cards"))).size();
		System.out.println("nini banners : "+ninibanners);
		System.out.println("nini cards : "+ninicards);
		//int totalnini = ninibanners + ninicards;
		logger.log(LogStatus.INFO, "Nini Number of Single Banners : "+ninibanners);
		logger.log(LogStatus.INFO, "Nini Number of Cards : "+ninicards);
		
		List<AndroidElement> cardslist = AppSameList("nini_cards");
		
		for(int cards=0; cards<ninicards; cards++) {
			//ClickEvents("nini_cards");
			cardslist.get(cards).click();
			Thread.sleep(3000);
			if(driver.findElements(By.xpath(obj.getProperty("buypkpopmesg"))).size()!=0) {
				logger.log(LogStatus.INFO, "Selected Nini Card is : "+AppValidation("buypkpopmesg"));
				if(driver.findElements(By.xpath(obj.getProperty("VIPPoints"))).size()!=0)  {
					logger.log(LogStatus.PASS, "Points : "+AppValidation("VIPPoints"));					
				} else  {
					logger.log(LogStatus.INFO, "VIP Points are not displayed for selected card pack");
				}				
				getResult1("pass");
				ClickEvents("toppopcancel");				
			} else {
				logger.log(LogStatus.INFO, "Navigated to Respective Page");
				Thread.sleep(8000);
				getResult1("pass");
				driver.navigate().back();
			}
			ClickEvents("niniopen");
			waituntillfound("nini_cards");
		}
		
		/*String toastmesg = tc.ToastMessage(TakeScreenshot(),"win Millions");
		getResult1("pass");
		logger.log(LogStatus.INFO, toastmesg);
		System.out.println("toastmesg == > "+toastmesg);
		if(toastmesg.equals("True")) {
			ClickEvents("nini_cards");
			//Thread.sleep(15000);
			waituntillfound("MusicWeekly");
		} else {
			System.out.println("No Nini");
		}
		*/
		
	}
	
	public void LowAlertsMessages() throws Exception {
		try {
			ClickEvents("homebtton");
			if(driver.findElements(By.xpath(obj.getProperty("BalanceAlertMsg"))).size()!=0) {
				String BalanceAmount = AppValidation("Main_Balance").replaceAll("[^a-zA-Z0-9_-]", "");
				String BalanceAlertMsg = AppValidation("BalanceAlertMsg");
				String voicemesg = "Your voice balance is low. Please";
				String datamesg = "Data less than 256 MB. Please";
				getResult1("pass");
				List<String> DataVoiceBalance = AppValidationList("tvDataValues");
				System.out.println("DataVoiceBalance => "+DataVoiceBalance);
				String databalance = DataVoiceBalance.get(0).toString().replaceAll("[^0-9]", "");
				System.out.println("databalance =>"+databalance);
				String voicebalance = DataVoiceBalance.get(1).toString().replaceAll("[^0-9]", "");
				//String VoiceBalance = AppValidation("VoiceBalance");
				
				// Balance Related
				
				if(Integer.valueOf(BalanceAmount) < Integer.valueOf(obj.getProperty("Boundarybalance"))) {
					String balancealert = AppValidation("BalanceAlertMsg");		
					getResult1("pass");
					logger.log(LogStatus.PASS, balancealert +" "+AppValidation("LowAlertRedirect"));
					logger.log(LogStatus.PASS, "Balance :" +BalanceAmount );
					ClickEvents("LowAlertRedirect");
					waituntillfound("TopUpText");
					if(AppValidation("TopUpText").equals("Topup")) {
						logger.log(LogStatus.PASS, "Redirected to TopUp Page");
						getResult1("pass");
					} else {
						logger.log(LogStatus.FAIL, "Redirection Fialed");
					}
					
				}		
			
			// Data Related
			
			else if((Integer.valueOf(databalance) < Integer.valueOf(obj.getProperty("BoundaryData"))) && BalanceAlertMsg.equals(datamesg) ) {
				//String DataBalanceAlert = AppValidation("BalanceAlertMsg");
				logger.log(LogStatus.PASS, BalanceAlertMsg +" "+AppValidation("LowAlertRedirect"));
				logger.log(LogStatus.PASS, "Data Balance :" +databalance );
				ClickEvents("LowAlertRedirect");
				waituntillfound("bpinternet");
				if(AppValidation("bpinternet").equals("INTERNET")) {
					logger.log(LogStatus.PASS, "Redirected to Packs Page");
					getResult1("pass");
				} else {
					logger.log(LogStatus.FAIL, "Redirection Fialed");
				}
			}
			
			
			// Voice Related
			
			else if((Integer.valueOf(voicebalance) < Integer.valueOf(obj.getProperty("BoundaryVoice"))) && BalanceAlertMsg.equals(voicemesg)) {
				//String DataBalanceAlert = AppValidation("BalanceAlertMsg");
				logger.log(LogStatus.PASS, BalanceAlertMsg +" "+AppValidation("LowAlertRedirect"));
				logger.log(LogStatus.PASS, "Voice Balance :" +voicebalance );
				ClickEvents("LowAlertRedirect");
				waituntillfound("bpvoice");
				if(AppValidation("bpvoice").equals("VOICE")) {
					logger.log(LogStatus.PASS, "Redirected to Packs Page");
					getResult1("pass");
				} else {
					logger.log(LogStatus.FAIL, "Redirection Fialed");
					}
				}
			} else {
				getResult1("pass");
				logger.log(LogStatus.PASS, "No alert messages");
			}
			
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ManageAcountsList()  {
		try {
			waituntillfound("ManageAccount_FullBanner");
			imagedimesions("ManageAccount_FullBanner");
			String mobilenom = AppValidation("ManageAccountMobileNumber");
			String usertype = AppValidation("ManageAccountType");
			logger.log(LogStatus.PASS, "Account Name : "+AppValidation("ManageAccountName"));
			logger.log(LogStatus.PASS, "Account Mobile Number : "+mobilenom);
			logger.log(LogStatus.PASS, "Account Type : "+usertype);
			logger.log(LogStatus.PASS, "Account Balance - Hide : "+AppValidation("ManageAccountAmount") +" "+AppValidation("ManageAccountMeasure"));
			getResult1("pass");
			ClickEvents("ManageAccountShowBalance");
			waituntillfound("ManageAccountAmount");
			logger.log(LogStatus.PASS, "Account Balance - View : "+AppValidation("ManageAccountAmount") +" "+AppValidation("ManageAccountMeasure"));
			ClickEvents("ManageAccountExpand");
			waituntillfound("AccountCollapse");
			getResult1("pass");
			if(driver.findElements(By.xpath(obj.getProperty("tvDataTitle"))).size()!=0) {
			logger.log(LogStatus.PASS, AppValidationList("tvDataTitle") + " : "+AppValidationList("tvDataValues"));
			} else {
				if(driver.findElements(By.xpath(obj.getProperty("SupernetExpiry"))).size()!=0) {
					logger.log(LogStatus.PASS, AppValidationList("Supernetvalidity") + " : "+AppValidationList("SupernetExpiry"));
				} else  {
					logger.log(LogStatus.INFO, "Service Not active");
				}
				
			}
			
			ClickEvents("ManageAccountTopUp");
			waituntillfound("TopUp_MobileNo");
			if(AppValidation("TopUp_MobileNo").equals(mobilenom)) {
				logger.log(LogStatus.PASS, "Redirected to TopUp Page");
				logger.log(LogStatus.PASS, "Added Account number and TopUp Mobile Number Matched : " +mobilenom);
				getResult1("pass");
				ClickEvents("backtohome");
				if(AppValidation("Refer_Titile").equals("Manage Accounts")) {
					logger.log(LogStatus.PASS, "Redirected Back to Manage Accounts Page");
					getResult1("pass");
				} else {
					logger.log(LogStatus.FAIL, "Redirection Failed");
				}
								
			} else {
				logger.log(LogStatus.FAIL, "MisMatch with Added Account number and TopUp Mobile Number ");
				getResult1("fail");
			}
			
			ClickEvents("ManageAccountBuyPack");
			Thread.sleep(5000);
			if(driver.findElements(By.xpath(obj.getProperty("bpinternet"))).size()!=0) {
				if(AppValidation("bpinternet").equals("INTERNET")) {
					logger.log(LogStatus.PASS, "Redirected to Buy Packs Page");					
					getResult1("pass");
					if(driver.findElements(By.xpath(obj.getProperty("buypackGift"))).size()!=0) {
						logger.log(LogStatus.FAIL, "Gift option should not be available");
						getResult1("fail");
						} 
					ClickEvents("backtohome");
					waituntillfound("Refer_Titile");
					if(AppValidation("Refer_Titile").equals("Manage Accounts")) {
						logger.log(LogStatus.PASS, "Redirected Back to Manage Accounts Page");
						getResult1("pass");
					} else {
						logger.log(LogStatus.FAIL, "Redirection Failed");
					}
					
				} else {
					logger.log(LogStatus.PASS, "Redirection Failed to Buy Packs");
				}
			} else {
				
				logger.log(LogStatus.PASS, "Redirected to Supernet Buy Packs Page");
				getResult1("pass");
				ClickEvents("backtohome");
			}
			
		//waituntillfound("MultiAccount_Register");
			Thread.sleep(2000);
			
		if(driver.findElements(By.xpath(obj.getProperty("MultiAccount_Register"))).size()!=0) {
			ClickEvents("MultiAccount_Register");
			if(AppValidation("Refer_Titile").equals("Register a friend/family"))  {
				logger.log(LogStatus.PASS, "Redirected to Register Page");
				getResult1("pass");
				ClickEvents("backtohome");	
				getResult1("pass");
			} else {
				logger.log(LogStatus.FAIL, "Redirection failed");
				}			
			} else {
				String RegStatus = otprecive("RegisterSim","RegisterStatus");				
				if(RegStatus.equals("0")) {
					logger.log(LogStatus.PASS, "Register Sim is not available as user already registered");
				} else {
					logger.log(LogStatus.FAIL, "User Not Reqistered and Register Sim Text not displayed");
				}
				
			}		
		
		if(usertype.equals("Prepaid") && !AppValidation("ManageAccountBalanceTrans").isEmpty()) {
			ClickEvents("ManageAccountBalanceTrans");
			waituntillfound("subscribe_confirm_pop_up1");
			if(driver.findElements(By.xpath(obj.getProperty("subscribe_confirm_pop_up1"))).size()!=0 && AppValidation("subscribe_confirm_pop_up1").equals("To transfer your balance you must connect to Ooredoo 3G/4G only.")) {
				logger.log(LogStatus.PASS, AppValidation("subscribe_confirm_pop_up1"));
				getResult1("pass");
				ClickEvents("Ok_button");
			} else {
				logger.log(LogStatus.FAIL, "Balance Transfer HE Error Popup not displayed");
			}
			
		}
		waituntillfound("ManageAccountRefreshButton");
		ClickEvents("ManageAccountRefreshButton");
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
