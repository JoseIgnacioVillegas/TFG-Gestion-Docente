package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.PlanEstudios;


@WebServlet("/EditarAsignaturaServlet")

public class EditarAsignaturaServlet extends HttpServlet{
	

	private final static Logger log = Logger.getLogger(EditarAsignaturaServlet.class);

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		
		String codigoAnt = req.getParameter("codigo1");
		String nom = req.getParameter("nombre");
		String codigo = req.getParameter("codigo");
		String acronimo = req.getParameter("acronimo");
		String tipo = req.getParameter("tipo");
		String curso = req.getParameter("curso");
		String semestre = req.getParameter("semestre");
		String planEstudios = req.getParameter("plan");
		
		
		double ects = 0.0;
		try{ ects = Double.parseDouble(req.getParameter("ects"));}catch(Exception e) {log.error(e); }
		
		int horasTeoria = 0;
		try{ horasTeoria = Integer.parseInt(req.getParameter("horasTeoria"));}catch(Exception e) {log.error(e); }
		
		int horasLab = 0;
		try{ horasLab = Integer.parseInt(req.getParameter("horasLab"));}catch(Exception e) {log.error(e); }
		
		String comentario = req.getParameter("comentario");
		int numeroAlumnos = 0;
		try{numeroAlumnos = Integer.parseInt(req.getParameter("numeroAlumnos"),10);}catch(Exception e) {log.error(e); }
		
		double horasApolo = 0.0;
		try{ horasApolo = Double.parseDouble(req.getParameter("horasApolo"));}catch(Exception e) {log.error(e); }

		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar docencia
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestiondocencia")){
			Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAnt);
			
			
			System.out.println(planEstudios);
			
			
			PlanEstudios plan = asignatura.getPlanEstudios();
			if(planEstudios.equals(plan.getCodigo())) {
				System.out.println("aki entra??");
			}else {
				System.out.println("hooooola k tl");
				System.out.println(planEstudios);
				plan = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios(planEstudios);
			}
			
			
			asignatura.setNombre(nom);
			asignatura.setCodigo(codigo);
			asignatura.setCurso(curso);
			asignatura.setAcronimo(acronimo);
			asignatura.setTipo(tipo);
			asignatura.setSemestre(semestre);
			asignatura.setEcts(ects);
			asignatura.setHorasTeoria(horasTeoria);
			asignatura.setHorasLab(horasLab);
			asignatura.setPlanEstudios(plan);
			asignatura.setComentario(comentario);
			asignatura.setHorasApolo(horasApolo);
			asignatura.setNumeroAlumnos(numeroAlumnos);
	
			AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
			

			log.info("El usuario "+currentUser.getPrincipal().toString()+" ha editado la asignatura "+codigo+" - "+nom);
	
			//Sacamos todas las asignaturas para pasarlas al jsp
			List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
			req.getSession().setAttribute("planesActuales", todosPlanes);
			getServletContext().getRequestDispatcher("/CRUDAsignatura.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}

}
