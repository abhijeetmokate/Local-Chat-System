package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	
	private static final String USERNAME = "root";
    private static final String PASSWORD = "pass123";
    private static final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL="jdbc:mysql://localhost/chatdatabase";   //
    public static PreparedStatement preparedStatement ;
    public static Connection connection ;
	
	
	public static void preQuery() // to creating the connection between program and the database
	{
		try {
			
			 Class.forName(JDBC_DRIVER); //driver register
			 
			 connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			 
			 
			 
		} catch (SQLException e ) {
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static int isUser(String username,String password) 
	{
		//String query = "SELECT * FROM logindb WHERE username="+username;
		int result=0;
		try {
			
			preparedStatement = connection.prepareStatement("select * from logindb where username = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				
				String name= resultSet.getString("username").toLowerCase();
				String pass= resultSet.getString("password");
				
				System.out.println(name+"-->"+pass);
				
				if(name.equals(username.toLowerCase()))
				{
					if(pass.equals(password))
					{
							result =1;
							break;
					}
					else {
						result=2;
						break;
					}
				}
				else
				{
					continue;
				}
			}
			
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
		
		
	}
	
	public static boolean signUp(String username,String password,String fname,String lname)
	{
		String insert = "INSERT INTO logindb VALUES(?,?,?,?)";
		int result=0;
		try {
			preparedStatement = connection.prepareStatement(insert);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, fname);
			preparedStatement.setString(4, lname);
			
			
			result=preparedStatement.executeUpdate();
			System.out.println("after exwcute "+result);
			
		} catch (SQLException e) {
			System.out.println("signup exception");
			e.printStackTrace();
		}
		
		if(result == 1)
		return true;
		else {
			return false;
		}
	}
	
	public static boolean createAccount(String signupString)
	{
		String rawString = signupString;
		String usernameString;
		String passwordString;
		String firstnameString;
		String lastnameString;
		boolean res=false;
		
		
			// seperating the users information from the string
			
			//signup:@:username:abhijeet,passkey:abhi1234,,fname:abhijeet,,,lname:mokate
			
			usernameString = rawString.substring(rawString.indexOf("username:")+9,rawString.indexOf(","));
			passwordString = rawString.substring(rawString.indexOf("passkey:")+8,rawString.indexOf(",,"));
			firstnameString = rawString.substring(rawString.indexOf("fname:")+6,rawString.indexOf(",,,"));
			lastnameString = rawString.substring(rawString.indexOf("lname:")+6);
			
			
			 res = signUp(usernameString, passwordString, firstnameString, lastnameString); // to insert the data into the database
			//System.out.println("we get in createaccount from signup method  "+res);
			
			 return res;
		
		
	}

}
