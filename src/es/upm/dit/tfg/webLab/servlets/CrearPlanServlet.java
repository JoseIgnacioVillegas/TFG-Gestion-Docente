package es.upm.dit.tfg.webLab.servlets;


import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.model.PlanEstudios;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/CrearPlanServlet")
public class CrearPlanServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(CrearPlanServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		req.getSession().removeAttribute("mensaje");
		String codigo = req.getParameter("codigo");
		String nombre = req.getParameter("nombre");

		PlanEstudios plan = new PlanEstudios();
		plan.setCodigo(codigo);
		plan.setNombre(nombre);
		PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(plan);
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha creado el plan de estudios "+plan.getCodigo()+" - "+plan.getNombre());
		

		List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
		
		
		req.getSession().setAttribute("planesActuales", todosPlanes);

		String msj = "Plan de Estudios creado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDPlan.jsp");

	}
}