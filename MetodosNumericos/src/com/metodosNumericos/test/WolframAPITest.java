package com.metodosNumericos.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.junit.Test;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAImage;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class WolframAPITest {
	private String APIKEY = "3E4U6H-JHX2L5T4QE";

	@Test
	public void test() throws WAException {
		WAEngine engine = new WAEngine();
		engine.setAppID(APIKEY);
		engine.addFormat("Image");
		
		WAQuery query = engine.createQuery();
		query.setInput("plot x^2 + sqrt(y)");
		
		System.out.println("Query URL: " + engine.toURL(query));
		
		WAQueryResult result = engine.performQuery(query);
		
		if (result.isError()) {
            System.out.println("Query error");
            System.out.println("  error code: " + result.getErrorCode());
            System.out.println("  error message: " + result.getErrorMessage());
        } else if (!result.isSuccess()) {
            System.out.println("Query was not understood; no results available.");
        } else {
            // Got a result.
            System.out.println("Successful query. Pods follow:\n");
            for (WAPod pod : result.getPods()) {
                if (!pod.isError()) {
                	if (!"3D plots".equals(pod.getTitle())) {
                		continue;
                	}
                    
                    for (WASubpod subpod : pod.getSubpods()) {
                    	if (!"Real part".equals(subpod.getTitle())) {
                    		continue;
                    	}
                        for (Object element : subpod.getContents()) {
                            if (element instanceof WAImage) {
                            	WAImage image = ((WAImage)element);
                            	System.out.println("image: " + image.getURL());
                            	
                            	byte[] byteArray = new byte[102400];
                                FileInputStream fis = null;
								try {
									fis = new FileInputStream(image.getFile());
								} catch (FileNotFoundException e) {
									System.out.println("FileNotFoundException: " + e.getMessage());
									e.printStackTrace();
								}
                                String base64String;
                                int bytesRead = 0;
                                try {
									while ((bytesRead = fis.read(byteArray)) != -1) {
									    new String(byteArray, 0, bytesRead);
									    base64String = Base64.getEncoder().encodeToString(byteArray);
									    System.out.println("base64String: " + base64String);
									}
								} catch (IOException e) {
									System.out.println("IOException: " + e.getMessage());
									e.printStackTrace();
								}
                            }
                        }
                    }
                }
            }
        }
	}

}
