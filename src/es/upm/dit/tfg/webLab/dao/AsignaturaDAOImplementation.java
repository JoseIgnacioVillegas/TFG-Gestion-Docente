package es.upm.dit.tfg.webLab.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import es.upm.dit.tfg.webLab.model.Asignatura;



public class AsignaturaDAOImplementation implements AsignaturaDAO{


	
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
		System.out.println("En el DAO: "+e);
	} finally {
		session.close();
	}		
}




}