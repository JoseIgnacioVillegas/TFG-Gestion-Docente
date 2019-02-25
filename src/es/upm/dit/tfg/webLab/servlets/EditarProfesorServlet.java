package es.upm.dit.tfg.webLab.servlets;


import org.apache.log4j.Logger;
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
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/EditarProfesorServlet")

public class EditarProfesorServlet extends HttpServlet{


	private final static Logger log = Logger.getLogger(EditarProfesorServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		req.getSession().removeAttribute("mensaje");
		
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String acronimo = req.getParameter("acronimo");
		String correo = req.getParameter("correo");
		String dedicacion = req.getParameter("dedicacion");
		int id = Integer.parseInt(req.getParameter("id"));
		String grupoStr = req.getParameter("grupo");
		Grupo grupo = GrupoDAOImplementation.getInstance().readGrupo(grupoStr);
		
		int plazaId = Integer.parseInt(req.getParameter("plaza"));
		Plaza plaza = PlazaDAOImplementation.getInstance().readPlaza(plazaId);
		
		Profesor profesorAnt = ProfesorDAOImplementation.getInstance().readProfesor(id);
		Usuario UsuarioAnt = profesorAnt.getUsuario();
		int UsuarioId = profesorAnt.getUsuario().getId();
		ProfesorDAOImplementation.getInstance().deleteProfesor(profesorAnt);
		

		UsuarioDAOImplementation.getInstance().deleteUsuario(UsuarioAnt);
		
		
		
		Usuario usuario =new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setCorreo(correo);
		usuario.setId(UsuarioId);
		UsuarioDAOImplementation.getInstance().createUsuario(usuario);
	
		
		
		Profesor profesor = new Profesor();
		profesor.setId(id);
		profesor.setAcronimo(acronimo);
		profesor.setPlaza(plaza);
		profesor.setGrupo(grupo);
		profesor.setDedicacion(dedicacion);
		profesor.setUsuario(usuario);
		
		ProfesorDAOImplementation.getInstance().createProfesor(profesor);
		
		
		Usuario usuarioAccion = (Usuario) req.getSession().getAttribute("usuario");
		//log.info("El usuario "+usuarioAccion.getNombre()+" "+usuarioAccion.getApellidos()+" ha editado el profesor "+nombre+" "+apellidos);

		
		//Sacamos todas las asignaturas para pasarlas al jsp
		List<Profesor> todosProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
		req.getSession().setAttribute("profesores", todosProfesores);
	
		
		String msj = "Profesor editado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDProfesor.jsp");
		
		
	}
	
}
		