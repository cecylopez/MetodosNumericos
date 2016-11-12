package com.metodosNumericos.servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFPage;

import sun.misc.BASE64Decoder;

@WebServlet("/GeneradorPDF")
public class GeneradorPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GeneradorPDF() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/GeneradorPDF invocado");
		System.out.println("params: " + request.getParameter("params"));
		System.out.println("puntos: " + request.getParameter("puntos"));
		System.out.println("img: " + request.getParameter("img"));
		
		PDFDocument doc = new PDFDocument();
		PDFPage page = doc.createPage(null);
		Graphics2D g2d = page.createGraphics();
		
		g2d.setFont(new Font("Helvetica", Font.BOLD, 16));
		g2d.setColor(Color.BLACK);
		
		int lineHeight = g2d.getFontMetrics().getHeight();
		int curLineHeight = 30;
		
		g2d.drawString("Parámetros", 30, curLineHeight);
		curLineHeight += (lineHeight * 2);
		
		g2d.setFont(new Font("Helvetica", Font.PLAIN, 10));
		for (String param: request.getParameter("params").split("&")) {
			String[] parmArr = param.split("=");
			g2d.drawString(parmArr[0], 30, curLineHeight);
			g2d.drawString(URLDecoder.decode(parmArr[1], "UTF-8"), 120, curLineHeight);
			curLineHeight += lineHeight;
		}
		
		curLineHeight += (lineHeight * 2);
		
		g2d.setFont(new Font("Helvetica", Font.BOLD, 16));
		g2d.drawString("Puntos", 30, curLineHeight);
		
		curLineHeight += (lineHeight * 2);
		g2d.setFont(new Font("Helvetica", Font.PLAIN, 10));
		
		String[] puntos = request.getParameter("puntos").split(",");
		
		int tipoMetodo = Integer.parseInt(request.getParameter("metodo"));
		int j = 1;
		
		switch(tipoMetodo) {
		case 1:
			g2d.drawString("Iteración (N)", 30, curLineHeight);
			g2d.drawString("pN", 100, curLineHeight);
			g2d.drawString("f(pN)", 230, curLineHeight);
			
			g2d.drawRect(25, curLineHeight - 10, 350, lineHeight);
			curLineHeight += lineHeight;
			
			for (int i=0; i<puntos.length - 1; i+=2) {
				g2d.drawString(String.valueOf(j++), 30, curLineHeight);
				g2d.drawString(String.valueOf(puntos[i]), 100, curLineHeight);
				g2d.drawString(String.valueOf(puntos[i + 1]), 230, curLineHeight);
				
				g2d.drawRect(25, curLineHeight - 10, 350, lineHeight);
				curLineHeight += lineHeight;
			}
			break;
			
		case 2:
			g2d.drawString(String.valueOf("x0"), 30, curLineHeight);
			g2d.drawString(String.valueOf(puntos[0]), 100, curLineHeight);
			g2d.drawString(String.valueOf(puntos[1]), 230, curLineHeight);
				
			g2d.drawRect(25, curLineHeight - 10, 350, lineHeight);
			curLineHeight += lineHeight;
			
			g2d.drawString(String.valueOf("x1"), 30, curLineHeight);
			g2d.drawString(String.valueOf(puntos[2]), 100, curLineHeight);
			g2d.drawString(String.valueOf(puntos[3]), 230, curLineHeight);
				
			g2d.drawRect(25, curLineHeight - 10, 350, lineHeight);
			curLineHeight += lineHeight;
			
			g2d.drawString(String.valueOf("x2"), 30, curLineHeight);
			g2d.drawString(String.valueOf(puntos[4]), 100, curLineHeight);
			g2d.drawString(String.valueOf(puntos[5]), 230, curLineHeight);
				
			g2d.drawRect(25, curLineHeight - 10, 350, lineHeight);
			curLineHeight += lineHeight;
			
			g2d.drawString(String.valueOf("resultado"), 30, curLineHeight);
			g2d.drawString(String.valueOf(" "), 100, curLineHeight);
			g2d.drawString(String.valueOf(puntos[7]), 230, curLineHeight);
				
			g2d.drawRect(25, curLineHeight - 10, 350, lineHeight);
			curLineHeight += lineHeight;
			
			break;
			
		case 3:
			g2d.drawString("Derivada en x0", 30, curLineHeight);
			g2d.drawString(puntos[0], 150, curLineHeight);
			
			g2d.drawRect(25, curLineHeight - 10, 250, lineHeight);
			curLineHeight += lineHeight;
			
			break;
			
		case 4:
			g2d.drawString("Resultado", 30, curLineHeight);
			g2d.drawString(puntos[0], 150, curLineHeight);
			
			g2d.drawRect(25, curLineHeight - 10, 250, lineHeight);
			curLineHeight += lineHeight;
			
			break;
		}
		
		curLineHeight += (lineHeight * 2);
		
		g2d.setFont(new Font("Helvetica", Font.BOLD, 16));
		g2d.drawString("Gráfica", 30, curLineHeight);
		
		curLineHeight += (lineHeight * 2);
		
		String imageData = request.getParameter("img");
		imageData = imageData.substring(imageData.lastIndexOf(",") + 1);
		
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(imageData)));
		g2d.drawImage(img, 30, curLineHeight, null);
		
		doc.addPage(page);
		
		doc.saveDocument(response.getOutputStream());
		
		response.setHeader("Content-Disposition", "attachment; filename=resultado.pdf");
		response.setContentType("application/pdf");
	}

}
