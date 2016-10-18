package com.metodosNumericos.test;

import static org.junit.Assert.*;

import org.junit.Test;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TestParser {

	@Test
	public void testAritmetica() {
		String ecuacion = "2x + 1";
		double valorX = 2.0;
		Expression e = new ExpressionBuilder(ecuacion).variables("x").build();
		e.setVariable("x", valorX);
		assertEquals("El resultado de la evaluacion debe de ser 5", 5.0, e.evaluate(), 0.1);
	}

	@Test
	public void testPotencias() {
		String ecuacion1 = "y^2 - sqrt y";
		double valorY = 4.0;
		Expression eP = new ExpressionBuilder(ecuacion1).variables("y").build();
		eP.setVariable("y", valorY);
		assertEquals("El resultado de la evaluacion de de ser 14", 14.0, eP.evaluate(), 0.1);

	}

	@Test
	public void testLogAndTrg() {
		String ecuacion2 = "sinh(x) - log(x) + cos(x)";
		double valorX = 0.5;
		Expression eL = new ExpressionBuilder(ecuacion2).variables("x").build();
		eL.setVariable("x", valorX);
		assertEquals("El resultado de la evaluacion debe de ser 2.09183", 2.09183, eL.evaluate(),0.1);
	}

}
