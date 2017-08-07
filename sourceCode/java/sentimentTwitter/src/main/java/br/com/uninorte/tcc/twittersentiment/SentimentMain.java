package br.com.uninorte.tcc.twittersentiment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class SentimentMain {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        JSONArray auxJsonArray = new JSONArray();
        JsonFiles jsonFiles = new JsonFiles();


        //String fileLocation = "/home/pablo/workspace/tcc/sentimentTwitter/sourceCode/java/sentimentTwitter/src/jsonFiles/100twitters.json";
        String fileLocation = "/home/pablo/Desktop/1-janeiro.json";


        auxJsonArray = jsonFiles.jsonFileRead(fileLocation);
        Translator translator = new Translator();
        Sentiment sentiment = new Sentiment();

        JSONObject newJsonFile = new JSONObject();
        JSONArray newJsonFileList = new JSONArray();
        String aux;

//		CreateFileExecel createFileEecel = new CreateFileExecel();
//		createFileEecel.expExcel("teste.xls", auxJsonArray);

        //criar arquivo json com o sentimento do texto
        File file = new File(
                "/home/pablo/workspace/tcc/sentimentTwitter/sourceCode/java/sentimentTwitter/src/jsonFiles/JsonFile-Response.json");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("[");

		/*
        fazer a tradução do texto e sentiment do texto, armazenando num array json
		 */
        System.out.println("TAMANHO: " + auxJsonArray.size());
        for (int i = 308; i < auxJsonArray.size(); i++) {
            System.out.println("traduzindo e sentimento do " + i);
            JSONObject texts = (JSONObject) auxJsonArray.get(i);

            aux = translator.translate(texts.get("text").toString());
            org.json.JSONObject tempJson = sentiment.sentimentResponse(aux);


            newJsonFile.put("id", i);
            newJsonFile.put("originalText", texts.get("text"));
            newJsonFile.put("translatedText", aux);
            newJsonFile.put("polarity", !tempJson.has("polarity") ? null : tempJson.get("polarity"));
            newJsonFile.put("type", !tempJson.has("type") ? null : tempJson.get("type"));
            //newJsonFileList.add(newJsonFile);
            fileWriter.write(newJsonFile.toJSONString() + ",");
            fileWriter.flush();
            newJsonFile = new JSONObject();

        }

		/*
        Criar arquivo com os sentiment coletado das API

        File file = new File(
                "/home/pablo/workspace/tcc/sentimentTwitter_Method1/sourceCode/java/sentimentTwitter_Method1/src/jsonFiles/JsonFile-Method2.json");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write(newJsonFileList.toJSONString());
        fileWriter.flush();
         */
        fileWriter.write("]");
        fileWriter.flush();
        fileWriter.close();

    }

}
