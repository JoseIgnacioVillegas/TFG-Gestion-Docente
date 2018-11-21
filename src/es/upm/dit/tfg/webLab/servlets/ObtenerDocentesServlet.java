package es.upm.dit.tfg.webLab.servlets;


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





@WebServlet("/ObtenerDocentesServlet")

public class ObtenerDocentesServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {


		String codigoAsignatura = req.getParameter("codigo");
		System.out.println("¿pss que hay aqui?1"+codigoAsignatura);
		
		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAsignatura);
		System.out.println("¿pss que hay aqui?2"+asignatura);
		
		List<Profesor> profesores = asignatura.getProfesores();
		System.out.println("¿pss que hay aqui?1"+profesores);
		
		List<Profesor> todosProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
		
		
		req.getSession().setAttribute("coordinador", asignatura.getCoordinador());
		req.getSession().setAttribute("todosProfesores", todosProfesores);
		req.getSession().setAttribute("profesoresPorAsignatura", profesores);
		System.out.println("¿pss que hay aqui?"+profesores);
		req.getSession().setAttribute("nombre", asignatura.getNombre());
		req.getSession().setAttribute("codigo", asignatura.getCodigo());
		req.getSession().setAttribute("acronimo", asignatura.getAcronimo());
		resp.sendRedirect(req.getContextPath()+ "/AsignarDocentes.jsp");
	}
}
		