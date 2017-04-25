package sentimentTwitter;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Translator {

	private String URL = "https://translation.googleapis.com/language/translate/v2?key=AIzaSyD6h-jESBjPYdeHJgNELJDqnA_h1usWG-U&source=pt&target=en&q=";

	public void translate(String text) {

		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get(URL + text).asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject myObject = new JSONObject(response);

		System.out.println(myObject.getJSONObject("body").getJSONObject("object").getJSONObject("data")
				.getJSONArray("translations"));
	}

}
