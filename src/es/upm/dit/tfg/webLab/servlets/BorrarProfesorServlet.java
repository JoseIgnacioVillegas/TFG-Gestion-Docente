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
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/BorrarProfesorServlet")
public class BorrarProfesorServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(BorrarProfesorServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		
		int idProfe = 0;
		try{
			idProfe = Integer.parseInt(req.getParameter("idProfe"));
		}catch(Exception e){
			log.error(e);
			System.out.println(e);
		}
		
		
		
		
		
		int idUsuario = 0;
		try{
			idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
		}catch(Exception e){
			log.error(e);
			System.out.println(e);
		}

		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			Profesor profesor = ProfesorDAOImplementation.getInstance().readProfesor(idProfe);

			List<Asignatura> asignaturas = profesor.getAsignaturasParticipa();	
			
			try {
				for (int i = 0; i< asignaturas.size(); i++) {
					Asignatura asignaturaParticipaBorrada = asignaturas.get(i);
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
			
			//profesor.deleteAsignaturaCoordina();
			try {
				Asignatura asignaturaCoordina = profesor.getAsignaturaCoordina();
				asignaturaCoordina.deleteCoordinador();
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignaturaCoordina);
			}catch(Exception e) {
				System.out.println(e);
			}finally {
					
			}
			
			
			
			ProfesorDAOImplementation.getInstance().deleteProfesor(profesor);
			
			Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(idUsuario);
			UsuarioDAOImplementation.getInstance().deleteUsuario(usuario);

			log.info("El usuario "+currentUser.getPrincipal().toString()+" ha borrado al profesor "+usuario.getNombre()+" "+usuario.getApellidos());
	
			List<Profesor> todasProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
			req.getSession().setAttribute("profesores", todasProfesores);
			getServletContext().getRequestDispatcher("/CRUDProfesor.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}
}