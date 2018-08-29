package com.Utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class AppiumServer extends BaseStep{

	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;	
	public String serverUrl;
	Map<String, String> env = new HashMap<>(System.getenv());	
	
	public Integer getPort() throws Exception {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		int port = socket.getLocalPort();
		socket.close();
		log.info("Available Port is : "+Integer.toString(port));
		return port;
	}
	
	public void startServer(String host) throws Exception{
		env.put("PATH", "/usr/local/bin:" + env.get("PATH"));
		builder = new AppiumServiceBuilder();
		builder.withEnvironment(env);
		builder.withIPAddress(host);
		int port = getPort();
		builder.usingPort(port);
		builder.withLogFile(new File(curdir+"/Logs/log.txt"));
		service = AppiumDriverLocalService.buildService(builder);
		log.info("Appium Server is starting.");
		service.start();
		log.info("Appium Server Started");
		serverUrl = "http://"+host+":"+port+"/wd/hub";
	}
	
	public void stopServer(){		
		service.stop();
		log.info("Appium Server Stopped");
	}
}
