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
	
	private String[] todosLosPermisos = {"leerusuario","crearusuario", "borrarusuario","editarusuario",
										"leerdocente","creardocente","boorardocente","editardocente",
										"leergrupo", "creargrupo","borrargrupo","editargrupo",
										"leerplaza","crearplaza","borrarplaza","editarplaza",
										"leerplan","crearplan","borrarplan","editarplan",
										"leerasignatura","crearasignatura","borrarasignatura","editarasignatura",
										"importarasignatura","asignardocentes","asignarasignaturas",
										"exportardatos","copiaseguridad","gestionarpermisos"};
	
	
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
	public String[] getTodosPermisos() {
		return this.todosLosPermisos;
	}
	
		
	
	public void setId(int id) {	
		this.id=id;
	}
	public void setUsuario(List<Usuario> usuario) {	
		this.usuario=usuario;	
	}
	public void setPermiso(String permiso) {	
		this.permiso=permiso;	
	}
	public void setDescripcion(String descripcion) {	
		this.descripcion=descripcion;	
	}
	

}

