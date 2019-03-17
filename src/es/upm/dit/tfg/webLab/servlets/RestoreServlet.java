package es.upm.dit.tfg.webLab.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.itextpdf.io.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

import org.apache.poi.util.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.h2.jdbcx.JdbcDataSource;



@WebServlet("/RestoreServlet")
public class RestoreServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
    // location to store file uploaded
   // private static final String UPLOAD_DIRECTORY = "upload";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
    /**
     * Upon receiving file upload submission, parses the request to read
     * upload data and saves the file on disk.
     * @throws java.io.IOException 
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
    	
    	Path currentRelativePath = Paths.get("");
	    String UPLOAD_DIRECTORY = currentRelativePath.toAbsolutePath().toString();
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(req)) {
            // if not, we stop here
            PrintWriter writer = resp.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
 
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
         
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest((RequestContext) req);
 
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
 
                        // saves the file on disk
                        item.write(storeFile);
                        req.setAttribute("message", "Upload has been done successfully!");
                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        // redirects client to message page
        resp.sendRedirect(req.getContextPath()+ "/GestorBBDD.jsp");
    }
}  
    
    /*
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {	
		
		req.getSession().removeAttribute("mensaje");
		
		
		Path currentRelativePath = Paths.get("");
	    String UPLOAD_DIRECTORY = currentRelativePath.toAbsolutePath().toString();
	    
		System.out.println(UPLOAD_DIRECTORY);
		 //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(req)){
        	System.out.println("ENTRA EN EL IF");
            try {
                List<FileItem> multiparts = new ServletFileUpload( new DiskFileItemFactory()).parseRequest((RequestContext) req);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        System.out.print("EL NOMBRE "+name);
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
            
               //File uploaded successfully
               req.setAttribute("mensaje", "File Uploaded Successfully");
            } catch (Exception ex) {
               req.setAttribute("message", "File Upload Failed due to " + ex);
            }          
          
        }else{
            req.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
		
		
		
		
		*/
		
		
		
  
		/*
		Path currentRelativePath = Paths.get("");
	    String s = currentRelativePath.toAbsolutePath().toString();
	    
		//String description = req.getParameter("description"); // Retrieves <input type="text" name="description">
	    Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    InputStream fileContent = filePart.getInputStream();
	    byte[] bytes = IOUtils.toByteArray(fileContent);
	    
	    FileOutputStream out = new FileOutputStream(s+"TFG.mv.db");
	    
	    
	    out.write(bytes);
	    
	    byte[] bytes = fileContent.getBytes();
	    
	    
	    
	    OutputStream os = null;
        try {
            os = new FileOutputStream(new File(s+ "TFG.mv.db"));
            os.write(fileContent.getBytes(), 0, fileContent.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
        
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(fileContent));
	    	writer.write(fileContent);
	    	
	    }catch(IOException e){
	    	System.out.println("La excepcion "+e);
	    }
	    
	    
	    // ... (do your job here)
	    
	    
	//
		
		        //FileItemFactory es una interfaz para crear FileItem
		        FileItemFactory file_factory = new DiskFileItemFactory();
		 
		        //ServletFileUpload esta clase convierte los input file a FileItem
		        ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
		        //sacando los FileItem del ServletFileUpload en una lista 
		        List items = null;
		        try {
		        	items = servlet_up.parseRequest((RequestContext) req);
		        }catch(Exception e){
		        	
		        }
		        
		//  
		       
		        
		        
		        
		        for(int i=0;i<fileContent.size();i++){
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
		
		*/
		
