package es.upm.dit.tfg.webLab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.*;

import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;


import org.hibernate.Session;


public class ProfesorDAOImplementation implements ProfesorDAO{

	public static ProfesorDAOImplementation instance;
	
	private ProfesorDAOImplementation() {
	
	}
	
	public static ProfesorDAOImplementation getInstance() {
		if (null == instance) {
			instance = new ProfesorDAOImplementation();
			}
		return instance;
	}
	
	@Override
	public void createProfesor(Profesor profesor) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.save(profesor);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}
	
	
	
	@Override
	public Profesor readProfesor(int id) {
		
		/*

CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery q = cb.createQuery(Order.class);
Root o = q.from(Order.class);
o.fetch("items", JoinType.INNER);
q.select(o);
q.where(cb.equal(o.get("id"), orderId));

Order order = (Order)this.em.createQuery(q).getSingleResult();

		 *
		 */
		
		
		
		
		
		Profesor profesor = null;
		Session session = SessionFactoryService.get().openSession();
		
		/*
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Profesor.class);
		Root rt = cq.from(Profesor.class);
		rt.fetch("Profesor", JoinType.INNER);
		cq.select(rt);
		cq.where(cb.equal(rt.get("id"), orderId));
		Order order = (Order)this.session.createQuery(cq).getSingleResult();
		*/
		try {
			session.beginTransaction();
			profesor = session.get(Profesor.class, id);
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return profesor;
	}
	
	@Override
	public void deleteProfesor(Profesor profesor) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.delete(profesor);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}
	
	
	@Override
	
	public List<Profesor> readProfesores() {
			
		Session session = SessionFactoryService.get().openSession();
			
		List<Profesor> profesores = new ArrayList<>();
			
		try {
				
			session.beginTransaction();
			
			profesores.addAll(session.createQuery("select t from Profesor t").getResultList() );
			session.getTransaction().commit();
			
		}catch(Exception e) {
				
		}finally {
			session.close();
		}	
	return profesores;
	}
	
	@Override
	public void updateProfesor(Profesor profesor) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(profesor);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("En el DAO: "+e);
		} finally {
			session.close();
		}		
	}
	
	
	
/* Es probable que me haga falta leer todos los profesores del tiron y los profesores para una asignatura concreta asi que igual 
esto me hace falta en el futuro pero por ahora NO
	@Override
	public List<Comentario> readComentarios() {
		Session session = SessionFactoryService.get().openSession();
		List<Comentario> comentarios = new ArrayList<>();
		try {
			session.beginTransaction();
			comentarios.addAll(session.createQuery("select t from Comentario t").getResultList() );
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return comentarios;
	}
	@Override
	public List<Comentario> readComentarios(Pensamiento pensamiento) {
		Session session = SessionFactoryService.get().openSession();
		List<Comentario> comentarios = new ArrayList<>();
		try {
			session.beginTransaction();
			comentarios.addAll(session.createQuery("select t from Comentario t  where t.pensamiento_id = :id")
	        .setParameter("id", pensamiento.getId())
	        .getResultList() );
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return comentarios;
	}
*/

}