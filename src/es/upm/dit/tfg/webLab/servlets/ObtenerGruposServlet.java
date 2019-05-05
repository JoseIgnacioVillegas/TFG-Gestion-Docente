package es.upm.dit.tfg.webLab.servlets;



import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.GrupoClase;
import es.upm.dit.tfg.webLab.model.Profesor;





@WebServlet("/ObtenerGruposServlet")

public class ObtenerGruposServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(ObtenerGruposServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		String BotonVisionGrupos = req.getParameter("visionGrupos");
		String codigoAsignatura = req.getParameter("codigo");
		
		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAsignatura);
		List<GrupoClase> gruposClase = new ArrayList<>();
		gruposClase = asignatura.getGruposClase();

		
		if(BotonVisionGrupos!=null&&BotonVisionGrupos.equals("visionGrupos")) {
			req.getSession().setAttribute("gruposClase", gruposClase);
			req.getSession().setAttribute("asignatura",asignatura);
			getServletContext().getRequestDispatcher("/VerGruposClase.jsp").forward(req, resp);
		}else {
			List<Profesor> todosProfesores = ProfesorDAOImplementation.getInstance().readProfesores();

			req.getSession().setAttribute("todosProfesores", todosProfesores);
			req.getSession().setAttribute("gruposClase", gruposClase);
			req.getSession().setAttribute("asignatura",asignatura);
			getServletContext().getRequestDispatcher("/CRUDGruposClase.jsp").forward(req, resp);
		}
	}
}
		