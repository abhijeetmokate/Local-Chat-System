package background;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import db.Database;
import ui.ServerUi;

class User extends Thread
{
	@SuppressWarnings("unused")
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private String userName;
	
	
	
	public User(Socket socket)
	{
		try {
			/*
			 *  obtaining the input and output streams to send and receive message from user
			 * */
			
			this.socket= socket;
			dataInputStream = new DataInputStream(socket.getInputStream()); 
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			//userName = username;
			
			
		} catch (IOException e) {
			System.out.println("Error In Creating user");
		}
		
	}
	
	public void sendMessage(String message)  //helper method
	{
		try {
			dataOutputStream.flush(); // flushes the stream
			dataOutputStream.writeUTF(message); // sending the message to user
			dataOutputStream.flush(); //flushing the stream
			//System.out.println("send message of user class");
		} catch (Exception e) {
			System.out.println("sendMessage method exception");
		}
		
	}
	
	@Override
	public void run() {
		String rawString;
		
		while(true)
		{
			try {
				rawString = dataInputStream.readUTF();  // to read the data from the connected user/client
				//System.out.println("\n"+rawString+"\n"); //debug purpose
				if(rawString.contains("login:@:"))  // for login purpose
				{
					String usernameString = rawString.substring(rawString.indexOf("username:")+9,rawString.indexOf(",")); // extracting username from the recieved string
					String passwordString = rawString.substring(rawString.indexOf("passkey:")+8); //extracting password
					
					int res = Database.isUser(usernameString, passwordString); // sending username and password for verification 
					
					
					if(res==1) // if the username and password is correct
					{
						userName = usernameString;  // 
					    BackgroundWork.introduceNewUserToAll(userName); // to send the online status of new user to all the previously connected users
						BackgroundWork.addUser(usernameString.toLowerCase(),this);
						BackgroundWork.introduceOldUserstoNew(this,userName);// to send the list of already connected users to the newly connected user
						ServerUi.newConnected(usernameString);
//						
						//System.out.println(userList.get(0)+"\n"+onlineUsers.containsKey(userName)); // debug puppose
						
						
						
					}
					else if(res==0) { // if the username is not present in the database 
						dataOutputStream.writeUTF("login:@:create account");
					}
					else if(res==2) // if the entered password is incorrect
					{
						dataOutputStream.writeUTF("login:@:wrong password");
					}
					else { // if any other problem occurs
						dataOutputStream.writeUTF("login:@:internal problem");
					}
					
					
					
				}
				else if(rawString.contains("signup:@:")) /// for signup purpose 
				{
					boolean res = Database.createAccount(rawString); // it will pass the string containing user's information to method and will create the user in the database and returns the true if succeed
					if(res == true) // account created successfully
					{
						dataOutputStream.writeUTF("signup:@:account created");
					}
					else {
						dataOutputStream.writeUTF("signup:@:cannot create account");
					}
				} 
				else { 
					//System.out.println("else of run"); // debug purpose
					BackgroundWork.sendToClient(rawString,userName); // send the recieved message along with the username to method to process
				}
				
				
				
				
				
				
				
				
//				if(rawString.contains(":@:"))
//				{
//					sendToClient(rawString,userName);
//					
//					
//				}
			} catch (SocketException e) {
				ServerUi.userDisconnected(userName);
				BackgroundWork.removeUser(userName);
				break;
			}
			catch (IOException e) {
				// TODO: handle exception
			}
			
		}
		
		//super.run();
	}
}
