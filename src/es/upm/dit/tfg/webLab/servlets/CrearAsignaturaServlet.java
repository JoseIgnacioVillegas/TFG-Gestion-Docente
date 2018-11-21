package es.upm.dit.tfg.webLab.servlets;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.h2.table.Plan;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.PlanEstudios;
import es.upm.dit.tfg.webLab.model.Profesor;

@WebServlet("/CrearAsignaturaServlet")

public class CrearAsignaturaServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		
		String nom = req.getParameter("nombre");
		String acronimo = req.getParameter("acronimo");
		String codigo = req.getParameter("codigo");
		String tipo = req.getParameter("tipo");
		String curso = req.getParameter("curso");
		String semestre = req.getParameter("semestre");
		double ects = Double.parseDouble(req.getParameter("ects"));
		int horasTeoria = Integer.parseInt(req.getParameter("horasTeoria"),10);
		int horasLab = Integer.parseInt(req.getParameter("horasLab"),10);
		String planCodigo = req.getParameter("plan");
		String comentario = req.getParameter("comentario");
		int numeroAlumnos = Integer.parseInt(req.getParameter("numeroAlumnos"),10);
		double horasApolo = Double.parseDouble(req.getParameter("horasApolo"));

		
		
		
		
		
		Asignatura asignaturaNueva = new Asignatura();	
		asignaturaNueva.setNombre(nom);
		asignaturaNueva.setAcronimo(acronimo);
		asignaturaNueva.setCodigo(codigo);
		asignaturaNueva.setTipo(tipo);
		asignaturaNueva.setCurso(curso);
		asignaturaNueva.setSemestre(semestre);
		asignaturaNueva.setEcts(ects);
		asignaturaNueva.setHorasTeoria(horasTeoria);
		asignaturaNueva.setHorasLab(horasLab);
		asignaturaNueva.setComentario(comentario);
		asignaturaNueva.setHorasApolo(horasApolo);
		asignaturaNueva.setNumeroAlumnos(numeroAlumnos);
		asignaturaNueva.setPlanEstudios(PlanEstudiosDAOImplementation.getInstance().readPlanEstudios(planCodigo));
		
		AsignaturaDAOImplementation.getInstance().createAsignatura(asignaturaNueva);
		

		//Sacamos todas las asignaturas para pasarlas al jsp
		List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
		req.getSession().setAttribute("planesActuales", todosPlanes);
	
		
		
		
		String msj = "Asignatura creada con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDAsignatura.jsp");

	
	}

}
