package es.upm.dit.tfg.webLab.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.itextpdf.io.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


@WebServlet("/RestoreServlet")
@MultipartConfig
public class RestoreServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
    	String path = "~";
    	System.out.println("hola");
    	
    	File fichero = new File("~/TFG.mv.db");
    	
    	if(fichero==null) {
    	}else {
    		fichero.delete();
    	}

	    
 		Part filePart = req.getPart( "file" );
     	File file = new File("~");
     	OutputStream output = new FileOutputStream(file);
 		InputStream fileContent = filePart.getInputStream();
 		byte[] buffer = new byte[10240];
 		for ( int length = 0; ( length = fileContent.read( buffer ) ) > 0; )output.write( buffer, 0, length );
     	output.close();
    	
    
    	resp.sendRedirect(req.getContextPath()+ "/GestorBBDD.jsp");
    }
   }
    	
    	
   
