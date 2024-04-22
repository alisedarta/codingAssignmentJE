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
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
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
		//backup
		this.restaurantName = restaurantName;
		this.rating = rating;
		this.address = address;
		this.cusines = Arrays.asList(cusineList);
		
	}
	public static void main(String[] args) {
		
		
        try {

            URL url = new URL("https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/BN29UR");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();
			//test if working
			System.out.println(responsecode);
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
				
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
				final JSONArray restaurants = obj.getJSONArray("restaurants");
				for (int i = 0; i < 10; ++i) {
				final JSONObject instance = restaurants.getJSONObject(i);
				
			//Get restaurant name
			System.out.println(instance.getString("name"));
			//Get restaurant rating
			System.out.println(instance.getJSONObject("rating").get("starRating"));
			//Get Address
			System.out.println(instance.getJSONObject("address").get("firstLine") + ", " + instance.getJSONObject("address").get("postalCode") + ", " + instance.getJSONObject("address").get("city") );
			//Get cuisines
			final JSONArray cusineArray = instance.getJSONArray("cuisines");
			for (int j = 0; j < cusineArray.length(); j++) {
				String cusineName = cusineArray.getJSONObject(j).getString("name");
				System.out.println(cusineName);
			}
			//System.out.println(instance.getJSONArray("cuisines"));
			
    }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    
		//backup test
		// TODO code application logic here
		Restaurants Res1 = new Restaurants("Subway", 3.5, "Brighton", "healthy", "yummy", "okay");
		//Console.WriteLine(Res1.restaurantName, +" " + Res1.rating + " " + Res1.address);
		System.out.println("Restaurant name: " + Res1.restaurantName);
		System.out.println("Rating: " + Res1.rating);
		System.out.println("Address: " + Res1.address);
		//System.out.println(Arrays.toString(cusines.toArray()));
		//cusines.forEach(System.out::print); 
		//cusines.stream().forEach(e -> 
		//		System.out.print(e + " "));
		String result = cusines.stream()
        .map(e -> e)
        .collect(Collectors.joining(", "));
		System.out.println(result);
		
	}
	
}
