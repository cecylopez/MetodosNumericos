package com.metodosNumericos.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFPage;

public class TestPDF {
	public static final String PDF_FILE = "/Users/robertux/test.pdf";
	
	@Before
	public void setUp() {
		File f = new File(PDF_FILE);
		if (f.exists()) {
			f.delete();
		}
	}

	@Test
	public void test() {
		PDFDocument doc = new PDFDocument();
		PDFPage page = doc.createPage(null);
		Graphics2D g2d = page.createGraphics();
		
		g2d.setFont(new Font("Helvetica", Font.BOLD, 16));
		g2d.setColor(Color.BLACK);
		g2d.drawString("Prueba Archivo PDF", 30, 30);
		
		doc.addPage(page);
		
		try {
			doc.saveDocument(PDF_FILE);
			
			File f = new File(PDF_FILE);
			assertTrue("Debe crearse archivo PDF", f.exists());
		} catch (IOException e) {
			System.out.println("Excepción: " + e.getMessage());
			e.printStackTrace();
			e.printStackTrace();
		}
	}

}
