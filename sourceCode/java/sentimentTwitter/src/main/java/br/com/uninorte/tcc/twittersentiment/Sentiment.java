package br.com.uninorte.tcc.twittersentiment;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by pablo on 8/5/17.
 * metodos para anasalise de sentimento
 */

public class Sentiment {


    public JSONObject sentimentResponse(String text) {


        text = dicionaryPT(text);

        /**
         * Sentiment140 - by twitter FREE!
         */
        try {
            HttpResponse<JsonNode> response = Unirest.post("http://www.sentiment140.com/api/bulkClassifyJson")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body("{data:[{text:" + text + "}]}")
                    .asJson();


            JSONObject myObject = new JSONObject(response);

            //System.out.println(myObject.getJSONObject("body").getJSONArray("array"));

            if (myObject.getJSONObject("body").getJSONArray("array").getJSONObject(0).has("data")) {


                int polaridade = myObject.getJSONObject("body").getJSONArray("array").getJSONObject(0).getJSONArray("data").getJSONObject(0).getInt("polarity");
                JSONObject tempJson = new JSONObject();
                tempJson.put("polarity", polaridade);
                if (polaridade == 0) {
                    tempJson.put("type", "negative");
                } else if (polaridade == 2) {
                    tempJson.put("type", "neutral");
                } else {
                    tempJson.put("type", "positive");
                }


                return tempJson;

            } else {
                return new JSONObject();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        //System.out.print(response);
        return null;


        /**
         * aylien text api
         */
        /*
        //text = "great value in its price range!";

        TextAPIClient client = new TextAPIClient("c7092b44", "13e9c68daae2e0b1bbc015859bebd962");
        SentimentParams.Builder builder = SentimentParams.newBuilder();
        builder.setMode("tweet");
        builder.setText(text);
        com.aylien.textapi.responses.Sentiment sentiment = new com.aylien.textapi.responses.Sentiment();
        try {
            sentiment = client.sentiment(builder.build());
        } catch (TextAPIException e) {
            e.printStackTrace();
        }


        return sentiment.getPolarity();
        */

        /**
         * twinword
         */
        /*
        //text = "great value in its price range!";
		
		try {
			// These code snippets use an open-source library.
			// http://unirest.io/java
			HttpResponse<JsonNode> response = Unirest.post("https://twinword-sentiment-analysis.p.mashape.com/analyze/")
					.header("X-Mashape-Key", "tsUPVnZIQEmshlGPfeKWJCaZYiwip1f8GzijsnHve6KXdWKuuX")
					.header("Content-Type", "application/x-www-form-urlencoded").header("Accept", "application/json")
					.field("text", text).asJson();
			
			JSONObject myObject = new JSONObject(response);
			
			return myObject.getJSONObject("polarity");
			
			//System.out.println(myObject.getJSONObject("body").getJSONObject("object"));
		} catch (UnirestException e) {
			System.out.println("Error:");
			e.printStackTrace();
		}
		return null;
		*/
    }

    public String dicionaryPT(String text) {
        return text
                .replaceAll("\'", "")
                .replaceAll("/'", "")
                .replaceAll("[-+=*&;%$#_|/.,:@]", "");

    }


}
