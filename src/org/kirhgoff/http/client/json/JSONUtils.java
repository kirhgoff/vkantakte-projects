package org.kirhgoff.http.client.json;

import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;


public class JSONUtils {
	@SuppressWarnings("unchecked")
	public static <T> T fromJSONToJava(String data, T model) {
			Marshaller<T> marshaller = (Marshaller<T>) TwoLattes.createMarshaller(model.getClass());
			return marshaller.unmarshall( (Json.Object) Json.fromString(data));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Object fromJavaToJSON(T object) {
		Marshaller<T> marshaller = (Marshaller<T>) TwoLattes.createMarshaller(object.getClass());
		return marshaller.marshall(object);
}
	

}
