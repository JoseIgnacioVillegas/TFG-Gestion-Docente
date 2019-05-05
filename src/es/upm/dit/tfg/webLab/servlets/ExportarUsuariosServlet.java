package es.upm.dit.tfg.webLab.servlets;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itextpdf.io.IOException;



import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.subject.Subject;

import java.io.FileOutputStream;





@WebServlet("/ExportarUsuariosServlet")

public class ExportarUsuariosServlet extends HttpServlet{
	
	private static String[] columns = {"Nombre", "Apellidos", "Email"};
    private static List<Usuario> todosUsuarios = UsuarioDAOImplementation.getInstance().readUsuarios();
    private static List<Profesor> profesores = ProfesorDAOImplementation.getInstance().readProfesores();

    private final static Logger log = Logger.getLogger(ExportarUsuariosServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar los datos
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestiondatos")){
			for(int i = 0; i < profesores.size(); i++) {
				for(int j = 0; j < todosUsuarios.size(); j++) {
					if(profesores.get(i).getUsuario().getId()==todosUsuarios.get(j).getId())todosUsuarios.remove(j);
				}
			}
			
			// Create a Workbook
	        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
	
	        /* CreationHelper helps us create instances of various things like DataFormat, 
	           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	        CreationHelper createHelper = workbook.getCreationHelper();
	
	        // Create a Sheet
	        Sheet sheet = workbook.createSheet("Usuarios");
	
	        // Create a Font for styling header cells
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());
	
	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	
	        // Create a Row
	        Row headerRow = sheet.createRow(0);
	
	        // Create cells
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(headerCellStyle);
	        }
	
	        // Create Cell Style for formatting Date
	        CellStyle dateCellStyle = workbook.createCellStyle();
	        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
	
	        // Create Other rows and cells with employees data
	        int rowNum = 1;
	        for(Usuario usuario: todosUsuarios) {
	            Row row = sheet.createRow(rowNum++);
	            try {row.createCell(0).setCellValue(usuario.getNombre());}catch(Exception e){log.error(e);}
	            
	            try {row.createCell(1).setCellValue(usuario.getApellidos());}catch(Exception e){log.error(e);}
	
				try { row.createCell(2).setCellValue(usuario.getCorreo());}catch(Exception e){log.error(e);}
	            
	        }
	
			// Resize all columns to fit the content size
	        for(int i = 0; i < columns.length; i++) {
	            sheet.autoSizeColumn(i);
	        }
	        
	        try {
		        // Write the output to a file
		        FileOutputStream fileOut = new FileOutputStream("UsuariosFile.xlsx");
		        workbook.write(fileOut);
		        fileOut.close();
		        resp.reset();
				resp.setContentType("application/vnd.ms-excel");
				resp.setHeader("Content-Disposition", "attachment; filename=Usuarios.xlsx");
				workbook.write(resp.getOutputStream());
		        workbook.close();
	        }catch(Exception e){
	        	log.error(e);
	        }
	        log.info("El usuario "+currentUser.getPrincipal().toString()+" ha exportado las asignaturas del departamento.");
	        getServletContext().getRequestDispatcher("/ExportarDatos.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
		
	}

}