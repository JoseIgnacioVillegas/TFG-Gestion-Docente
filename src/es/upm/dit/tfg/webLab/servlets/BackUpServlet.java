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

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Backup;

import com.itextpdf.io.IOException;



@WebServlet("/BackUpServlet")
public class BackUpServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {	
		req.getSession().removeAttribute("mensaje");
  
      Connection conn = null;
      Statement stmt = null;

      try{
    	  
         JdbcDataSource datasource = new JdbcDataSource();
         datasource.setUrl("jdbc:h2:memFS:TFG");
         conn = datasource.getConnection("sa", "sa");
         stmt = conn.createStatement();
         //El archivo se genera en la carpeta personal 
         stmt.execute("BACKUP TO '~/backup.zip'");
         
         
         
         
         /*
         	resp.reset();
			resp.setContentType("application/vnd.ms-excel");
			resp.setHeader("Content-Disposition", "attachment; filename=Docentes.xlsx");
			conn.write(resp.getOutputStream());
			*/
        
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
      resp.sendRedirect(req.getContextPath()+ "/GestorBBDD.jsp");
   }
}