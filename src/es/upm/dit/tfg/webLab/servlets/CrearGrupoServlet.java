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
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Profesor;

@WebServlet("/CrearGrupoServlet")
public class CrearGrupoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String nom = req.getParameter("nombre");
		String acrom = req.getParameter("acronimo");
		
		

		Grupo grupo = new Grupo();
		grupo.setNombre(nom);
		grupo.setAcronimo(acrom);
		

		GrupoDAOImplementation.getInstance().createGrupo(grupo);
		
		
		List<Grupo> todosGrupos = GrupoDAOImplementation.getInstance().readGrupos();
		
		List<String> nombres = new ArrayList<String>();
		for (int i=0; i<todosGrupos.size(); i++) {
			nombres.add(todosGrupos.get(i).getNombre());
			nombres.add(todosGrupos.get(i).getAcronimo());
		}
		
		req.getSession().setAttribute("grupos", todosGrupos);


		String msj = "Grupo creado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDGrupo.jsp");

	}
}
