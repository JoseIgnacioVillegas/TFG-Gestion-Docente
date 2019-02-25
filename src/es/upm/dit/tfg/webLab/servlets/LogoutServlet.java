package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.tfg.webLab.model.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(LogoutServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha cerrado sesion.");
		currentUser.logout();
		req.getSession().invalidate();
		//getServletContext().getRequestDispatcher("/loginPage.jsp").forward(req, resp);
		resp.sendRedirect(req.getContextPath() + "/loginPage.jsp");
	}
}
