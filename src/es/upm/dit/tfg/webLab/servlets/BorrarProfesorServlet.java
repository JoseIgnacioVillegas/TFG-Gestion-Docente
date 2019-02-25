package es.upm.dit.tfg.webLab.servlets;

import org.apache.log4j.Logger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

@WebServlet("/BorrarProfesorServlet")
public class BorrarProfesorServlet extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(BorrarProfesorServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		req.getSession().removeAttribute("mensaje");
		int idProfe = Integer.parseInt(req.getParameter("idProfe"));
		int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));

		Profesor profesor = ProfesorDAOImplementation.getInstance().readProfesor(idProfe);
		ProfesorDAOImplementation.getInstance().deleteProfesor(profesor);
		
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(idUsuario);
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuario);
		
		Usuario usuarioAccion = (Usuario) req.getSession().getAttribute("usuario");

		//log.info("El usuario "+usuarioAccion.getNombre()+" "+usuarioAccion.getApellidos()+" ha borrado al profesor "+usuario.getNombre()+" "+usuario.getApellidos());

		
		List<Profesor> todasProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
		req.getSession().setAttribute("profesores", todasProfesores);
		
		String msj = "Profesor borrado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDProfesor.jsp");
	}
}