package es.upm.dit.tfg.webLab.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.PlanEstudios;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

public class AsignaturaDAOImplementationTest {
	Asignatura asignatura;
	Profesor profe;
	Usuario user;
	Plaza plaza;
	Grupo grupo;
	PlanEstudios plan;
	
	
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
		
		
		plan = new PlanEstudios();
		plan.setCodigo("50");
		plan.setNombre("Plan Prueba");
		PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(plan);
		
		
		asignatura = new Asignatura();
		asignatura.setAcronimo("PRB");
		asignatura.setCodigo("00");
		asignatura.setComentario("Es una prueba");
		asignatura.setCurso("2");
		asignatura.setEcts(3.0);
		asignatura.setHorasApolo(2.0);
		asignatura.setHorasLab(3);
		asignatura.setHorasTeoria(6);
		asignatura.setNombre("PRUEBA");
		asignatura.setNumeroAlumnos(60);
		asignatura.setPlanEstudios(plan);
		asignatura.setSemestre("primero");
		asignatura.setTipo("Obligatoria");
		asignatura.setCoordinador(profe);
		
	}
	
	@Test
	public void testReadAsignatura() {
		Asignatura asignaturaPrueba = AsignaturaDAOImplementation.getInstance().readAsignatura("00");
		assertEquals("PRB", asignaturaPrueba.getAcronimo());
		assertEquals("00", asignaturaPrueba.getCodigo());
		assertEquals("Es una prueba", asignaturaPrueba.getComentario());
		assertEquals("2", asignaturaPrueba.getCurso());
		assertEquals(3.0, asignaturaPrueba.getEcts());
		assertEquals(2.0, asignaturaPrueba.getHorasApolo());
		assertEquals(3, asignaturaPrueba.getHorasLab());
		assertEquals(6, asignaturaPrueba.getHorasTeoria());
		assertEquals("PRUEBA"        , asignaturaPrueba.getNombre());
		assertEquals(60, asignaturaPrueba.getNumeroAlumnos());
		assertEquals("50", asignaturaPrueba.getPlanEstudios().getCodigo());
		assertEquals("Plan Prueba", asignaturaPrueba.getPlanEstudios().getNombre());
		assertEquals("00", asignaturaPrueba.getPlanEstudios().getAsignaturas().get(0).getCodigo());
		assertEquals("primero", asignaturaPrueba.getSemestre());
		assertEquals("Obligatoria", asignaturaPrueba.getTipo());
		assertEquals("Prueba Prueba", asignaturaPrueba.getCoordinador().getUsuario().getApellidos());
		assertEquals("PRB", asignaturaPrueba.getCoordinador().getAcronimo());
		
	}
	
	@Test
	public void testUpdateAsignatura() {
		Asignatura asignaturaPrueba = AsignaturaDAOImplementation.getInstance().readAsignatura("00");
		asignaturaPrueba.setAcronimo("PRB1");
		asignaturaPrueba.setCodigo("01");
		asignaturaPrueba.setComentario("Es una prueba1");
		asignaturaPrueba.setCurso("21");
		asignaturaPrueba.setEcts(7.0);
		asignaturaPrueba.setHorasApolo(8.0);
		asignaturaPrueba.setHorasLab(9);
		asignaturaPrueba.setHorasTeoria(60);
		asignaturaPrueba.setNombre("PRUEBA1");
		asignaturaPrueba.setNumeroAlumnos(70);
		asignaturaPrueba.setSemestre("primero1");
		asignaturaPrueba.setTipo("Obligatoria1");
		AsignaturaDAOImplementation.getInstance().updateAsignatura(asignaturaPrueba);
		
		Asignatura asignaturaPrueba1 = AsignaturaDAOImplementation.getInstance().readAsignatura("01");
		
		
		
		assertEquals("PRB1", asignaturaPrueba1.getAcronimo());
		assertEquals("01", asignaturaPrueba1.getCodigo());
		assertEquals("Es una prueba1", asignaturaPrueba1.getComentario());
		assertEquals("21", asignaturaPrueba1.getCurso());
		assertEquals(7.0, asignaturaPrueba1.getEcts());
		assertEquals(8.0, asignaturaPrueba1.getHorasApolo());
		assertEquals(9, asignaturaPrueba1.getHorasLab());
		assertEquals(60, asignaturaPrueba1.getHorasTeoria());
		assertEquals("PRUEBA1", asignaturaPrueba1.getNombre());
		assertEquals(70, asignaturaPrueba1.getNumeroAlumnos());
		assertEquals("50", asignaturaPrueba1.getPlanEstudios().getCodigo());
		assertEquals("Plan Prueba", asignaturaPrueba1.getPlanEstudios().getNombre());
		assertEquals("01", asignaturaPrueba1.getPlanEstudios().getAsignaturas().get(0).getCodigo());
		assertEquals("primero1", asignaturaPrueba1.getSemestre());
		assertEquals("Obligatoria1", asignaturaPrueba1.getTipo());
		assertEquals("Prueba Prueba", asignaturaPrueba1.getCoordinador().getUsuario().getApellidos());
		assertEquals("PRB", asignaturaPrueba1.getCoordinador().getAcronimo());
	}
	
	@Test
	public void testDeleteAsignatura() {
		Asignatura asignaturaPrueba = AsignaturaDAOImplementation.getInstance().readAsignatura("01");
		AsignaturaDAOImplementation.getInstance().deleteAsignatura(asignaturaPrueba);
		
		Asignatura asignaturaPrueba1 = null;
		
		try {asignaturaPrueba1 = AsignaturaDAOImplementation.getInstance().readAsignatura("01");}catch(Exception e) {}


		String acronimo = ""; try { acronimo = asignaturaPrueba1.getAcronimo();}catch(Exception e) {}
		String codigo = "";try { codigo = asignaturaPrueba1.getCodigo();}catch(Exception e) {}
		String comentario = "";try { comentario = asignaturaPrueba1.getComentario();}catch(Exception e) {}
		String curso = "";try { curso = asignaturaPrueba1.getCurso(); }catch(Exception e) {}
		int lab = 0;try {lab = asignaturaPrueba1.getHorasLab(); }catch(Exception e) {}
		int teoria = 0;try { teoria = asignaturaPrueba1.getHorasTeoria();}catch(Exception e) {}
		String nombre = ""; try {nombre = asignaturaPrueba1.getNombre(); }catch(Exception e) {}
		int  alumnos= 0; try {alumnos = asignaturaPrueba1.getNumeroAlumnos(); }catch(Exception e) {}
		String semestre = ""; try {semestre = asignaturaPrueba1.getSemestre(); }catch(Exception e) {}
		String tipo = ""; try { tipo=asignaturaPrueba1.getTipo();}catch(Exception e) {}
		
		assertEquals("", acronimo);
		assertEquals("", codigo);
		assertEquals("", comentario);
		assertEquals("", curso);
		assertEquals(0, lab);
		assertEquals(0, teoria);
		assertEquals("", nombre);
		assertEquals(0, alumnos);
		assertEquals("", semestre);
		assertEquals("", tipo);
	}
	
	@After
	public void after() throws Exception {
		UsuarioDAOImplementation.getInstance().deleteUsuario(user);
		GrupoDAOImplementation.getInstance().deleteGrupo(grupo);
		PlazaDAOImplementation.getInstance().deletePlaza(plaza);
		ProfesorDAOImplementation.getInstance().deleteProfesor(profe);
		PlanEstudiosDAOImplementation.getInstance().deletePlanEstudios(plan);
	}

}
