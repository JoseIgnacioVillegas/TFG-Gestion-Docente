package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
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
		req.getSession().removeAttribute("mensaje");
		int id = Integer.parseInt(req.getParameter("id"));

		Plaza plaza = PlazaDAOImplementation.getInstance().readPlaza(id);
		PlazaDAOImplementation.getInstance().deletePlaza(plaza);
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha borrado la plaza de profesor "+plaza.getPlaza());
		
		
		List<Plaza> todasPlazas = PlazaDAOImplementation.getInstance().readPlazas();
	
		
		req.getSession().setAttribute("plazas", todasPlazas);
		

		String msj = "Plaza borrada con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDPlaza.jsp");
	}
}
