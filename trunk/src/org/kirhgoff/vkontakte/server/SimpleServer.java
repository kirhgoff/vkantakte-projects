package org.kirhgoff.vkontakte.server;

import org.mortbay.http.SocketListener;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.WebApplicationContext;


public class SimpleServer {

	public static void main(String[] args) throws Exception {
		//String jetty_default=new java.io.File("./start.jar").exists()?".":"../..";;
		//String jetty_home = System.getProperty("jetty.home",jetty_default);
		
		Server server = new Server();
		SocketListener listener = new SocketListener(); 
		listener.setPort(8080);
		server.addListener(listener);
				
		//Connector connector=new SelectChannelConnector();
		//connector.setPort(Integer.getInteger("jetty.port",8080).intValue());
		//server.setConnectors(new Connector[]{connector});
		
		WebApplicationContext webapp = new WebApplicationContext();
		webapp.setContextPath("/");
		webapp.setContextPath("C:/Projects/vkontakte/web-app/");
		//webapp.setDefaultsDescriptor(jetty_home+"/etc/webdefault.xml");
		
		server.addContext(webapp);
		
		server.start();
		server.join();
	}

}
