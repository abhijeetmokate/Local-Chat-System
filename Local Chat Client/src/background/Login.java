package background;

import network.*;

public class Login {
	
	public static void sendLoginDetails(String usernameString , String password)
	{
		//login:@:username:abhijeet,passkey:abhi1234
		String loginString = "login:@:username:"+usernameString+",passkey:"+password;
		
			Functionality.send(loginString);
		
	}
	
	public static void sendSignupDetails(String fname,String lname,String username,String password)
	{
		//signup:@:username:abhijeet,passkey:abhi1234,fname:abhijeet,lname:mokate
		//String signUpString= "signup:@:username:"+username+",passkey:"+password+",,fname:"+fname+",,,lname"+lname;
	      String signUpString= "signup:@:username:"+username+",passkey:"+password+",,fname:"+fname+",,,lname:"+lname;

			Functionality.send(signUpString);
		
	}
	
	

}
