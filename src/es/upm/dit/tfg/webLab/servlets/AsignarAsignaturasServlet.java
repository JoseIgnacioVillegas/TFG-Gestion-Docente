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
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Profesor;



@WebServlet("/AsignarAsignaturasServlet")

public class AsignarAsignaturasServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(AsignarAsignaturasServlet.class);

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		req.getSession().removeAttribute("mensaje");
		
		String docenteId = req.getParameter("id");
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			Profesor profesor = null;
			try {
				profesor = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(docenteId));
			}catch(Exception e){
				log.error(e);
			}
			
			
			
			
			//Saco el array de los id de los profesores borrados
			String asignaturasParticipa[]; 
			asignaturasParticipa = req.getParameterValues("participa");
			
			
			try {
				for (int i = 0; i< asignaturasParticipa.length; i++) {
					Asignatura asignaturaParticipa = AsignaturaDAOImplementation.getInstance().readAsignatura(asignaturasParticipa[i]);
					List<Profesor> profesoresParticipan = asignaturaParticipa.getProfesores();
					profesoresParticipan.add(profesor);
					AsignaturaDAOImplementation.getInstance().updateAsignatura(asignaturaParticipa);
				}
				}catch(Exception e) {
					System.out.println(e);
				}finally {
						
				}
			
			
			//Saco el array de los id de los profesores borrados
			String asignaturasParticipaBorradas[]; 
			asignaturasParticipaBorradas = req.getParameterValues("asignaturasParticipaBorradas");

			try {
				for (int i = 0; i< asignaturasParticipaBorradas.length; i++) {
					Asignatura asignaturaParticipaBorrada = AsignaturaDAOImplementation.getInstance().readAsignatura(asignaturasParticipaBorradas[i]);
					List<Profesor> profesoresParticipan = asignaturaParticipaBorrada.getProfesores();
					for (int j = 0; j< profesoresParticipan.size(); j++) {
						if(profesoresParticipan.get(j).getId()==profesor.getId())profesoresParticipan.remove(j);
					}
					asignaturaParticipaBorrada.setProfesores(profesoresParticipan);
					AsignaturaDAOImplementation.getInstance().updateAsignatura(asignaturaParticipaBorrada);
				}
				}catch(Exception e) {
					System.out.println(e);
				}finally {
						
				}

			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////// A PARTIR DE AQUI SE HACE LO DEL COORDINADOR //////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
			String asignaturaCoordinaBorradaCodigo = req.getParameter("asignaturasCoordinaBorradas");
			String codigoAsignaturasCoordina = req.getParameter("coordina");
			
			
			if(codigoAsignaturasCoordina==null && asignaturaCoordinaBorradaCodigo==null) {
				//En caso de que no nos pasen asignaturas para borrar ni asignaturas para poner, no habría que hacer nada 
			}else if(asignaturaCoordinaBorradaCodigo!=null){
				//En caso de que la asignatura que hay que borrar no sea null, pues se borra
				Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(asignaturaCoordinaBorradaCodigo);
				asignatura.deleteCoordinador();
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
				profesor.deleteAsignaturaCoordina();
				ProfesorDAOImplementation.getInstance().updateProfesor(profesor);
				
			}else if (codigoAsignaturasCoordina!=null){
				
				
				try {
					//En caso de que la asignatura a coordinar no sea nula, pues la asignamos
					Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAsignaturasCoordina);
					asignatura.setCoordinador(profesor);
					AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
					profesor.setAsignaturaCoordina(asignatura);
					ProfesorDAOImplementation.getInstance().updateProfesor(profesor);
				}catch(Exception e) {
					System.out.println(e);
				}finally {
				}
				
			}else {
				//No se realiza ninguna accion
				
			}

			String msj = "Usuarios asignados con éxito";
			req.getSession().setAttribute("mensaje", msj);
			getServletContext().getRequestDispatcher("/CRUDProfesor.jsp").forward(req, resp);
		
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}
}
		