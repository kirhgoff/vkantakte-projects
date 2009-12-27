package org.kirhgoff.http.json;

import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;


public class JSONUtils {
	@SuppressWarnings("unchecked")
	public static <T> T fromJSONToJava(String data, T model) {
			// model.fromJSONObj(new JSONObject(data));
			Marshaller<T> marshaller = (Marshaller<T>) TwoLattes.createMarshaller(model.getClass());
			return marshaller.unmarshall( (Json.Object) Json.fromString(data));
	}

}
