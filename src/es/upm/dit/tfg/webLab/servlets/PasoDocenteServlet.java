package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;

import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PasoDocenteServlet")
public class PasoDocenteServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(LogoutServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("correo");
		Usuario user = UsuarioDAOImplementation.getInstance().readUsuarioPorCorreo(email);
		user.getProfesor().getAsignaturaCoordina();
		user.getProfesor().getAsignaturasParticipa();
		
		
		
		
		
		req.getSession().setAttribute("usuario", user);
		getServletContext().getRequestDispatcher("/Perfil.jsp").forward(req, resp);
	}
}
