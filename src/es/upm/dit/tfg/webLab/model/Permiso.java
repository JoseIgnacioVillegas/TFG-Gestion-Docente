package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import org.hibernate.annotations.Fetch;

import org.hibernate.annotations.FetchMode;




@Entity
public class Permiso implements Serializable{
	@Id
	private int id;
  
	
	@ManyToMany(mappedBy="permisos",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Usuario> usuario;
	
	private String descripcion;
	private String permiso;
	
	
	public Permiso() {
		id=0;
		usuario=new ArrayList<Usuario>();
		descripcion="";
		permiso="";
	}
		
		

	
	
	public int getId() {	
		return this.id;
	}
	public List<Usuario> getUsuario() {
		return this.usuario;
	}
	public String getPermiso() {
		return this.permiso;
	}
	public String getDescripcion() {
		return this.descripcion;
	}

		
	
	public void setId(int id) {	
		this.id=id;
	}
	public void addUsuario(Usuario usuario) {	
		this.usuario.add((Usuario) usuario);	
	}
	public void setPermiso(String permiso) {	
		this.permiso=permiso;	
	}
	public void setDescripcion(String descripcion) {	
		this.descripcion=descripcion;	
	}
	

}

