package sentimentTwitter_Method2;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.TextAPIException;
import com.aylien.textapi.parameters.SentimentParams;

public class Sentiment2 {


    public String sentimentResponse(String text) {

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


    }


}
