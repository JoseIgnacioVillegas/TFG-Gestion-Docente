package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/BorrarPlazaServlet")
public class BorrarPlazaServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(BorrarPlazaServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		req.getSession().removeAttribute("mensaje");
		int id = Integer.parseInt(req.getParameter("id"));

		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			Plaza plaza = PlazaDAOImplementation.getInstance().readPlaza(id);
			PlazaDAOImplementation.getInstance().deletePlaza(plaza);
			
			Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
	
			//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha borrado la plaza de profesor "+plaza.getPlaza());
			
			
			List<Plaza> todasPlazas = PlazaDAOImplementation.getInstance().readPlazas();
		
			
			req.getSession().setAttribute("plazas", todasPlazas);
			
	
			String msj = "Plaza borrada con éxito";
			req.getSession().setAttribute("mensaje", msj);

			getServletContext().getRequestDispatcher("/CRUDPlaza.jsp").forward(req, resp);
			
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}
}
