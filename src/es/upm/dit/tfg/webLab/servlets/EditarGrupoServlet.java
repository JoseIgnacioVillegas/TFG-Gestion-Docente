package es.upm.dit.tfg.webLab.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;

@WebServlet("/EditarGrupoServlet")
public class EditarGrupoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String nomAnt = req.getParameter("nombre1");
		String nom = req.getParameter("nombre");
		String acrom = req.getParameter("acronimo");
		


		Grupo grupo = GrupoDAOImplementation.getInstance().readGrupo(nomAnt);
		GrupoDAOImplementation.getInstance().deleteGrupo(grupo);


		Grupo grupoNuevo = new Grupo();	
		grupoNuevo.setNombre(nom);
		grupoNuevo.setAcronimo(acrom);;


		GrupoDAOImplementation.getInstance().createGrupo(grupoNuevo);

		
		List<Grupo> todosGrupos = GrupoDAOImplementation.getInstance().readGrupos();
		
		
		req.getSession().setAttribute("grupos", todosGrupos);
		
		
		
		String msj = "Grupo editado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDGrupo.jsp");

	}
}
