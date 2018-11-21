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

		/*
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery q = cb.createQuery(Order.class);
		Root o = q.from(Order.class);
		o.fetch("items", JoinType.INNER);
		q.select(o);
		q.where(cb.equal(o.get("id"), codigo));
		Order order = (Order)session.createQuery(q).getSingleResult();
			*/
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
		
		/*
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery q = cb.createQuery(Order.class);
		Root o = q.from(Order.class);
		o.fetch("items", JoinType.INNER);
		q.select(o);
		q.where(cb.equal(o.get("id"), orderId));
		Order order = (Order)session.createQuery(q).getSingleResult();
			*/
		
		
		//this.em.createQuery(q).getSingleResult();
			
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
		//asignaturas.addAll(session.createQuery("select t from Asignatura t").getResultList() );
		
		
		listaAsignaturas.addAll(session.createQuery("select t from Asignatura t  where t.plan_codigo = :codigo").setParameter("codigo", codigo).getResultList() );
		
		
		/*
		listaAsignaturas.addAll( session.createQuery("select t from Asignatura t  where t.plan_codigo = :codigo")
		        .setParameter("codigo", codigo))
		.getResultList();
		
		*/
		
		//listaAsignaturas = session.get(PlanEstudios.class, codigo);

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




/*  --Seguramente esto no se pueda hacer de esta manera, lo que tengo que hacer aqui es que cada vez que se modifique una asignatura, se modifique también la lista del plan de la misma 
Eso o bien (quizas seria mas sensato) que cuando se llame al plan, este llame a la bbdd y actualice las asignaturas de su lista y asi esten siempre actualizadas
Por tanto, habría que crear una query para sacar las asignaturas por codigo de plan 

QUIZÁS LO MÁS PUTO LÓGICO SERÍA QUE EL PLAN NO TUVIESE UNA LISTA DEFINIDA COMO TAL Y QUE CADA VEZ QUE SE NECESITE LLAMAR AL PLAN, QUE SE LLAME A LAS ASIGNATURAS DEL MISMO 
CON EL UN PUT QUERY QUE SAQUE LAS ASIGNATURAS POR CODIGO DE PLAN, COHONEH





 Es probable que me haga falta leer todos los profesores del tiron y los profesores para una asignatura concreta asi que igual 
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