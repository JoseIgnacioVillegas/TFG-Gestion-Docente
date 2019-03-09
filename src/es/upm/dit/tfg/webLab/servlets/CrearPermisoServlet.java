package es.upm.dit.tfg.webLab.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;

import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Usuario;



@WebServlet("/CrearPermisoServlet")
public class CrearPermisoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		req.getSession().removeAttribute("mensaje");
		String nom = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		Usuario usuario = null;
		
		System.out.print(currentUser);
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			List<Permiso> todosPermisos = PermisoDAOImplementation.getInstance().readPermisos();
			int idMaxPermiso = todosPermisos.size() +1;
			
			System.out.println(nom);
			Permiso permiso =new Permiso();
			permiso.setPermiso(nom);
			permiso.setDescripcion(descripcion);
			permiso.setId(idMaxPermiso);
			permiso.addUsuario(usuario);
			System.out.print(permiso.getPermiso());
			PermisoDAOImplementation.getInstance().createPermiso(permiso);
			
			
			List<Permiso> todosPermisosEnviar = PermisoDAOImplementation.getInstance().readPermisos();
			
			req.getSession().setAttribute("permisos", todosPermisosEnviar);
	
			
			String msj = "Permiso creado con éxito";
			req.getSession().setAttribute("mensaje", msj);

			getServletContext().getRequestDispatcher("/CRUDPermisos.jsp").forward(req, resp);
			
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}

	}
}
