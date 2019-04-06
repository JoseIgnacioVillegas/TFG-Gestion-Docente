package es.upm.dit.tfg.webLab.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.GrupoClaseDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.GrupoClase;
import es.upm.dit.tfg.webLab.model.Profesor;



@WebServlet("/CRUDGruposClaseServlet")

public class CRUDGruposClaseServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		req.getSession().removeAttribute("mensaje");
		String codigoAsignatura = req.getParameter("asignatura");
		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAsignatura);

		//Primero creamos los nuevos grupos
		String nombre[]; 
		nombre = req.getParameterValues("nombreGrupoNuevo");
		String nAlumnos[]; 
		nAlumnos = req.getParameterValues("numeroAlumnosGrupoNuevo");
		String hPracticas[]; 
		hPracticas = req.getParameterValues("hPracticasNuevo");
		String hLab[]; 
		hLab = req.getParameterValues("hLabNuevo");
		String hTeoria[]; 
		hTeoria = req.getParameterValues("hTeoriaNuevo");
		String descripcion[]; 
		descripcion = req.getParameterValues("descripcionGrupoNuevo");
		
		
		for (int i = 0; i< nombre.length; i++) {
			
			if(nombre[i]=="") {
				
			}else {
			
				int numeroAlumnos = 0;
				try {
					numeroAlumnos =Integer.parseInt(nAlumnos[i]);
				}catch(NumberFormatException ex){
					
				}
				GrupoClase grupoNuevo = new GrupoClase();
				grupoNuevo.setNombre(nombre[i]);
				grupoNuevo.setAsignatura(asignatura);
				grupoNuevo.setNumeroAlumnos(numeroAlumnos);
				grupoNuevo.setDescripcion(descripcion[i]);
				GrupoClaseDAOImplementation.getInstance().createGrupoClase(grupoNuevo);
				
				asignatura.setGrupoClase(grupoNuevo);
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
			}
			
		}
		
		
		
		String gruposBorrados[]; 
		gruposBorrados = req.getParameterValues("gruposBorrados");
		
		
		List<GrupoClase> gruposActuales = asignatura.getGruposClase();
		try {
		for (int i = 0; i< gruposBorrados.length; i++) {
			for (int j = 0; j< gruposActuales.size(); j++) {
				if(gruposActuales.get(j).getId()==Integer.parseInt(gruposBorrados[i]))gruposActuales.remove(j);
			}
			GrupoClaseDAOImplementation.getInstance().deleteGrupoClase(GrupoClaseDAOImplementation.getInstance().readGrupoClase(Integer.parseInt(gruposBorrados[i])));
		}
		asignatura.setGruposClase(gruposActuales);
		AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
		}catch(Exception e) {
			
		}
		
		
		getServletContext().getRequestDispatcher("/CRUDAsignatura.jsp").forward(req, resp);
		
	}
}