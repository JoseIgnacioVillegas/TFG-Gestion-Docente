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
import es.upm.dit.tfg.webLab.dao.ProfesorGrupoClaseAsociacionDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.GrupoClase;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.ProfesorGrupoClaseAsociacion;



@WebServlet("/CRUDGruposClaseServlet")

public class CRUDGruposClaseServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		req.getSession().removeAttribute("mensaje");
		String codigoAsignatura = req.getParameter("asignatura");
		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAsignatura);

		//Primero creamos los nuevos grupos
		//Atributos de los grupos
		String nombre[]; 
		nombre = req.getParameterValues("nombreGrupoNuevo");
		String nAlumnos[]; 
		nAlumnos = req.getParameterValues("numeroAlumnosGrupoNuevo");
		String descripcion[]; 
		descripcion = req.getParameterValues("descripcionGrupoNuevo");
		
		//Atributos de los profesores 
		String hPracticas[]; 
		hPracticas = req.getParameterValues("hPracticasNuevo");
		String hLab[]; 
		hLab = req.getParameterValues("hLabNuevo");
		String hTeoria[]; 
		hTeoria = req.getParameterValues("hTeoriaNuevo");
		String profesores[];
		profesores = req.getParameterValues("docentes");
		
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
				
				String nProfesores = req.getParameter(nombre[i]);
				for (int j = 0; j< nProfesores.length(); j++) {
					Profesor profe = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(profesores[j]));
					System.out.println(profe.getUsuario().getNombre());
										
					
					ProfesorGrupoClaseAsociacion asociacion = new ProfesorGrupoClaseAsociacion();
					
					asociacion.setHorasLaboratorio(Integer.parseInt(hLab[j]));
					asociacion.setHorasPracticas(Integer.parseInt(hPracticas[j]));
					asociacion.setHorasTeoria(Integer.parseInt(hTeoria[j]));
					asociacion.setGrupoClase(grupoNuevo);
					asociacion.setProfesor(profe);
					ProfesorGrupoClaseAsociacionDAOImplementation.getInstance().createAsociacion(asociacion);
					ProfesorDAOImplementation.getInstance().updateProfesor(profe);
					GrupoClaseDAOImplementation.getInstance().updateGrupoClase(grupoNuevo);
				}
				
				
				String hLabCambio[] = new String[hLab.length-nProfesores.length()];
				String hTeoriaCambio[] = new String[hLab.length-nProfesores.length()];
				String profesoresCambio[] = new String[hLab.length-nProfesores.length()];
				int indice = 0;
				for (int j = nProfesores.length(); j< hLab.length; j++) {
					hLabCambio[indice] = hLabCambio[j];
					hTeoriaCambio[indice] = hTeoria[j];
					profesoresCambio[indice] = profesores[j];
					indice++;
				}
				
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