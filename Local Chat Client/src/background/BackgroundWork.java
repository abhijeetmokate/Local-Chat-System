package background;

import java.util.List;
import network.Functionality;
import ui.*;
import javax.swing.SwingWorker;

public class BackgroundWork extends SwingWorker<Void, String>
{
	@Override
	protected Void doInBackground() throws Exception {
		Functionality.connect();
		String messageString;
		while(true)
		{
			messageString = Functionality.recieve();
			publish(messageString);
		}
		
	}
	
	@Override
	protected void process(List<String> chunks) {
		String nameString ="";
		String msgString = chunks.get(chunks.size()-1);
		if(msgString.contains("Online Users Are::"))
		{
			nameString = LoginPage.closeLoginWindow();
			UserWindow objUserWindow = new UserWindow();
			objUserWindow.openUserWindow(nameString,msgString);
		}
		else if(msgString.contains("New User Connected@::("))
		{
			UserWindow.userConnected(msgString);
		}
		else if(msgString.contains("User Disconnected@::(")) {
			UserWindow.userDisconnected(msgString);
		}
		else if(msgString.contains("login:@:") || msgString.contains("signup:@:")  ) {
			LoginPage.processRecievedMessage(msgString);
		}
		else {
			
			UserWindow.setRecievedMessage(msgString);
			
		}
		
	}
	
}
