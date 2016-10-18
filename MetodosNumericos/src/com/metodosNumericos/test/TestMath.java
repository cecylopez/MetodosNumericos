package com.metodosNumericos.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.metodosNumericos.util.Evaluador;

public class TestMath {

	@Test
	public void testDiferenciacion() {
		String ecuacion = "x^2 + 1";
		assertEquals("el valor de x debe de ser 2.0", 2.0, Evaluador.derivar(ecuacion, 1), 0.1);
	}
	@Test
	public void testDifTrigonometrica(){
		String ecuacion= "sin(x)";
		assertEquals("el valor de x debe de ser -1.14263", 0.8414, Evaluador.derivar(ecuacion, 1), 0.1);
	}

}
