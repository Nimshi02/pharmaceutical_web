package com.spring.mongodb.model;

public class Admin_login {
	String username;
	String password;


public Admin_login(String username,String password) {
	this.username=username;
	this.password=password;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}
public boolean validate(String username,String password)
{
	if(username.compareTo("Admin")==0 && password.compareTo("Admin")==0)
	{
		System.out.println("Success");
		return true;
	}
	else
	{
		System.out.println("fail");
		return false;
	}
		
}


}

