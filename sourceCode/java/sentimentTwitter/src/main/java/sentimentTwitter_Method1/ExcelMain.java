package sentimentTwitter;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ExcelMain {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {


        String fileLocation = "/home/pablo/workspace/tcc/sentimentTwitter_Method1/sourceCode/java/sentimentTwitter_Method1/src/jsonFiles/JsonFile-Method2.json";

        // novo array de json
        JSONArray newJsonArray = new JSONArray();

        JSONObject newObject_for_newJsonArray = new JSONObject();

        JSONParser parser = new JSONParser();

        // pega todos os objetos do arquivo
        Object obj = parser.parse(new FileReader(fileLocation));

        // pega os objetos do arquivo e trasforma no array json
        JSONArray fileJsonArray = (JSONArray) obj;

        //JSONObject fileJsonObjetc = (JSONObject) obj;

        for (int i = 0; i < fileJsonArray.size(); i++) {

            // pega todos os objetos dentro da array
            JSONObject twitter = (JSONObject) fileJsonArray.get(i);

            //pega os objeto dentro de um determinado array
            JSONObject sentiment = (JSONObject) twitter.get("sentiment");


            newObject_for_newJsonArray.put("originalText", twitter.get("originalText"));
            newObject_for_newJsonArray.put("sentiment", sentiment.get("type"));
            newJsonArray.add(newObject_for_newJsonArray);
            newObject_for_newJsonArray = new JSONObject();

        }

        CreateFileExecel createFileExecel = new CreateFileExecel();
        createFileExecel.expExcel("test_execel_01.xls", newJsonArray);


    }

}
