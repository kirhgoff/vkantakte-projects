package org.kirhgoff.conf;

import java.io.IOException;
import java.util.Properties;

import org.kirhgoff.mail.client.MailClient;

public class PropertyLoader {

	public static final String VKONTAKTE_API_ID = "vkontakte.api_id";
	public static final String VKONTAKTE_SECRET = "vkontakte.secret";
	public static Properties getInstance(String environment)
			throws IOException {
		Properties instanceProps = new Properties ();
		instanceProps.load(MailClient.class.getResourceAsStream("/" + environment + ".properties"));
		return instanceProps;
	}
	public static final String MAIL_FILE_ROOT = "mail.file.root";
	public static final String MAIL_PROVIDER = "mail.provider";
	public static final String MAIL_PASSWORD = "mail.password";
	public static final String MAIL_USERNAME = "mail.username";
	public static final String MAIL_HOST = "mail.host";

}
