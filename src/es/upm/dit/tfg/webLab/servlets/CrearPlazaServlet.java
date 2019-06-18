package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.model.Plaza;

@WebServlet("/CrearPlazaServlet")
public class CrearPlazaServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(CrearPlazaServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		String nom = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
		
			//List<Plaza> plazas = PlazaDAOImplementation.getInstance().readPlazas();
			//int id = plazas.size()+1;
	
			Plaza plaza = new Plaza();
			//plaza.setId(id);
			plaza.setPlaza(nom);
			plaza.setDescripcion(descripcion);
			PlazaDAOImplementation.getInstance().createPlaza(plaza);
			
			log.info("El usuario "+currentUser.getPrincipal().toString()+" ha creado la plaza de profesor "+nom);

			List<Plaza> todasPlazas = PlazaDAOImplementation.getInstance().readPlazas();
		
			req.getSession().setAttribute("plazas", todasPlazas);
			getServletContext().getRequestDispatcher("/CRUDPlaza.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}

	}
}
