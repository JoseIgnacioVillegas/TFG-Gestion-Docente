package es.upm.dit.tfg.webLab.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.model.Grupo;

public class GrupoDAOImplementationTest {
	Grupo grupo;
	
	@Before
	public void setUp() throws Exception {
		grupo = new Grupo();
		grupo.setAcronimo("PRB");
		grupo.setNombre("PRUEBA");
		GrupoDAOImplementation.getInstance().createGrupo(grupo);
	}
	
	@Test
	public void testReadGrupo() {
		Grupo grupoPrueba = GrupoDAOImplementation.getInstance().readGrupo("PRUEBA");
		assertEquals("PRB", grupoPrueba.getAcronimo());
		assertEquals("PRUEBA", grupoPrueba.getNombre());
	}
	
	@Test
	public void testUpdateGrupo() {
		Grupo grupo1 = new Grupo();
		grupo1.setAcronimo("PRB1");
		grupo1.setNombre("PRUEBA1");
		GrupoDAOImplementation.getInstance().createGrupo(grupo1);
		
		
		Grupo grupoPrueba = GrupoDAOImplementation.getInstance().readGrupo("PRUEBA1");
		grupoPrueba.setAcronimo("PRB2");
		grupoPrueba.setNombre("PRUEBA2");
		GrupoDAOImplementation.getInstance().updateGrupo(grupoPrueba);
		
		Grupo grupoPrueba1 = GrupoDAOImplementation.getInstance().readGrupo("PRUEBA2");
		
		assertEquals("PRB2", grupoPrueba1.getAcronimo());
		assertEquals("PRUEBA2", grupoPrueba1.getNombre());
	}
	
	
	@Test
	public void testDeleteGrupo() {
		Grupo grupoPrueba = GrupoDAOImplementation.getInstance().readGrupo("PRUEBA");
		GrupoDAOImplementation.getInstance().deleteGrupo(grupoPrueba);
		Grupo grupoPrueba1 = null;
		
		
		try {grupoPrueba1 = GrupoDAOImplementation.getInstance().readGrupo("PRUEBA");}catch(Exception e) {}
		
		String acronimo = "";
		try {acronimo  = grupoPrueba1.getAcronimo();}catch(Exception e) {}
		
		String nombre = "";
		try {nombre = grupoPrueba1.getNombre();}catch(Exception e) {}
		
		assertEquals("", acronimo);
		assertEquals("", nombre);
	}
	
	@After
	public void after() throws Exception {
		Grupo grupoBorrar = GrupoDAOImplementation.getInstance().readGrupo("PRUEBA2");
		GrupoDAOImplementation.getInstance().deleteGrupo(grupoBorrar);
	}


}
