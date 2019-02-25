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

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/BorrarGrupoServlet")
public class BorrarGrupoServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(BorrarGrupoServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String nom = req.getParameter("grupo");

		Grupo grupo = GrupoDAOImplementation.getInstance().readGrupo(nom);
		GrupoDAOImplementation.getInstance().deleteGrupo(grupo);

		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha borrado el grupo de investigacion "+grupo.getNombre());
		
		
		List<Grupo> todosGrupos = GrupoDAOImplementation.getInstance().readGrupos();
		
		req.getSession().setAttribute("grupos", todosGrupos);
		
		String msj = "Grupo borrado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDGrupo.jsp");

	}
}