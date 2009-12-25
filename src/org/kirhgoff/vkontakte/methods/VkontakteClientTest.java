package org.kirhgoff.vkontakte.methods;

import org.junit.Assert;
import org.junit.Test;


public class VkontakteClientTest {
	@Test
	public void testVkontakteClient() throws Exception {
		String vkontakteAPI = "http://api.vkontakte.ru/api.php";
		String api_id = "4";
		String version = "2.0";
		String method = "secure.getAppBalance";
		long timestamp = 1238714241;
		long random = 83962759;
		//String sig = "7598d64720bb39544679f2ca256fa538";
		String expectedResult = "http://api.vkontakte.ru/api.php?api_id=4&v=2.0&method=secure.getAppBalance&timestamp=1238714241&random=83962759&sig=988d68423545fc37b4484d39c201a795";
		
		VkontakteRequest request = new VkontakteRequest (vkontakteAPI);
		request.setApiID (api_id);
		request.setVersion (version);
		request.setMethod (method);
		request.setTimestamp (timestamp);
		request.setRandom (random);
		String requestURL = request.getURL ();
		
		Assert.assertEquals (expectedResult, requestURL);
	}
}
