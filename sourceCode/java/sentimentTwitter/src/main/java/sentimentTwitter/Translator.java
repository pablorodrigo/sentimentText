package sentimentTwitter;


import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Translator {

	private String URL = "https://translation.googleapis.com/language/translate/v2?key=AIzaSyD6h-jESBjPYdeHJgNELJDqnA_h1usWG-U&source=pt&target=en&q=";

	public String translate(String text) {
		
		String aux;
		
		text = text.replace("ñ", "não").replace("Ñ", "não").replaceAll("#", "").replaceAll("_", "").replaceAll(" ", "+");
		
		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get(URL + text)
					.header("Content-Type", "application/json; charset=UTF-8")
					.header("Accept", "application/json; charset=UTF-8")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		JSONObject myObject = new JSONObject(response);
		
		aux = myObject.getJSONObject("body").getJSONObject("object").getJSONObject("data")
				.getJSONArray("translations").getJSONObject(0).get("translatedText").toString();
		
		return aux;
		
		//System.out.println(myObject.getJSONObject("body").getJSONObject("object").getJSONObject("data")
			//	.getJSONArray("translations").getJSONObject(0).get("translatedText"));
	}

}
