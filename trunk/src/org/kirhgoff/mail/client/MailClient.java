package org.kirhgoff.mail.client;

import java.util.Arrays;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class MailClient {

  private static String mailFileRoot;

public static void main(String[] args) throws Exception {

    Properties props = new Properties();
    Properties instanceProps = new Properties ();
    instanceProps.load(MailClient.class.getResourceAsStream("/dev.properties"));
    
    String host = instanceProps.getProperty("mail.host");
    String username = instanceProps.getProperty("mail.username");
    String password = instanceProps.getProperty("mail.password");
    String provider = instanceProps.getProperty("mail.provider");
    mailFileRoot = instanceProps.getProperty("mail.file.root");
    
    System.out.println("Loaded properties: username=" + username + ", host=" + host);
    Session session = Session.getDefaultInstance(props, null);
    Store store = session.getStore(provider);
    store.connect(host, username, password);

    System.out.println("Getting folder.");
    Folder inbox = store.getFolder("INBOX");
    if (inbox == null) {
      System.out.println("No INBOX");
      System.exit(1);
    }
    System.out.println("permanent flags = " + Arrays.asList(inbox.getPermanentFlags().getUserFlags()));
    inbox.open(Folder.HOLDS_MESSAGES);

    System.out.println("Getting messages");
    Message[] messages = inbox.getMessages();
    for (int i = 0; i < messages.length; i++) {
    	Object contentRaw = messages [i].getContent();
    	if (!(contentRaw instanceof MimeMultipart)) {
    		System.out.println("Something wrong happened, message is not MimeMultipart");
    		break;
    	}
    	MimeMultipart multipart = (MimeMultipart) contentRaw;
    	dumpHeaders (messages [i]);
    	saveAttachments (multipart);
    }
    inbox.close(false);
    store.close();
  }

	private static void dumpHeaders(Message message) throws MessagingException {
		Address[] from = message.getFrom();
		for (int i = 0; i < from.length; i++) {
			Address address = from [i];
			System.out.println("From: " + address.toString());
		}
	
	}

	private static void saveAttachments(MimeMultipart multipart) throws Exception {
		int size = multipart.getCount();
		for (int i = 0; i < size; i++) {
			BodyPart bodyPart = multipart.getBodyPart(i);
			if (bodyPart instanceof MimeBodyPart && bodyPart.getFileName() != null) {
				MimeBodyPart mimeBodyPart = (MimeBodyPart) bodyPart;
				mimeBodyPart.saveFile(mailFileRoot + "/" + mimeBodyPart.getFileName());
			}
			else {
				System.out.println("Is not mime, skipping: " + bodyPart.getFileName());
			}
		}
	}
}