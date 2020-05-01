package com.redhat.kafkaconsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaDemoController {

    public static Logger logger = LoggerFactory.getLogger(KafkaDemoApplication.class);

    @Autowired
    PuzzleService puzzleService;
    
    @Autowired
    Puzzle puzzle;

	/* JSON Format:
	{
		"message": "abc",
		"acrostic": [
			{
				"letter": "a",
				"word": "all"
			},
			{
				"letter": "b",
				"word": "beasts"
			},
			{
				"letter": "c",
				"word": "code"
			}   			
		]
	}
	*/
	@SuppressWarnings("unchecked")
	public JSONObject createAcrostic(String message) throws Exception {
    	char[] messageArray = message.toCharArray();
        JSONObject JSONOutputObject = new JSONObject();
        
        JSONOutputObject.put("message", message); 
        puzzle.setMessage(message);
        Map<String, String> JSONMap; 
        JSONArray JSONOutputArray = new JSONArray(); 

        for (char messageCharacter : messageArray) {
			
			String url = "https://api.datamuse.com/words?sp=" + messageCharacter + "*";
			
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");

			BufferedReader outputBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));String outputLine;
			StringBuffer response = new StringBuffer();

			while ((outputLine = outputBuffer.readLine()) != null) {
				response.append(outputLine);
			}
			outputBuffer.close();
			
			JSONParser parser = new JSONParser();
			JSONArray JSONArray = (JSONArray) parser.parse(response.toString());
			
			Random rand = new Random();

			int n = rand.nextInt(JSONArray.size());
			JSONObject JSONObject = (JSONObject) JSONArray.get(n);
			while ( JSONObject.get("word").toString().length() < 2 ) {
				n = rand.nextInt(JSONArray.size());
				JSONObject = (JSONObject) JSONArray.get(n);
			}			
			JSONMap = new LinkedHashMap<String, String>(2);
			JSONMap.put("letter", "" + messageCharacter + ""); 
	        JSONMap.put("word", JSONObject.get("word").toString()); 
	        JSONOutputArray.add(JSONMap);
		}
        JSONOutputObject.put("acrostic", JSONOutputArray);
        puzzle.setMap(JSONOutputArray.toJSONString());
        KafkaDemoApplication.logger.info("Payload: " + JSONOutputObject);
        return JSONOutputObject;
	}
	
	public String sendMessage(JSONObject value) throws Exception {
    	String encodedJSONObject = Base64.getUrlEncoder().encodeToString(value.toJSONString().getBytes());
        String url = System.getenv("KAFKA_PRODUCER_URL") + "/msg/"
        				+ encodedJSONObject + "/" + System.getenv("KAFKA_UI_TOPIC");
        
        // Persist to DB
        puzzleService.save(puzzle);
        puzzle.setId(null);
    	return postToProducer(url);
    }
    
    public String postToProducer(String url) throws Exception {
    	
		URL obj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");

		BufferedReader outputBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));String outputLine;
		StringBuffer response = new StringBuffer();

		while ((outputLine = outputBuffer.readLine()) != null) {
			response.append(outputLine);
		}
		outputBuffer.close();
		
		JSONParser parser = new JSONParser();
		JSONObject JSONObject = (JSONObject) parser.parse(response.toString());
		
		return JSONObject.toJSONString();
    }
}
