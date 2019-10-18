package Model.Rest.Capital;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import CoreAPI.APICore;

public class RESTModels {
	
	
	//REST CALL SERVICES
	//-----------------------------------------------------------------------------------------------
	public void restCallServices(String city, String restRule) {
		  try {

			URL url = new URL("https://restcountries.eu/rest/v2/capital/"+city+"?fields="+restRule+"");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed REST: HTTP Error Code: "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String restOutput;
			System.out.println("Rest API Response\n\n");
			while ((restOutput = br.readLine()) != null) {
				System.out.println(""+APICore.GREEN+"Response Output"+"\n---------------\n"+ restOutput);
			}

			conn.disconnect();}
		  
		  	catch (MalformedURLException e) {e.printStackTrace();}
		    catch (IOException e) {e.printStackTrace();}
	}
	//-----------------------------------------------------------------------------------------------
	
	
	//REST RESPONSE CONTROL
	//-----------------------------------------------------------------------------------------------
	public void restCallServices(String city, String restRule, String searchValue) {
		  try {

			URL url = new URL("https://restcountries.eu/rest/v2/capital/"+city+"?fields="+restRule+"");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed REST: HTTP Error Code: "
						+ conn.getResponseCode());
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
			String restOutput;

			if (searchValue == null) {
				System.out.println("Rest API Response\n\n");
				while ((restOutput = bufferedReader.readLine()) != null) {
					System.out.println(""+APICore.BLUE+"Response Output"+"\n---------------\n"+ restOutput);
				}
			}
			else {
				
				while ((restOutput = bufferedReader.readLine()) != null) {
					Boolean found = restOutput.contains(searchValue);
					
					if(found) {
						System.out.println("["+searchValue+"]");
						System.out.println(""+APICore.GREEN+"is in the Response [TRUE]");
					}
					else {
						System.out.println("["+searchValue+"]");
						System.out.println(""+APICore.RED+"is NOT in the Response [FALSE]");
					}
				}
			}
			
			conn.disconnect();}
		  
		  	catch (MalformedURLException e) {e.printStackTrace();} 
		    catch (IOException e) {e.printStackTrace();}
	}
	//-----------------------------------------------------------------------------------------------

	
}