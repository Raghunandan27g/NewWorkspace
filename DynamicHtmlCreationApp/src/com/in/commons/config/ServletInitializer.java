package com.in.commons.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in.commons.controller.UserController;
import com.in.commons.util.fileGeneration;

/**
 * Servlet implementation class ServletInitializer
 */
@WebServlet("/ServletInitializer")
public class ServletInitializer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserController.stepwiseGenerationController(request);
//		fileGeneration.multiPageTemplateFileManipulate(path);//
//		fileGeneration.indexTemplateFileManipulate(path);
	}

}
