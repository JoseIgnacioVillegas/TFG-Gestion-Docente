package es.upm.dit.tfg.webLab.servlets;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;





@WebServlet("/RevisarPermisosServlet")

public class RevisarPermisosServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {

		
		req.getSession().removeAttribute("mensaje");

		int usuarioId = Integer.parseInt(req.getParameter("id"));
		String origen = req.getParameter("origen");
		String destino = req.getParameter("destino");
		
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(usuarioId);
		
		List<Permiso> permisos = usuario.getPermisos();
		
		if(permisos.size()==0 && origen.equals("/VistaInicial.jsp")) {
			String msj = "No tienes acceso a esta funcion";
			req.getSession().setAttribute("mensaje", msj);
			resp.sendRedirect(req.getContextPath()+ "/VistaInicial.jsp");
		}else {
			for (int i=0; i<permisos.size(); i++) {
				//esto esta mal porque estoy comparando vistas con el nombre del permiso, tendria que modificar el permiso en si para meter un string con la vista asociada al permiso
				
				if(permisos.get(i).getPermiso().equals(destino)) {
					//si el permiso es igual al destino, pues entra aqui porque tiene permiso basicamente 
					resp.sendRedirect(req.getContextPath()+ destino);
				}
			}
			String msj = "No tienes acceso a esta funcion";
			req.getSession().setAttribute("mensaje", msj);
			resp.sendRedirect(req.getContextPath()+ origen);
		}

		
	}
}
		