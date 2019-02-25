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
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/EditarGrupoServlet")
public class EditarGrupoServlet extends HttpServlet{
	private final static Logger log = Logger.getLogger(EditarGrupoServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		req.getSession().removeAttribute("mensaje");
		String nomAnt = req.getParameter("nombre1");
		String nom = req.getParameter("nombre");
		String acrom = req.getParameter("acronimo");
		


		Grupo grupo = GrupoDAOImplementation.getInstance().readGrupo(nomAnt);
		grupo.setNombre(nom);
		grupo.setAcronimo(acrom);
		GrupoDAOImplementation.getInstance().updateGrupo(grupo);
		
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha creado el grupo de investigacion "+nom);

		
		List<Grupo> todosGrupos = GrupoDAOImplementation.getInstance().readGrupos();
		
		
		req.getSession().setAttribute("grupos", todosGrupos);
		
		String msj = "Grupo editado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDGrupo.jsp");

	}
}
