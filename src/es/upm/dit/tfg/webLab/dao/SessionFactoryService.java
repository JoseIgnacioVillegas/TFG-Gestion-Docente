package es.upm.dit.tfg.webLab.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class SessionFactoryService {
    	private SessionFactory sessionFactory1;
    	private static SessionFactoryService sfs1;
    	private SessionFactoryService() {
                   	sessionFactory1 = new Configuration().configure().buildSessionFactory();
    	}
    	
    	public static SessionFactory get() {
                   	if( null == sfs1 ) sfs1 = new SessionFactoryService();
                   	return sfs1.sessionFactory1;
    	}
            	
}