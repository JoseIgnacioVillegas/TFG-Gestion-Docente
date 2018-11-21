package es.upm.dit.tfg.webLab.dao;


import java.util.ArrayList;

import java.util.List;

import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;



import org.hibernate.Session;




public class UsuarioDAOImplementation implements UsuarioDAO{

	

public static UsuarioDAOImplementation instance;

	
	
private UsuarioDAOImplementation() {
	
	}

	
	
public static UsuarioDAOImplementation getInstance() {
		
	if (null == instance) {
			
		instance = new UsuarioDAOImplementation();
			
	}
		
	return instance;
	
}
	
	

@Override
	
public void createUsuario(Usuario usuario) {
		
	Session session = SessionFactoryService.get().openSession();
		
	try {
			
		session.beginTransaction();

			
		session.save(usuario);

			
		session.getTransaction().commit();

		
	} 
	catch (Exception e) {
		

	} finally {
			
		session.close();
		
	}	
	
}
	
	
	
	

@Override
	
public Usuario readUsuario(int id) {
		
	Usuario usuario = null;
		
	Session session = SessionFactoryService.get().openSession();
		
	try {
			
		session.beginTransaction();
			
		usuario = session.get(Usuario.class, id);
			
		session.getTransaction().commit();
		
	}catch(Exception e) {
			
		
	}finally {
			
	session.close();
		
	}
		
	return usuario;
	
}
	
	

@Override
	
public void deleteUsuario(Usuario usuario) {
		
	Session session = SessionFactoryService.get().openSession();
		
	try {
			
		session.beginTransaction();

			
		session.delete(usuario);

			
		session.getTransaction().commit();

		
	} catch (Exception e) {
		
	} finally {
			
		session.close();
		
	}	
	
}
	
	
	


@Override
	
	
public List<Usuario> readUsuarios() {
			
		
	Session session = SessionFactoryService.get().openSession();
			
		
	List<Usuario> usuarios = new ArrayList<>();
			
		
	try {
				
			
		session.beginTransaction();
			
			
		usuarios.addAll(session.createQuery("select t from Usuario t").getResultList() );
			
		session.getTransaction().commit();
			
		
	}catch(Exception e) {
				
		
	}finally {
			
		session.close();
		
	}	
	
	return usuarios;
	
}
	
	

	


@Override
	
public Usuario readUsuarioPorCorreo(String email) {
		
	Usuario usuario = null;
		
	Session session = SessionFactoryService.get().openSession();
		
	try {
			
		session.beginTransaction();
			
		usuario = (Usuario) session
			        
			.createQuery("select p from Usuario p where p.correo= :correo")
			       
			.setParameter("correo", email)
			        
			.uniqueResult();
			
		session.getTransaction().commit();
		
	}catch(Exception e) {
		
	}finally {
			
		session.close();
		
	}
		
	return usuario;
	
}




	
	
}