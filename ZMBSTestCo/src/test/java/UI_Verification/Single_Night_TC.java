package UI_Verification;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Utility.ApplicationListners;
import Utility.ConfigReader;
import Utility.GenericUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(ApplicationListners.class)

public class Single_Night_TC {
	private GenericUtility gu = new GenericUtility();
	private WebDriver driver;
	private ConfigReader cr = new ConfigReader();
	private Properties prop;

	@BeforeSuite
	public void getProperty() {					
		ConfigReader cr = new ConfigReader();
		prop = cr.init_prop();
	}	
	
	
	@Test(priority=1)
	 public void Single_Night_Single_RoomBooking() throws InterruptedException {
		 BigDecimal sum = new BigDecimal("0.00"); 
			//randomCheckInOutMonthYearDate     SingleNight_SingleRoom_randomCheckInOutMonthYearDate
		    ArrayList<String> randomDates = GenericUtility.SingleNight_SingleRoom_randomCheckInOutMonthYearDate();
		    String CheckinMonthandYear = randomDates.get(0);
		    String CheckinDate = randomDates.get(1);
		    String CheckOutMonthYear = randomDates.get(2);
		    String CheckOutDate = randomDates.get(3);
		    String API_CheckInDate = randomDates.get(4);
		    String API_CheckOutDate = randomDates.get(5);
		    String No_of_Booking_Days = randomDates.get(6);
		    String API_Checkindate_plus1_=randomDates.get(7); 
		    driver = gu.setup("chrome", prop.getProperty("URL"));
	        gu.LogPrinter("URL Launched successfully");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        try {
	            WebElement closeButton = driver.findElement(By.cssSelector(".btn-close"));
	            gu.ClickByJS(driver, closeButton);
	            //closeButton.click();
	            // Continue with the rest of your code after clicking the element
	            gu.LogPrinter(" Continue with the rest of your code after clicking the Close Button");
	        } catch (NoSuchElementException e) {
	            // Element not found, continue without clicking
	        	gu.LogPrinter(" Close Button not found, continue without clicking");
	            // Add any desired actions or leave it empty
	        }

	        WebElement StartDateInputField = driver.findElement(By.xpath("//div[@id='start-Date-Input']"));
	        gu.ClickByJS(driver, StartDateInputField);
	        // StartDateInputField.click();
	        gu.ScrollByDpwnarrow(driver, 2);
	        
	        gu.LogPrinter("<======Check-in Date for Booking: " + API_CheckInDate + "======>");
	        gu.LogPrinter("<======Check-out Date for Booking: " + API_CheckOutDate + "=====>");
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.LogPrinter("No of Booking Days: " + No_of_Booking_Days);
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.CheckINOUTDateProvider(driver, CheckinMonthandYear, CheckinDate, CheckOutMonthYear, CheckOutDate);
	        Thread.sleep(2000);
	        
	        try {
	        WebElement BookBtn=driver.findElement(By.xpath("//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"]"));
	        gu.ClickByJS(driver, BookBtn);
	        }
	        catch(Exception e) {
	        	 gu.LogPrinter("Rooms not available for Booking ");
		            gu.LogPrinter("");
					gu.LogPrinter("");
		            driver.close();	
		            return;
	        }
	        
	        
	        WebElement CompleteBookingBtn = driver
					.findElement(By.xpath("//button[@class=\"sr-sbmt\" and text()=\"Complete Booking\"]"));
			gu.ClickByJS(driver, CompleteBookingBtn);
			
			try {
				WebElement PrivacyStatePopUp=driver.findElement(By.xpath("//button[@class=\"btn-close\"]"));
				PrivacyStatePopUp.click();
			}
			catch(Exception e ) {
				//System.out.println("Privacy Statement Not Applied");
			}
			
			WebElement AdultDropdowncartPage=driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"adult\"]"));
			gu.selectbyvisibleText(AdultDropdowncartPage, "1");
			System.out.println("Adult Selected Successfully");
			try {
			    WebElement childDropdowncartPage = driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"child\"]"));
			    gu.selectbyvisibleText(childDropdowncartPage, "1");
			    System.out.println("Child Selected Successfully");
			} catch (NoSuchElementException e) {
			    System.out.println("Child dropdown element not found");
			    // Handle the exception or perform alternative actions
			}
			
			gu.LogPrinter("--Guest Details-- ");
			WebElement FirstName = driver.findElement(By.xpath("//input[@id=\"f_0_0\"]"));
			FirstName.sendKeys(prop.getProperty("FirstName"));
			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
			WebElement LastName = driver.findElement(By.xpath("//input[@id=\"l_0_0\"]"));
			LastName.sendKeys(prop.getProperty("LastName"));
			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
			
			
			
			WebElement MobileNo = driver.findElement(By.xpath("//input[@id=\"phone\"]"));
			MobileNo.sendKeys(prop.getProperty("MobileNumber"));
			gu.LogPrinter("Contact no of Guest :- " + prop.getProperty("MobileNumber"));
			WebElement MailId = driver.findElement(By.xpath("//input[@id=\"email\"]"));
			MailId.sendKeys(prop.getProperty("MailID"));
			gu.LogPrinter("Mail id of Guest :- " + prop.getProperty("MailID"));
			WebElement ConfirmBookingBtn = driver.findElement(By.xpath("//button[@class=\"bkrm confirm-booking mb-0\" and @name=\"payMethod\"]"));
			gu.ClickByJS(driver, ConfirmBookingBtn);
			
			WebElement Credit_Card_No=driver.findElement(By.xpath("//input[@class=\"form-control card-number-input\"]"));
			Credit_Card_No.sendKeys(prop.getProperty("CreditCardNumber"));
			
			WebElement ExpiryDateMM=driver.findElement(By.xpath("//i[@class=\"fa-solid fa-angle-down\"]"));
			gu.ClickByJS(driver, ExpiryDateMM);
			WebElement ExpiryMonth=driver.findElement(By.xpath("//li[@id=\"MM6\"]"));
			gu.ClickByJS(driver, ExpiryMonth);
			
			WebElement ExpiryYear=driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-angle-down\"])[2]"));
			gu.ClickByJS(driver, ExpiryYear);
			WebElement ExpYear=driver.findElement(By.xpath("//li[@id=\"2028\"]"));
			gu.ClickByJS(driver, ExpYear);
			WebElement CVV=driver.findElement(By.xpath("//input[@placeholder=\"CVV\"]"));
			CVV.sendKeys("123");
			WebElement ConfirmBtnCredit=driver.findElement(By.xpath("//button[@id=\"btn1\"]"));
			gu.ClickByJS(driver, ConfirmBtnCredit);
			driver.close();
	 }
	
	@Test(priority=2)
	 public void Single_Night_Single_Room_SingleBooking_With_Single_Addon() throws InterruptedException {

		 BigDecimal sum = new BigDecimal("0.00"); 
			//randomCheckInOutMonthYearDate     SingleNight_SingleRoom_randomCheckInOutMonthYearDate
		    ArrayList<String> randomDates = GenericUtility.SingleNight_SingleRoom_randomCheckInOutMonthYearDate();
		    String CheckinMonthandYear = randomDates.get(0);
		    String CheckinDate = randomDates.get(1);
		    String CheckOutMonthYear = randomDates.get(2);
		    String CheckOutDate = randomDates.get(3);
		    String API_CheckInDate = randomDates.get(4);
		    String API_CheckOutDate = randomDates.get(5);
		    String No_of_Booking_Days = randomDates.get(6);
		    String API_Checkindate_plus1_=randomDates.get(7); 
		    driver = gu.setup("chrome", prop.getProperty("URL"));
	        gu.LogPrinter("URL Launched successfully");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        try {
	            WebElement closeButton = driver.findElement(By.cssSelector(".btn-close"));
	            gu.ClickByJS(driver, closeButton);
	            //closeButton.click();
	            // Continue with the rest of your code after clicking the element
	            gu.LogPrinter(" Continue with the rest of your code after clicking the Close Button");
	        } catch (NoSuchElementException e) {
	            // Element not found, continue without clicking
	        	gu.LogPrinter(" Close Button not found, continue without clicking");
	            // Add any desired actions or leave it empty
	        }

	        WebElement StartDateInputField = driver.findElement(By.xpath("//div[@id='start-Date-Input']"));
	        gu.ClickByJS(driver, StartDateInputField);
	        // StartDateInputField.click();
	        gu.ScrollByDpwnarrow(driver, 2);
	        
	        gu.LogPrinter("<======Check-in Date for Booking: " + API_CheckInDate + "======>");
	        gu.LogPrinter("<======Check-out Date for Booking: " + API_CheckOutDate + "=====>");
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.LogPrinter("No of Booking Days: " + No_of_Booking_Days);
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.CheckINOUTDateProvider(driver, CheckinMonthandYear, CheckinDate, CheckOutMonthYear, CheckOutDate);
	        Thread.sleep(2000);
	        try {
	        WebElement BookBtn=driver.findElement(By.xpath("//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"]"));
	        gu.ClickByJS(driver, BookBtn);
	        }
	        catch(Exception e) {
       	 gu.LogPrinter("Rooms not available for Booking ");
	            gu.LogPrinter("");
				gu.LogPrinter("");
	            driver.close();	
	            return;
	        }
	        WebElement CompleteBookingBtn = driver
					.findElement(By.xpath("//button[@class=\"sr-sbmt\" and text()=\"Complete Booking\"]"));
			gu.ClickByJS(driver, CompleteBookingBtn);
			
			try {
				WebElement PrivacyStatePopUp=driver.findElement(By.xpath("//button[@class=\"btn-close\"]"));
				PrivacyStatePopUp.click();
			}
			catch(Exception e ) {
				//System.out.println("Privacy Statement Not Applied");
			}
			
			WebElement AdultDropdowncartPage=driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"adult\"]"));
			gu.selectbyvisibleText(AdultDropdowncartPage, "1");
			System.out.println("Adult Selected Successfully");
			try {
			    WebElement childDropdowncartPage = driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"child\"]"));
			    gu.selectbyvisibleText(childDropdowncartPage, "1");
			    System.out.println("Child Selected Successfully");
			} catch (NoSuchElementException e) {
			    System.out.println("Child dropdown element not found");
			    // Handle the exception or perform alternative actions
			}
			
			gu.LogPrinter("--Guest Details-- ");
			WebElement FirstName = driver.findElement(By.xpath("//input[@id=\"f_0_0\"]"));
			FirstName.sendKeys(prop.getProperty("FirstName"));
			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
			WebElement LastName = driver.findElement(By.xpath("//input[@id=\"l_0_0\"]"));
			LastName.sendKeys(prop.getProperty("LastName"));
			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
			
			
			
			WebElement MobileNo = driver.findElement(By.xpath("//input[@id=\"phone\"]"));
			MobileNo.sendKeys(prop.getProperty("MobileNumber"));
			gu.LogPrinter("Contact no of Guest :- " + prop.getProperty("MobileNumber"));
			WebElement MailId = driver.findElement(By.xpath("//input[@id=\"email\"]"));
			MailId.sendKeys(prop.getProperty("MailID"));
			gu.LogPrinter("Mail id of Guest :- " + prop.getProperty("MailID"));
			
			WebElement AddonADDbtn=driver.findElement(By.xpath("(//button[@class=\"addon-add-btn bkrm m-0\"])[1]"));
			gu.ClickByJS(driver, AddonADDbtn);	
			
			WebElement AddonCheckBox=driver.findElement(By.xpath("//input[@class=\"form-check-input mt-0\"]"));
			gu.ClickByJS(driver, AddonCheckBox);
			WebElement AddonApplyBtn=driver.findElement(By.xpath("//button[@class=\"bkrm w-auto fs14 fw500 btn btn-primary\"]"));
			gu.ClickByJS(driver, AddonApplyBtn);
			
	//		WebElement ApplyAddonBtn=driver.findElement(By.xpath("//button[@class=\"bkrm w-auto fs14 fw500 btn btn-primary\" and @type=\"button\"]"));
	//		gu.ClickByJS(driver, ApplyAddonBtn);
			
			
			
			WebElement ConfirmBookingBtn = driver.findElement(By.xpath("//button[@class=\"bkrm confirm-booking mb-0\" and @name=\"payMethod\"]"));
			gu.ClickByJS(driver, ConfirmBookingBtn);
			
			WebElement Credit_Card_No=driver.findElement(By.xpath("//input[@class=\"form-control card-number-input\"]"));
			Credit_Card_No.sendKeys(prop.getProperty("CreditCardNumber"));
			
			WebElement ExpiryDateMM=driver.findElement(By.xpath("//i[@class=\"fa-solid fa-angle-down\"]"));
			gu.ClickByJS(driver, ExpiryDateMM);
			WebElement ExpiryMonth=driver.findElement(By.xpath("//li[@id=\"MM6\"]"));
			gu.ClickByJS(driver, ExpiryMonth);
			
			WebElement ExpiryYear=driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-angle-down\"])[2]"));
			gu.ClickByJS(driver, ExpiryYear);
			WebElement ExpYear=driver.findElement(By.xpath("//li[@id=\"2028\"]"));
			gu.ClickByJS(driver, ExpYear);
			WebElement CVV=driver.findElement(By.xpath("//input[@placeholder=\"CVV\"]"));
			CVV.sendKeys("123");
			WebElement ConfirmBtnCredit=driver.findElement(By.xpath("//button[@id=\"btn1\"]"));
			gu.ClickByJS(driver, ConfirmBtnCredit);
			driver.close();
	 
	 }
	
	 @Test(priority=3)
	 public void Single_Night_Single_Room_Booking_With_PromoCode() throws InterruptedException {
		 BigDecimal sum = new BigDecimal("0.00"); 
			//randomCheckInOutMonthYearDate     SingleNight_SingleRoom_randomCheckInOutMonthYearDate
		    ArrayList<String> randomDates = GenericUtility.SingleNight_SingleRoom_randomCheckInOutMonthYearDate();
		    String CheckinMonthandYear = randomDates.get(0);
		    String CheckinDate = randomDates.get(1);
		    String CheckOutMonthYear = randomDates.get(2);
		    String CheckOutDate = randomDates.get(3);
		    String API_CheckInDate = randomDates.get(4);
		    String API_CheckOutDate = randomDates.get(5);
		    String No_of_Booking_Days = randomDates.get(6);
		    String API_Checkindate_plus1_=randomDates.get(7); 
		    driver = gu.setup("chrome", prop.getProperty("URL"));
	        gu.LogPrinter("URL Launched successfully");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        try {
	            WebElement closeButton = driver.findElement(By.cssSelector(".btn-close"));
	            gu.ClickByJS(driver, closeButton);
	            //closeButton.click();
	            // Continue with the rest of your code after clicking the element
	            gu.LogPrinter(" Continue with the rest of your code after clicking the Close Button");
	        } catch (NoSuchElementException e) {
	            // Element not found, continue without clicking
	        	gu.LogPrinter(" Close Button not found, continue without clicking");
	            // Add any desired actions or leave it empty
	        }

	        WebElement StartDateInputField = driver.findElement(By.xpath("//div[@id='start-Date-Input']"));
	        gu.ClickByJS(driver, StartDateInputField);
	        // StartDateInputField.click();
	        gu.ScrollByDpwnarrow(driver, 2);
	        
	        gu.LogPrinter("<======Check-in Date for Booking: " + API_CheckInDate + "======>");
	        gu.LogPrinter("<======Check-out Date for Booking: " + API_CheckOutDate + "=====>");
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.LogPrinter("No of Booking Days: " + No_of_Booking_Days);
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.CheckINOUTDateProvider(driver, CheckinMonthandYear, CheckinDate, CheckOutMonthYear, CheckOutDate);
	        Thread.sleep(2000);
	        
	        WebElement PromoInput=driver.findElement(By.xpath("//input[@class=\"PromoInput form-control promoinput fs14 fw400\"]"));
	        PromoInput.sendKeys("MBR");
	        WebElement Searchbtn=driver.findElement(By.xpath("//button[@class=\"Apply-btn\"]"));
	        gu.ClickByJS(driver, Searchbtn);
	        //Thread.sleep(5000);
	        
	        try {
	        WebElement BookBtn=driver.findElement(By.xpath("//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"]"));
	        gu.ClickByJS(driver, BookBtn);
	        }
	        catch(Exception e) {
	        	 gu.LogPrinter("Rooms not available for Booking ");
		            gu.LogPrinter("");
					gu.LogPrinter("");
		            driver.close();		
		            return;
	        }
	        
	        WebElement CompleteBookingBtn = driver
					.findElement(By.xpath("//button[@class=\"sr-sbmt\" and text()=\"Complete Booking\"]"));
			gu.ClickByJS(driver, CompleteBookingBtn);
			
			try {
				WebElement PrivacyStatePopUp=driver.findElement(By.xpath("//button[@class=\"btn-close\"]"));
				PrivacyStatePopUp.click();
			}
			catch(Exception e ) {
				//System.out.println("Privacy Statement Not Applied");
			}
			
			WebElement AdultDropdowncartPage=driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"adult\"]"));
			gu.selectbyvisibleText(AdultDropdowncartPage, "1");
			System.out.println("Adult Selected Successfully");
			try {
			    WebElement childDropdowncartPage = driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"child\"]"));
			    gu.selectbyvisibleText(childDropdowncartPage, "1");
			    System.out.println("Child Selected Successfully");
			} catch (NoSuchElementException e) {
			    System.out.println("Child dropdown element not found");
			    // Handle the exception or perform alternative actions
			}
			
			gu.LogPrinter("--Guest Details-- ");
			WebElement FirstName = driver.findElement(By.xpath("//input[@id=\"f_0_0\"]"));
			FirstName.sendKeys(prop.getProperty("FirstName"));
			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
			WebElement LastName = driver.findElement(By.xpath("//input[@id=\"l_0_0\"]"));
			LastName.sendKeys(prop.getProperty("LastName"));
			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
			
			
			
			WebElement MobileNo = driver.findElement(By.xpath("//input[@id=\"phone\"]"));
			MobileNo.sendKeys(prop.getProperty("MobileNumber"));
			gu.LogPrinter("Contact no of Guest :- " + prop.getProperty("MobileNumber"));
			WebElement MailId = driver.findElement(By.xpath("//input[@id=\"email\"]"));
			MailId.sendKeys(prop.getProperty("MailID"));
			gu.LogPrinter("Mail id of Guest :- " + prop.getProperty("MailID"));
			
			System.out.println("CTest");
			WebElement ConfirmBookingBtn = driver.findElement(By.xpath("//button[@class=\"bkrm confirm-booking mb-0\" and @name=\"payMethod\"]"));
			gu.ClickByJS(driver, ConfirmBookingBtn);
			System.out.println("DTest");
			
			
			WebElement Credit_Card_No=driver.findElement(By.xpath("//input[@class=\"form-control card-number-input\"]"));
			Credit_Card_No.sendKeys(prop.getProperty("CreditCardNumber"));
			
			WebElement ExpiryDateMM=driver.findElement(By.xpath("//i[@class=\"fa-solid fa-angle-down\"]"));
			gu.ClickByJS(driver, ExpiryDateMM);
			WebElement ExpiryMonth=driver.findElement(By.xpath("//li[@id=\"MM6\"]"));
			gu.ClickByJS(driver, ExpiryMonth);
			
			WebElement ExpiryYear=driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-angle-down\"])[2]"));
			gu.ClickByJS(driver, ExpiryYear);
			WebElement ExpYear=driver.findElement(By.xpath("//li[@id=\"2028\"]"));
			gu.ClickByJS(driver, ExpYear);
			WebElement CVV=driver.findElement(By.xpath("//input[@placeholder=\"CVV\"]"));
			CVV.sendKeys("123");
			WebElement ConfirmBtnCredit=driver.findElement(By.xpath("//button[@id=\"btn1\"]"));
			gu.ClickByJS(driver, ConfirmBtnCredit);	 
		    driver.close();
	 }
	 
	 @Test(priority=4)
	 public void Single_Night_Multiple_RoomBooking_With_Single_RoomType() throws InterruptedException {

		 BigDecimal sum = new BigDecimal("0.00"); 
			//randomCheckInOutMonthYearDate     SingleNight_SingleRoom_randomCheckInOutMonthYearDate
		    ArrayList<String> randomDates = GenericUtility.SingleNight_SingleRoom_randomCheckInOutMonthYearDate();
		    String CheckinMonthandYear = randomDates.get(0);
		    String CheckinDate = randomDates.get(1);
		    String CheckOutMonthYear = randomDates.get(2);
		    String CheckOutDate = randomDates.get(3);
		    String API_CheckInDate = randomDates.get(4);
		    String API_CheckOutDate = randomDates.get(5);
		    String No_of_Booking_Days = randomDates.get(6);
		    String API_Checkindate_plus1_=randomDates.get(7); 
		    driver = gu.setup("chrome", prop.getProperty("URL"));
	        gu.LogPrinter("URL Launched successfully");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        try {
	            WebElement closeButton = driver.findElement(By.cssSelector(".btn-close"));
	            gu.ClickByJS(driver, closeButton);
	            //closeButton.click();
	            // Continue with the rest of your code after clicking the element
	            gu.LogPrinter(" Continue with the rest of your code after clicking the Close Button");
	        } catch (NoSuchElementException e) {
	            // Element not found, continue without clicking
	        	gu.LogPrinter(" Close Button not found, continue without clicking");
	            // Add any desired actions or leave it empty
	        }

	        WebElement StartDateInputField = driver.findElement(By.xpath("//div[@id='start-Date-Input']"));
	        gu.ClickByJS(driver, StartDateInputField);
	        // StartDateInputField.click();
	        gu.ScrollByDpwnarrow(driver, 2);
	        
	        gu.LogPrinter("<======Check-in Date for Booking: " + API_CheckInDate + "======>");
	        gu.LogPrinter("<======Check-out Date for Booking: " + API_CheckOutDate + "=====>");
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.LogPrinter("No of Booking Days: " + No_of_Booking_Days);
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.CheckINOUTDateProvider(driver, CheckinMonthandYear, CheckinDate, CheckOutMonthYear, CheckOutDate);
	        Thread.sleep(2000);
	        
	        try {
	        WebElement RoomsDropDownHomePage=driver.findElement(By.xpath("(//select[@aria-label=\"Default select example\"])[1]"));
	        Select select=new Select(RoomsDropDownHomePage);
	        select.selectByIndex(2);
	        }
	        catch(Exception e) {
	        	 gu.LogPrinter("Rooms not available for Booking ");
		            gu.LogPrinter("");
					gu.LogPrinter("");
		            driver.close();	     
		            return;
	        }
	        WebElement BookBtn=driver.findElement(By.xpath("//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"]"));
	        gu.ClickByJS(driver, BookBtn);
	        WebElement CompleteBookingBtn = driver
					.findElement(By.xpath("//button[@class=\"sr-sbmt\" and text()=\"Complete Booking\"]"));
			gu.ClickByJS(driver, CompleteBookingBtn);
			
			try {
				WebElement PrivacyStatePopUp=driver.findElement(By.xpath("//button[@class=\"btn-close\"]"));
				PrivacyStatePopUp.click();
			}
			catch(Exception e ) {
				//System.out.println("Privacy Statement Not Applied");
			}
			
			WebElement AdultDropdowncartPage=driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"adult\"]"));
			gu.selectbyvisibleText(AdultDropdowncartPage, "1");
			System.out.println("Adult Selected Successfully");
			try {
			    WebElement childDropdowncartPage = driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"child\"]"));
			    gu.selectbyvisibleText(childDropdowncartPage, "1");
			    System.out.println("Child Selected Successfully");
			} catch (NoSuchElementException e) {
			    System.out.println("Child dropdown element not found");
			    // Handle the exception or perform alternative actions
			}
			
			gu.LogPrinter("--Guest Details-- ");
			WebElement FirstName = driver.findElement(By.xpath("//input[@id=\"f_0_0\"]"));
			FirstName.sendKeys(prop.getProperty("FirstName"));
			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
			WebElement LastName = driver.findElement(By.xpath("//input[@id=\"l_0_0\"]"));
			LastName.sendKeys(prop.getProperty("LastName"));
			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
			
