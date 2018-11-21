package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.PlanEstudios;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/CrearPlanServlet")
public class CrearPlanServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(CrearPlanServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String codigo = req.getParameter("codigo");
		String nombre = req.getParameter("nombre");
		
		
		

		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		PlanEstudios plan = new PlanEstudios();
		plan.setCodigo(codigo);
		plan.setNombre(nombre);
		PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(plan);
		
		List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
		
		
		req.getSession().setAttribute("planesActuales", todosPlanes);
		
		
		log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha creado el plan de estudios "+plan.getCodigo()+" - "+plan.getNombre());
		//System.out.println(x);
		String nuevoLog ="holiii";
		CrearLog logACrear = new CrearLog();
		logACrear.nuevoLog(nuevoLog);
		


		String msj = "Plan de Estudios creado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDPlan.jsp");

	}
}