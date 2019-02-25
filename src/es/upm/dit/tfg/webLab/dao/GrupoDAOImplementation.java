package es.upm.dit.tfg.webLab.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Grupo;





public class GrupoDAOImplementation implements GrupoDAO{

	public static GrupoDAOImplementation instance;
	
	private GrupoDAOImplementation() {
	
	}
	
	public static GrupoDAOImplementation getInstance() {
		if (null == instance) {
			instance = new GrupoDAOImplementation();
			}
		return instance;
	}
	
	@Override
	public void createGrupo(Grupo grupo) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.save(grupo);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}



	@Override
	public void deleteGrupo(Grupo grupo) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.delete(grupo);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}


	@Override
	public Grupo readGrupo(String nombre) {
		Grupo grup = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			grup = session.get(Grupo.class, nombre);

			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return grup;
	}

	@Override
	public List<Grupo> readGrupos() {
		Session session = SessionFactoryService.get().openSession();
		List<Grupo> grupos = new ArrayList<>();
		try {
			session.beginTransaction();
			grupos.addAll(session.createQuery("select t from Grupo t").getResultList() );
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return grupos;
	}


	@Override
	public void updateGrupo(Grupo grupo) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(grupo);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}		
	}

}