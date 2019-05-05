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


import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/EditarUsuarioServlet")

public class EditarUsuarioServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(EditarUsuarioServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		int id = 0;
		try{ id = Integer.parseInt(req.getParameter("id"));}catch(Exception e) {log.error(e); }
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String correo = req.getParameter("correo");
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(id);
			
			usuario.setId(id);
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			usuario.setCorreo(correo);
			
			
			UsuarioDAOImplementation.getInstance().updateUsuario(usuario);
			
			log.info("El usuario "+currentUser.getPrincipal().toString()+" ha editado el usuario "+nombre+" "+apellidos);
	
			List<Usuario> todoUsuarios = UsuarioDAOImplementation.getInstance().readUsuarios();
			List<Usuario> usuarios =new ArrayList();
			
			for (int i=0; i<todoUsuarios.size(); i++) {
				Profesor profesor = todoUsuarios.get(i).getProfesor();
				if(profesor==null) usuarios.add(todoUsuarios.get(i));
			}
			
			req.getSession().setAttribute("usuarios", usuarios);
			getServletContext().getRequestDispatcher("/CRUDPAS.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
		
	}
	
}
		