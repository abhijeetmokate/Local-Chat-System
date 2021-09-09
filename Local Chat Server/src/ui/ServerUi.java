package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import background.*;

import javax.swing.JScrollPane;



public class ServerUi {

	private JFrame frame;
	static JTextArea userInfo;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerUi window = new ServerUi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerUi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Chat Server");
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//new BackgroundWork().execute(); // executing the background thread to accept the connection from the clients
				new BackgroundWork().execute();
				
				
			}
		});
		frame.setBounds(100, 100, 896, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 892, 517);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		userInfo = new JTextArea();
		userInfo.setForeground(Color.BLUE);
		userInfo.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		userInfo.setEditable(false);
		userInfo.setBackground(Color.LIGHT_GRAY);
		userInfo.setBounds(0, 0, 892, 517);
		userInfo.setMargin(new Insets(20, 25, 20, 20));
		panel.add(userInfo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 892, 517);
		panel.add(scrollPane);
	}
	
	public static void serverCreated(ServerSocket serverSocket) {
		try {
			userInfo.setText("Server Established:\n\nIP Address:\t"+InetAddress.getLocalHost()+"\nPort:\t"+serverSocket.getLocalPort()+"\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
	
	public static void newConnected(String userName) {
		userInfo.append("\nNew User "+userName+" Connected"); // to show the message on screen
	}
	
	public static void userDisconnected(String name)
	{
		userInfo.append("\n"+name+" Closed the Connection"); // if user disconnect 
	}

}
