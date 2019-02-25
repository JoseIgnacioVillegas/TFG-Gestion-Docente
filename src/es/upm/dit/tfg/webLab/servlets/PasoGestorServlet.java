package es.upm.dit.tfg.webLab.servlets;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

@WebServlet("/PasoGestorServlet")
public class PasoGestorServlet extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getSession().removeAttribute("mensaje");
		//Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		//System.out.println(currentUser.hasRole("gestor"));
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println(currentUser.getPrincipal());
	      System.out.println(currentUser.isAuthenticated());

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		
		if (currentUser.hasRole("gestor")){
			req.getSession().setAttribute("currentUser", currentUser);
			//getServletContext().getRequestDispatcher("/VistaGestor.jsp").forward(req, resp);
			
			resp.sendRedirect(req.getContextPath() + "/VistaInicial.jsp");
		}else {
			getServletContext().getRequestDispatcher("/VistaGestor.jsp").forward(req, resp);
		}
	}

}
