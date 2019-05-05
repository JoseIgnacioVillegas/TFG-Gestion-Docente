package es.upm.dit.tfg.webLab.dao;


import org.apache.log4j.Logger;
import org.hibernate.Session;

import es.upm.dit.tfg.webLab.model.ProfesorGrupoClaseAsociacion;




public class ProfesorGrupoClaseAsociacionDAOImplementation implements ProfesorGrupoClaseAsociacionDAO{
	private final static Logger log = Logger.getLogger(ProfesorGrupoClaseAsociacionDAOImplementation.class);

	public static ProfesorGrupoClaseAsociacionDAOImplementation instance;
	private ProfesorGrupoClaseAsociacionDAOImplementation() {
	}
	public static ProfesorGrupoClaseAsociacionDAOImplementation getInstance() {
		if (null == instance) {
			instance = new ProfesorGrupoClaseAsociacionDAOImplementation();
		}
		return instance;
	}
		
		
	
	@Override
	public void createAsociacion(ProfesorGrupoClaseAsociacion asociacion) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(asociacion);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}	
	}
	
	
	
		
	
	@Override
	public void deleteAsociacion(ProfesorGrupoClaseAsociacion asociacion) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(asociacion);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}	
	}
	
	
		
	
	@Override
	public ProfesorGrupoClaseAsociacion readAsociacion(int id) {
		ProfesorGrupoClaseAsociacion asociacion = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			asociacion = session.get(ProfesorGrupoClaseAsociacion.class, id);
			session.getTransaction().commit();
		}catch(Exception e) {
			log.error(e);
		}finally {
			session.close();
		}
		return asociacion;
	}
	
	
	
	
	@Override
	public void updateAsociacion(ProfesorGrupoClaseAsociacion asociacion) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(asociacion);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}		
	}




}