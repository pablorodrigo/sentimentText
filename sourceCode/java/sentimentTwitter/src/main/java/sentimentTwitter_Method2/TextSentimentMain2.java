package sentimentTwitter_Method2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class TextSentimentMain2 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        JSONArray auxJsonArray = new JSONArray();
        JsonFiles2 jsonFiles = new JsonFiles2();
        auxJsonArray = jsonFiles.jsonFileRead("");
        Translator2 translator = new Translator2();
        Sentiment2 sentiment = new Sentiment2();

        JSONObject newJsonFile = new JSONObject();
        JSONArray newJsonFileList = new JSONArray();
        String aux;

//		CreateFileExecel createFileEecel = new CreateFileExecel();
//		createFileEecel.expExcel("teste.xls", auxJsonArray);

		/*
        fazer a tradução do texto e sentiment do texto, armazenando num array json
		 */
        for (int i = 0; i < auxJsonArray.size(); i++) {
            System.out.println("traduzindo e sentimento do " + i);
            JSONObject texts = (JSONObject) auxJsonArray.get(i);
            newJsonFile.put("originalText", texts.get("text").toString());
            // System.out.println(texts.get("text").toString());
            // System.out.println(texts.get("text").toString().replaceAll("#",
            // "").replaceAll("_", "").replaceAll(" ", "+"));
            // translator.translate(texts.get("text").toString());
            // translator.translate(texts.get("text").toString().replaceAll("#",
            // "").replaceAll("_", "").replaceAll(" ", "+"));

            //esperar 1 minutos para exeucutar mais requisicoes, api permite 60/min
            if (i == 60)
                new Thread().sleep(60000);


            aux = translator.translate(texts.get("text").toString());
            newJsonFile.put("translatedText", aux);
            newJsonFile.put("sentiment", sentiment.sentimentResponse(aux));
            newJsonFileList.add(newJsonFile);
            newJsonFile = new JSONObject();

        }

        System.out.println(newJsonFileList);

		/*
		Criar arquivo com os sentiment coletado das API
		 */
        File file = new File(
                "/home/pablo/workspace/tcc/sentimentTwitter_Method1/sourceCode/java/sentimentTwitter_Method1/src/jsonFiles/JsonFile-Method2.json");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write(newJsonFileList.toJSONString());
        fileWriter.flush();
        fileWriter.close();

    }

}
