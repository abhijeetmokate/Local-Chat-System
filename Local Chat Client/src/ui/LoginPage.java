package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import background.Login;
import background.*;

public class LoginPage {
	
	//===================================================================================================================================================

	private static JFrame frame;
	private static JTextField usernametextField;
	private JPasswordField pwdPassword;
	private JTextField firstnamefield;
	private JTextField lastnamefield;
	private JTextField usernamefieldsignup;
	private JTextField passwordfieldsignup;
	private JTextField confirmpasswordfield; 
	private static CardLayout cardLayout;
	private static  JPanel rightpanel;
	//===================================================================================================================================================

	public static void main(String[] args) {
		 new LoginPage();
		LoginPage.frame.setVisible(true);
	}

	//===================================================================================================================================================

	public LoginPage() {
		initialize();
		cardLayout = (CardLayout) rightpanel.getLayout();
	}

	
	//===================================================================================================================================================

	private void initialize() {
		frame = new JFrame("Login Page");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				new BackgroundWork().execute();
				
				
			}
		});
		frame.setBounds(100, 100, 941, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		JPanel leftpanel = new JPanel();
		leftpanel.setBounds(0, 0, 475, 547);
		frame.getContentPane().add(leftpanel);
		leftpanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/login.jpg"));
		lblNewLabel.setBounds(0, 0, 475, 547);
		leftpanel.add(lblNewLabel);
		
		rightpanel = new JPanel();
		rightpanel.setBackground(new Color(0, 0, 0));
		rightpanel.setBounds(473, 0, 454, 547);
		frame.getContentPane().add(rightpanel);
		rightpanel.setLayout(new CardLayout(0, 0));
		
		JPanel login = new JPanel();
		login.setBackground(new Color(0, 0, 0));
		login.setForeground(new Color(255, 255, 255));
		rightpanel.add(login, "login");
		login.setLayout(null);
		
		JLabel loginlabel = new JLabel("Login");
		loginlabel.setForeground(new Color(255, 250, 250));
		loginlabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginlabel.setFont(new Font("Perpetua Titling MT", Font.BOLD, 36));
		loginlabel.setBounds(175, 66, 157, 49);
		login.add(loginlabel);
		
		JLabel usernamelabel = new JLabel("Username");
		usernamelabel.setForeground(new Color(255, 255, 255));
		usernamelabel.setFont(new Font("Perpetua", Font.PLAIN, 22));
		usernamelabel.setBounds(90, 164, 273, 32);
		login.add(usernamelabel);
		
		usernametextField = new JTextField();
		usernametextField.setForeground(new Color(255, 255, 255));
		usernametextField.setOpaque(false);
		usernametextField.setBorder(null);
		usernametextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernametextField.setBounds(90, 201, 273, 28);
		login.add(usernametextField);
		usernametextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Perpetua", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(90, 274, 273, 37);
		login.add(lblNewLabel_2);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10)
				{
					String unameString = usernametextField.getText().toLowerCase();
					String pwordString = String.valueOf(pwdPassword.getPassword());
					Login.sendLoginDetails(unameString, pwordString);
				}
				
			}
		});
		pwdPassword.setForeground(new Color(255, 255, 255));
		pwdPassword.setBorder(null);
		pwdPassword.setOpaque(false);
		pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pwdPassword.setBounds(90, 311, 273, 28);
		login.add(pwdPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String unameString = usernametextField.getText().toLowerCase();
				String pwordString = String.valueOf(pwdPassword.getPassword());
				
				Login.sendLoginDetails(unameString, pwordString);
				//send
			}
		});
		btnNewButton.setFont(new Font("Perpetua", Font.BOLD, 22));
		btnNewButton.setBounds(57, 421, 113, 49);
		login.add(btnNewButton);
		
		JButton signupbutton = new JButton("Sign Up");
		signupbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(rightpanel, "signup");
				
			}
		});
		signupbutton.setFont(new Font("Perpetua", Font.BOLD, 22));
		signupbutton.setBounds(292, 418, 113, 52);
		login.add(signupbutton);
		
		JSeparator usersep = new JSeparator();
		usersep.setBounds(90, 227, 273, 19);
		login.add(usersep);
		
		JSeparator passsep = new JSeparator();
		passsep.setBounds(90, 337, 273, 19);
		login.add(passsep);
		
		JPanel signup = new JPanel();
		signup.setName("signup");
		signup.setBackground(new Color(0, 0, 0));
		rightpanel.add(signup, "signup");
		signup.setLayout(null);
		
		JLabel signuplabel = new JLabel("Sign Up");
		signuplabel.setHorizontalAlignment(SwingConstants.CENTER);
		signuplabel.setForeground(new Color(255, 250, 250));
		signuplabel.setFont(new Font("Perpetua Titling MT", Font.BOLD, 36));
		signuplabel.setBounds(148, 31, 157, 49);
		signup.add(signuplabel);
		
		JLabel FirstNamelabel = new JLabel("First Name");
		FirstNamelabel.setForeground(Color.WHITE);
		FirstNamelabel.setFont(new Font("Perpetua", Font.PLAIN, 22));
		FirstNamelabel.setBounds(90, 105, 273, 32);
		signup.add(FirstNamelabel);
		
		JLabel lastnamelabel = new JLabel("Last Name");
		lastnamelabel.setForeground(Color.WHITE);
		lastnamelabel.setFont(new Font("Perpetua", Font.PLAIN, 22));
		lastnamelabel.setBounds(90, 165, 273, 32);
		signup.add(lastnamelabel);
		
		JLabel usernamelabelsignup = new JLabel("Username");
		usernamelabelsignup.setForeground(Color.WHITE);
		usernamelabelsignup.setFont(new Font("Perpetua", Font.PLAIN, 22));
		usernamelabelsignup.setBounds(90, 225, 273, 32);
		signup.add(usernamelabelsignup);
		
		JLabel passwordlabel = new JLabel("Password");
		passwordlabel.setForeground(Color.WHITE);
		passwordlabel.setFont(new Font("Perpetua", Font.PLAIN, 22));
		passwordlabel.setBounds(90, 285, 273, 32);
		signup.add(passwordlabel);
		
		JLabel confirmpasswordlabel = new JLabel("Confirm Password");
		confirmpasswordlabel.setForeground(Color.WHITE);
		confirmpasswordlabel.setFont(new Font("Perpetua", Font.PLAIN, 22));
		confirmpasswordlabel.setBounds(90, 347, 273, 32);
		signup.add(confirmpasswordlabel);
		
		firstnamefield = new JTextField();
		firstnamefield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		firstnamefield.setOpaque(false);
		firstnamefield.setForeground(Color.WHITE);
		firstnamefield.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstnamefield.setColumns(10);
		firstnamefield.setBorder(null);
		firstnamefield.setBounds(90, 127, 273, 28);
		signup.add(firstnamefield);
		
		lastnamefield = new JTextField();
		lastnamefield.setOpaque(false);
		lastnamefield.setForeground(Color.WHITE);
		lastnamefield.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastnamefield.setColumns(10);
		lastnamefield.setBorder(null);
		lastnamefield.setBounds(90, 187, 273, 28);
		signup.add(lastnamefield);
		
		usernamefieldsignup = new JTextField();
		usernamefieldsignup.setOpaque(false);
		usernamefieldsignup.setForeground(Color.WHITE);
		usernamefieldsignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernamefieldsignup.setColumns(10);
		usernamefieldsignup.setBorder(null);
		usernamefieldsignup.setBounds(90, 247, 273, 28);
		signup.add(usernamefieldsignup);
		
		passwordfieldsignup = new JTextField();
		passwordfieldsignup.setOpaque(false);
		passwordfieldsignup.setForeground(Color.WHITE);
		passwordfieldsignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordfieldsignup.setColumns(10);
		passwordfieldsignup.setBorder(null);
		passwordfieldsignup.setBounds(90, 309, 273, 28);
		signup.add(passwordfieldsignup);
		
		confirmpasswordfield = new JTextField();
		confirmpasswordfield.setOpaque(false);
		confirmpasswordfield.setForeground(Color.WHITE);
		confirmpasswordfield.setFont(new Font("Tahoma", Font.PLAIN, 16));
		confirmpasswordfield.setColumns(10);
		confirmpasswordfield.setBorder(null);
		confirmpasswordfield.setBounds(90, 370, 273, 28);
		signup.add(confirmpasswordfield);
		
		JSeparator fnamesep = new JSeparator();
		fnamesep.setBounds(90, 155, 273, 2);
		signup.add(fnamesep);
		
		JSeparator lnamesep = new JSeparator();
		lnamesep.setBounds(90, 213, 273, 2);
		signup.add(lnamesep);
		
		JSeparator usernamesep = new JSeparator();
		usernamesep.setBounds(90, 273, 273, 2);
		signup.add(usernamesep);
		
		JSeparator passwordsep = new JSeparator();
		passwordsep.setBounds(90, 335, 273, 2);
		signup.add(passwordsep);
		
		JSeparator confirmpasswordsep = new JSeparator();
		confirmpasswordsep.setBounds(90, 396, 273, 2);
		signup.add(confirmpasswordsep);
		
		JButton signupbutton2 = new JButton("Sign Up");
		signupbutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(passwordfieldsignup.getText().equals(confirmpasswordfield.getText())) {
					String unameString = usernamefieldsignup.getText();
					String passkeyString= confirmpasswordfield.getText();
					String fnameString = firstnamefield.getText();
					String lnameString = lastnamefield.getText();
					
					Login.sendSignupDetails(fnameString, lnameString, unameString, passkeyString);
			}
				else {
					JOptionPane.showMessageDialog(frame,"Please Re-Enter the Password");
				}
			}
		});
		signupbutton2.setFont(new Font("Perpetua", Font.BOLD, 22));
		signupbutton2.setBounds(288, 453, 113, 52);
		signup.add(signupbutton2);
		
		JButton backbutton = new JButton("Back");
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cardLayout.show(rightpanel, "login");
				
			}
		});
		backbutton.setFont(new Font("Perpetua", Font.BOLD, 22));
		backbutton.setBounds(54, 453, 113, 52);
		signup.add(backbutton);
		
		
	
	}
	
	//===================================================================================================================================================
	
	public static void loginError(String msg)
	{
		System.out.println(msg);
		if(msg.contains("login:@:create account"))
		{
			JOptionPane.showMessageDialog(frame, "Please Sign Up First !!");
		}
		else if(msg.contains("login:@:wrong password"))
		{
			JOptionPane.showMessageDialog(frame, "Please Enter Correct Password !!");
		}
		else if(msg.contains("login:@:login success"))
		{
			
			
		}
		else if(msg.contains("login:@:internal problem"))
		{
			JOptionPane.showMessageDialog(frame, "Server Probelm !!");
		}
	}
	
