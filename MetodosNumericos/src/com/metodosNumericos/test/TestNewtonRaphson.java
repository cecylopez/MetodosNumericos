package com.metodosNumericos.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.metodosNumericos.util.Evaluador;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TestNewtonRaphson {
	public static final String VAR_X = "x";
	private BigDecimal p0;
	private BigDecimal e;
	private String fx;

	@Before
	public void setUp() {
		p0 = new BigDecimal(2);
		e = new BigDecimal(5);
		fx = "x^3 - 2x";
	}
	
	@Test
	public void test() {
		//--evaluar p0 en derivada
		//evaluar p0 en funcion
				
		//iterar hasta que cant de decimales de pN < e
		//pN = p0 - (f(p0) / f'(p0))
		
		BigDecimal fp0 = new BigDecimal(Evaluador.evaluar(fx, p0.doubleValue()));
		
		assertEquals("f(p0) debe ser igual a 4", new BigDecimal("4"), fp0);
		
		BigDecimal d_fp0 = new BigDecimal(Evaluador.derivar(fx, p0.doubleValue()));
		
		assertEquals("f'(p0) debe ser igual a 10", new BigDecimal("10"), d_fp0);
	}

}
