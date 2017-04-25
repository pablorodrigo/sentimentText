package sentimentTwitter;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Sentiment {

	
	public void sentimentResponse(String text){
		
		text = "great value in its price range!";
		
		try {
			// These code snippets use an open-source library.
			// http://unirest.io/java
			HttpResponse<JsonNode> response = Unirest.post("https://twinword-sentiment-analysis.p.mashape.com/analyze/")
					.header("X-Mashape-Key", "26YXTJMZimmshDP7IzoC2fwyaXGtp1SSD8IjsnK3ACuieZcbQm")
					.header("Content-Type", "application/x-www-form-urlencoded").header("Accept", "application/json")
					.field("text", text).asJson();
			
			JSONObject myObject = new JSONObject(response);
			
			System.out.println(myObject.get("body"));
		} catch (UnirestException e) {
			System.out.println("Error:");
			e.printStackTrace();
		}
		
	}
	
	

}
