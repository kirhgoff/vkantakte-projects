package org.kirhgoff.http.server;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kirhgoff.http.client.Command;
import org.kirhgoff.http.client.json.JSONUtils;
import org.kirhgoff.http.client.model.FilesList;

public class MethodServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(MethodServlet.class);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		
		String methodName = request.getHeader("methodName");
		log.info ("Received request, methodName=" + methodName);
		
		String result = null;
		if (methodName.equals(Command.GET_LIST_OF_FILES)) {
			result = getListOfFiles (request);
		}//All other methods go here
		else {
			//TODO error processing
			result = "Cannot find such method:" + methodName;
		}
		response.setHeader("result", result);
		response.flushBuffer();
		log.info("Sent response =" + result);
	  }

	private String getListOfFiles(HttpServletRequest request) {
		FilesList list = new FilesList ();
		list.setNames(Arrays.asList (new String [] {"aaa", "bbb"}));
		return JSONUtils.fromJavaToJSON(list).toString();
	}
	
	
}
