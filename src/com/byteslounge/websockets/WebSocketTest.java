// WebSocketTest
package com.byteslounge.websockets;

import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketTest {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {

		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
			}
		}

	}

	@OnOpen
	public void onOpen(Session session) {
		// Add session to the connected sessions set
		clients.add(session);
		System.out.println(this);
		System.out.println("Server onOpen");
	}

	@OnClose
	public void onClose(Session session) {
		// Remove session from the connected sessions set
		clients.remove(session);
		System.out.println(this);
		System.out.println("Server onClose");
	}
	
	// https://www.programcreek.com/java-api-examples/?api=javax.websocket.OnError
	@OnError
	public void onError(Throwable t) throws Throwable {
	    // Most likely cause is a user closing their browser. Check to see if
	    // the root cause is EOF and if it is ignore it.
	    // Protect against infinite loops.
	    int count = 0;
	    Throwable root = t;
	    while (root.getCause() != null && count < 20) {
	        root = root.getCause();
	        count ++;
	    }
	    if (root instanceof EOFException) {
	        // Assume this is triggered by the user closing their browser and
	        // ignore it.
	    	System.out.println("Server onError EOF " + t.getMessage());
	    } else {
	        throw t;
	    }
	}

}
