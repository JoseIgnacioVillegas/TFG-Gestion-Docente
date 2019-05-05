package es.upm.dit.tfg.webLab.servlets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Usuario;


@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(EditarUsuarioServlet.class);
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		List<Usuario> todoUsuarios = UsuarioDAOImplementation.getInstance().readUsuarios();

		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setCorreo(email);
		usuario.setPassword(convertirSHA256(password));

		log.info("Se ha registrado un nuevo usuario en el sistema: "+nombre+" "+apellidos);
		getServletContext().getRequestDispatcher("/loginPage.jsp").forward(req, resp);
	}
	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}
	
}