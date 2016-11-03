package com.metodosNumericos.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.metodosNumericos.beans.Punto;
import com.metodosNumericos.util.Evaluador;

@WebServlet("/Metodo3")
public class Metodo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double x0 = Double.parseDouble(request.getParameter("campoX0"));
		String fx = request.getParameter("fX");
		
		JsonObject obj = new JsonObject();
		JsonArray arr = new JsonArray();
		
		List<Punto> puntos = Evaluador.calcularDiferenciasCentrales(fx, x0);
		
		JsonArray arp1 = new JsonArray();
		arp1.add(new JsonPrimitive(puntos.get(0).getX()));
		arp1.add(new JsonPrimitive(puntos.get(0).getY()));
		arr.add(arp1);
		
		JsonArray arp2 = new JsonArray();
		arp2.add(new JsonPrimitive(puntos.get(1).getX()));
		arp2.add(new JsonPrimitive(puntos.get(1).getY()));
		arr.add(arp2);
		
		obj.add("puntos", arr);
		obj.add("derivada", new JsonPrimitive(puntos.get(2).getY()));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-Type", "application/json; charset=UTF-8");
    	
    	response.getWriter().write(obj.toString());
		response.getWriter().flush();
	}

}
