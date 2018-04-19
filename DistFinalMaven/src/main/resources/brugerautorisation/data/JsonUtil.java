package data;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper mapper;
static {
	mapper = new ObjectMapper();
	
}
public static String ConvertJavaToJason(Object object) {
	String jsonResult = "";
	try {
		jsonResult = mapper.writeValueAsString(object);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return jsonResult;
}

public static <T> T JsonToJava(String jsonToString, Class<T> cls) {
	T result = null;
	try {
		result = mapper.readValue(jsonToString, cls);
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}
}
