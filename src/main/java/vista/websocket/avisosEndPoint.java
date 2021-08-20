/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author AllanRamiro
 */
@ServerEndpoint("/aviso")
public class avisosEndPoint {

    private static ArrayList<RemoteEndpoint.Basic> clients = new ArrayList();
    
    private RemoteEndpoint.Basic client;
    
    @OnOpen
    public String onOpen(Session s) {
        client = s.getBasicRemote();
        clients.add(client);
        
        return "Connected!";
    }
    
    @OnClose
    public void onClose(Session s) {
        clients.remove(client);
    }
    
    @OnMessage
    public void onMessage(String message) {
        for(RemoteEndpoint.Basic client: clients) {
            try {
                client.sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(ServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
