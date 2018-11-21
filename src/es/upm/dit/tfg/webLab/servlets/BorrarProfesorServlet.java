package es.upm.dit.tfg.webLab.servlets;


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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		int idProfe = Integer.parseInt(req.getParameter("idProfe"));
		int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));

		Profesor profesor = ProfesorDAOImplementation.getInstance().readProfesor(idProfe);
		ProfesorDAOImplementation.getInstance().deleteProfesor(profesor);
		
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(idUsuario);
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuario);
		
		List<Profesor> todasProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
		req.getSession().setAttribute("profesores", todasProfesores);
		
		String msj = "Profesor borrado con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDProfesor.jsp");
	}
}