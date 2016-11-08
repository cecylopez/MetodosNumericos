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

@WebServlet("/Metodo2")
public class Metodo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double x0 = Evaluador.evaluar(request.getParameter("limiteInferior"));
		double x2 = Evaluador.evaluar(request.getParameter("limiteSuperior"));
		String fx = request.getParameter("funcion");
		
		JsonObject obj = new JsonObject();
		JsonArray arr = new JsonArray();
		
		List<Punto> puntos = Evaluador.calcularSimpson(fx, x0, x2);
		
		for(Punto p: puntos) {
			JsonArray arp = new JsonArray();
			arp.add(new JsonPrimitive(p.getX()));
			arp.add(new JsonPrimitive(p.getY()));
			arr.add(arp);
		}
		
		obj.add("puntos", arr);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-Type", "application/json; charset=UTF-8");
    	
    	response.getWriter().write(obj.toString());
		response.getWriter().flush();
	}

}
