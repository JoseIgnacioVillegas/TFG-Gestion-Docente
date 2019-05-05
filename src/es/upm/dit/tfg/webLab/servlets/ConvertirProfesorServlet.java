package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConvertirProfesorServlet")
public class ConvertirProfesorServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(LogoutServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		Usuario user = UsuarioDAOImplementation.getInstance().readUsuario(Integer.parseInt(id));
		String acronimo = req.getParameter("ConvertirDocente");
		String dedic = req.getParameter("dedicacion");
		String grup = req.getParameter("grupo");
		
		Grupo grupo = GrupoDAOImplementation.getInstance().readGrupo(grup);
		
		Plaza plaza = null;
		
		try {
			int plz = Integer.parseInt(req.getParameter("plaza"));
			plaza = PlazaDAOImplementation.getInstance().readPlaza(plz);
		}catch(Exception e){
			
		};
		
		List<Profesor> todosProfes = ProfesorDAOImplementation.getInstance().readProfesores();
		int idMaxProfe = todosProfes.size() +1;
		
		
		
		Profesor profesor = new Profesor();
		profesor.setId(idMaxProfe);
		profesor.setAcronimo(acronimo);
		if(plaza!=null)profesor.setPlaza(plaza);
		profesor.setGrupo(grupo);
		profesor.setDedicacion(dedic);
		profesor.setUsuario(user);
		
		ProfesorDAOImplementation.getInstance().createProfesor(profesor);
		
		
		List<Usuario> usuarios = UsuarioDAOImplementation.getInstance().readUsuarios();
		
		req.getSession().setAttribute("usuarios", usuarios);
		getServletContext().getRequestDispatcher("/CRUDPAS.jsp").forward(req, resp);
		
	}
}
