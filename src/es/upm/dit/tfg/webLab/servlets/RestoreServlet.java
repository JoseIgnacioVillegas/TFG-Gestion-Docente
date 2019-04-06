package es.upm.dit.tfg.webLab.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.DeleteDbFiles;

import com.itextpdf.io.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



@WebServlet("/RestoreServlet")
@MultipartConfig
public class RestoreServlet extends HttpServlet{
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
    	
    	//Ya sube los archivos a escritorio
    	Path currentRelativePath = Paths.get("");
	    String s = currentRelativePath.toAbsolutePath().toString();
	    String path = s+"/TFG.mv.db";
		Part filePart = req.getPart( "file" );
    	File file = new File(path);
    	OutputStream output = new FileOutputStream(file);
		InputStream fileContent = filePart.getInputStream();
		byte[] buffer = new byte[10240];
		for ( int length = 0; ( length = fileContent.read( buffer ) ) > 0; )output.write( buffer, 0, length );
    	output.close();
    	
    	
    	
    	
    	
    	//Ahora a ver si conseguimos hacer el restore
    	Connection conn = null;
	     Statement stmt = null;
    	 try{
	    	  
	         JdbcDataSource datasource = new JdbcDataSource();
	         datasource.setUrl("jdbc:h2:memFS:TFG");
	         conn = datasource.getConnection("sa", "sa");
	         stmt = conn.createStatement();
	         
	         
	         //Aqui es donde tenemos que hacer el restore
	         DeleteDbFiles.execute("~", "TFG", true);
	         
	         stmt.execute(String.format("RUNSCRIPT FROM '%s'", path));
  
	         
	         stmt.close();
	         conn.close();
	
	      }
	      catch (Exception e){
	         e.printStackTrace();
	      }
	      finally{
	         if (stmt != null){
	            try{
	               stmt.close();
	            }
	            catch (SQLException e){
	               e.printStackTrace();
	            }
	         }
	         if (conn != null){
	            try{
	               conn.close();
	            }
	            catch (SQLException e){
	               e.printStackTrace();
	            }
	         }
	      }
	      
	      
    	 //Eto para cuando funcione, que luego lo borre
    	 file.delete();
    
    	resp.sendRedirect(req.getContextPath()+ "/GestorBBDD.jsp");
    }
   }
    	
    	
   
