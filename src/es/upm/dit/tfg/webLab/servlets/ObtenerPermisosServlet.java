package es.upm.dit.tfg.webLab.servlets;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/ObtenerPermisosServlet")

public class ObtenerPermisosServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(ObtenerPermisosServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {

		int codigoUsuario = 0;
		System.out.println("el primer id "+req.getParameter("id"));
		String idString = req.getParameter("id");
		int id = 0 ;
		try{id = Integer.parseInt(idString);}catch(Exception e) {
			System.out.println("flipando "+e);
			log.error(e);}
		
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(id);
		System.out.println("el id del usuario "+codigoUsuario);
		System.out.println("el usuario "+ usuario);
		//System.out.println("el correo "+usuario.getCorreo()+"  y el id:  "+usuario.getId());
		
		List<Permiso> permisosUsuario = null;
		try {permisosUsuario = usuario.getPermisos();}catch(Exception e) {log.error(e);}
		List<Permiso> todosPermisos = PermisoDAOImplementation.getInstance().readPermisos();
		
		
		try {
			for (int j = 0; j< permisosUsuario.size(); j++) {
				for (int i = 0; i< todosPermisos.size(); i++) {
					if(permisosUsuario.get(j).getId()==todosPermisos.get(i).getId())todosPermisos.remove(i);
				}
			}
		}catch(Exception e) {log.error(e);}
		
		req.getSession().setAttribute("permisosUsuario", permisosUsuario);
		req.getSession().setAttribute("todosPermisos", todosPermisos);
		req.getSession().setAttribute("usuario", usuario);

		getServletContext().getRequestDispatcher("/AsignarPermisos.jsp").forward(req, resp);
		
	}
}
		