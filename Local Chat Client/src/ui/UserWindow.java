package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import network.Functionality;





public class UserWindow implements ListSelectionListener{
	
	//========================================================================================================================================================================

	
	static JTextArea chatBox;
	static JScrollPane chatPanel;
	static //JList onlineUserJList;
	JList<String> onlineUserJList = new JList<String>();
	 static DefaultListModel<String> listModel = new DefaultListModel<String>();
	static JButton sendButton;
	static JLabel usersnamelabel;
	static JLabel piclabel;
	
	static JFrame userWindow;

	
	
	private String message;
	private static List<String> onlineUsers;//
	private static HashMap<String, CustomisedTextPanel> chatMap= new HashMap<String,CustomisedTextPanel>();
	
	private static Logger logger = Logger.getLogger("UserWindow");

//========================================================================================================================================================================

	/**
	 * @wbp.parser.entryPoint
	 */
	private  void initialize(String nameString)
	{

		userWindow = new JFrame();
		userWindow.setResizable(false);
		userWindow.setBounds(100, 100, 934, 597);
		userWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		userWindow.setVisible(false);
		userWindow.getContentPane().setLayout(null);
		userWindow.setTitle(nameString.toUpperCase()+"'s chat Window");
		
		onlineUserJList.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		onlineUserJList.setBounds(0, 0, 277, 559);
		onlineUserJList.getSelectionModel().addListSelectionListener(this);
		onlineUserJList.setModel(listModel);
		onlineUserJList.setFont(new Font("Cambria Math", Font.BOLD, 17));
		userWindow.getContentPane().add(onlineUserJList);
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) onlineUserJList.getCellRenderer();
		onlineUserJList.setFixedCellHeight(40);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		chatPanel = new JScrollPane();
		chatPanel.setOpaque(false);
		chatPanel.setBounds(278, 60, 642, 431);
		userWindow.getContentPane().add(chatPanel);
		
		chatBox = new JTextArea();
		chatBox.setLineWrap(true);
		chatBox.setWrapStyleWord(true);
		chatBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					sendButtonClick();
				}
			}
		});
		chatBox.setColumns(5);
		chatBox.setMargin(new Insets(10, 10, 5, 5));
		chatBox.setEnabled(false);
		chatBox.setFont(new Font("Arial", Font.PLAIN, 18));
		chatBox.setBackground(UIManager.getColor("Button.light"));
		chatBox.setBounds(278, 490, 558, 69);
		userWindow.getContentPane().add(chatBox);
		
		sendButton = new JButton("");
		sendButton.setBackground(Color.gray);
		sendButton.setEnabled(false);
		sendButton.setIcon(new ImageIcon("images/send.png"));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sendButtonClick();
				
			}
		});
		sendButton.setFont(new Font("Arial Black", Font.PLAIN, 40));
		sendButton.setBounds(831, 490, 89, 69);
		userWindow.getContentPane().add(sendButton);
		
		piclabel = new JLabel("");
		piclabel.setIcon(new ImageIcon("images/user.png"));
		piclabel.setHorizontalAlignment(SwingConstants.TRAILING);
		piclabel.setBackground(Color.GRAY);
		piclabel.setBounds(278, 0, 77, 61);
		userWindow.getContentPane().add(piclabel);
		
		usersnamelabel = new JLabel("");
		usersnamelabel.setBackground(Color.WHITE);
		usersnamelabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
		usersnamelabel.setBounds(365, 0, 564, 61);
		userWindow.getContentPane().add(usersnamelabel);
		
	}
	
//========================================================================================================================================================================
	public static void userInitialize(String msg)
	{
		String[] userArray = extractUsers(msg);
		onlineUsers =new LinkedList<String>(Arrays.asList(userArray)); 
		if(msg.contains("Online Users Are::()")==false)
		{
		for(int i=0;i<onlineUsers.size();i++)
		{
			CustomisedTextPanel obj = new CustomisedTextPanel();
			String name= onlineUsers.get(i);
			chatMap.put(name, obj);
			listModel.addElement(name);
		}
		}
	}

//========================================================================================================================================================================

	public void openUserWindow(String nameString,String usersList) {
		
		initialize(nameString);
		userInitialize(usersList);
		userWindow.setVisible(true);
		
	}

