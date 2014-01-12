package net.juniper.monitor.test;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
/**
 * mock space server
 * @author juntaod
 *
 */
public class ServerStartThread implements Runnable {

	@Override
	public void run() {
		String ip = getIpAddress();
        HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(ip, 8888), 0);
			server.createContext("/", new ResponseHandler());
			server.setExecutor(null); // creates a default executor
			
			server.start();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getIpAddress(){
		try{
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()){
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()){
					InetAddress ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address){
						return ip.getHostAddress();
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}

class ResponseHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
    	try{
        	String path = t.getRequestURI().getPath();
        	if(path.equals("/sm/css/style.css")){
        		byte[] bytes = "serer ok".getBytes();
        		t.sendResponseHeaders(200, bytes.length);
        		t.getResponseBody().write(bytes);
        	}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		t.close();
    	}
    }
}