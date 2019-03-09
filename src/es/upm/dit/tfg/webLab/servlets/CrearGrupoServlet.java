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

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/CrearGrupoServlet")
public class CrearGrupoServlet extends HttpServlet{
	private final static Logger log = Logger.getLogger(CrearGrupoServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		req.getSession().removeAttribute("mensaje");
		String nom = req.getParameter("nombre");
		String acrom = req.getParameter("acronimo");
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			Grupo grupo = new Grupo();
			grupo.setNombre(nom);
			grupo.setAcronimo(acrom);
			
	
			GrupoDAOImplementation.getInstance().createGrupo(grupo);
			Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
	
			//log.info("El usuario "+usuario.getNombre()+" "+usuario.getApellidos()+" ha creado el plan de estudios "+nom);
	
			
			List<Grupo> todosGrupos = GrupoDAOImplementation.getInstance().readGrupos();
			
			req.getSession().setAttribute("grupos", todosGrupos);
	
	
			String msj = "Grupo creado con éxito";
			req.getSession().setAttribute("mensaje", msj);

			getServletContext().getRequestDispatcher("/CRUDGrupo.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}

	}
}
