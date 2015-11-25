package ut02.ejemplos.JSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParseTest {

	private static final String URL = "http://itvocationalteacher.blogspot.com/feeds/posts/default?alt=json&max-results=5";

	public static void main(String[] args) {

		try {
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.connect();


			InputStream stream = connection.getInputStream();
						
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8")); 
		    StringBuilder responseStrBuilder = new StringBuilder();

		    String inputStr;
		    
		    while ((inputStr = streamReader.readLine()) != null)
		        responseStrBuilder.append(inputStr);
		    
		    String reponseData = responseStrBuilder.toString();
		   
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reponseData);

			JSONObject root = (JSONObject) jsonObject.get("feed");
			JSONArray posts = (JSONArray) root.get("entry");

			for (int i = 0; i < posts.size(); i++) {
				JSONObject jsonPost = (JSONObject) posts.get(i);
				JSONObject title = (JSONObject) jsonPost.get("title");
				System.out.println(title.get("$t"));
			}

		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {

		} catch (NullPointerException ex) {

		} catch (ParseException e) {

		} catch (Exception e) {

		}

	}

}