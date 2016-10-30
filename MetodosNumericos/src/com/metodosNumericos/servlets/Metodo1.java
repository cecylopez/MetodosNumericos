package com.metodosNumericos.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.metodosNumericos.beans.Point;
import com.metodosNumericos.util.Evaluador;

@WebServlet("/Metodo1")
public class Metodo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BigDecimal p0 = new BigDecimal(request.getParameter("campoP0"));
		BigDecimal e = new BigDecimal(request.getParameter("campoE"));
		String fx = request.getParameter("funcion");
		
		JsonObject obj = new JsonObject();
		JsonArray arr = new JsonArray();
		
		List<Point> puntos = Evaluador.calcNewtonRaphson(fx, p0.doubleValue(), e.doubleValue());
		
		for(Point p: puntos) {
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
