package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import ui.LoginPage;

public class Functionality {
	
	static Socket socket;
	static DataInputStream dataInputStream;
	static DataOutputStream dataOutputStream;
	
	//this method will establish a connection with server
	public static void connect() throws Exception
	{
		try {
			socket = new Socket("192.168.225.39", 50505);// 
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			LoginPage.offline();
			
		}
		
	}
	
	//Method to send the Data To server 
	public static void send(String data)
	{
		try {
			dataOutputStream.writeUTF(data);
			dataOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Method to recieve the messages from server
	public static String recieve() throws Exception {
		String recievedString="";
		try {
			recievedString = dataInputStream.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return recievedString;
	}
	
	

}
