package Utility;

import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SU_APIBE_Utility {

	public static String GetPropertyInfo(String propertyID) {
		// https://fetchdata.staah.net/?RequestType=info&PropertyId=514299&DeviceInfo=y

		RestAssured.baseURI = "https://fetchdata.staah.net/";
		Response response = RestAssured.given().param("RequestType", "info").param("PropertyId", propertyID)
				.param("IsObject", "Y").when().header("X-Api-Key", "cPPq1uh0xD6BpfDFpGWEx9fxnDOUA3Y25RdigC0X").get();
		// System.out.println("Response = "+response.getBody().asString());
		// System.out.println(response.prettyPrint());
		String a = response.getBody().asPrettyString();
		// System.out.println(a);
		return a;
	}
				
	public static String GetRateCalculte() {
		// Get request for rate calculation
		RestAssured.baseURI = "https://fetchdata.staah.net/";
		Response response = RestAssured.given().param("RequestType", "bedata").param("Country", "NZ")
				.param("PropertyId", "514299").param("Product", "no").param("CheckOutDate", "2023-05-21")
				.param("CheckInDate", "2023-05-20")

				.when().header("X-Api-Key", "cPPq1uh0xD6BpfDFpGWEx9fxnDOUA3Y25RdigC0X").get();
		String a = response.getBody().asPrettyString();
		// System.out.println(a);
		return a;
	}

	public static String GetRateCalculteUtil(String PropertyID, String checkinDate, String checkoutDate) {
		// Get request for rate calculation
		RestAssured.baseURI = "https://fetchdata.staah.net/";
		Response response = RestAssured.given().param("RequestType", "bedata").param("Country", "NZ")
				.param("PropertyId", PropertyID).param("Product", "no").param("CheckOutDate", checkoutDate)
				.param("CheckInDate", checkinDate)

				.when().header("X-Api-Key", "cPPq1uh0xD6BpfDFpGWEx9fxnDOUA3Y25RdigC0X").get();
		String a = response.getBody().asPrettyString();
		// System.out.println(a);
		return a;
	}

	// generic method for to catch guest rate before tax for all rooms
	public static String Guest1RateBeforeTax(String RoomNO, String Date, String Guestfor) {
		SU_APIBE_Utility.GetRateCalculteUtil("514299", "2023-05-20", "2023-05-21");
		JsonPath js = new JsonPath(SU_APIBE_Utility.GetRateCalculteUtil("514299", "2023-05-20", "2023-05-21"));
		int obplen = js.getInt("Product[0].Rooms[0].RatePlans[0].Rates[\"" + Date + "\"].OBP.1.RateBeforeTax.size()");
		System.out.println(obplen);
		String Guest1RateBeforeTax = js.getString("Product[0].Rooms[" + RoomNO + "].RatePlans[0].Rates[\"" + Date
				+ "\"].OBP." + Guestfor + ".RateBeforeTax");
		System.out.println(Guest1RateBeforeTax);
		return Guest1RateBeforeTax;
	}

	public static String RateAfterTax(String RoomNo, String Date, String Guestfor) {
		SU_APIBE_Utility.GetRateCalculteUtil("514299", "2023-05-20", "2023-05-21");
		JsonPath js = new JsonPath(SU_APIBE_Utility.GetRateCalculteUtil("514299", "2023-05-20", "2023-05-21"));
		String a = js.getString("Product[0].Rooms[" + RoomNo + "].RatePlans[0].Rates[\"" + Date + "\"].OBP." + Guestfor
				+ ".RateAfterTax");
		// System.out.println(a);
		return a;
	}

	public static ArrayList<String> Test(String RoomNo, String Date, String Guestfor) {
		SU_APIBE_Utility.GetRateCalculteUtil("514299", "2023-05-20", "2023-05-21");
		JsonPath js = new JsonPath(SU_APIBE_Utility.GetRateCalculteUtil("514299", "2023-05-20", "2023-05-21"));
		String a = js.getString("Product[0].Rooms[" + RoomNo + "].RatePlans[0].Rates[\"" + Date + "\"].OBP." + Guestfor
				+ ".RateAfterTax");
		ArrayList<String> rateaftertax = new ArrayList<String>();
		rateaftertax.add(a);
		// System.out.println(a);
		return rateaftertax;
	}

	public static String GetBaseRate(String PropertyID) {
		//  https://fetchdata.staah.net/?RequestType=berate&Country=IN&FromDate=2023-05-20&Product=yes&ToDate=2023-05-21&PropertyId=514299
		RestAssured.baseURI = "https://fetchdata.staah.net/";
		Response response = RestAssured.given().param("RequestType", "berate").param("Country", "IN").param("FromDate", "2023-05-20").
				param("Product", "yes").param("ToDate", "2023-05-21").
				param("PropertyId", PropertyID)
				.when().header("X-Api-Key", "cPPq1uh0xD6BpfDFpGWEx9fxnDOUA3Y25RdigC0X").get();
		// System.out.println("Response = "+response.getBody().asString());
		// System.out.println(response.prettyPrint());
		String a = response.getBody().asPrettyString();
		// System.out.println(a);
		return a;
	}
	
	@Test
	public void testabove() {
		System.out.println(SU_APIBE_Utility.Guest1RateBeforeTax("0", "2023-05-21", "1"));
	}
}
