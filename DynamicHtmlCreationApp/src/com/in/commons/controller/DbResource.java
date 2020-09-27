package com.in.commons.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import com.in.commons.util.UserConstants;

public class DbResource {
public static Connection getConnection()	{
	Connection con=null;
	try {
			Class.forName("org.postgresql.Driver");
			con= DriverManager.getConnection(UserConstants.DB_URL+UserConstants.DB_NAME, UserConstants.DB_USERNAME, UserConstants.DB_PASSWORD);
			System.out.println(con);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
	}
}
