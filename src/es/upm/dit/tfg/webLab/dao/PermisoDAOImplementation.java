package es.upm.dit.tfg.webLab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.*;

import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;

import org.hibernate.Session;


public class PermisoDAOImplementation implements PermisoDAO{

	public static PermisoDAOImplementation instance;
	
	private PermisoDAOImplementation() {
	
	}
	
	public static PermisoDAOImplementation getInstance() {
		if (null == instance) {
			instance = new PermisoDAOImplementation();
			}
		return instance;
	}
	
	@Override
	public void createPermiso(Permiso permiso) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			
			session.save(permiso);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}
	
	
	
	@Override
	public Permiso readPermiso(int id) {

		
		Permiso permiso = null;
		Session session = SessionFactoryService.get().openSession();
		

		try {
			session.beginTransaction();
			permiso = session.get(Permiso.class, id);
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return permiso;
	}
	
	@Override
	public void deletePermiso(Permiso permiso) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.delete(permiso);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}	
	}
	
	
	@Override
	
	public List<Permiso> readPermisos() {
			
		Session session = SessionFactoryService.get().openSession();
			
		List<Permiso> permisos = new ArrayList<>();
			
		try {
				
			session.beginTransaction();
			
			permisos.addAll(session.createQuery("select t from Permiso t").getResultList() );
			session.getTransaction().commit();
			
		}catch(Exception e) {
				
		}finally {
			session.close();
		}	
	return permisos;
	}
	
	@Override
	public void updatePermiso(Permiso permiso) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(permiso);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("En el DAO: "+e);
		} finally {
			session.close();
		}		
	}
	
	
	
	
	@Override
	public Permiso readPermisoPorNombre(String nombre) {
		Permiso permiso = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			permiso = (Permiso) session
				.createQuery("select p from Permiso p where p.permiso= :permiso")
				.setParameter("permiso", nombre)    
				.uniqueResult();
			session.getTransaction().commit();
		}catch(Exception e) {
		}finally {	
			session.close();
		}
		return permiso;
	}
	
	/*
	@Override
	public List<Permiso> readPermisosPorUsuario(int usuarioid) {
		List<Permiso> listaPermisos = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
		
			listaPermisos.addAll(session.createQuery("select t from Permiso t  where t.usuario_id = :usuario_id").setParameter("usuario_id", usuarioid).getResultList() );
			

			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("eeeeeeeeeeeheeee"+e);
		}finally {
			session.close();
		}
		return listaPermisos;
	}
	
	*/


}