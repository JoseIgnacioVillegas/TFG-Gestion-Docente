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
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.PlanEstudios;

@WebServlet("/BorrarAsignaturaServlet")

public class BorrarAsignaturaServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {	
		String codigo = req.getParameter("codigo");
	

		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigo);
		AsignaturaDAOImplementation.getInstance().deleteAsignatura(asignatura);

		//Sacamos todas las asignaturas para pasarlas al jsp
		List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
		
		req.getSession().setAttribute("planesActuales", todosPlanes);
		
		
		
		
		String msj = "Asignatura borrada con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDAsignatura.jsp");
	}

}
