package org.kirhgoff.vkontakte.methods;

import java.net.URLEncoder;
import java.util.Properties;

import org.kirhgoff.conf.PropertyLoader;

public class SendNotificationRequest {
	private VkontakteRequest request;
	private final String message;
//	private final String format = "XML";
	private final int uid;

	public SendNotificationRequest(Properties props, int uid, String message) {
		this.uid = uid;
		this.message = message;
		request = new VkontakteRequest ();
		request.setMethod(Methods.SECURE_SEND_NOTIFICATION);
		request.setSecret(props.getProperty(PropertyLoader.VKONTAKTE_SECRET));
		request.setApiID(props.getProperty(PropertyLoader.VKONTAKTE_API_ID));
	}
	
	public String getURL () throws Exception {
		return request.getURL() + "&uid=" + uid + "&message=" + URLEncoder.encode(message, "UTF-8"); 
	}
	
	public void setTestMode (boolean testMode) {
		request.setTestMode (testMode);
	}
}
