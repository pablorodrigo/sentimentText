package br.com.uninorte.tcc.twittersentiment;


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

      /*   HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(URL + text)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .header("Accept", "application/json; charset=UTF-8")
                    .asJson();
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 */

        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post("https://translation.googleapis.com/language/translate/v2?key=AIzaSyD6h-jESBjPYdeHJgNELJDqnA_h1usWG-U&")
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .header("Accept", "application/json; charset=UTF-8")
                    .queryString("target", "en")
                    .queryString("source", "pt")
                    .queryString("q", text)
                    .asJson();

        } catch (UnirestException e) {
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
    public String dicionaryPT(String text) {
        return text
                .replaceAll("[ñÑ]", "não")
                .replaceAll("https://www.", "")
                .replaceAll("\\b(?i)Telefônica\\b", "telefonia")
                .replaceAll("\\b(?i)pré\\b", "pré-pago")
                .replaceAll("\\b(?i)pós\\b", "pós-pago")
                .replaceAll("\\b(?i)vlw\\b", "obrigado")
                .replaceAll("\\b(?i)qdo\\b", "quando")
                .replaceAll("\\b(?i)hj\\b", "hoje")
                .replaceAll("\\b(?i)msg\\b", "mensagem")
                .replaceAll("\\b(?i)Tweetar\\b", "Tweet")
                .replaceAll("\\b(?i)tomara\\b", "hope")
                // bad words
                .replaceAll("\\b(?i)pqp\\b", "fucking hell")
                .replaceAll("\\b(?i)kd\\b", "fucking hell")
                .replaceAll("\\b(?i)vtnc\\b", "fuck you")
                .replaceAll("\\b(?i)vsf\\b", "fuck you")
                .replaceAll("\\b(?i)vai se fuder\\b", "fuck you")
                .replaceAll("\\b(?i)saco\\b", "merda")
                .replaceAll("\\b(?i)porra\\b", "merda");


        /*

                .replaceAll("%", "porcento")
                .replaceAll("#", "")
                .replaceAll("_", "")
                .replaceAll("\'", "")
                .replaceAll("[-+=*&;%$#_|/]", "")
                .replaceAll(" ", "+")
                .replaceAll("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", "");


         */

    }
}
