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


import es.upm.dit.tfg.webLab.model.Profesor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;





@WebServlet("/ExportarDocentesServlet")

public class ExportarDocentesServlet extends HttpServlet{
	
	private static String[] columns = {"Nombre", "Apellidos", "Acronimo", "Email", "Grupo de investigación", "Plaza de profesor", "Dedicación"};
    private static List<Profesor> profesores = ProfesorDAOImplementation.getInstance().readProfesores();
    

	private final static Logger log = Logger.getLogger(ExportarDocentesServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {	
		
		req.getSession().removeAttribute("mensaje");

		// Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Docentes");

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
        for(Profesor profesor: profesores) {
            Row row = sheet.createRow(rowNum++);
            try {row.createCell(0).setCellValue(profesor.getUsuario().getNombre());
            }catch(Exception e){System.out.println(e);}
            
            try {row.createCell(1).setCellValue(profesor.getUsuario().getApellidos());
            }catch(Exception e){System.out.println(e);}

            try { row.createCell(2).setCellValue(profesor.getAcronimo());
			}catch(Exception e){System.out.println(e);}
            
			try { row.createCell(3).setCellValue(profesor.getUsuario().getCorreo());
			}catch(Exception e){System.out.println(e);}

			try { row.createCell(4).setCellValue(profesor.getGrupo().getNombre());
			}catch(Exception e){System.out.println(e);}
			
			try {row.createCell(5).setCellValue(profesor.getPlaza().getPlaza());
			}catch(Exception e){System.out.println(e);}
			
			try {row.createCell(6).setCellValue(profesor.getDedicacion());
			}catch(Exception e){System.out.println(e);}
            
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        
        try {
	        // Write the output to a file
	        FileOutputStream fileOut = new FileOutputStream("DocenteFile.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();
	        
	        resp.reset();
			resp.setContentType("application/vnd.ms-excel");
			resp.setHeader("Content-Disposition", "attachment; filename=Docentes.xlsx");
			workbook.write(resp.getOutputStream());
	        workbook.close();
        }catch(Exception e){
        	System.out.println(e);
        }
        
      
		
		
		resp.sendRedirect(req.getContextPath()+ "/ExportarDatos.jsp");
		
	}
	
	



	    
	
	
	
	
}