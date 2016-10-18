package com.metodosNumericos.util;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Evaluador {
	public static double evaluar(String ecuacion, double valorX) {
		Expression e = new ExpressionBuilder(ecuacion).variables("x").build();
		e.setVariable("x", valorX);
		return e.evaluate();
	}
	public static double derivar(String ecuacion, double valorX){
		UnivariateFunction funcion = new UnivariateFunction() {
			
			@Override
			public double value(double x) {
				
				return Evaluador.evaluar(ecuacion, valorX);
			}
		};
		FiniteDifferencesDifferentiator differentiator = new FiniteDifferencesDifferentiator(5, 0.01);
		UnivariateDifferentiableFunction completeF = differentiator.differentiate(funcion);
		return completeF.value(valorX);

	}
}
