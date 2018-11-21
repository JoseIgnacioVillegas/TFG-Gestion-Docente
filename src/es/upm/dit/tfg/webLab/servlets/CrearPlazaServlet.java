package es.upm.dit.tfg.webLab.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;

@WebServlet("/CrearPlazaServlet")
public class CrearPlazaServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String nom = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		List<Plaza> plazas = PlazaDAOImplementation.getInstance().readPlazas();
		int id = plazas.size()+1;

		Plaza plaza = new Plaza();
		plaza.setId(id);
		plaza.setPlaza(nom);
		plaza.setDescripcion(descripcion);
		PlazaDAOImplementation.getInstance().createPlaza(plaza);
		
		List<Plaza> todasPlazas = PlazaDAOImplementation.getInstance().readPlazas();
	
		
		req.getSession().setAttribute("plazas", todasPlazas);
		


		String msj = "Plaza creada con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDPlaza.jsp");

	}
}
