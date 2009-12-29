package org.kirhgoff.http.server;

import java.util.Properties;

import org.kirhgoff.conf.PropertyLoader;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class ServerRunner {
	private Server server;

	public void init () throws Exception {
	    PropertyLoader.loadEnvironment("dev");
	    
        server = new Server();
        Connector connector=new SelectChannelConnector();
        connector.setPort(8080);
        server.setConnectors(new Connector[]{connector});
        
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar("web-app/");
        webapp.setExtractWAR(true);
        server.setHandler(webapp);
        
        server.start();
        server.join();
	}
	
	public void stop() throws Exception {
		server.stop();
	}
	
    public static void main(String[] args) throws Exception {
    	ServerRunner runner = new ServerRunner ();
    	runner.init ();
    }
}