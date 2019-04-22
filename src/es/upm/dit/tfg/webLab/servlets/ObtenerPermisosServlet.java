package es.upm.dit.tfg.webLab.servlets;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/ObtenerPermisosServlet")

public class ObtenerPermisosServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		req.getSession().removeAttribute("mensaje");


		int codigoUsuario = Integer.parseInt(req.getParameter("id"));
		
		
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(codigoUsuario);
		
		
		
		List<Permiso> permisosUsuario = usuario.getPermisos();
		List<Permiso> todosPermisos = PermisoDAOImplementation.getInstance().readPermisos();
		
		
		try {
			for (int j = 0; j< permisosUsuario.size(); j++) {
				for (int i = 0; i< todosPermisos.size(); i++) {
					if(permisosUsuario.get(j).getId()==todosPermisos.get(i).getId())todosPermisos.remove(i);
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
		}
		
		req.getSession().setAttribute("permisosUsuario", permisosUsuario);
		req.getSession().setAttribute("todosPermisos", todosPermisos);
		req.getSession().setAttribute("usuario", usuario);

		getServletContext().getRequestDispatcher("/AsignarPermisos.jsp").forward(req, resp);
		
	}
}
		