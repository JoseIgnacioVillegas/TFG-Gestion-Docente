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


@WebServlet("/EditarPlanServlet")
public class EditarPlanServlet extends HttpServlet{
	

	private final static Logger log = Logger.getLogger(EditarPlanServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		String codigoAnt = req.getParameter("codigo1");
		String codigo = req.getParameter("codigo");
		String nombre = req.getParameter("nombre");

		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar docencia
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestiondocencia")){
			PlanEstudios plan = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios(codigoAnt);
			List<Asignatura> asignaturas = plan.getAsignaturas();
			
			PlanEstudiosDAOImplementation.getInstance().deletePlanEstudios(plan);
	
			PlanEstudios planNuevo = new PlanEstudios();	
			planNuevo.setNombre(nombre);
			planNuevo.setCodigo(codigo);
			PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(planNuevo);
			
			log.info("El usuario "+currentUser.getPrincipal().toString()+" ha editado el plan de estudios "+codigo+" - "+nombre);
	
			try {
				for(int i=0;i<asignaturas.size();i++) {
					asignaturas.get(i).setPlanEstudios(planNuevo);
					AsignaturaDAOImplementation.getInstance().createAsignatura(asignaturas.get(i));
					
				}
				}catch(Exception e){
					log.error(e); 
				}
			
			List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
			
			req.getSession().setAttribute("planesActuales", todosPlanes);
			getServletContext().getRequestDispatcher("/CRUDPlan.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}

	}
}