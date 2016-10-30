package com.metodosNumericos.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.metodosNumericos.util.Evaluador;

public class TestMath {

	@Test
	public void testDiferenciacion() {
		String ecuacion = "x^2 + 1";
		assertEquals("el valor de x debe de ser 4.0", 4.0, Evaluador.derivar(ecuacion, 2), 0.1);
	}
	
	@Test
	public void testDiferenciacion2() {
		String ecuacion = "x^3-2x";
		assertEquals("el valor de x debe de ser 10", 10, Evaluador.derivar(ecuacion, 2), 0.1);
	}
	
	@Test
	public void testDifTrigonometrica() {
		String ecuacion = "cos(x)";
		assertEquals("el valor de x debe de ser 0", 0, Evaluador.derivar(ecuacion, 0), 0.1);
	}
	
	@Test
	public void testExponencial() {
		String ecuacion = "e^x";
		assertEquals("El valor de x debe ser 2.718", 2.718, Evaluador.derivar(ecuacion, 1), 0.1);
	}
	
	@Test
	public void testCantDecimales() {
		assertEquals("La cantidad de decimales debe ser 5", 5, Evaluador.compararDecimales(1.123451, 1.123456));
		
		assertEquals("La cantidad de decimales debe ser 0", 0, Evaluador.compararDecimales(1.123451, 1.2123456));
		
		assertEquals("La cantidad de decimales debe ser 2", 2, Evaluador.compararDecimales(1.1243451, 1.123456));
	}

 }
