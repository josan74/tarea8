package ut02.ejemplos.JSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParseFile {

	private static final String FILEPATH = "./res/json/Person.json";

	public static void main(String[] args) {

		// read the json file
		FileReader reader;
		try {
			reader = new FileReader(FILEPATH);

			JSONParser jsonParser = new JSONParser();

			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			// get a String from the JSON object
			String name = (String) jsonObject.get("name");
			String lastname = (String) jsonObject.get("lastname");

			System.out.println("The employee  is: " + name + " " + lastname);

			// get a number from the JSON object
			long emp_no = (Long) jsonObject.get("emp_no");
			System.out.println("The emp_no is: " + emp_no);

			// handle a structure into the json object
			JSONObject job = (JSONObject) jsonObject.get("job");
			System.out.println("Into job structure, commission: "
					+ job.get("commission"));

			// get an array from the JSON object
			JSONArray salary = (JSONArray) jsonObject.get("salary");

			for (int i = 0; i < salary.size(); i++) {
				JSONObject innerObj = (JSONObject) salary.get(i);
				System.out.println("date " + innerObj.get("date") + " wage "
						+ innerObj.get("wage"));
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} catch (ParseException e) {

		}

	}

}