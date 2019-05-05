package es.upm.dit.tfg.webLab.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import es.upm.dit.tfg.webLab.model.Asignatura;



public class AsignaturaDAOImplementation implements AsignaturaDAO{
	private final static Logger log = Logger.getLogger(AsignaturaDAOImplementation.class);
	
	public static AsignaturaDAOImplementation instance;
	
	private AsignaturaDAOImplementation() {
	}
	
	public static AsignaturaDAOImplementation getInstance() {
		if (null == instance) {
			instance = new AsignaturaDAOImplementation();
		}
		return instance;
	}
		
	@Override
	public void createAsignatura(Asignatura asignatura) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(asignatura);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}	
	}
	
	
	@Override
	public void deleteAsignatura(Asignatura asignatura) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(asignatura);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}	
	}
	
	
		
	
	@Override
	public Asignatura readAsignatura(String codigo) {
		Asignatura asignatura = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			asignatura = session.get(Asignatura.class, codigo);
			session.getTransaction().commit();
		}catch(Exception e) {
			log.error(e);
		}finally {
			session.close();
		}
		return asignatura;
	}
	
	
	@Override
	public List<Asignatura> readAsignaturas() {
		Session session = SessionFactoryService.get().openSession();
		List<Asignatura> asignaturas = new ArrayList<>();
		try {
			session.beginTransaction();
			asignaturas.addAll(session.createQuery("select t from Asignatura t").getResultList() );
			session.getTransaction().commit();
		}catch(Exception e) {
			log.error(e);
		}finally {
			session.close();
		}
	return asignaturas;
	}
	
	
	@Override
	public List<Asignatura> readAsignaturasPorPlan(String codigo) {
		List<Asignatura> listaAsignaturas = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			listaAsignaturas.addAll(session.createQuery("select t from Asignatura t  where t.plan_codigo = :codigo").setParameter("codigo", codigo).getResultList() );
			session.getTransaction().commit();
		}catch(Exception e) {
			log.error(e);
		}finally {
			session.close();
		}
		return listaAsignaturas;
	}
	
	@Override
	public void updateAsignatura(Asignatura asignatura) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(asignatura);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}		
	}

}