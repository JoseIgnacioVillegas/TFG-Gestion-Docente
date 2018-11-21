package es.upm.dit.tfg.webLab.servlets;


import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;


import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.PlanEstudios;
import es.upm.dit.tfg.webLab.model.Profesor;


@WebServlet("/EditarAsignaturaServlet")

public class EditarAsignaturaServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		
		
		String codigoAnt = req.getParameter("codigo1");
		String nom = req.getParameter("nombre");
		String codigo = req.getParameter("codigo");
		String acronimo = req.getParameter("acronimo");
		String tipo = req.getParameter("tipo");
		String curso = req.getParameter("curso");
		String semestre = req.getParameter("semestre");
		double ects = Double.parseDouble(req.getParameter("ects"));
		int horasTeoria = Integer.parseInt(req.getParameter("horasTeoria"));
		int horasLab = Integer.parseInt(req.getParameter("horasLab"));
		String comentario = req.getParameter("comentario");
		int numeroAlumnos = Integer.parseInt(req.getParameter("numeroAlumnos"),10);
		double horasApolo = Double.parseDouble(req.getParameter("horasApolo"));

		
	    
		
		
		
		
		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAnt);
		PlanEstudios plan = asignatura.getPlanEstudios();
		AsignaturaDAOImplementation.getInstance().deleteAsignatura(asignatura);

		
		
		Asignatura asignaturaNueva = new Asignatura();	
		
		asignaturaNueva.setNombre(nom);
		asignaturaNueva.setCodigo(codigo);
		asignaturaNueva.setCurso(curso);
		asignaturaNueva.setAcronimo(acronimo);
		asignaturaNueva.setTipo(tipo);
		asignaturaNueva.setSemestre(semestre);
		asignaturaNueva.setEcts(ects);
		asignaturaNueva.setHorasTeoria(horasTeoria);
		asignaturaNueva.setHorasLab(horasLab);
		asignaturaNueva.setPlanEstudios(plan);
		asignaturaNueva.setComentario(comentario);
		asignaturaNueva.setHorasApolo(horasApolo);
		asignaturaNueva.setNumeroAlumnos(numeroAlumnos);

		AsignaturaDAOImplementation.getInstance().createAsignatura(asignaturaNueva);
		
		//Sacamos todas las asignaturas para pasarlas al jsp
		List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
		req.getSession().setAttribute("planesActuales", todosPlanes);
	
		String msj = "Asignatura editada con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDAsignatura.jsp");

	
	}

}
