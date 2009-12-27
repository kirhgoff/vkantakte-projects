package org.kirhgoff.http.client;

import org.kirhgoff.http.client.model.FilesList;

public class HttpClient {
	public static void main(String[] args) {
		
		ServerConnection connection = new ServerConnection ("http://localhost:8080/method");
		Command command = new Command (connection);
		command.setMethodName (Command.GET_LIST_OF_FILES);
		command.addParameter (new PostParameter ("user_id"), "kirill.lastovirya");
		command.invoke ();
		FilesList filesList = (FilesList) command.getResponse ();
		
		System.out.println("Printing file names:");
		for (int i = 0; i < filesList.names.length; i++) {
			System.out.println("name=" + filesList.names [i]);
		}
	}
	

}