// Guest2 First and last name 			
			WebElement FirstNameGuest2=driver.findElement(By.xpath("//input[@id=\"f_0_1\"]"));
			FirstNameGuest2.sendKeys(prop.getProperty("FirstNameGuest2"));
			gu.LogPrinter("First Name of Guest2 :- " + prop.getProperty("FirstNameGuest2"));
			WebElement LastNameGuest2=driver.findElement(By.xpath("//input[@id=\"l_0_1\"]"));
			LastNameGuest2.sendKeys(prop.getProperty("LastNameGuest2"));
			gu.LogPrinter("Last Name of Guest2 :- " + prop.getProperty("LastNameGuest2"));
			
			
			
			WebElement MobileNo = driver.findElement(By.xpath("//input[@id=\"phone\"]"));
			MobileNo.sendKeys(prop.getProperty("MobileNumber"));
			gu.LogPrinter("Contact no of Guest :- " + prop.getProperty("MobileNumber"));
			WebElement MailId = driver.findElement(By.xpath("//input[@id=\"email\"]"));
			MailId.sendKeys(prop.getProperty("MailID"));
			gu.LogPrinter("Mail id of Guest :- " + prop.getProperty("MailID"));
			
			
			
	//		WebElement ApplyAddonBtn=driver.findElement(By.xpath("//button[@class=\"bkrm w-auto fs14 fw500 btn btn-primary\" and @type=\"button\"]"));
	//		gu.ClickByJS(driver, ApplyAddonBtn);
			
			
			
			WebElement ConfirmBookingBtn = driver.findElement(By.xpath("//button[@class=\"bkrm confirm-booking mb-0\" and @name=\"payMethod\"]"));
			gu.ClickByJS(driver, ConfirmBookingBtn);
			
			WebElement Credit_Card_No=driver.findElement(By.xpath("//input[@class=\"form-control card-number-input\"]"));
			Credit_Card_No.sendKeys(prop.getProperty("CreditCardNumber"));
			
			WebElement ExpiryDateMM=driver.findElement(By.xpath("//i[@class=\"fa-solid fa-angle-down\"]"));
			gu.ClickByJS(driver, ExpiryDateMM);
			WebElement ExpiryMonth=driver.findElement(By.xpath("//li[@id=\"MM6\"]"));
			gu.ClickByJS(driver, ExpiryMonth);
			
			WebElement ExpiryYear=driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-angle-down\"])[2]"));
			gu.ClickByJS(driver, ExpiryYear);
			WebElement ExpYear=driver.findElement(By.xpath("//li[@id=\"2028\"]"));
			gu.ClickByJS(driver, ExpYear);
			WebElement CVV=driver.findElement(By.xpath("//input[@placeholder=\"CVV\"]"));
			CVV.sendKeys("123");
			WebElement ConfirmBtnCredit=driver.findElement(By.xpath("//button[@id=\"btn1\"]"));
			gu.ClickByJS(driver, ConfirmBtnCredit);
			driver.close();	 
	 }
	 		
	 @Test(priority=5)
	 public void Single_Night_Multiple_Room_Booking_With_Multiple_RoomTypes() throws InterruptedException {
		 BigDecimal sum = new BigDecimal("0.00"); 
			//randomCheckInOutMonthYearDate     SingleNight_SingleRoom_randomCheckInOutMonthYearDate
		    ArrayList<String> randomDates = GenericUtility.randomCheckInOutMonthYearDate();
		    String CheckinMonthandYear = randomDates.get(0);
		    String CheckinDate = randomDates.get(1);
		    String CheckOutMonthYear = randomDates.get(2);
		    String CheckOutDate = randomDates.get(3);
		    String API_CheckInDate = randomDates.get(4);
		    String API_CheckOutDate = randomDates.get(5);
		    String No_of_Booking_Days = randomDates.get(6);
		    String API_Checkindate_plus1_=randomDates.get(7); 
		    driver = gu.setup("chrome", prop.getProperty("URL"));
	        gu.LogPrinter("URL Launched successfully");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        try {
	            WebElement closeButton = driver.findElement(By.cssSelector(".btn-close"));
	            gu.ClickByJS(driver, closeButton);
	            //closeButton.click();
	            // Continue with the rest of your code after clicking the element
	            gu.LogPrinter(" Continue with the rest of your code after clicking the Close Button");
	        } catch (NoSuchElementException e) {
	            // Element not found, continue without clicking
	        	gu.LogPrinter(" Close Button not found, continue without clicking");
	            // Add any desired actions or leave it empty
	        }

	        WebElement StartDateInputField = driver.findElement(By.xpath("//div[@id='start-Date-Input']"));
	        gu.ClickByJS(driver, StartDateInputField);
	        // StartDateInputField.click();
	        gu.ScrollByDpwnarrow(driver, 2);
	        
	        gu.LogPrinter("<======Check-in Date for Booking: " + API_CheckInDate + "======>");
	        gu.LogPrinter("<======Check-out Date for Booking: " + API_CheckOutDate + "=====>");
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.LogPrinter("No of Booking Days: " + No_of_Booking_Days);
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.CheckINOUTDateProvider(driver, CheckinMonthandYear, CheckinDate, CheckOutMonthYear, CheckOutDate);
	        Thread.sleep(2000);
	        
	        try {
	        WebElement RoomsDropDownHomePage=driver.findElement(By.xpath("(//select[@aria-label=\"Default select example\"])[1]"));
	        Select select=new Select(RoomsDropDownHomePage);
	        select.selectByIndex(2);
	        }
	        catch(Exception e) {
	        	 gu.LogPrinter("Rooms not available for Booking ");
		            gu.LogPrinter("");
					gu.LogPrinter("");
		            driver.close();	
		            return;
	        }
	        WebElement BookBtn=driver.findElement(By.xpath("//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"]"));
	        gu.ClickByJS(driver, BookBtn);
	       
	        try {
	        	WebElement RoomsDropDownHomePage=driver.findElement(By.xpath("(//select[@aria-label=\"Default select example\"])[2]"));
		        Select select=new Select(RoomsDropDownHomePage);
		        select.selectByIndex(2);
		        	
	        }
	        catch(Exception e) {
	        	 gu.LogPrinter("Rooms not available for Booking ");
		            gu.LogPrinter("");
					gu.LogPrinter("");
		            driver.close();
	        	return;
	        }
	        WebElement BookBtn1=driver.findElement(By.xpath("(//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"])[2]"));
	        gu.ClickByJS(driver, BookBtn1);
	       
	        
	        
	        
	        WebElement CompleteBookingBtn = driver
					.findElement(By.xpath("//button[@class=\"sr-sbmt\" and text()=\"Complete Booking\"]"));
			gu.ClickByJS(driver, CompleteBookingBtn);
			
			try {
				WebElement PrivacyStatePopUp=driver.findElement(By.xpath("//button[@class=\"btn-close\"]"));
				PrivacyStatePopUp.click();
			}
			catch(Exception e ) {
				//System.out.println("Privacy Statement Not Applied");
			}
			
			WebElement AdultDropdowncartPage=driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"adult\"]"));
			gu.selectbyvisibleText(AdultDropdowncartPage, "1");
			System.out.println("Adult Selected Successfully");
			try {
			    WebElement childDropdowncartPage = driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"child\"]"));
			    gu.selectbyvisibleText(childDropdowncartPage, "1");
			    System.out.println("Child Selected Successfully");
			} catch (NoSuchElementException e) {
			    System.out.println("Child dropdown element not found");
			    // Handle the exception or perform alternative actions
			}
			
			gu.LogPrinter("--Guest Details-- ");
			WebElement FirstName = driver.findElement(By.xpath("//input[@id=\"f_0_0\"]"));
			FirstName.sendKeys(prop.getProperty("FirstName"));
			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
			WebElement LastName = driver.findElement(By.xpath("//input[@id=\"l_0_0\"]"));
			LastName.sendKeys(prop.getProperty("LastName"));
			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
			
