package es.upm.dit.tfg.webLab.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import java.util.List;

import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Plaza;

public class PlazaDAOImplementation implements PlazaDAO{

	public static PlazaDAOImplementation instance;
	
	private PlazaDAOImplementation() {
	
	}
	
	public static PlazaDAOImplementation getInstance() {
		if (null == instance) {
			instance = new PlazaDAOImplementation();
			}
		return instance;
	}
	
	@Override
	public void createPlaza(Plaza plaza) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.save(plaza);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}



	@Override
	public void deletePlaza(Plaza plaza) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.delete(plaza);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}



	@Override
	public Plaza readPlaza(int id) {
		Plaza plaza = null;
		
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			plaza = session.get(Plaza.class, id);
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return plaza;
	}

	@Override
	public List<Plaza> readPlazas() {
		Session session = SessionFactoryService.get().openSession();
		List<Plaza> plazas = new ArrayList<>();
		try {
			session.beginTransaction();
			plazas.addAll(session.createQuery("select t from Plaza t").getResultList() );
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return plazas;
	}
	
	@Override
	public void updatePlaza(Plaza plaza) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(plaza);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("En el DAO: "+e);
		} finally {
			session.close();
		}		
	}

}
