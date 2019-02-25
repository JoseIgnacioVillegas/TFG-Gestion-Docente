package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
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
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/BorrarAsignaturaServlet")

public class BorrarAsignaturaServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(BorrarAsignaturaServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {	
		
		req.getSession().removeAttribute("mensaje");
		String codigo = req.getParameter("codigo");
	

		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigo);
		AsignaturaDAOImplementation.getInstance().deleteAsignatura(asignatura);

		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha borrado la asignatura "+asignatura.getCodigo()+" - "+asignatura.getNombre());
		
		//Sacamos todas las asignaturas para pasarlas al jsp
		List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
		
		req.getSession().setAttribute("planesActuales", todosPlanes);
		
		String msj = "Asignatura borrada con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDAsignatura.jsp");
	}

}
