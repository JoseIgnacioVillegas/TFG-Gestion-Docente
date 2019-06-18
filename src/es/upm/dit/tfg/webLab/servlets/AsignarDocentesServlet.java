package es.upm.dit.tfg.webLab.servlets;



import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Profesor;





@WebServlet("/AsignarDocentesServlet")

public class AsignarDocentesServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(AsignarDocentesServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		
		//Sacamos el codigo de la asignatura que queremos modificar
		
		String codAsignatura = req.getParameter("codigoAsignatura");
		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codAsignatura);
		
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
		
		
		//Primero realizamos todas las acciones relacionadas con la actualizacion de los profesores de la asignatura 

			//Sacamos todos los profesores actuales de esta asignautra 
			List<Profesor> profesoresParticipan = asignatura.getProfesores();
			
			//Saco el array de los id de los profesores borrados
			String profesoresBorradosId[]; 
			profesoresBorradosId = req.getParameterValues("profesoresBorrados");
			
			
			//Comparamos los profesores que hemos borrado con los profesores que participan en esta asignatura 
			// Así nos queda una lista solo con los profesores que partician en esta asignatura 
			try {
				for (int i = 0; i< profesoresBorradosId.length; i++) {
					for (int j = 0; j< profesoresParticipan.size(); j++) {
						if(Integer.parseInt(profesoresBorradosId[i])==profesoresParticipan.get(j).getId()) { 
							profesoresParticipan.remove(j);
							log.info("El usuario "+currentUser.getPrincipal().toString()+" ha borrado el profesor" +profesoresParticipan.get(j).getUsuario().getNombre()+" "+ profesoresParticipan.get(j).getUsuario().getApellidos()+" a la asignatura "+asignatura.getNombre());

						}
					}
				}
				}catch(Exception e) {
					log.error(e);
				}

			
			//Saco el ID de los nuesvos profesores para añadirlos a la lista
			String profesoresId[]; 
			profesoresId = req.getParameterValues("profesor");
			
			
			
			//Volvemos a la lista de antes, ahora añadiendo los profesores que han sido marcados previamente 
			
			try {
				for (int i = 0; i< profesoresId.length; i++) {
					Profesor profe = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(profesoresId[i]));
					profesoresParticipan.add(profe);
					log.info("El usuario "+currentUser.getPrincipal().toString()+" ha asignado el profesor" +profe.getUsuario().getNombre()+" "+ profe.getUsuario().getApellidos()+" a la asignatura "+asignatura.getNombre());

				}
				//y hago un set en la asignatura de la lista completa de profesores
				asignatura.setProfesores(profesoresParticipan);
			
			}catch(Exception e) {
				log.error(e);
			}
			
			
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////// AQUI EMPIEZA EL SET Y EL BORRADO DEL COORDINADOR///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		// Siguiendo la misma logica que la anterior:
			
			String coordinadorBorradoId = req.getParameter("coordinadorBorrado");
			String coordinadorId = req.getParameter("coordinador");
			System.out.println("NUEVO COORDINADOR "+coordinadorId);
			System.out.println("COORDINADOR BORRADO "+coordinadorBorradoId);
			
			Profesor coordinador = null;
			try {
				coordinador = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(coordinadorId));
			}catch(Exception e) {
				log.error(e);
			}
			

			
			
			
		
			if(coordinadorBorradoId!=null && coordinadorId!=null) {
				//Si el coordinador a borrar es nulo y el coordinador a poner no es nulo, simplemente se cambia uno por otro 
				System.out.println("SE CAMBIA EL COORDINADOR");
				
				log.info("El usuario "+currentUser.getPrincipal().toString()+" ha cambiado el coordinador de la asignatura " +asignatura.getNombre() +" por el profesor "+ coordinador.getUsuario().getNombre()+" "+ coordinador.getUsuario().getApellidos());

				coordinador.setAsignaturaCoordina(asignatura);
				asignatura.setCoordinador(coordinador);
				
			}else if(coordinadorBorradoId!=null && coordinadorId==null){
				//Si el coordinador a borrar no es nulo y el nuevo coordinador si lo es, simplemente borramos el coordinador
				//Profesor coordinadorBorrado = new Profesor();
				System.out.println("PONER NUEVO COORDINADOR");
				asignatura.deleteCoordinador();
				log.info("El usuario "+currentUser.getPrincipal().toString()+" ha borrado el coordinador de la asignatura " +asignatura.getNombre());

				
			}else if (coordinadorBorradoId==null && coordinadorId!=null){
				//Si el coordinador a borrar es nulo pero el nuevo coordinador no es nulo, ponemos el nuevo coordinador
				System.out.println("BORRAR EL COORDINADOR");
				asignatura.deleteCoordinador();
				asignatura.setCoordinador(coordinador);

				
				coordinador.deleteAsignaturaCoordina();
				coordinador.setAsignaturaCoordina(asignatura);
				log.info("El usuario "+currentUser.getPrincipal().toString()+" ha asignado el coordinador "+coordinador.getUsuario().getNombre()+" "+ coordinador.getUsuario().getApellidos()+" de la asignatura " +asignatura.getNombre());

				
			}else if (coordinadorBorradoId==null && coordinadorId==null) {
				//En caso de que ambos sean nulos, no habría que hacer nada 
				
			}else {
				//No se realiza ninguna accion
				
			}			

			AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
			ProfesorDAOImplementation.getInstance().updateProfesor(coordinador);
			getServletContext().getRequestDispatcher("/CRUDAsignatura.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}
}