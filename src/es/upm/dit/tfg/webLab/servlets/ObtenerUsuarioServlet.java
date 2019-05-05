package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ObtenerUsuarioServlet")
public class ObtenerUsuarioServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(ObtenerUsuarioServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String BotonConvertirDocente = req.getParameter("ConvertirDocente");
		
		
		if(BotonConvertirDocente!=null&&BotonConvertirDocente.equals("ConvertirDocente")) {
			String id = req.getParameter("id");
			Usuario user = null;
			try{ user = UsuarioDAOImplementation.getInstance().readUsuario(Integer.parseInt(id));}catch(Exception e) {log.error(e);}
			
			req.getSession().setAttribute("permisos", PermisoDAOImplementation.getInstance().readPermisos());
			req.getSession().setAttribute("plazas", PlazaDAOImplementation.getInstance().readPlazas());
			req.getSession().setAttribute("grupos", GrupoDAOImplementation.getInstance().readGrupos());
			req.getSession().setAttribute("usuario", user);
			getServletContext().getRequestDispatcher("/ConvertirDocente.jsp").forward(req, resp);
		}else {
			String email = req.getParameter("usuario");
			Usuario user = UsuarioDAOImplementation.getInstance().readUsuarioPorCorreo(email);
			req.getSession().setAttribute("usuario", user);
			getServletContext().getRequestDispatcher("/Perfil.jsp").forward(req, resp);
		}
	}
}
