package es.upm.dit.tfg.webLab.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.model.PlanEstudios;

public class PlanEstudiosDAOImplementationTest {

	PlanEstudios plan;

	@Before
	public void setUp() throws Exception {
		PlanEstudios plan = new PlanEstudios();
		plan.setCodigo("50");
		plan.setNombre("Plan Prueba");
		PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(plan);
	}
		
	@Test
	public void testReadPlaza() {
		PlanEstudios planPrueba = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios("50");
		assertEquals("50", planPrueba.getCodigo());
		assertEquals("Plan Prueba", planPrueba.getNombre());
	}
		
	@Test
	public void testUpdatePlaza() {
		PlanEstudios plan1 = new PlanEstudios();
		plan1.setCodigo("51");
		plan1.setNombre("Plan Prueba1");
		PlanEstudiosDAOImplementation.getInstance().createPlanEstudios(plan1);
			
		PlanEstudios planPrueba = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios("51");
		planPrueba.setCodigo("52");
		planPrueba.setNombre("Plan Prueba2");
		PlanEstudiosDAOImplementation.getInstance().updatePlanEstudios(planPrueba);
			
		PlanEstudios planPrueba1 = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios("52");
			
		assertEquals("52", planPrueba1.getCodigo());
		assertEquals("Plan Prueba2", planPrueba1.getNombre());
	}
		
		
	@Test
	public void testDeleteUsuario() {
		PlanEstudios planPrueba = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios("50");
		PlanEstudiosDAOImplementation.getInstance().deletePlanEstudios(planPrueba);

		PlanEstudios planPrueba1 = null;
			
		try {planPrueba1 = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios("50");}catch(Exception e) {}
			
		String codigo = "";
		try {codigo = planPrueba1.getCodigo();}catch(Exception e) {}
			
		String nombre = "";
		try {nombre = planPrueba1.getNombre();}catch(Exception e) {}
			
		assertEquals("", codigo);
		assertEquals("", nombre);
		
		}
		
		@After
		public void after() throws Exception {
			PlanEstudios planBorrar = PlanEstudiosDAOImplementation.getInstance().readPlanEstudios("52");
			PlanEstudiosDAOImplementation.getInstance().deletePlanEstudios(planBorrar);
		}

}
