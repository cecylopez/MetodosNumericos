package com.metodosNumericos.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.metodosNumericos.util.Evaluador;

@WebServlet("/Metodo4")
public class Metodo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n  = Integer.parseInt(request.getParameter("cparticionesn"));
		int m  = Integer.parseInt(request.getParameter("cparticionesm"));
		int o  = Integer.parseInt(request.getParameter("cparticioneso"));
		
		String integralM = request.getParameter("IntegralM");
		
		double limiteS1 = Evaluador.evaluar(request.getParameter("limiteS1"));
		String limiteS2 = request.getParameter("limiteS2");
		double limiteS3 = Evaluador.evaluar(request.getParameter("limiteS3"));
		
		double limiteI1 = Evaluador.evaluar(request.getParameter("limiteI1"));
		String limiteI2 = request.getParameter("limiteI2");
		double limiteI3 = Evaluador.evaluar(request.getParameter("limiteI3"));
		
		System.out.println("n = " + n + " \t m = " + m + " \t o = " + o);
		System.out.println("integralM = " + integralM);
		System.out.println("limite S1 = " + limiteS1 + " \tS2 = " + limiteS2 + " \tS3 = " + limiteS3);
		System.out.println("limite I1 = " + limiteI1 + " \tI2 = " + limiteI2 + " \tI3 = " + limiteI3);
		
		JsonObject obj = new JsonObject();
		
		//Paso 1
		double h = (limiteS1 - limiteI1) / (double)n;
		double j1 = 0;	//Términos extremos
		double j2 = 0;	//Términos pares
		double j3 = 0;	//Términos impares
		
		//Paso 2
		for (int i=0; i<=n; i++) {
			
			System.out.println("======== PARA I = " + i);
			//Paso 3
			double x = limiteI1 + (i * h);
			String HX = "(" + limiteS2 + " - " + limiteI2 + ") / " + m;
			double K1 = Evaluador.evaluar(integralM.replaceAll("y", "(" + limiteI2 + ")"), x) + Evaluador.evaluar(integralM.replaceAll("y", "(" + limiteS2 + ")"), x);	//Términos extremos
			double K2 = 0;		//Términos pares
			double K3 = 0;		//Términos impares
			
			System.out.println("\tx(" + i + ") = " + x);
			System.out.println("\tHX(" + i + ") = " + HX);
			System.out.println("\tK1(" + i + ") = " + K1 + " \tusando " + integralM.replaceAll("y", "(" + limiteI2 + ")") + " + " + integralM.replaceAll("y", "(" + limiteS2 + ")"));
			System.out.println("\tK2(" + i + ") = " + K2);
			System.out.println("\tK3(" + i + ") = " + K3);
			
			//Paso 4
			for (int j=1; j<m; j++) {
				
				//Paso 5
				double y = Evaluador.evaluar(limiteI2 + " +  (" + j + " * " + HX + ")", x);
				double Q = Evaluador.evaluar(integralM, x, y);
				
				System.out.println("\t\ty(" + j + ") = " + y);
				System.out.println("\t\tQ(" + j + ") = " + Q);
				//Paso 6
				if (j % 2 == 0) {
					K2 += Q;
				} else {
					K3 += Q;
				}
			}
			
			//Paso 7
			double L = (K1 + (2.0 * K2) + (4.0 * K3)) * (Evaluador.evaluar(HX, x) / 3.0);
			System.out.println("\tL(" + i + ") = " + L);
			
			//Paso 8
			if (i == 0 || i == n) {
				j1 += L;
			} else if (i % 2 == 0) {
				j2 += L;
			} else {
				j3 += L;
			}
			
			System.out.println("\tj1(" + i + ") = " + j1);
			System.out.println("\tj2(" + i + ") = " + j2);
			System.out.println("\tj3(" + i + ") = " + j3);
		}
		
		//Paso 9
		double J = (j1 + (2 * j2) + (4 * j3)) * (h/3);
		
		System.out.println("\n\nJ = " + J);
		
		obj.add("resultado", new JsonPrimitive(J));
		obj.add("urlGrafico", new JsonPrimitive(Evaluador.obtenerImagenPlot(integralM)));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-Type", "application/json; charset=UTF-8");
    	response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    	
    	response.getWriter().write(obj.toString());
		response.getWriter().flush();
	}

}
