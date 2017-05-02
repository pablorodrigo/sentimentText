package sentimentTwitter;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFiles {

	@SuppressWarnings("unchecked")
	public JSONArray jsonFileRead(String fileLocation) throws Exception {

		fileLocation = "/home/pablo/workspace/trabalhoUninorte/sentimentTwitter/sourceCode/java/sentimentTwitter/src/jsonFiles/100twitters.json";
		
		// novo array de json
		JSONArray newJsonArray = new JSONArray();

		JSONObject newObject_for_newJsonArray = new JSONObject();

		JSONParser parser = new JSONParser();

		// pega todos os objetos do arquivo
		Object obj = parser.parse(new FileReader(fileLocation));

		// pega os objetos do arquivo e trasforma no array json
		JSONArray fileJsonArray = (JSONArray) obj;

		for (int i = 0; i < fileJsonArray.size(); i++) {

			JSONObject twitter = (JSONObject) fileJsonArray.get(i);

			newObject_for_newJsonArray.put("text", twitter.get("text"));
			newObject_for_newJsonArray.put("mentions", twitter.get("mentions"));
			newJsonArray.add(newObject_for_newJsonArray);
			newObject_for_newJsonArray = new JSONObject();

		}

		return newJsonArray;

	}

}
