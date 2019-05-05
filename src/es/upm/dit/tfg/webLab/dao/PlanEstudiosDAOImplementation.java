package es.upm.dit.tfg.webLab.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;


import es.upm.dit.tfg.webLab.model.PlanEstudios;




public class PlanEstudiosDAOImplementation implements PlanEstudiosDAO{
	private final static Logger log = Logger.getLogger(PlanEstudiosDAOImplementation.class);
	
	public static PlanEstudiosDAOImplementation instance;
	private PlanEstudiosDAOImplementation() {
	}
	public static PlanEstudiosDAOImplementation getInstance() {
		if (null == instance) {
			instance = new PlanEstudiosDAOImplementation();
			}
		return instance;
	}
	
	@Override
	public void createPlanEstudios(PlanEstudios plan) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(plan);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}	
	}



	@Override
	public void deletePlanEstudios(PlanEstudios plan) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(plan);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}	
	}


	@Override
	public PlanEstudios readPlanEstudios(String codigo) {
		PlanEstudios plan = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			plan = session.get(PlanEstudios.class, codigo);
			session.getTransaction().commit();
		}catch(Exception e) {
			log.error(e);
		}finally {
			session.close();
		}
		return plan;
	}
	
	@Override
	public List<PlanEstudios> readTodosPlanesEstudios() {
		Session session = SessionFactoryService.get().openSession();
		List<PlanEstudios> planes = new ArrayList<>();
		try {
			session.beginTransaction();
			planes.addAll(session.createQuery("select t from PlanEstudios t").getResultList() );
			session.getTransaction().commit();
		}catch(Exception e) {
			log.error(e);
		}finally {
			session.close();
		}
		return planes;
	}
	
	

}