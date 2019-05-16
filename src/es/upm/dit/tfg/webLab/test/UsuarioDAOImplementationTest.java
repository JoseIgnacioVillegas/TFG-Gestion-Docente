package es.upm.dit.tfg.webLab.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Usuario;

public class UsuarioDAOImplementationTest {
	
	Usuario user;
	
	@Before
	public void setUp() throws Exception {
		user = new Usuario();
		user.setId(2000);
		user.setApellidos("Villegas Villegas");
		user.setCorreo("nachovv96@gmail.com");
		user.setNombre("Nacho");
		user.setPassword("1234");
		UsuarioDAOImplementation.getInstance().createUsuario(user);
	}
	
	@Test
	public void testReadUsuario() {
		Usuario usuarioPrueba = UsuarioDAOImplementation.getInstance().readUsuario(2000);
		assertEquals("Villegas Villegas", usuarioPrueba.getApellidos());
		assertEquals("nachovv96@gmail.com", usuarioPrueba.getCorreo());
		assertEquals("Nacho", usuarioPrueba.getNombre());
	}
	
	@Test
	public void testReadUsuarioPorCorreo() {
		Usuario usuarioPrueba = UsuarioDAOImplementation.getInstance().readUsuarioPorCorreo("nachovv96@gmail.com");
		
		assertEquals("Villegas Villegas", usuarioPrueba.getApellidos());
		assertEquals("nachovv96@gmail.com", usuarioPrueba.getCorreo());
		assertEquals("Nacho", usuarioPrueba.getNombre());
	}
	
	@Test
	public void testUpdateUsuario() {
		Usuario usuarioPrueba = UsuarioDAOImplementation.getInstance().readUsuario(2000);
		usuarioPrueba.setApellidos("García García");
		usuarioPrueba.setNombre("José Ignacio");
		UsuarioDAOImplementation.getInstance().updateUsuario(usuarioPrueba);
		Usuario usuarioPrueba1 = UsuarioDAOImplementation.getInstance().readUsuario(1);
		
		assertEquals("García García", usuarioPrueba1.getApellidos());
		assertEquals("nachovv96@gmail.com", usuarioPrueba1.getCorreo());
		assertEquals("José Ignacio", usuarioPrueba1.getNombre());
	}
	
	@Test
	public void testDeleteUsuario() {
		Usuario usuarioPrueba = UsuarioDAOImplementation.getInstance().readUsuario(2000);
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuarioPrueba);
		Usuario usuarioPrueba1 = null;
		try {
			usuarioPrueba1 = UsuarioDAOImplementation.getInstance().readUsuario(2000);
		}catch(Exception e) {
			
		}
		String apellidos = "";
		try {apellidos = usuarioPrueba1.getApellidos();}catch(Exception e) {}
		
		String correo = "";
		try {correo = usuarioPrueba1.getCorreo();}catch(Exception e) {}
		
		String nombre = "";
		try {nombre = usuarioPrueba1.getNombre();}catch(Exception e) {}
		
		assertEquals("", apellidos);
		assertEquals("", correo);
		assertEquals("", nombre);
	}
	
	@After
	public void after() throws Exception {
		Usuario usuarioBorrar = UsuarioDAOImplementation.getInstance().readUsuario(2001);
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuarioBorrar);
	}

}
