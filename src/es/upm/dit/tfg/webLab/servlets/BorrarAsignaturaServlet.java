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

@WebServlet("/BorrarAsignaturaServlet")

public class BorrarAsignaturaServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(BorrarAsignaturaServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {	
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		String codigo = req.getParameter("codigo");
	

		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar docencia
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestiondocencia")){
			Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigo);
			AsignaturaDAOImplementation.getInstance().deleteAsignatura(asignatura);
	
			
			log.info("El usuario "+currentUser.getPrincipal().toString()+" ha borrado la asignatura "+asignatura.getCodigo()+" - "+asignatura.getNombre());
			
			//Sacamos todas las asignaturas para pasarlas al jsp
			List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
			
			req.getSession().setAttribute("planesActuales", todosPlanes);
			getServletContext().getRequestDispatcher("/CRUDAsignatura.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}

}
