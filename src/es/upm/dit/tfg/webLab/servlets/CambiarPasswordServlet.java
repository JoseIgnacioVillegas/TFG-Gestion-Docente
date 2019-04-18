package es.upm.dit.tfg.webLab.servlets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Usuario;



@WebServlet("/CambiarPasswordServlet")

public class CambiarPasswordServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String email = req.getParameter("usuario");
		Usuario user = UsuarioDAOImplementation.getInstance().readUsuarioPorCorreo(email);
		String passNueva = req.getParameter("passNueva");
		String passAntigua = req.getParameter("passAntigua");
		String shaAntigua = convertirSHA256(passAntigua);
		String shaBBDD = user.getPassword();
		if(shaBBDD.equals(shaAntigua)) {
			user.setPassword(convertirSHA256(passNueva));
			UsuarioDAOImplementation.getInstance().updateUsuario(user);
		}
		getServletContext().getRequestDispatcher("/Perfil.jsp").forward(req, resp);
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