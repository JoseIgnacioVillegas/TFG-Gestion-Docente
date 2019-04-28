package es.upm.dit.tfg.webLab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.PlanEstudios;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/DocenteServlet")
public class DocenteServlet extends HttpServlet{
	
	public static List<Asignatura> todasAsignaturas = new ArrayList<Asignatura>();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Botones vision del departamento
		String BotonPlanesYAsignaturas = req.getParameter("planes");
		String BotonProfesores = req.getParameter("profesores");
		String BotonPlazaYGrupo = req.getParameter("plazagrupo");
		
		
		
		String BotonDatos = req.getParameter("datos");
				
		// Vision asignaturas y planes
		if(BotonPlanesYAsignaturas!=null && BotonPlanesYAsignaturas.equals("planes")) {
			
			List<PlanEstudios> listaPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
			List<Profesor> listaProfesores = ProfesorDAOImplementation.getInstance().readProfesores();

			req.getSession().setAttribute("planesActuales", listaPlanes);
			req.getSession().setAttribute("listaProfesores", listaProfesores);
			
			resp.sendRedirect(req.getContextPath() + "/VisionAsignaturas.jsp");
		
		}

		// Vision profesores
		if(BotonProfesores!=null && BotonProfesores.equals("profesores")) {
			List<Profesor> listaProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
			List<Grupo> listaGrupos = GrupoDAOImplementation.getInstance().readGrupos();
			
			List<Plaza> listaPlazas = PlazaDAOImplementation.getInstance().readPlazas();
			List<Permiso> listaPermisos = PermisoDAOImplementation.getInstance().readPermisos();
			
			req.getSession().setAttribute("permisos", listaPermisos);
			req.getSession().setAttribute("profesores", listaProfesores);
			req.getSession().setAttribute("grupos", listaGrupos);
			req.getSession().setAttribute("plazas", listaPlazas);
			
			getServletContext().getRequestDispatcher("/VisionDocentes.jsp").forward(req, resp);
		}
		
		// Vision plazas y grupos de investigación
		if(BotonPlazaYGrupo!=null && BotonPlazaYGrupo.equals("plazagrupo")) {
			
			List<Grupo> todosGrupos = GrupoDAOImplementation.getInstance().readGrupos();
			req.getSession().setAttribute("grupos", todosGrupos);
			
			List<Plaza> todasPlazas = PlazaDAOImplementation.getInstance().readPlazas();
			
			for (int i=0; i<todasPlazas.size(); i++) {
				todasPlazas.get(i).getDescripcion();
			}
			
			req.getSession().setAttribute("plazas", todasPlazas);
			resp.sendRedirect(req.getContextPath() + "/VisionPlazaGrupo.jsp");
						
		}
		
		// Vision los datos personales
		if(BotonDatos!=null && BotonDatos.equals("datos")) {
			String correo = req.getParameter("email");
			Usuario user = UsuarioDAOImplementation.getInstance().readUsuarioPorCorreo(correo);
			Profesor profe = user.getProfesor();
			
			List<Asignatura> asigParticipa = null;
			try {
			asigParticipa = profe.getAsignaturasParticipa();
			}catch(Exception e) {
				
			}
			Asignatura coordi=null;
			try {
			coordi = profe.getAsignaturaCoordina();
}catch(Exception e) {
				
			}
			//HAbria que enviar los grupos de clase y las horas, etc 
			req.getSession().setAttribute("profesor", profe);
			req.getSession().setAttribute("asignaturasParticipa", asigParticipa);
			req.getSession().setAttribute("asignaturaCoordina", coordi);
		
			resp.sendRedirect(req.getContextPath() + "/VisionDatos.jsp");
		}
		
	}
}