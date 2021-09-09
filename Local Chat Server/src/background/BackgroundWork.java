package background;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingWorker;
import db.Database;
import ui.ServerUi;


public class BackgroundWork extends SwingWorker<String, Socket>
{	
	private ServerSocket serverSocket;
	private Socket socket;
	private static List<String> userList= new ArrayList<String>();
	private static HashMap<String, User> onlineUsers= new HashMap<String,User>();
	@Override
	public String doInBackground() throws Exception {
		serverSocket = new ServerSocket(50505);
		ServerUi.serverCreated(serverSocket);
		Database.preQuery();
		while(true) // to accept the connections from the client
		{
		socket = serverSocket.accept(); // listens and accepts the new connection form the client
		System.out.println("connected");
		publish(socket); // it will give the socket to process further
		}
		
	}
	
	@Override
	protected void process(List<Socket> chunks) {
		Socket socc = (Socket) chunks.get(chunks.size()-1); // to get the first element of the list
		
		
				User userThread = new User(socc); // passing the socket to User class to create the new user on new thread
				userThread.start(); // starting new user thread
	}
	
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
	}
	
public static void addUser(String uname,User user)
{
	onlineUsers.put(uname,user);
	userList.add(uname);
}

public static void removeUser(String uname) {
	userList.remove(uname);
	onlineUsers.remove(uname);
	removeUserFromClient(uname);
}
	
	public static  void sendToClient(String recievedString,String uname) // process the message 
	{
		
		//System.out.println("sendtoclient=======>>>>>"+recievedString+"------>>"+uname); // debug purpose
		
		
		//System.out.println("sendToclient"+recievedString); // debug purpose
		String nameString;
		String messageString;
		String messageToSendString;
		nameString = (recievedString.substring(0,recievedString.indexOf(":@:"))).toLowerCase(); // to extract the user's name to whom sending message
		//System.out.println(nameString);
		messageString = recievedString.substring(recievedString.indexOf(":@:")+3); //to extract the message from string
		//System.out.println(messageString);
		messageToSendString = uname+":@:"+messageString; //building the string with the message and the user from which message is obtained
		//System.out.println(messageToSendString);
		if(onlineUsers.isEmpty()==false) // to check if the users are online 
		{
		 User user = onlineUsers.get(nameString); // getting the thread of the user to whom we want to send the message
		//System.out.println(user);
		user.sendMessage(messageToSendString); // sending message to user
		}
	}
	
	public static  void introduceOldUserstoNew(User user,String name)
	{
		String onlineUser="Online Users Are::(";
		for(int i=0;i<userList.size();i++)
		{
			if(name.toLowerCase() != userList.get(i).toLowerCase())
			onlineUser=onlineUser+userList.get(i)+",";
		}
		onlineUser= onlineUser+")";
		
		//User user = onlineUsers.get(name);
		user.sendMessage(onlineUser);
		
	}

	public static void removeUserFromClient(String name) {
		for(Map.Entry<String, User> user: onlineUsers.entrySet())
		{
			user.getValue().sendMessage("User Disconnected@::("+name+")");
		}
	}

	public static void introduceNewUserToAll(String name)
	{
		if(onlineUsers.isEmpty()==false)
		{
		for(Map.Entry<String, User> user: onlineUsers.entrySet())
		{
			user.getValue().sendMessage("New User Connected@::("+name+")");
					}
		}
		
	}

	


}
