package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;


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
import es.upm.dit.tfg.webLab.model.Usuario;



@WebServlet("/ImportarAsignaturasServlet")

public class ImportarAsignaturasServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(ImportarAsignaturasServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		req.getSession().removeAttribute("mensaje");

		String planesCodigo[]; 
		planesCodigo = req.getParameterValues("codigoPlan");
		
		List<PlanEstudios> planes = (List<PlanEstudios>) req.getSession().getAttribute("listaPlanes");
		
		String asignaturasCodigo[]; 
		asignaturasCodigo = req.getParameterValues("codigoAsignatura");
		
		List<Asignatura> todasAsignaturas = (List<Asignatura>) req.getSession().getAttribute("listaAsignaturas");
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
		
			if(planesCodigo!=null) {
				for (int i = 0; i< planesCodigo.length; i++) { 
					for (int j = 0; j< planes.size(); j++) { 
						if(planesCodigo[i].equals(planes.get(j).getCodigo())) {
							PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(planes.get(j));
							//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha importado el plan de estudios "+planes.get(j).getCodigo()+" - "+planes.get(j).getNombre());
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
								
								if(asignaturasCodigo[i].equals(todasAsignaturas.get(j).getCodigo()) && plan==null) {
								
									List<Asignatura> asignaturasPlan = new ArrayList();
									asignaturasPlan.add(todasAsignaturas.get(j));
									
									PlanEstudios crearPlan = new PlanEstudios();
									crearPlan.setCodigo(todasAsignaturas.get(j).getPlanEstudios().getCodigo());
									crearPlan.setNombre(todasAsignaturas.get(j).getPlanEstudios().getNombre());
									crearPlan.setAsignaturas(asignaturasPlan);
									PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(crearPlan);
									//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha importado el plan de estudios "+todasAsignaturas.get(j).getPlanEstudios().getCodigo()+" - "+todasAsignaturas.get(j).getPlanEstudios().getNombre());
	
									AsignaturaDAOImplementation.getInstance().createAsignatura(todasAsignaturas.get(j));
									//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha importado la asignatura "+todasAsignaturas.get(j).getCodigo()+" - "+todasAsignaturas.get(j).getNombre());
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
									//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha importado la asignatura "+todasAsignaturas.get(j).getCodigo()+" - "+todasAsignaturas.get(j).getNombre());
	
									//Le metemos el nuevo plan 
									todasAsignaturas.get(j).setPlanEstudios(crearPlan);
									
								
								}
								
								
								
							} 
						
					}
				} 
				
			}
			//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" importado asignaturas.");
	
			List<PlanEstudios> planesEnviar = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
			req.getSession().setAttribute("planesActuales",  planesEnviar);
	
			String msj = "Asignaturas importadas con éxito";
			req.getSession().setAttribute("mensaje", msj);

			getServletContext().getRequestDispatcher("/CRUDAsignatura.jsp").forward(req, resp);
		
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}
	
	
	
	
}		