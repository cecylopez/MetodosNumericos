package com.metodosNumericos.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.metodosNumericos.util.Evaluador;

public class TestDerivada {

	@Test
	public void testPolinomica() {
		assertEquals("El resultado de la derivada de f(x) debe ser 12", 12, Evaluador.derivar("x^3", 2), 0.01);
	}

	@Test
	public void testTrigonometrica() {
		assertEquals("El resultado de la derivada de f(x) debe ser 0.5", 0.5, Evaluador.derivar("sin(x)", 30), 0.01);
	}
	
	@Test
	public void testDiferenciasCentrales() {
		assertEquals("El resultado de la derivada de f(x) debe ser 12", 12.25, Evaluador.calcularDiferenciasCentrales("x^3", 2).get(2).getY(), 0.01);
		
		assertEquals("El resultado de la derivada de f(x) debe ser 1", 0.95, Evaluador.calcularDiferenciasCentrales("sin(x)", 0).get(2).getY(), 0.01);
	}
}
