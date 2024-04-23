/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justeats;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author alise
 */
public class Restaurants {

	/**
	 * @param args the command line arguments
	 */
	private String address;
	private double rating;
	private String restaurantName;
	public static List<String> cusines;
	


	public Restaurants(String restaurantName, double rating, String address, String...cusineList)
	{
		//Example constructor
		this.restaurantName = restaurantName;
		this.rating = rating;
		this.address = address;
		this.cusines = Arrays.asList(cusineList);
		
	}
	public static void main(String[] args) {
		printResults(fetchJson("BN29UR"));	
	}
	
	private static JSONObject fetchJson(String postcode){
		try {
			//Set up URL
            URL url = new URL("https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/" + postcode);
			System.out.println("List of restaurants for postcode " + postcode + ":");
			//GET API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
			
            //Getting the response code
            int responsecode = conn.getResponseCode();
			//test if working
			//System.out.println(responsecode);
			
			//Check for non-OK response code
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } 
			else{
				
                String response = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    response += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();
				//System.out.print(response);
				
				//Turn response string into JSON Object
				final JSONObject obj = new JSONObject(response);
				return obj;
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	
	private static void printResults(JSONObject obj){
		//Get restaurant array
		final JSONArray restaurants = obj.getJSONArray("restaurants");
				//Loop through first ten restaurants returned
			for (int i = 0; i < 10; ++i) {
				final JSONObject instance = restaurants.getJSONObject(i);
				//Add seperating line
				System.out.println("________________");
				
				//Get restaurant name
				System.out.println("Name: " + instance.getString("name"));
				
				//Get cuisines
				final JSONArray cusineArray = instance.getJSONArray("cuisines");
				System.out.print("Cuisines: ");
				//Loop through Cuisine array to return each element
				for (int j = 0; j < cusineArray.length(); j++) {
					String cusineName = cusineArray.getJSONObject(j).getString("name");
					System.out.print(cusineName);
					//Add comma between values
					if (j != cusineArray.length() - 1){
						System.out.print(", ");
					}
					else {
						System.out.println();
					}
				}
				
				//Get restaurant rating
				System.out.println("Rating: " + instance.getJSONObject("rating").get("starRating"));
				
				//Get Address
				System.out.println("Address: " + instance.getJSONObject("address").get("firstLine") + ", " + instance.getJSONObject("address").get("postalCode") + ", " + instance.getJSONObject("address").get("city") );
			
				
				}
	}
}
