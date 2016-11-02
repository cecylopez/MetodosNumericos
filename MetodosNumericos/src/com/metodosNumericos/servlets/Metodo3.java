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
import com.metodosNumericos.util.Evaluador;

@WebServlet("/Metodo3")
public class Metodo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static double DIFERENCIA_H = 0.5;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double x0 = Double.parseDouble(request.getParameter("campoX0"));
		String fx = request.getParameter("fX");

		double fx1 = Evaluador.evaluar(fx, (x0 - DIFERENCIA_H));
		double fx2 = Evaluador.evaluar(fx, (x0 + DIFERENCIA_H));
		
		double derivada = ((fx2 - fx1) / (2d * DIFERENCIA_H));
		
		JsonObject obj = new JsonObject();
		JsonArray arr = new JsonArray();
		
		JsonArray arp1 = new JsonArray();
		arp1.add(new JsonPrimitive(x0 - DIFERENCIA_H));
		arp1.add(new JsonPrimitive(fx1));
		arr.add(arp1);
		
		JsonArray arp2 = new JsonArray();
		arp2.add(new JsonPrimitive(x0 + DIFERENCIA_H));
		arp2.add(new JsonPrimitive(fx2));
		arr.add(arp2);
		
		obj.add("puntos", arr);
		obj.add("derivada", new JsonPrimitive(derivada));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-Type", "application/json; charset=UTF-8");
    	
    	response.getWriter().write(obj.toString());
		response.getWriter().flush();
	}

}
