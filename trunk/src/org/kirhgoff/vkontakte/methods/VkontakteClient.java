package org.kirhgoff.vkontakte.methods;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.kirhgoff.mail.client.MailClient;

import sun.net.www.protocol.http.HttpURLConnection;

public class VkontakteClient {
	public static final int KIRILL_ID = 1876407;
	
	public static void main(String[] args) throws Exception {
		sendNotification (KIRILL_ID, "hello Kiryusha");
	}

	private static void sendNotification(int uid, String string) throws Exception {
	    Properties instanceProps = new Properties ();
	    instanceProps.load(MailClient.class.getResourceAsStream("/dev.properties"));
		
		SendNotificationRequest request = new SendNotificationRequest (instanceProps, uid, "Hello buddy!");
		System.out.println("Sending request: " + request.getURL());
		HttpURLConnection connection = new HttpURLConnection (new URL (request.getURL()), null);
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		String string2 = IOUtils.toString(inputStream);
		System.out.println(string2);
		
	}
}
