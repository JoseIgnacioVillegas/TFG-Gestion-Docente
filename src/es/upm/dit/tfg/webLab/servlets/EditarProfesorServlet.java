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

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/EditarProfesorServlet")

public class EditarProfesorServlet extends HttpServlet{


	private final static Logger log = Logger.getLogger(EditarProfesorServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String acronimo = req.getParameter("acronimo");
		String correo = req.getParameter("correo");
		String dedicacion = req.getParameter("dedicacion");
		int id = 0;
		try{ id = Integer.parseInt(req.getParameter("id"));}catch(Exception e) {log.error(e); }
		String grupoStr = req.getParameter("grupo");
		int plazaId = 0;
		try{ plazaId = Integer.parseInt(req.getParameter("plaza"));}catch(Exception e) {log.error(e); }
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			Grupo grupo = GrupoDAOImplementation.getInstance().readGrupo(grupoStr);
			
			
			Plaza plaza = PlazaDAOImplementation.getInstance().readPlaza(plazaId);
			
			Profesor profesor = ProfesorDAOImplementation.getInstance().readProfesor(id);
			Usuario usuario = profesor.getUsuario();
			String contra = usuario.getPassword();
			
			
			//int UsuarioId = profesorAnt.getUsuario().getId();
			
			
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			usuario.setCorreo(correo);
			usuario.setPassword(contra);
			UsuarioDAOImplementation.getInstance().updateUsuario(usuario);
		

			profesor.setAcronimo(acronimo);
			if(plaza!=null)profesor.setPlaza(plaza);
			profesor.setGrupo(grupo);
			profesor.setDedicacion(dedicacion);
			
			ProfesorDAOImplementation.getInstance().updateProfesor(profesor);
			
			
			
			/*
			ProfesorDAOImplementation.getInstance().deleteProfesor(profesorAnt);
	
			UsuarioDAOImplementation.getInstance().deleteUsuario(UsuarioAnt);
	
			
			
			
			Usuario usuario =new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			usuario.setCorreo(correo);
			//usuario.setId(290);
			usuario.setPassword(contra);
			UsuarioDAOImplementation.getInstance().createUsuario(usuario);
		
			
			
			Profesor profesor = new Profesor();
			//profesor.setId(290);
			profesor.setAcronimo(acronimo);
			if(plaza!=null)profesor.setPlaza(plaza);
			profesor.setGrupo(grupo);
			profesor.setDedicacion(dedicacion);
			profesor.setUsuario(usuario);
			
			ProfesorDAOImplementation.getInstance().createProfesor(profesor);
			
			*/
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
			Profesor profesor = new Profesor();
			profesor.setId(id);
			profesor.setAcronimo(acronimo);
			profesor.setPlaza(plaza);
			profesor.setGrupo(grupo);
			profesor.setDedicacion(dedicacion);
			//profesor.setUsuario(usuario);
			
			ProfesorDAOImplementation.getInstance().createProfesor(profesor);
			
			
			Usuario usuario =new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			usuario.setCorreo(correo);
			usuario.setId(UsuarioId);
			usuario.setPassword(contra);
			usuario.setProfesor(profesor);
			UsuarioDAOImplementation.getInstance().createUsuario(usuario);
			
			
			profesor.setUsuario(usuario);
			
			ProfesorDAOImplementation.getInstance().updateProfesor(profesor);
			*/
			//UsuarioDAOImplementation.getInstance().updateUsuario(usuario);

			log.info("El usuario "+currentUser.getPrincipal().toString()+" ha editado el profesor "+nombre+" "+apellidos);
	
			//Sacamos todas las asignaturas para pasarlas al jsp
			List<Profesor> todosProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
			req.getSession().setAttribute("profesores", todosProfesores);
			getServletContext().getRequestDispatcher("/CRUDProfesor.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
		
		
	}
	
}
		