package es.upm.dit.tfg.webLab.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.model.Permiso;



@WebServlet("/CrearPermisoServlet")
public class CrearPermisoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getSession().removeAttribute("mensaje");
		String nom = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		
		
		List<Permiso> todosPermisos = PermisoDAOImplementation.getInstance().readPermisos();
		int idMaxPermiso = todosPermisos.size() +1;
		
		
		Permiso permiso =new Permiso();
		permiso.setPermiso(nom);
		permiso.setDescripcion(descripcion);
		permiso.setId(idMaxPermiso);
		PermisoDAOImplementation.getInstance().createPermiso(permiso);
		
		
		List<Permiso> todosPermisosEnviar = PermisoDAOImplementation.getInstance().readPermisos();
		
		req.getSession().setAttribute("permisos", todosPermisosEnviar);

		
		String msj = "Permiso creado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath() + "/CRUDPermisos.jsp");

	}
}
