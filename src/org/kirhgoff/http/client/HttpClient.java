package org.kirhgoff.http.client;

import org.kirhgoff.http.client.model.FilesList;

public class HttpClient {

	public static void main(String[] args) throws Exception {
			runTest();
	}

	private static void runTest() throws Exception {
		Command command = new Command("http://localhost:8080/MethodInvoker", Command.GET_LIST_OF_FILES);
		command.addParameter(Command.USER_ID, "kirill.lastovirya");

		System.out.println("Printing file names:");
		FilesList filesList = (FilesList) command.invoke();

		for (String name: filesList.names) {
			System.out.println("name=" + name);
		}
	}

}
