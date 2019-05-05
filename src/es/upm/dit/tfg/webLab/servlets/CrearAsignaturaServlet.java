package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;

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
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.GrupoClase;
import es.upm.dit.tfg.webLab.model.PlanEstudios;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/CrearAsignaturaServlet")

public class CrearAsignaturaServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(CrearAsignaturaServlet.class);
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		req.getSession().removeAttribute("mensaje");
		
		String nom = req.getParameter("nombre");
		String acronimo = req.getParameter("acronimo");
		String codigo = req.getParameter("codigo");
		String tipo = req.getParameter("tipo");
		String curso = req.getParameter("curso");
		String semestre = req.getParameter("semestre");
		
		
		
		String planCodigo = req.getParameter("plan");
		String comentario = req.getParameter("comentario");
		
		int numeroAlumnos = 0;
		try {numeroAlumnos = Integer.parseInt(req.getParameter("numeroAlumnos"),10);}catch(Exception e) {System.out.println(e);}
		
		double horasApolo = 0.0;
		try {horasApolo = Double.parseDouble(req.getParameter("horasApolo"));}catch(Exception e) {System.out.println(e);}
		
		double ects = 0.0;
		try {ects = Double.parseDouble(req.getParameter("ects"));}catch(Exception e) {System.out.println(e);}
		
		int horasTeoria = 0;
		try {horasTeoria = Integer.parseInt(req.getParameter("horasTeoria"),10);}catch(Exception e) {System.out.println(e);}
		
		int horasLab = 0;
		try {horasLab = Integer.parseInt(req.getParameter("horasLab"),10);}catch(Exception e) {System.out.println(e);}
		
		
		
		
		
		String nombreGrupo[]; 
		nombreGrupo = req.getParameterValues("nombreGrupo");
		
		String numeroAlumnosGrupo[]; 
		numeroAlumnosGrupo = req.getParameterValues("numeroAlumnosGrupo");
		
		String descripcionGrupo[]; 
		descripcionGrupo = req.getParameterValues("descripcionGrupo");
		
		String profesoresId[]; 
		profesoresId = req.getParameterValues("profesor");
	
		
		
		int coordinadorId = 0;
		try {
			coordinadorId = Integer.parseInt(req.getParameter("coordinador"),10);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar docencia
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestiondocencia")){
	
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

		
			try {
				List<Profesor> profesores = new ArrayList<>();
				for (int i = 0; i< profesoresId.length; i++) {
					Profesor nuevoProfesor = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(profesoresId[i],10));
					nuevoProfesor.addAsignaturaaParticipa(asignaturaNueva);
					profesores.add(nuevoProfesor);
				}
				asignaturaNueva.setProfesores(profesores);
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignaturaNueva);
			}catch(Exception e) {
				System.out.println(e);
			}finally {
						
			}
			
			
			
			try {
				Profesor coordinador = ProfesorDAOImplementation.getInstance().readProfesor(coordinadorId);
				asignaturaNueva.setCoordinador(coordinador);
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignaturaNueva);
			}catch(Exception e) {
				System.out.println(e);
			}finally {
						
			}
			
			
			
			
			
			Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
	
			//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha creado la asignatura "+codigo+" - "+nom);
	
			//Sacamos todas las asignaturas para pasarlas al jsp
			List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
			req.getSession().setAttribute("planesActuales", todosPlanes);
	
			String msj = "Asignatura creada con éxito";
			req.getSession().setAttribute("mensaje", msj);

			getServletContext().getRequestDispatcher("/CRUDAsignatura.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}

	
	}

}
