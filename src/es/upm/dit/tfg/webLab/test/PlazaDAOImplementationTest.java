package es.upm.dit.tfg.webLab.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.model.Plaza;

public class PlazaDAOImplementationTest {

Plaza plaza;
	
	@Before
	public void setUp() throws Exception {
		plaza = new Plaza();
		plaza.setId(2000);
		plaza.setDescripcion("Descripción prueba");
		plaza.setPlaza("Nombre prueba");
		PlazaDAOImplementation.getInstance().createPlaza(plaza);
	}
	
	@Test
	public void testReadPlaza() {
		Plaza plazaPrueba = PlazaDAOImplementation.getInstance().readPlaza(2000);
		assertEquals("Descripción prueba", plazaPrueba.getDescripcion());
		assertEquals("Nombre prueba", plazaPrueba.getPlaza());
	}
	
	@Test
	public void testUpdatePlaza() {
		Plaza plaza1 = new Plaza();
		plaza1.setId(2001);
		plaza1.setDescripcion("Descripción prueba");
		plaza1.setPlaza("Nombre prueba");
		PlazaDAOImplementation.getInstance().createPlaza(plaza1);
		
		
		Plaza plazaPrueba = PlazaDAOImplementation.getInstance().readPlaza(2001);
		plazaPrueba.setDescripcion("Otra prueba");
		plazaPrueba.setPlaza("Otro Nombre");
		PlazaDAOImplementation.getInstance().updatePlaza(plazaPrueba);
		
		Plaza plazaPrueba1 = PlazaDAOImplementation.getInstance().readPlaza(2001);
		
		assertEquals("Otra prueba", plazaPrueba1.getDescripcion());
		assertEquals("Otro Nombre", plazaPrueba1.getPlaza());
	}
	
	
	@Test
	public void testDeleteUsuario() {
		Plaza plazaPrueba = PlazaDAOImplementation.getInstance().readPlaza(2000);
		PlazaDAOImplementation.getInstance().deletePlaza(plazaPrueba);
		Plaza plazaPrueba1 = null;
		
		
		try {
			plazaPrueba1 = PlazaDAOImplementation.getInstance().readPlaza(2000);
		}catch(Exception e) {
			
		}
		String descripcion = "";
		try {descripcion = plazaPrueba1.getDescripcion();}catch(Exception e) {}
		
		String nombre = "";
		try {nombre = plazaPrueba1.getPlaza();}catch(Exception e) {}
		
		assertEquals("", descripcion);
		assertEquals("", nombre);
	}
	
	@After
	public void after() throws Exception {
		Plaza plazaBorrar = PlazaDAOImplementation.getInstance().readPlaza(2001);
		PlazaDAOImplementation.getInstance().deletePlaza(plazaBorrar);
	}


}
