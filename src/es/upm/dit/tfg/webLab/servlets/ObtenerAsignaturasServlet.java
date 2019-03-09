package es.upm.dit.tfg.webLab.servlets;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import es.upm.dit.tfg.webLab.model.Profesor;





@WebServlet("/ObtenerAsignaturasServlet")

public class ObtenerAsignaturasServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		req.getSession().removeAttribute("mensaje");
		String profesorId = req.getParameter("idProfe");
		Profesor profesor = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(profesorId));

		List<Asignatura> asignaturasParticipa = profesor.getAsignaturasParticipa();
		

		req.getSession().setAttribute("profesor", profesor);
		req.getSession().setAttribute("asignaturasParticipa", asignaturasParticipa);
		req.getSession().setAttribute("todosPlanes", PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios());
		req.getSession().setAttribute("todasAsignaturas", AsignaturaDAOImplementation.getInstance().readAsignaturas());
		req.getSession().setAttribute("asignaturasCoordina", profesor.getAsignaturaCoordina());

		getServletContext().getRequestDispatcher("/AsignarAsignaturas.jsp").forward(req, resp);
	}
}
		