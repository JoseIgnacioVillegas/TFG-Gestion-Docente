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

import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/EditarPlazaServlet")
public class EditarPlazaServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(EditarPlazaServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		req.getSession().removeAttribute("mensaje");
		int id = Integer.parseInt(req.getParameter("id"));
		String nom = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		
		Plaza plaza = PlazaDAOImplementation.getInstance().readPlaza(id);
		plaza.setPlaza(nom);
		plaza.setDescripcion(descripcion);
		PlazaDAOImplementation.getInstance().updatePlaza(plaza);
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha la plaza de profesor "+nom);

		
		List<Plaza> todasPlazas = PlazaDAOImplementation.getInstance().readPlazas();
		
		
		req.getSession().setAttribute("plazas", todasPlazas);
		
		
		String msj = "Plaza editada con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDPlaza.jsp");

	}
}
