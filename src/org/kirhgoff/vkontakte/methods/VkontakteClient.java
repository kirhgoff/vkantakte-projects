package org.kirhgoff.vkontakte.methods;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.kirhgoff.conf.PropertyLoader;

import sun.net.www.protocol.http.HttpURLConnection;

public class VkontakteClient {
	public static final int KIRILL_ID = 1876407;
	
	public static void main(String[] args) throws Exception {
		sendNotification (KIRILL_ID, "hello Kiryusha");
	}

	private static void sendNotification(int uid, String string) throws Exception {
	    Properties instanceProps = PropertyLoader.getInstance("dev");
		
		SendNotificationRequest request = new SendNotificationRequest (instanceProps, uid, "Hello buddy!");
		request.setTestMode(false);
		System.out.println("Sending request: " + request.getURL());
		HttpURLConnection connection = new HttpURLConnection (new URL (request.getURL()), null);
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		System.out.println(IOUtils.toString(inputStream));
		
	}
}
