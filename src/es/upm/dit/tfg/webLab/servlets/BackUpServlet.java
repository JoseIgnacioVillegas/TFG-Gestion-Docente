package es.upm.dit.tfg.webLab.servlets;


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
	
	      try{
	    	  
	         JdbcDataSource datasource = new JdbcDataSource();
	         datasource.setUrl("jdbc:h2:memFS:TFG");
	         conn = datasource.getConnection("sa", "sa");
	         stmt = conn.createStatement();
	         //El archivo se genera en la carpeta personal 
	         stmt.execute("BACKUP TO '~/backup.zip'");
	        
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
	      
	      getServletContext().getRequestDispatcher("/GestorBBDD.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
   }
}