package test.jes.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import test.jes.web.CustomSpringConfigurator;


@Component
@ServerEndpoint(value="/WebSocket2")
public class WebSocket2 {
	
	ArrayList<Session> list;
	
	@OnOpen
	public void open(Session session) {
		list=SessionList.getInstance().list;
		System.out.println(list.size());
		synchronized (list) {
			list.add(session);
		}		
		System.out.println(session.getId()+" 접속 ok :"+list.size());
	}
	
	@OnMessage
	public void receiveMsg(String msg) {
		System.out.println("받은 메세지:"+msg +" : "+list.size()+"명에게 전송합니다");
		
		//broadcast
		for(Session session:list) {
			try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	@OnClose
	public void close(Session session) {
		System.out.println(session.getId()+" 접속 end");
		synchronized (list) {
			list.remove(session);
		}		
	}

}





