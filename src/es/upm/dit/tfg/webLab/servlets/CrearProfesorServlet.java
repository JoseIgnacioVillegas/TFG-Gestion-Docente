package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/CrearProfesorServlet")
public class CrearProfesorServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(CrearProfesorServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("mensaje");
		String nom = req.getParameter("nombre");
		String ape = req.getParameter("apellidos");
		String acrom = req.getParameter("acronimo");
		String corr = req.getParameter("correo");
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
		
		List<Usuario> todosUsuarios = UsuarioDAOImplementation.getInstance().readUsuarios();
		int idMaxUsuario = todosUsuarios.size() +1;
		
		
		Usuario usuario =new Usuario();
		usuario.setNombre(nom);
		usuario.setApellidos(ape);
		usuario.setCorreo(corr);
		usuario.setId(idMaxUsuario);
		UsuarioDAOImplementation.getInstance().createUsuario(usuario);
	
		
		
		Profesor profesor = new Profesor();
		profesor.setId(idMaxProfe);
		profesor.setAcronimo(acrom);
		if(plaza!=null)profesor.setPlaza(plaza);
		profesor.setGrupo(grupo);
		profesor.setDedicacion(dedic);
		profesor.setUsuario(usuario);
		
		ProfesorDAOImplementation.getInstance().createProfesor(profesor);
		
		Usuario usuarioAccion = (Usuario) req.getSession().getAttribute("usuario");

		//log.info("El usuario "+usuarioAccion.getNombre()+" "+usuarioAccion.getApellidos()+" ha creado el profesor "+nom+" "+ape);

		
	
		
		//Sacamos todas las asignaturas para pasarlas al jsp
		List<Profesor> todosProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
		req.getSession().setAttribute("profesores", todosProfesores);

		
		
		
		String msj = "Profesor creado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDProfesor.jsp");

	}
}
