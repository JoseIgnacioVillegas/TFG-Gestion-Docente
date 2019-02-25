package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(CrearUsuarioServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("mensaje");
		String nom = req.getParameter("nombre");
		String ape = req.getParameter("apellidos");
		String corr = req.getParameter("correo");
		
		
		List<Usuario> todosUsuarios = UsuarioDAOImplementation.getInstance().readUsuarios();
		int idMaxUsuario = todosUsuarios.size() +1;
		
		
		Usuario usuario =new Usuario();
		usuario.setNombre(nom);
		usuario.setApellidos(ape);
		usuario.setCorreo(corr);
		usuario.setId(idMaxUsuario);
		UsuarioDAOImplementation.getInstance().createUsuario(usuario);
		
		
		Usuario usuarioAccion = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuarioAccion.getNombre()+" "+usuarioAccion.getApellidos()+" ha creado el usuario "+nom+" "+ape);

		
		List<Usuario> todoUsuarios = UsuarioDAOImplementation.getInstance().readUsuarios();
		List<Usuario> usuarios =new ArrayList();
		
		for (int i=0; i<todoUsuarios.size(); i++) {
			Profesor profesor = todoUsuarios.get(i).getProfesor();
			if(profesor==null) usuarios.add(todoUsuarios.get(i));
		}
		
		req.getSession().setAttribute("usuarios", usuarios);

		
		String msj = "Usuario creado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		getServletContext().getRequestDispatcher("/CRUDPAS.jsp").forward(req, resp);
		//resp.sendRedirect(req.getContextPath() + "/CRUDPAS.jsp");

	}
}
