package com.in.commons.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.commons.controller.UserController;
import com.in.commons.dao.UserTableDetailsModel;
import com.in.commons.util.fileGeneration;

/**
 * Servlet implementation class ServletInitializer
 */
@WebServlet("/ServletInitializer")
public class ServletInitializer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        UserTableDetailsModel obj=DAOConfig.getValueFromRequestConvertToPOJO(request);
		System.out.println(obj.getTxtUrl());
		//boolean status=UserController.stepwiseGenerationController(request);
		response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write("true");
	}
	
}
