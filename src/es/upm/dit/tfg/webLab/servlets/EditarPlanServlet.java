package es.upm.dit.tfg.webLab.servlets;

import java.util.ArrayList;
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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String codigoAnt = req.getParameter("codigo1");
		String codigo = req.getParameter("codigo");
		String nombre = req.getParameter("nombre");
		
		
		PlanEstudios plan = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios(codigoAnt);
		List<Asignatura> asignaturas = plan.getAsignaturas();
		
		/*
		try {
			for(int i=0;i<asignaturas.size();i++) {
				AsignaturaDAOImplementation.getInstance().deleteAsignatura(asignaturas.get(i));
				//asignaturas.get(i).setPlanEstudios(planNuevo);
			}
			}catch(Exception e){
				
			}finally {
				
			}
		*/
		//PlanEstudios plan = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios(codigoAnt);
		PlanEstudiosDAOImplementation.getInstance().deletePlanEstudios(plan);


		PlanEstudios planNuevo = new PlanEstudios();	
		planNuevo.setNombre(nombre);
		planNuevo.setCodigo(codigo);
		PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(planNuevo);
		
		try {
			for(int i=0;i<asignaturas.size();i++) {
				asignaturas.get(i).setPlanEstudios(planNuevo);
				AsignaturaDAOImplementation.getInstance().createAsignatura(asignaturas.get(i));
				
			}
			}catch(Exception e){
				
			}finally {
				
			}
		
		
		List<PlanEstudios> todosPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
		
		
		req.getSession().setAttribute("planesActuales", todosPlanes);
		
		
		
		String msj = "Plan editado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDPlan.jsp");

	}
}