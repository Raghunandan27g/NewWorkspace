package com.in.commons.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbResource {
public Connection getConnection()	{
	Connection con=null;
	try {
		String url="jdbc:postgresql://localhost:5432/testdb";
		String username="";
		String password="";
			Class.forName("org.postgresql.Driver");
			con= DriverManager.getConnection(url, username, password);
			System.out.println(con);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
	}
}
