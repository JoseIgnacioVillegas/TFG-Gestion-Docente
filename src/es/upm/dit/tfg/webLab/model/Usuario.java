package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	  
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)  
	@Fetch(value = FetchMode.JOIN)
	private Profesor profesor;
	
	@ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE}, fetch =FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Permiso> permisos;

	private String nombre;
	private String apellidos;
	private String correo;
	
	@Column(name="password")
	private String password;
	
	public Usuario() {
		this.id=0;
		this.permisos=new ArrayList<Permiso>();
		this.nombre="";
		this.apellidos="";
		this.correo="";
		this.profesor=null;
		this.password="";
	}
		
	
	public int getId() {
		return this.id;
	}
	public List<Permiso> getPermisos() {
		return this.permisos;
	}
	public String getNombre() {
		return this.nombre;
	}
	public String getApellidos() {	
		return this.apellidos;
	}
	public String getCorreo() {
		return this.correo;
	}
	public Profesor getProfesor() {
		return this.profesor;
	}
	public String getPassword() {
		return this.password;
	}
		
	
	
	public void setId(int id) {
		this.id=id;
	}
	public void setPermisos(List<Permiso> permisos) {
		this.permisos=permisos;
	}
	public void setNombre(String nombre) {	
		this.nombre=nombre;
	}
	public void setApellidos(String apellidos) {	
		this.apellidos=apellidos;
	}
	public void setCorreo(String correo) {	
		this.correo=correo;
	}
	public void setProfesor(Profesor profesor) {	
		this.profesor=profesor;
	}
	public void setPassword(String password) {	
		this.password=password;
	}
}

