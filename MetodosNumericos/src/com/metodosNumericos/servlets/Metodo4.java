package com.metodosNumericos.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Metodo4
 */
@WebServlet("/Metodo4")
public class Metodo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("Invocado servlet Metodo4. Datos recibidos: ");
		
		for (Entry<String, String[]> entry: request.getParameterMap().entrySet()) {
			System.out.println("\t ==== " + entry.getKey() + Arrays.toString(entry.getValue()));
		}
	}

}
