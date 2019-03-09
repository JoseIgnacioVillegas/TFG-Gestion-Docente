package es.upm.dit.tfg.webLab.servlets;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.model.Permiso;


@WebServlet("/EditarPermisoServlet")
public class EditarPermisoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		req.getSession().removeAttribute("mensaje");
		int id = Integer.parseInt(req.getParameter("id"));
		String permiso = req.getParameter("permiso");
		String descripcion = req.getParameter("descripcion");
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			Permiso instanciaPermiso = PermisoDAOImplementation.getInstance().readPermiso(id);
			instanciaPermiso.setPermiso(permiso);
			instanciaPermiso.setDescripcion(descripcion);
			
			PermisoDAOImplementation.getInstance().updatePermiso(instanciaPermiso);
	
			req.getSession().setAttribute("permisos", PermisoDAOImplementation.getInstance().readPermisos());
			
			String msj = "Permiso editado con éxito";
			req.getSession().setAttribute("mensaje", msj);
			
			getServletContext().getRequestDispatcher("/CRUDPermisos.jsp").forward(req, resp);
			
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}

	}
}
