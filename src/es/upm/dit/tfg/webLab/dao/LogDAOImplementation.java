

package es.upm.dit.tfg.webLab.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.tfg.webLab.model.Log;



public class LogDAOImplementation implements LogDAO{

	public static LogDAOImplementation instance;
	
	private LogDAOImplementation() {
	
	}
	
	public static LogDAOImplementation getInstance() {
		if (null == instance) {
			instance = new LogDAOImplementation();
			}
		return instance;
	}
	
	@Override
	public void createLog(Log log) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.save(log);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}



	@Override
	public void deleteLog(Log log) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.delete(log);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}


	@Override
	public Log readLog(int id) {
		Log log= null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			log = session.get(Log.class, id);

			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return log;
	}
	
	@Override
	public List<Log> readTodosLog() {
		Session session = SessionFactoryService.get().openSession();
		List<Log> logs = new ArrayList<>();
		try {
			session.beginTransaction();
			logs.addAll(session.createQuery("select t from Log t").getResultList() );
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return logs;
	}
	
	

}