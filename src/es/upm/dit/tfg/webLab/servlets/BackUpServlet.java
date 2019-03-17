package es.upm.dit.tfg.webLab.servlets;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Backup;

import com.itextpdf.io.IOException;



@WebServlet("/BackUpServlet")
public class BackUpServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {	
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		req.getSession().removeAttribute("mensaje");
  
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar los datos
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestiondatos")){
	      Connection conn = null;
	      Statement stmt = null;
	      
	      Path currentRelativePath = Paths.get("");
	      String s = currentRelativePath.toAbsolutePath().toString();
	      String path = "'"+s+"/backup.zip'";
	      
	      try{
	    	  
	         JdbcDataSource datasource = new JdbcDataSource();
	         datasource.setUrl("jdbc:h2:memFS:TFG");
	         conn = datasource.getConnection("sa", "sa");
	         stmt = conn.createStatement();
	         //El archivo se genera en la carpeta personal 
	         
	         stmt.execute("BACKUP TO "+ path);
	        
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
	      
	      
	      	// The zip file you want to download
	        File zipFile = new File(s+"/backup.zip");
	        resp.setContentType("application/zip");
	        resp.addHeader("Content-Disposition", "attachment; filename=" + "backup.zip");
	        resp.setContentLength((int) zipFile.length());
	        

	        try {
	        	
	            FileInputStream fileInputStream = new FileInputStream(zipFile);
	            OutputStream responseOutputStream = resp.getOutputStream();
	            int bytes;
	            while ((bytes = fileInputStream.read()) != -1) {
	                responseOutputStream.write(bytes);
	            }
	        } catch (IOException e) {
	        	System.out.println("lo de la excepcion "+e);
	            
	        }
	      
	      zipFile.delete();
	      
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
   }
}