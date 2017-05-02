package sentimentTwitter;

import java.io.File;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestClass {

	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception  {
		
		JSONArray auxJsonArray = new JSONArray();
		JsonFiles jsonFiles = new JsonFiles();
		auxJsonArray = jsonFiles.jsonFileRead("");
		Translator translator = new Translator();
		Sentiment sentiment = new Sentiment();
		
		JSONObject newJsonFile = new JSONObject();
		JSONArray newJsonFileList = new JSONArray();
		String aux;

		for (int i = 90; i < auxJsonArray.size(); i++) {
			System.out.println("traduzindo e sentimento do "+i);
			JSONObject texts = (JSONObject) auxJsonArray.get(i);
			newJsonFile.put("originalText", texts.get("text").toString());
			//System.out.println(texts.get("text").toString());
			//System.out.println(texts.get("text").toString().replaceAll("#", "").replaceAll("_", "").replaceAll(" ", "+"));
			//translator.translate(texts.get("text").toString());
			//translator.translate(texts.get("text").toString().replaceAll("#", "").replaceAll("_", "").replaceAll(" ", "+"));
			aux = translator.translate(texts.get("text").toString());
			newJsonFile.put("translatedText", aux);
			newJsonFile.put("sentiment", sentiment.sentimentResponse(aux));
			newJsonFileList.add(newJsonFile);
			newJsonFile = new JSONObject();
			
			
		}
		
		System.out.println(newJsonFileList);
		
		File file=new File("/home/pablo/workspace/trabalhoUninorte/sentimentTwitter/sourceCode/java/sentimentTwitter/src/jsonFiles/JsonFile.json"); 
		file.createNewFile();  
        FileWriter fileWriter = new FileWriter(file);  

        fileWriter.write(newJsonFileList.toJSONString());  
        fileWriter.flush();  
        fileWriter.close();
	}
	
}
