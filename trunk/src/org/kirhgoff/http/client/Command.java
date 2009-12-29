package org.kirhgoff.http.client;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.kirhgoff.http.client.json.JSONUtils;
import org.kirhgoff.http.client.model.FilesList;

import sun.net.www.protocol.http.HttpURLConnection;

public class Command {

	//methods
	public static final String GET_LIST_OF_FILES = "getListOfFiles";
	
	//parameters
	public static final String USER_ID = "user_id";
	
	private final String serverURL; // TODO optimize connection logic - do not
									// reconnect every time
	private Map<String, String> parameters = new HashMap<String, String>();

	public Command(String serverURL, String methodName) {
		this.serverURL = serverURL;
		addParameter("methodName", methodName);
	}

	public void addParameter(String parameter, String value) {
		parameters.put(parameter, value);
	}

	public Object invoke() throws Exception {
		// TODO move out to some invoker
		// TODO optimize
		FilesList filesList = null;
		HttpURLConnection httpConnection = null;

		try {
			httpConnection = new HttpURLConnection(new URL(null,serverURL), null);
			httpConnection.setRequestMethod("POST");
			for (Iterator<String> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				String value = parameters.get(name);
				httpConnection.addRequestProperty(name, value);
			}
			httpConnection.connect();
			//InputStream inputStream = httpConnection.getInputStream();
			//System.out.println(IOUtils.toString(inputStream));
			//inputStream.close();
			
			// request is sent
			System.out.println("received response status=" + httpConnection.getResponseMessage() 
					+ ", length=" + httpConnection.getContentLength());
			String headerField = httpConnection.getHeaderField("result");
			filesList = JSONUtils.fromJSONToJava(headerField, new FilesList());
		} finally {
			if (httpConnection != null) httpConnection.disconnect ();
		}
		return filesList;

	}

}
