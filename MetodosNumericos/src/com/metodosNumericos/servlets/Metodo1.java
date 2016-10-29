package com.metodosNumericos.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@WebServlet("/Metodo1")
public class Metodo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject obj = new JsonObject();
		JsonArray arr = new JsonArray();
		
		JsonArray arr1 = new JsonArray();
		arr1.add(new JsonPrimitive(0));
		arr1.add(new JsonPrimitive(0));
		
		JsonArray arr2 = new JsonArray();
		arr2.add(new JsonPrimitive(1));
		arr2.add(new JsonPrimitive(1));
		
		JsonArray arr3 = new JsonArray();
		arr3.add(new JsonPrimitive(2));
		arr3.add(new JsonPrimitive(4));
		
		JsonArray arr4 = new JsonArray();
		arr4.add(new JsonPrimitive(4));
		arr4.add(new JsonPrimitive(16));
		
		JsonArray arr5 = new JsonArray();
		arr5.add(new JsonPrimitive(8));
		arr5.add(new JsonPrimitive(64));
		
		arr.add(arr1);
		arr.add(arr2);
		arr.add(arr3);
		arr.add(arr4);
		arr.add(arr5);
		
		obj.add("puntos", arr);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-Type", "application/json; charset=UTF-8");
    	
    	response.getWriter().write(obj.toString());
		response.getWriter().flush();
	}

}
