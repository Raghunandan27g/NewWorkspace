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

import org.json.JSONObject;

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
       
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objMap=new ObjectMapper();
		try {
			Map mapAsJSON=UserController.stepwiseGenerationController(request);
			String jsonResponse = objMap.writeValueAsString(mapAsJSON);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
