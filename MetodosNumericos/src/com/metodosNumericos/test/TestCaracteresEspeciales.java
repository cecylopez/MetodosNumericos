package com.metodosNumericos.test;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.Test;

public class TestCaracteresEspeciales {

	@Test
	public void test() throws UnsupportedEncodingException {
		String normal = "x^3 - 2x";
		String escapado = "x%5E3+-+2x";
		
		assertEquals("Función debe poder convertirse correctamente", normal, URLDecoder.decode(escapado, "UTF-8"));
	}

}