// Guest2 First and last name 			
			WebElement FirstNameGuest2=driver.findElement(By.xpath("//input[@id=\"f_0_1\"]"));
			FirstNameGuest2.sendKeys(prop.getProperty("FirstNameGuest2"));
			gu.LogPrinter("First Name of Guest2 :- " + prop.getProperty("FirstNameGuest2"));
			WebElement LastNameGuest2=driver.findElement(By.xpath("//input[@id=\"l_0_1\"]"));
			LastNameGuest2.sendKeys(prop.getProperty("LastNameGuest2"));
			gu.LogPrinter("Last Name of Guest2 :- " + prop.getProperty("LastNameGuest2"));
			
// for roomtype2
			gu.LogPrinter("--Guest Details-- ");
			WebElement FirstNameRoomType2 = driver.findElement(By.xpath("//input[@id=\"f_1_0\"]"));
			FirstNameRoomType2.sendKeys(prop.getProperty("FirstName"));
			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
			WebElement LastNameRoomType2 = driver.findElement(By.xpath("//input[@id=\"l_1_0\"]"));
			LastNameRoomType2.sendKeys(prop.getProperty("LastName"));
			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
			
			
			WebElement FirstNameGuest2RoomType2=driver.findElement(By.xpath("//input[@id=\"f_1_1\"]"));
			FirstNameGuest2RoomType2.sendKeys(prop.getProperty("FirstNameGuest2"));
			gu.LogPrinter("First Name of Guest2 :- " + prop.getProperty("FirstNameGuest2"));
			WebElement LastNameGuest2RoomType2=driver.findElement(By.xpath("//input[@id=\"l_1_1\"]"));
			LastNameGuest2RoomType2.sendKeys(prop.getProperty("LastNameGuest2"));
			gu.LogPrinter("Last Name of Guest2 :- " + prop.getProperty("LastNameGuest2"));
			
			
			
			WebElement MobileNo = driver.findElement(By.xpath("//input[@id=\"phone\"]"));
			MobileNo.sendKeys(prop.getProperty("MobileNumber"));
			gu.LogPrinter("Contact no of Guest :- " + prop.getProperty("MobileNumber"));
			WebElement MailId = driver.findElement(By.xpath("//input[@id=\"email\"]"));
			MailId.sendKeys(prop.getProperty("MailID"));
			gu.LogPrinter("Mail id of Guest :- " + prop.getProperty("MailID"));
			
			
			
	//		WebElement ApplyAddonBtn=driver.findElement(By.xpath("//button[@class=\"bkrm w-auto fs14 fw500 btn btn-primary\" and @type=\"button\"]"));
	//		gu.ClickByJS(driver, ApplyAddonBtn);
			
			
			
			WebElement ConfirmBookingBtn = driver.findElement(By.xpath("//button[@class=\"bkrm confirm-booking mb-0\" and @name=\"payMethod\"]"));
			gu.ClickByJS(driver, ConfirmBookingBtn);
			
			WebElement Credit_Card_No=driver.findElement(By.xpath("//input[@class=\"form-control card-number-input\"]"));
			Credit_Card_No.sendKeys(prop.getProperty("CreditCardNumber"));
			
			WebElement ExpiryDateMM=driver.findElement(By.xpath("//i[@class=\"fa-solid fa-angle-down\"]"));
			gu.ClickByJS(driver, ExpiryDateMM);
			WebElement ExpiryMonth=driver.findElement(By.xpath("//li[@id=\"MM6\"]"));
			gu.ClickByJS(driver, ExpiryMonth);
			
			WebElement ExpiryYear=driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-angle-down\"])[2]"));
			gu.ClickByJS(driver, ExpiryYear);
			WebElement ExpYear=driver.findElement(By.xpath("//li[@id=\"2028\"]"));
			gu.ClickByJS(driver, ExpYear);
			WebElement CVV=driver.findElement(By.xpath("//input[@placeholder=\"CVV\"]"));
			CVV.sendKeys("123");
			WebElement ConfirmBtnCredit=driver.findElement(By.xpath("//button[@id=\"btn1\"]"));
			gu.ClickByJS(driver, ConfirmBtnCredit);
			driver.close();	 
	 
	 }
	 
	 
	      @Test(priority=6)
	      public void Verify_Single_Night_Single_Room_Booking_TotalAmount_For_Home_Cart_ConfirmationPage()
				throws InterruptedException {
	    	  		
		    BigDecimal sum = new BigDecimal("0.00"); 
			//randomCheckInOutMonthYearDate     SingleNight_SingleRoom_randomCheckInOutMonthYearDate
		    ArrayList<String> randomDates = GenericUtility.SingleNight_SingleRoom_randomCheckInOutMonthYearDate();
		    String CheckinMonthandYear = randomDates.get(0);
		    String CheckinDate = randomDates.get(1);
		    String CheckOutMonthYear = randomDates.get(2);
		    String CheckOutDate = randomDates.get(3);
		    String API_CheckInDate = randomDates.get(4);
		    String API_CheckOutDate = randomDates.get(5);
		    String No_of_Booking_Days = randomDates.get(6);
		    String API_Checkindate_plus1_=randomDates.get(7); 
		    driver = gu.setup("chrome", prop.getProperty("URL"));
	        gu.LogPrinter("URL Launched successfully");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        try {
	            WebElement closeButton = driver.findElement(By.cssSelector(".btn-close"));
	            gu.ClickByJS(driver, closeButton);
	            //closeButton.click();
	            // Continue with the rest of your code after clicking the element
	            gu.LogPrinter(" Continue with the rest of your code after clicking the Close Button");
	        } catch (NoSuchElementException e) {
	            // Element not found, continue without clicking
	        	gu.LogPrinter(" Close Button not found, continue without clicking");
	            // Add any desired actions or leave it empty
	        }

	        WebElement StartDateInputField = driver.findElement(By.xpath("//div[@id='start-Date-Input']"));
	        gu.ClickByJS(driver, StartDateInputField);
	        // StartDateInputField.click();
	        gu.ScrollByDpwnarrow(driver, 2);
	        
	        gu.LogPrinter("<======Check-in Date for Booking: " + API_CheckInDate + "======>");
	        gu.LogPrinter("<======Check-out Date for Booking: " + API_CheckOutDate + "=====>");
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.LogPrinter("No of Booking Days: " + No_of_Booking_Days);
	        gu.LogPrinter("");
	        gu.LogPrinter("");
	        gu.CheckINOUTDateProvider(driver, CheckinMonthandYear, CheckinDate, CheckOutMonthYear, CheckOutDate);
	        Thread.sleep(2000);
	        
	        try {
	        WebElement BookBtn=driver.findElement(By.xpath("//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"]"));
	        gu.ClickByJS(driver, BookBtn);
	        }
	        catch(Exception e) {
	        	 gu.LogPrinter("Rooms not available for Booking ");
		            gu.LogPrinter("");
					gu.LogPrinter("");
		            driver.close();	
		            return;
	        }   
	      //label[text()=\"Total Amount \"]//following-sibling::span
	        
	        WebElement TotalAmount=driver.findElement(By.xpath("//span[@class=\"tt-amount\"]"));
	        String HomePageTotalAmount=TotalAmount.getText();
	        gu.LogPrinter("HomePageTotalAmount  :-  "+HomePageTotalAmount);
	        
	        
	        WebElement CompleteBookingBtn = driver
					.findElement(By.xpath("//button[@class=\"sr-sbmt\" and text()=\"Complete Booking\"]"));
			gu.ClickByJS(driver, CompleteBookingBtn);
			
			try {
				WebElement PrivacyStatePopUp=driver.findElement(By.xpath("//button[@class=\"btn-close\"]"));
				PrivacyStatePopUp.click();
			}
			catch(Exception e ) {
				//System.out.println("Privacy Statement Not Applied");
			}				
			
			WebElement AdultDropdowncartPage=driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"adult\"]"));
			gu.selectbyvisibleText(AdultDropdowncartPage, "1");
			System.out.println("Adult Selected Successfully");
			try {
			    WebElement childDropdowncartPage = driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"child\"]"));
			    gu.selectbyvisibleText(childDropdowncartPage, "0");
			    System.out.println("Child Selected Successfully");
			} catch (NoSuchElementException e) {
			    System.out.println("Child dropdown element not found");
			    // Handle the exception or perform alternative actions
			}
			
			WebElement CartPageTotalAmount=driver.findElement(By.xpath("//span[@class=\"tt-amount\"]"));
			String CartPageTotal=CartPageTotalAmount.getText();
			gu.LogPrinter("CartPageTotal Amount :- "+CartPageTotal);
			
			AssertJUnit.assertEquals(HomePageTotalAmount, CartPageTotal);
			gu.LogPrinter("CartPage Total Amount Validated with HomePage Total Amount Successfully ");
			
			gu.LogPrinter("--Guest Details-- ");
			WebElement FirstName = driver.findElement(By.xpath("//input[@id=\"f_0_0\"]"));
			FirstName.sendKeys(prop.getProperty("FirstName"));
			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
			WebElement LastName = driver.findElement(By.xpath("//input[@id=\"l_0_0\"]"));
			LastName.sendKeys(prop.getProperty("LastName"));
			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
			
			
			
			WebElement MobileNo = driver.findElement(By.xpath("//input[@id=\"phone\"]"));
			MobileNo.sendKeys(prop.getProperty("MobileNumber"));
			gu.LogPrinter("Contact no of Guest :- " + prop.getProperty("MobileNumber"));
			WebElement MailId = driver.findElement(By.xpath("//input[@id=\"email\"]"));
			MailId.sendKeys(prop.getProperty("MailID"));
			gu.LogPrinter("Mail id of Guest :- " + prop.getProperty("MailID"));
			WebElement ConfirmBookingBtn = driver.findElement(By.xpath("//button[@class=\"bkrm confirm-booking mb-0\" and @name=\"payMethod\"]"));
			gu.ClickByJS(driver, ConfirmBookingBtn);
			
			WebElement Credit_Card_No=driver.findElement(By.xpath("//input[@class=\"form-control card-number-input\"]"));
			Credit_Card_No.sendKeys(prop.getProperty("CreditCardNumber"));
			
			WebElement ExpiryDateMM=driver.findElement(By.xpath("//i[@class=\"fa-solid fa-angle-down\"]"));
			gu.ClickByJS(driver, ExpiryDateMM);
			WebElement ExpiryMonth=driver.findElement(By.xpath("//li[@id=\"MM6\"]"));
			gu.ClickByJS(driver, ExpiryMonth);
			
			WebElement ExpiryYear=driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-angle-down\"])[2]"));
			gu.ClickByJS(driver, ExpiryYear);
			WebElement ExpYear=driver.findElement(By.xpath("//li[@id=\"2028\"]"));
			gu.ClickByJS(driver, ExpYear);
			WebElement CVV=driver.findElement(By.xpath("//input[@placeholder=\"CVV\"]"));
			CVV.sendKeys("123");
			WebElement ConfirmBtnCredit=driver.findElement(By.xpath("//button[@id=\"btn1\"]"));
			gu.ClickByJS(driver, ConfirmBtnCredit);
			
			gu.implicitwait(5);
			WebElement ConfirmPageTotal=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[4]/td[2]"));
			String ConfiramtionPageTotalAmount=ConfirmPageTotal.getText();
			gu.LogPrinter("ConfiramtionPageTotalAmount  :- "+ConfiramtionPageTotalAmount);
			
			AssertJUnit.assertEquals(gu.DoubleTypeCasting(ConfiramtionPageTotalAmount), gu.DoubleTypeCasting(CartPageTotal));
			gu.LogPrinter("ConfiramtionPage Total Amount Validated With CartPage Total Amount Successfully");
			driver.close();
	 
	 }
	      
	 
	     
	      
	      @Test(priority=7)
	      public void Verify_Single_Night_Single_Room_Booking_Confirmation_Status() throws InterruptedException {

	 		    BigDecimal sum = new BigDecimal("0.00"); 
	 			//randomCheckInOutMonthYearDate     SingleNight_SingleRoom_randomCheckInOutMonthYearDate
	 		    ArrayList<String> randomDates = GenericUtility.SingleNight_SingleRoom_randomCheckInOutMonthYearDate();
	 		    String CheckinMonthandYear = randomDates.get(0);
	 		    String CheckinDate = randomDates.get(1);
	 		    String CheckOutMonthYear = randomDates.get(2);
	 		    String CheckOutDate = randomDates.get(3);
	 		    String API_CheckInDate = randomDates.get(4);
	 		    String API_CheckOutDate = randomDates.get(5);
	 		    String No_of_Booking_Days = randomDates.get(6);
	 		    String API_Checkindate_plus1_=randomDates.get(7); 
	 		    driver = gu.setup("chrome", prop.getProperty("URL"));
	 	        gu.LogPrinter("URL Launched successfully");
	 	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	 	        try {
	 	            WebElement closeButton = driver.findElement(By.cssSelector(".btn-close"));
	 	            gu.ClickByJS(driver, closeButton);
	 	            //closeButton.click();
	 	            // Continue with the rest of your code after clicking the element
	 	            gu.LogPrinter(" Continue with the rest of your code after clicking the Close Button");
	 	        } catch (NoSuchElementException e) {
	 	            // Element not found, continue without clicking
	 	        	gu.LogPrinter(" Close Button not found, continue without clicking");
	 	            // Add any desired actions or leave it empty
	 	        }

	 	        WebElement StartDateInputField = driver.findElement(By.xpath("//div[@id='start-Date-Input']"));
	 	        gu.ClickByJS(driver, StartDateInputField);
	 	        // StartDateInputField.click();
	 	        gu.ScrollByDpwnarrow(driver, 2);
	 	        
	 	        gu.LogPrinter("<======Check-in Date for Booking: " + API_CheckInDate + "======>");
	 	        gu.LogPrinter("<======Check-out Date for Booking: " + API_CheckOutDate + "=====>");
	 	        gu.LogPrinter("");
	 	        gu.LogPrinter("");
	 	        gu.LogPrinter("No of Booking Days: " + No_of_Booking_Days);
	 	        gu.LogPrinter("");
	 	        gu.LogPrinter("");
	 	        gu.CheckINOUTDateProvider(driver, CheckinMonthandYear, CheckinDate, CheckOutMonthYear, CheckOutDate);
	 	        Thread.sleep(2000);
	 	        
	 	        try {
	 	        WebElement BookBtn=driver.findElement(By.xpath("//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"]"));
	 	        gu.ClickByJS(driver, BookBtn);
	 	        }
	 	        catch(Exception e) {
	 	        	 gu.LogPrinter("Rooms not available for Booking ");
	 		            gu.LogPrinter("");
	 					gu.LogPrinter("");
	 		            driver.close();	
	 		            return;
	 	        }
	 	        
	 	        
	 	        WebElement CompleteBookingBtn = driver
	 					.findElement(By.xpath("//button[@class=\"sr-sbmt\" and text()=\"Complete Booking\"]"));
	 			gu.ClickByJS(driver, CompleteBookingBtn);
	 			
	 			try {
	 				WebElement PrivacyStatePopUp=driver.findElement(By.xpath("//button[@class=\"btn-close\"]"));
	 				PrivacyStatePopUp.click();
	 			}
	 			catch(Exception e ) {
	 				//System.out.println("Privacy Statement Not Applied");
	 			}
	 			
	 			WebElement AdultDropdowncartPage=driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"adult\"]"));
	 			gu.selectbyvisibleText(AdultDropdowncartPage, "1");
	 			System.out.println("Adult Selected Successfully");
	 			try {
	 			    WebElement childDropdowncartPage = driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"child\"]"));
	 			    gu.selectbyvisibleText(childDropdowncartPage, "1");
	 			    System.out.println("Child Selected Successfully");
	 			} catch (NoSuchElementException e) {
	 			    System.out.println("Child dropdown element not found");
	 			    // Handle the exception or perform alternative actions
	 			}
	 			
	 			gu.LogPrinter("--Guest Details-- ");
	 			WebElement FirstName = driver.findElement(By.xpath("//input[@id=\"f_0_0\"]"));
	 			FirstName.sendKeys(prop.getProperty("FirstName"));
	 			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
	 			WebElement LastName = driver.findElement(By.xpath("//input[@id=\"l_0_0\"]"));
	 			LastName.sendKeys(prop.getProperty("LastName"));
	 			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
	 			
	 			
	 			
	 			WebElement MobileNo = driver.findElement(By.xpath("//input[@id=\"phone\"]"));
	 			MobileNo.sendKeys(prop.getProperty("MobileNumber"));
	 			gu.LogPrinter("Contact no of Guest :- " + prop.getProperty("MobileNumber"));
	 			WebElement MailId = driver.findElement(By.xpath("//input[@id=\"email\"]"));
	 			MailId.sendKeys(prop.getProperty("MailID"));
	 			gu.LogPrinter("Mail id of Guest :- " + prop.getProperty("MailID"));
	 			WebElement ConfirmBookingBtn = driver.findElement(By.xpath("//button[@class=\"bkrm confirm-booking mb-0\" and @name=\"payMethod\"]"));
	 			gu.ClickByJS(driver, ConfirmBookingBtn);
	 			
	 			WebElement Credit_Card_No=driver.findElement(By.xpath("//input[@class=\"form-control card-number-input\"]"));
	 			Credit_Card_No.sendKeys(prop.getProperty("CreditCardNumber"));
	 			
	 			WebElement ExpiryDateMM=driver.findElement(By.xpath("//i[@class=\"fa-solid fa-angle-down\"]"));
	 			gu.ClickByJS(driver, ExpiryDateMM);
	 			WebElement ExpiryMonth=driver.findElement(By.xpath("//li[@id=\"MM6\"]"));
	 			gu.ClickByJS(driver, ExpiryMonth);
	 			
	 			WebElement ExpiryYear=driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-angle-down\"])[2]"));
	 			gu.ClickByJS(driver, ExpiryYear);
	 			WebElement ExpYear=driver.findElement(By.xpath("//li[@id=\"2028\"]"));
	 			gu.ClickByJS(driver, ExpYear);
	 			WebElement CVV=driver.findElement(By.xpath("//input[@placeholder=\"CVV\"]"));
	 			CVV.sendKeys("123");
	 			WebElement ConfirmBtnCredit=driver.findElement(By.xpath("//button[@id=\"btn1\"]"));
	 			gu.ClickByJS(driver, ConfirmBtnCredit);
	 			
	 			WebElement ConfirmationPageStatus=driver.findElement(By.xpath("//span[text()=\"Status:\"]//following::span[1]"));
	 			String status=ConfirmationPageStatus.getText();
	 			AssertJUnit.assertEquals(status, "Confirmed");
	 			gu.LogPrinter(" Booking Status Verified Successfully ");
	 			
	 			driver.close();
	 	 
	      }
	      
	      @Test(priority=8)
	      public void Verify_MailId_MobileNO_for_SingleNight_SingleRoom_() throws InterruptedException {
	 		    BigDecimal sum = new BigDecimal("0.00"); 
	 			//randomCheckInOutMonthYearDate     SingleNight_SingleRoom_randomCheckInOutMonthYearDate
	 		    ArrayList<String> randomDates = GenericUtility.SingleNight_SingleRoom_randomCheckInOutMonthYearDate();
	 		    String CheckinMonthandYear = randomDates.get(0);
	 		    String CheckinDate = randomDates.get(1);
	 		    String CheckOutMonthYear = randomDates.get(2);
	 		    String CheckOutDate = randomDates.get(3);
	 		    String API_CheckInDate = randomDates.get(4);
	 		    String API_CheckOutDate = randomDates.get(5);
	 		    String No_of_Booking_Days = randomDates.get(6);
	 		    String API_Checkindate_plus1_=randomDates.get(7); 
	 		    driver = gu.setup("chrome", prop.getProperty("URL"));
	 	        gu.LogPrinter("URL Launched successfully");
	 	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	 	        try {
	 	            WebElement closeButton = driver.findElement(By.cssSelector(".btn-close"));
	 	            gu.ClickByJS(driver, closeButton);
	 	            //closeButton.click();
	 	            // Continue with the rest of your code after clicking the element
	 	            gu.LogPrinter(" Continue with the rest of your code after clicking the Close Button");
	 	        } catch (NoSuchElementException e) {
	 	            // Element not found, continue without clicking
	 	        	gu.LogPrinter(" Close Button not found, continue without clicking");
	 	            // Add any desired actions or leave it empty
	 	        }

	 	        WebElement StartDateInputField = driver.findElement(By.xpath("//div[@id='start-Date-Input']"));
	 	        gu.ClickByJS(driver, StartDateInputField);
	 	        // StartDateInputField.click();
	 	        gu.ScrollByDpwnarrow(driver, 2);
	 	        
	 	        gu.LogPrinter("<======Check-in Date for Booking: " + API_CheckInDate + "======>");
	 	        gu.LogPrinter("<======Check-out Date for Booking: " + API_CheckOutDate + "=====>");
	 	        gu.LogPrinter("");
	 	        gu.LogPrinter("");
	 	        gu.LogPrinter("No of Booking Days: " + No_of_Booking_Days);
	 	        gu.LogPrinter("");
	 	        gu.LogPrinter("");
	 	        gu.CheckINOUTDateProvider(driver, CheckinMonthandYear, CheckinDate, CheckOutMonthYear, CheckOutDate);
	 	        Thread.sleep(2000);
	 	        
	 	        try {
	 	        WebElement BookBtn=driver.findElement(By.xpath("//button[@class=\"bkrm tabBtnNone m-md-0 notranslate\"]"));
	 	        gu.ClickByJS(driver, BookBtn);
	 	        }
	 	        catch(Exception e) {
	 	        	 gu.LogPrinter("Rooms not available for Booking ");
	 		            gu.LogPrinter("");
	 					gu.LogPrinter("");
	 		            driver.close();	
	 		            return;
	 	        }
	 	        
	 	        
	 	        WebElement CompleteBookingBtn = driver
	 					.findElement(By.xpath("//button[@class=\"sr-sbmt\" and text()=\"Complete Booking\"]"));
	 			gu.ClickByJS(driver, CompleteBookingBtn);
	 			
	 			try {
	 				WebElement PrivacyStatePopUp=driver.findElement(By.xpath("//button[@class=\"btn-close\"]"));
	 				PrivacyStatePopUp.click();
	 			}
	 			catch(Exception e ) {
	 				//System.out.println("Privacy Statement Not Applied");
	 			}
	 			
	 			WebElement AdultDropdowncartPage=driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"adult\"]"));
	 			gu.selectbyvisibleText(AdultDropdowncartPage, "1");
	 			System.out.println("Adult Selected Successfully");
	 			try {
	 			    WebElement childDropdowncartPage = driver.findElement(By.xpath("//select[@class=\"form-control form-select\" and @name=\"child\"]"));
	 			    gu.selectbyvisibleText(childDropdowncartPage, "1");
	 			    System.out.println("Child Selected Successfully");
	 			} catch (NoSuchElementException e) {
	 			    System.out.println("Child dropdown element not found");
	 			    // Handle the exception or perform alternative actions
	 			}
	 			
	 			gu.LogPrinter("--Guest Details-- ");
	 			WebElement FirstName = driver.findElement(By.xpath("//input[@id=\"f_0_0\"]"));
	 			FirstName.sendKeys(prop.getProperty("FirstName"));
	 			gu.LogPrinter("First Name of Guest :- " + prop.getProperty("FirstName"));
	 			WebElement LastName = driver.findElement(By.xpath("//input[@id=\"l_0_0\"]"));
	 			LastName.sendKeys(prop.getProperty("LastName"));
	 			gu.LogPrinter("Last Name of Guest :- " + prop.getProperty("LastName"));
	 			
	 			
	 			
	 			WebElement MobileNo = driver.findElement(By.xpath("//input[@id=\"phone\"]"));
	 			MobileNo.sendKeys(prop.getProperty("MobileNumber"));
	 			gu.LogPrinter("Contact no of Guest :- " + prop.getProperty("MobileNumber"));
	 			WebElement MailId = driver.findElement(By.xpath("//input[@id=\"email\"]"));
	 			MailId.sendKeys(prop.getProperty("MailID"));
	 			gu.LogPrinter("Mail id of Guest :- " + prop.getProperty("MailID"));
	 			WebElement ConfirmBookingBtn = driver.findElement(By.xpath("//button[@class=\"bkrm confirm-booking mb-0\" and @name=\"payMethod\"]"));
	 			gu.ClickByJS(driver, ConfirmBookingBtn);
	 			
	 			WebElement Credit_Card_No=driver.findElement(By.xpath("//input[@class=\"form-control card-number-input\"]"));
	 			Credit_Card_No.sendKeys(prop.getProperty("CreditCardNumber"));
	 			
	 			WebElement ExpiryDateMM=driver.findElement(By.xpath("//i[@class=\"fa-solid fa-angle-down\"]"));
	 			gu.ClickByJS(driver, ExpiryDateMM);
	 			WebElement ExpiryMonth=driver.findElement(By.xpath("//li[@id=\"MM6\"]"));
	 			gu.ClickByJS(driver, ExpiryMonth);
	 			
	 			WebElement ExpiryYear=driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-angle-down\"])[2]"));
	 			gu.ClickByJS(driver, ExpiryYear);
	 			WebElement ExpYear=driver.findElement(By.xpath("//li[@id=\"2028\"]"));
	 			gu.ClickByJS(driver, ExpYear);
	 			WebElement CVV=driver.findElement(By.xpath("//input[@placeholder=\"CVV\"]"));
	 			CVV.sendKeys("123");
	 			WebElement ConfirmBtnCredit=driver.findElement(By.xpath("//button[@id=\"btn1\"]"));
	 			gu.ClickByJS(driver, ConfirmBtnCredit);
	 			
	 			WebElement ConfirmationPageMailID=driver.findElement(By.xpath("//span[@style=\"color: #1db8f0;\"] "));
	 			String ConfirmPageMailId=ConfirmationPageMailID.getText();
	 			gu.LogPrinter("Confirmation Page Mail Id :-  "+ConfirmPageMailId);
	 			
	 			AssertJUnit.assertEquals(ConfirmPageMailId, prop.getProperty("MailID"));
	 			gu.LogPrinter("Mail ID Verified Successfully ");
	 			
	 			WebElement ConfirmationPageMobileNo=driver.findElement(By.xpath("//span[@style=\"color: #1db8f0;\"]//following::span[1] "));
	 			System.out.println(ConfirmationPageMobileNo.getText());
	 			
	 			String ConfirmationPageMobileno = ConfirmationPageMobileNo.getText().replaceAll("\\+91", "");
	 			gu.LogPrinter(" Confirmation Page Mobile no :- "+ConfirmationPageMobileno);
	 			AssertJUnit.assertEquals(prop.getProperty("MobileNumber"), ConfirmationPageMobileno);
	 			gu.LogPrinter(" Mobile Number Verified Successfully ");
	 			
	 			driver.close();
	 	 
	      }
	     @Test
	     public void demo() {
	    	  WebDriverManager.chromedriver().setup();
	 		  ChromeOptions ops = new ChromeOptions();
				ops.addArguments("--remote-allow-origins=*");				
				WebDriver driver = new ChromeDriver(ops);
			
				driver.get("https://openjdk.org/install/");
	     }
}
