package com.in.commons.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in.commons.util.HtmlTest;

/**
 * Servlet implementation class ServletInitializer
 */
@WebServlet("/ServletInitializer")
public class ServletInitializer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String path= "F:\\SpringWorkspace\\JustDoIt\\";
		String path="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\JustDoIt\\";
		System.out.println(path);
		HtmlTest.templateFileManipulate(path);
	}

}
