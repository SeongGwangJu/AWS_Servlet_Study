package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;

public class JsonParseUtil {
	public static Map<String, Object> toMap(InputStream inputstream) {
		StringBuilder jsonData = new StringBuilder("");
		BufferedReader bufferedReader = null;
		
		if(inputstream == null) {
			return null;
		}
		
		bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
		
		while(true) {
			try {
				String data = bufferedReader.readLine();
				if(data == null) {
					break;
				}
				jsonData.append(data);
				
			} catch (IOException e) {
				return null;
			}
		}
		
		Gson gson = new Gson();
		return gson.fromJson(jsonData.toString(), Map.class);
	}
	
	public static String toJson(Object object) {
		
		Gson gson = new Gson();
		return gson.toJson(object);
	}
}