//===================================================================================================================================================

	
	public static void signUpInfo(String msg)
	{
		if(msg.contains("signup:@:account created"))
		{
			JOptionPane.showMessageDialog(frame, "Account Created Login To Continue");
			cardLayout.show(rightpanel, "login");
		}
		else if(msg.contains("signup:@:cannot create account"))
		{
			JOptionPane.showMessageDialog(frame, "Username Already Taken Choose Another Username");
		}
		else {
			JOptionPane.showMessageDialog(frame, "Failed To Sign Up");
		}
	}
//===================================================================================================================================================
	public static void processRecievedMessage(String msgString)
	{
		if(msgString.contains("login:@:"))
		{
			//LoginPage.getAccess(msgString);
			loginError(msgString);
		}
		else if(msgString.contains("signup:@:"))
		{
			//LoginPage.signUpConfirm(msgString);
			signUpInfo(msgString);
		}
		else {
			//
		}
		
	}
	
	//===================================================================================================================================================
	
	public static String closeLoginWindow()
	{
		JOptionPane.showMessageDialog(frame, "login successfull");
		frame.setVisible(false);
		return usernametextField.getText().toLowerCase();
		
	}
	//===================================================================================================================================================
	
	public static void offline() {
		JOptionPane.showMessageDialog(frame, "Server is Offline");
		System.exit(0);
	}
	
	

}
