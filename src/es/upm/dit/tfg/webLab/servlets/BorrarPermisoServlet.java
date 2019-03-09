package es.upm.dit.tfg.webLab.servlets;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.model.Permiso;


@WebServlet("/BorrarPermisoServlet")
public class BorrarPermisoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		int id = Integer.parseInt(req.getParameter("id"));
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			Permiso permiso = PermisoDAOImplementation.getInstance().readPermiso(id);
			PermisoDAOImplementation.getInstance().deletePermiso(permiso);
	
			List<Permiso> todoPermisos = PermisoDAOImplementation.getInstance().readPermisos();
			
			
			req.getSession().setAttribute("permisos", todoPermisos);
	
			
			String msj = "Permiso borrado con éxito";
			req.getSession().setAttribute("mensaje", msj);

			getServletContext().getRequestDispatcher("/CRUDPermisos.jsp").forward(req, resp);
			
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}
}