package es.upm.dit.tfg.webLab.servlets;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.model.Permiso;


@WebServlet("/EditarPermisoServlet")
public class EditarPermisoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		req.getSession().removeAttribute("mensaje");
		int id = Integer.parseInt(req.getParameter("id"));
		String permiso = req.getParameter("permiso");
		String descripcion = req.getParameter("descripcion");
		
		Permiso instanciaPermiso = PermisoDAOImplementation.getInstance().readPermiso(id);
		instanciaPermiso.setPermiso(permiso);
		instanciaPermiso.setDescripcion(descripcion);
		
		PermisoDAOImplementation.getInstance().updatePermiso(instanciaPermiso);

		
		req.getSession().setAttribute("permisos", PermisoDAOImplementation.getInstance().readPermisos());
		
		
		String msj = "Permiso editado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDPermisos.jsp");

	}
}
