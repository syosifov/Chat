package com.byteslounge.websockets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

import org.junit.Test;

public class ClientTest {
	
	
	
	
	@Test
	public void t1() {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		Client c = new Client();
		try {
			container.connectToServer(c, 
					new URI("ws://localhost:8080/Chat/websocket"));
			c.sendMessage("Hello, I am the Client.");
			Thread.sleep(5000);
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		Client c = new Client();
		try {
			container.connectToServer(c, 
					new URI("ws://localhost:8080/Chat/websocket"));
			c.sendMessage("Hello, I am the Client.");
			Thread.sleep(5000);
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
