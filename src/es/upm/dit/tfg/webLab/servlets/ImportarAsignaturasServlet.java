package es.upm.dit.tfg.webLab.servlets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.table.Plan;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.PlanEstudios;



@WebServlet("/ImportarAsignaturasServlet")

public class ImportarAsignaturasServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {

		String planesCodigo[]; 
		planesCodigo = req.getParameterValues("codigoPlan");
		
		
		List<PlanEstudios> planes = (List<PlanEstudios>) req.getSession().getAttribute("listaPlanes");
		
		String asignaturasCodigo[]; 
		asignaturasCodigo = req.getParameterValues("codigoAsignatura");
		
		List<Asignatura> todasAsignaturas = (List<Asignatura>) req.getSession().getAttribute("listaAsignaturas");
		
	
		
		
		if(planesCodigo!=null) {
			for (int i = 0; i< planesCodigo.length; i++) { 
				for (int j = 0; j< planes.size(); j++) { 
					if(planesCodigo[i].equals(planes.get(j).getCodigo())) {
						PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(planes.get(j));
						
				
					}
				} 
			} 
		}
		
		
		
		if(asignaturasCodigo!=null) {
			for (int i = 0; i< asignaturasCodigo.length; i++) { 
				Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(asignaturasCodigo[i]);
				

				if(asignatura==null ) {
						for (int j = 0; j< todasAsignaturas.size(); j++) { 
							PlanEstudios plan = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios(todasAsignaturas.get(j).getPlanEstudios().getCodigo());
							System.out.println("el planecito: "+plan);
							
							if(asignaturasCodigo[i].equals(todasAsignaturas.get(j).getCodigo()) && plan==null) {
							
								List<Asignatura> asignaturasPlan = new ArrayList();
								asignaturasPlan.add(todasAsignaturas.get(j));
								
								PlanEstudios crearPlan = new PlanEstudios();
								crearPlan.setCodigo(todasAsignaturas.get(j).getPlanEstudios().getCodigo());
								crearPlan.setNombre(todasAsignaturas.get(j).getPlanEstudios().getNombre());
								crearPlan.setAsignaturas(asignaturasPlan);
								PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(crearPlan);
								
								AsignaturaDAOImplementation.getInstance().createAsignatura(todasAsignaturas.get(j));
								todasAsignaturas.get(j).setPlanEstudios(crearPlan);

							}else if (asignaturasCodigo[i].equals(todasAsignaturas.get(j).getCodigo()) && plan !=null) {
								
								//Sacasmos las asignaturas actuales
								List<Asignatura> asignaturasPlan = plan.getAsignaturas();
								//Añadimos la nueva asignatura a la lista 
								asignaturasPlan.add(todasAsignaturas.get(j));
								//Llamamos al objeto plan
								PlanEstudios crearPlan = todasAsignaturas.get(j).getPlanEstudios();
								//le metemos la nueva asignatura
								crearPlan.setAsignaturas(asignaturasPlan);
								
								//Creamos la nueva asignatura 
								AsignaturaDAOImplementation.getInstance().createAsignatura(todasAsignaturas.get(j));
								//Le metemos el nuevo plan 
								todasAsignaturas.get(j).setPlanEstudios(crearPlan);
								
							
							}
							
							
							
						} 
					
				}
			} 
			
		}
		List<PlanEstudios> planesEnviar = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
		req.getSession().setAttribute("planesActuales",  planesEnviar);

		String msj = "Asignaturas importadas con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDAsignatura.jsp");
	}
	
	
	
	
}		