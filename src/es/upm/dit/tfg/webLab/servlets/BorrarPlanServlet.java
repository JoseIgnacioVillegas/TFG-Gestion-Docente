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

@WebServlet("/BorrarPlanServlet")
public class BorrarPlanServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String codigo = req.getParameter("codigo");

		PlanEstudios plan = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios(codigo);
		
		List<Asignatura> asignaturas = AsignaturaDAOImplementation.getInstance().readAsignaturasPorPlan(codigo);
		try {
		for(int i=0;i<asignaturas.size();i++) {
			AsignaturaDAOImplementation.getInstance().deleteAsignatura(asignaturas.get(i));
		}
		}catch(Exception e){
			
		}finally {
			
		}
		PlanEstudiosDAOImplementation.getInstance().deletePlanEstudios(plan);
		
		
		
		List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();

		
		
		
		req.getSession().setAttribute("planesActuales", todosPlanes);
		
		
		

		String msj = "Plan borrado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDPlan.jsp");
	}
}