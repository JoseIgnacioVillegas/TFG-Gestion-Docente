package es.upm.dit.tfg.webLab.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.realm.UserDatabaseRealm;

import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		Usuario usuario = null;
		//String id = "";
		try {
			usuario = UsuarioDAOImplementation.getInstance().readUsuarioPorCorreo(email);
			//id = Integer.toString(usuario.getId());
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		
		
		
		String msj = "El correo o la contraseña son inválidos, por favor intentelo de nuevo";
		
		
		
		if(usuario != null) {
			//req.getSession().setAttribute("usuarioId", id);
			req.getSession().setAttribute("usuario", usuario);
			resp.sendRedirect(req.getContextPath() + "/VistaInicial.jsp");
		}else {
			req.getSession().setAttribute("error", msj);
			resp.sendRedirect(req.getContextPath() + "/Login.jsp");
		}
		
		
		
		
		
		/*
		
		
		String coordinador = "coordinador@upm.es";
		String gestor = "gestor@upm.es";
		String profesor = "profesor@upm.es";
		String PAS = "pas@upm.es";
		String colaborador = "colaborador@upm.es";
		String contrasena = "1234";
		String msj = "El correo o la contraseña son inválidos, por favor intentelo de nuevo";
		
		if(null != email) {
			
			if(email.equals(coordinador)) {
				if (pass.equals(contrasena)) {
					req.getSession().setAttribute("email", email);
					resp.sendRedirect(req.getContextPath() + "/VistaInicial.jsp");
				}else {
					req.getSession().setAttribute("error", msj);
					resp.sendRedirect(req.getContextPath() + "/Login.jsp");
				}
				
			}else if(email.equals(gestor)){
				if (pass.equals(contrasena)) {
					req.getSession().setAttribute("email", email);
					resp.sendRedirect(req.getContextPath() + "/VistaInicial.jsp");
				}else {
					req.getSession().setAttribute("error", msj);
					resp.sendRedirect(req.getContextPath() + "/Login.jsp");
				}
			}else if (email.equals(profesor)){
				if (pass.equals(contrasena)) {
					req.getSession().setAttribute("email", email);
					resp.sendRedirect(req.getContextPath() + "/VistaInicial.jsp");
				}else {
					req.getSession().setAttribute("error", msj);
					resp.sendRedirect(req.getContextPath() + "/Login.jsp");
				}
				
				
			}else if (email.equals(PAS)){
				if (pass.equals(contrasena)) {
					req.getSession().setAttribute("email", email);
					resp.sendRedirect(req.getContextPath() + "/VistaInicial.jsp");
				}else {
					req.getSession().setAttribute("error", msj);
					resp.sendRedirect(req.getContextPath() + "/Login.jsp");
				}
			}else {
				req.getSession().setAttribute("error", msj);
				resp.sendRedirect(req.getContextPath() + "/Login.jsp");
			}
		} else {
			req.getSession().setAttribute("error", msj);
			resp.sendRedirect(req.getContextPath() + "/Login.jsp");
			
		}
		*/
	}

}