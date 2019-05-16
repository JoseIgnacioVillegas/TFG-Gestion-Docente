package es.upm.dit.tfg.webLab.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

public class ProfesorDAOImplementationTest {

	Profesor profe;
	Usuario user;
	Plaza plaza;
	Grupo grupo;
	
	
	@Before
	public void setUp() throws Exception {
		
		user = new Usuario();
		user.setId(2000);
		user.setApellidos("Prueba Prueba");
		user.setCorreo("prueba@gmail.com");
		user.setNombre("Prueba");
		user.setPassword("1234");
		UsuarioDAOImplementation.getInstance().createUsuario(user);
		
		
		plaza = new Plaza();
		plaza.setId(2000);
		plaza.setDescripcion("Descripción prueba");
		plaza.setPlaza("Nombre prueba");
		PlazaDAOImplementation.getInstance().createPlaza(plaza);
		
		
		grupo = new Grupo();
		grupo.setAcronimo("PRB");
		grupo.setNombre("PRUEBA");
		GrupoDAOImplementation.getInstance().createGrupo(grupo);
		
		profe = new Profesor();
		profe.setAcronimo("PRB");
		profe.setDedicacion("5");
		profe.setUsuario(user);
		profe.setPlaza(plaza);
		profe.setGrupo(grupo);
		profe.setId(2000);
	}
	
	@Test
	public void testReadProfesor() {
		Profesor profesorPrueba = ProfesorDAOImplementation.getInstance().readProfesor(2000);
		assertEquals("PRB", profesorPrueba.getAcronimo());
		assertEquals("5", profesorPrueba.getDedicacion());
		assertEquals("PRB", profesorPrueba.getGrupo().getAcronimo());
		assertEquals("PRUEBA",profesorPrueba.getGrupo().getNombre());
		assertEquals("Descripción prueba",profesorPrueba.getPlaza().getDescripcion());
		assertEquals("Nombre prueba",profesorPrueba.getPlaza().getPlaza());
		assertEquals("Prueba Prueba",profesorPrueba.getUsuario().getApellidos());
		assertEquals("prueba@gmail.com",profesorPrueba.getUsuario().getCorreo());
		assertEquals("Prueba",profesorPrueba.getUsuario().getNombre());
	}
	
	@Test
	public void testUpdateProfesor() {
		Profesor profesorPrueba = ProfesorDAOImplementation.getInstance().readProfesor(2000);
		profesorPrueba.setAcronimo("PRUEBA30");
		profesorPrueba.setDedicacion("800");
		profesorPrueba.setId(20001);
		ProfesorDAOImplementation.getInstance().updateProfesor(profesorPrueba);
		
		
		Profesor profesorPrueba1 = ProfesorDAOImplementation.getInstance().readProfesor(2001);
		
		assertEquals("PRUEBA30", profesorPrueba1.getAcronimo());
		assertEquals("800", profesorPrueba1.getDedicacion());
		assertEquals(2001, profesorPrueba1.getId());
		
		assertEquals("PRB", profesorPrueba1.getGrupo().getAcronimo());
		assertEquals("PRUEBA",profesorPrueba1.getGrupo().getNombre());
		assertEquals("Descripción prueba",profesorPrueba1.getPlaza().getDescripcion());
		assertEquals("Nombre prueba",profesorPrueba1.getPlaza().getPlaza());
		assertEquals("Prueba Prueba",profesorPrueba1.getUsuario().getApellidos());
		assertEquals("prueba@gmail.com",profesorPrueba1.getUsuario().getCorreo());
		assertEquals("Prueba",profesorPrueba1.getUsuario().getNombre());
	}
	
	@Test
	public void testDeleteProfesor() {
		Profesor profesorPrueba = ProfesorDAOImplementation.getInstance().readProfesor(2001);
		ProfesorDAOImplementation.getInstance().deleteProfesor(profesorPrueba);
		Profesor profesorPrueba1 = null;
		
		try {profesorPrueba1 = ProfesorDAOImplementation.getInstance().readProfesor(2001);}catch(Exception e) {}
		
		String acronimo = "";
		try {acronimo = profesorPrueba1.getAcronimo();}catch(Exception e) {}
		
		String dedicacion = "";
		try {dedicacion = profesorPrueba1.getDedicacion();}catch(Exception e) {}
		
		
		assertEquals("", acronimo);
		assertEquals("", dedicacion);
	}
	
	@After
	public void after() throws Exception {
		UsuarioDAOImplementation.getInstance().deleteUsuario(user);
		GrupoDAOImplementation.getInstance().deleteGrupo(grupo);
		PlazaDAOImplementation.getInstance().deletePlaza(plaza);
	}
	

}
