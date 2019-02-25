package es.upm.dit.tfg.webLab.servlets;




import org.apache.log4j.Logger;
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
		req.getSession().removeAttribute("mensaje");
		
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String correo = req.getParameter("correo");
		
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(id);
		
		usuario.setId(id);
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setCorreo(correo);
		
		
		UsuarioDAOImplementation.getInstance().updateUsuario(usuario);
		
		Usuario usuarioAccion = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuarioAccion.getNombre()+" "+usuarioAccion.getApellidos()+" ha editado el usuario "+nombre+" "+apellidos);

		/*
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuarioAnt);
		
		
		
		Usuario usuarioNuevo = new Usuario();	
		usuarioNuevo.setId(id);
		usuarioNuevo.setNombre(nombre);
		usuarioNuevo.setApellidos(apellidos);
		usuarioNuevo.setCorreo(correo);
		UsuarioDAOImplementation.getInstance().createUsuario(usuarioNuevo);
		*/
		
		
		List<Usuario> todoUsuarios = UsuarioDAOImplementation.getInstance().readUsuarios();
		List<Usuario> usuarios =new ArrayList();
		
		for (int i=0; i<todoUsuarios.size(); i++) {
			Profesor profesor = todoUsuarios.get(i).getProfesor();
			if(profesor==null) usuarios.add(todoUsuarios.get(i));
		}
		
		req.getSession().setAttribute("usuarios", usuarios);
		

		
		
		
		String msj = "Usuario editado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath() + "/CRUDPAS.jsp");
		
	}
	
}
		