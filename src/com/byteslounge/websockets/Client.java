// https://stackoverflow.com/questions/26452903/javax-websocket-client-simple-example

package com.byteslounge.websockets;


import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


@ClientEndpoint
public class Client {

	private Session userSession = null;
    
    /**
     * Callback hook for Connection open events.
     * 
     * @param userSession
     *            the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
        System.out.println("Client onOpen");
        System.out.println("Client userSession.id " + userSession.getId());
    }
 
    /**
     * Callback hook for Connection close events.
     * 
     * @param userSession
     *            the userSession which is getting closed.
     * @param reason
     *            the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
    	System.out.println("Client onClose");
        System.out.println("Client userSession.id " + userSession.getId());
        this.userSession = null;
    }
 
    /**
     * Callback hook for Message Events. This method will be invoked when a
     * client send a message.
     * 
     * @param message
     *            The text message
     */
    @OnMessage
    public void onMessage(String message) {
    	System.out.println("Client onMessage");
        System.out.println(message);
    }
 
    
    /**
     * Send a message.
     * 
     * @param user
     * @param message
     */
    public void sendMessage(String message) {
    	System.out.println("Client sendMessage");
        this.userSession.getAsyncRemote().sendText(message);
    }
 
    
}
