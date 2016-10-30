package com.metodosNumericos.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.metodosNumericos.beans.Punto;
import com.metodosNumericos.util.Evaluador;

public class TestNewtonRaphson {
	public static final String VAR_X = "x";
	private double p0;
	private double e;
	private String fx;

	@Before
	public void setUp() {
		p0 = 2;
		e = 5;
		fx = "x^3 - 2x";
	}
	
	@Test
	public void test() {
		List<Punto> puntos = Evaluador.calcNewtonRaphson(fx, p0, e);
		assertTrue("Debe devolver al menos un punto", puntos.size() > 0);
		assertEquals("El resultado de la función debe ser 1.41421", 1.41421, puntos.get(puntos.size() - 1).getX(), 0.01);
	}

}
