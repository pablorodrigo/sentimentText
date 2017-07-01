package sentimentTwitter;


import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.unbescape.html.HtmlEscape;

/**
 * Class que utiliza api do google para traduzir um texto em pt para en
 */
public class Translator {

	private String URL = "https://translation.googleapis.com/language/translate/v2?key=AIzaSyD6h-jESBjPYdeHJgNELJDqnA_h1usWG-U&source=pt&target=en&q=";

	public String translate(String text) {
		
		String aux;
		
		text = dicionaryPT(text);
		
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

		// api para manipulacao de strings
		String unescaped = HtmlEscape.unescapeHtml(aux);

		return unescaped;
		
		//System.out.println(myObject.getJSONObject("body").getJSONObject("object").getJSONObject("data")
			//	.getJSONArray("translations").getJSONObject(0).get("translatedText"));
	}

	/*
	antes de traduzir ele verifica algumas palavras, para substituir de uma forma que a tradução seja mais satisfatoria
	 */
	public String dicionaryPT(String text){
		return text.replaceAll("ñ", "não")
				.replaceAll("Ñ", "não")
				.replaceAll("pqp", "puta+que+pariu")
				.replaceAll("Hj", "Hoje")
				.replaceAll("hj", "hoje")
				.replaceAll("msg", "mensagem")
				.replaceAll("Telefônica", "telefonia")
				.replaceAll("pré", "pré-pago")
				.replaceAll("pós", "pós-pago")
				.replaceAll("https://www.", "")
				.replaceAll("%", "porcento")
				.replaceAll("#", "")
				.replaceAll("_", "")
				.replaceAll(" ", "+")
				.replaceAll("Tweetar", "Tweet")
				.replaceAll("PORRA", "merda")
				.replaceAll("saco", "merda");

	}
}