//========================================================================================================================================================================

	public void recievedMessage()
	{
		if(message.contains("Online Users Are::("))
		{
			//if(message.contains("Online Users Are::()")==false)
			userInitialize(message);
			
		}
		
		else if(message.contains("New User Connected@::("))
		{
			userConnected(message);
		}
		
		else if(message.contains("User Disconnected@::("))
		{
			userDisconnected(message);
		}
		
		else
		{
			System.out.println(message);
			setRecievedMessage(message);
		}
		
	}
	//========================================================================================================================================================================

	
	public static  void userConnected(String msg)
	{
		try {
			
			String name = (msg.substring(msg.indexOf("@::(")+4,msg.indexOf(")"))).toUpperCase();
			onlineUsers.add(name);
			System.out.println(name);
			CustomisedTextPanel obj = new CustomisedTextPanel();
			chatMap.put(name, obj);
			listModel.addElement(name);
			
			
			
		} catch (NullPointerException e) {
			
			String infoString = "Error Occured when user connected";
			logger.log(null, infoString,e);
		}
		
		
	}
	
	//========================================================================================================================================================================

	public static  void userDisconnected(String msg)
	{
		String name = (msg.substring(msg.indexOf("(")+1,msg.indexOf(")"))).toUpperCase();
		onlineUsers.remove(name);
		
		chatMap.remove(name);
		listModel.removeElement(name);
		
		if(listModel.isEmpty()==true)
		{
			sendButton.setEnabled(false);
			chatBox.setEnabled(false);
		}
		
	} 
	
	//========================================================================================================================================================================

	public static String extractName(String msg)
	{
		String extractedName = msg.substring(0,msg.indexOf(":"));
		return extractedName.toUpperCase();
	}

	
	//========================================================================================================================================================================
	
	public static String decodeMessage(String msg)
	{
		//recieved  ==>>abhi:@:Wassup?  |||    decoded ==>>>Wassup?
		String extractMsg="";
		try {
			extractMsg = msg.substring(msg.indexOf(":@:")+3); 
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return extractMsg;
	}
	
	//========================================================================================================================================================================
	public static String[] extractUsers(String msg) //this method return the Array of the Strings (Online users name)
	{
		String list[]=null;
		String msg1="";
		msg=msg.toUpperCase();
		msg1 = msg.substring(msg.indexOf("(")+1, msg.indexOf(")"));
		list = msg1.split(",");
		
		return list;
	}
	//========================================================================================================================================================================

	@Override
	public void valueChanged(ListSelectionEvent e) {
		chatBox.setEnabled(true);
		sendButton.setEnabled(true);
		chatPanel.setViewportView(chatMap.get(onlineUserJList.getSelectedValue()));
		usersnamelabel.setText(onlineUserJList.getSelectedValue());
	}
	
	
	//========================================================================================================================================================================
	public void sendButtonClick()
	{
		String textBoxMsg= chatBox.getText();
		sendMessageToServer(textBoxMsg);
		setMyMessage(textBoxMsg);
		chatBox.setText("");
	}
	
	
	//========================================================================================================================================================================
	public void sendMessageToServer(String msg)
	{
		String messageToSendString = insertName(msg);
		Functionality.send(messageToSendString);
		
	}
	
	
	//This method Sets the sent Message to chat feed================================================================================================================
	public void setMyMessage(String msg)
	{
		CustomisedTextPanel obj= chatMap.get(onlineUserJList.getSelectedValue());
		obj.myMessage("\n"+msg);
	}

	
	//This Method sets the Received message to chat feed==============================================================================================================
	public static  void setRecievedMessage(String msg)
	{
		String name= extractName(msg);
		String msg1 = decodeMessage(msg);
		CustomisedTextPanel obj= chatMap.get(name);
		obj.recievedMessage("\n"+msg1);
	}
	
	
	//Combines the message with the reciever's name so that server can know whom to send the message=================================================================
	public String insertName(String msg)
	{
		//Hello --> abhijeet:@:Hello
		String name= onlineUserJList.getSelectedValue()+":@:"+msg;
		return name;
		
	}
	
	

}
