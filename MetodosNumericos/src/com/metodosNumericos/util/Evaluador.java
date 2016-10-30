package com.metodosNumericos.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;

import com.metodosNumericos.beans.Point;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Evaluador {
	public static final long MAX_ITERACIONES = 100;

	public static double evaluar(String ecuacion, double valorX) {
		Expression e = new ExpressionBuilder(ecuacion).variables("x").build();
		e.setVariable("x", valorX);
		return e.evaluate();
	}

	public static double derivar(String ecuacion, double valorX) {
		UnivariateFunction basicF = new UnivariateFunction() {
			public double value(double x) {
				double result = Evaluador.evaluar(ecuacion, x);
				System.out.println(ecuacion + " para x = " + x + " es " + result);
				return result;
			}
		};

		FiniteDifferencesDifferentiator differentiator = new FiniteDifferencesDifferentiator(5, 0.01);
		UnivariateDifferentiableFunction completeF = differentiator.differentiate(basicF);

		DerivativeStructure xDS = new DerivativeStructure(1, 1, 0, 2);
		DerivativeStructure yDS = completeF.value(xDS);
		
		double derivada = yDS.getPartialDerivative(1);
		System.out.println("f'(" + valorX + ") = " + derivada);

		return derivada;
	}

	public static List<Point> calcNewtonRaphson(String fx, double p0, double epsilon) {
		List<Point> puntos = new ArrayList<>();
		
		double pN = p0;
		double pNant = 0;
		int cantDecimales = 0;
		int i = 1;
		
		do {
			double fpN = Evaluador.evaluar(fx, pN);
			double dpN = Evaluador.derivar(fx, pN);
			
			System.out.println("i = " + i);
			System.out.println("pN = " + pN);
			System.out.println("f(pN) = " + fpN);
			System.out.println("f'(pN) = " + dpN);
			
			puntos.add(new Point(pN, fpN));
			
			pNant = pN;
			pN = pN - (fpN / dpN);
			
			cantDecimales = compararDecimales(pN, pNant);
			System.out.println("decimales: " + cantDecimales);
			
		} while ((i++ > MAX_ITERACIONES) || cantDecimales <= epsilon);
		
		return puntos;
	}
	
	public static int compararDecimales(double num1, double num2) {
		String strNum1 = String.valueOf(num1);
		String strNum2 = String.valueOf(num2);
		
		System.out.println("Comparando decimales de " + strNum1 + " con " + strNum2);
		
		if (!strNum1.contains(".") || !strNum2.contains(".")) {
			return 0;
		} else {
			String decsNum1 = strNum1.substring(strNum1.lastIndexOf(".") + 1);
			String decsNum2 = strNum2.substring(strNum2.lastIndexOf(".") + 1);
			int i = 0;
			while (decsNum1.charAt(i) == decsNum2.charAt(i)) i++;
			
			return i;
		}
	}
}
