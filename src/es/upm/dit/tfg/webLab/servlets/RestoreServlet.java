package es.upm.dit.tfg.webLab.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.h2.jdbcx.JdbcDataSource;



@WebServlet("/RestoreServlet")
public class RestoreServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {	
		
		req.getSession().removeAttribute("mensaje");
  
     
		
		
		        //FileItemFactory es una interfaz para crear FileItem
		        FileItemFactory file_factory = new DiskFileItemFactory();
		 
		        //ServletFileUpload esta clase convierte los input file a FileItem
		        ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
		        /*sacando los FileItem del ServletFileUpload en una lista */
		        List items = null;
		        try {
		        	items = servlet_up.parseRequest((RequestContext) req);
		        }catch(Exception e){
		        	
		        }
		        
		        for(int i=0;i<items.size();i++){
		            //FileItem representa un archivo en memoria que puede ser pasado al disco duro
		            FileItem item = (FileItem) items.get(i);
		            //item.isFormField() false=input file; true=text field
		            if (! item.isFormField()){
		            	Connection conn = null;
		                Statement stmt = null;

		                try{
		                   JdbcDataSource datasource = new JdbcDataSource();
		                   
		                   datasource.setUrl("jdbc:h2:memFS:TFG");

		                   conn = datasource.getConnection("sa", "sa");
		                   stmt = conn.createStatement();
		                   stmt.execute("drop all objects delete files");
		                   stmt.execute(String.format("RUNSCRIPT FROM '%s'", item));
		                   
		                }catch(Exception e) {
		                	
		                }
		                //cual sera la ruta al archivo en el servidor
		            	//Aqui es donde se guarda el archivo obtenido, que sería el 'item', es decir, 'item, habria que meterlo en la bbdd

		            }
		        }
		
		
		
      resp.sendRedirect(req.getContextPath()+ "/GestorBBDD.jsp");
   }
}