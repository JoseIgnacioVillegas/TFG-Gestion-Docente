package es.upm.dit.tfg.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@WebServlet("/PasoGestorServlet")
public class PasoGestorServlet extends HttpServlet{
	private final static Logger log = Logger.getLogger(PasoGestorServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Subject currentUser = SecurityUtils.getSubject();
		
		req.getSession().setAttribute("currentUser", currentUser);
		getServletContext().getRequestDispatcher("/VistaGestor.jsp").forward(req, resp);
	}

}
