package com.metodosNumericos.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/Metodo4")
public class Metodo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cparticiones  = Integer.parseInt(request.getParameter("cparticiones"));
		String IntegralM = request.getParameter("IntegralM");
		
		JsonObject obj = new JsonObject();
		JsonArray arr = new JsonArray();
		
		//TODO: Implementar
		
		
		obj.add("puntos", arr);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-Type", "application/json; charset=UTF-8");
    	
    	response.getWriter().write(obj.toString());
		response.getWriter().flush();
	}

}
