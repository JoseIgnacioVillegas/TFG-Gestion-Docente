package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Plaza implements Serializable{


	@Id
	private int id;
	
	
	private String nombre;
	
	@OneToMany(mappedBy = "plaza", fetch = FetchType.EAGER) //OJITO A ESTO QUE LO DE USER ME LO HE INVENTADO
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Profesor> profesores;
	
	private String descripcion;
	
	public Plaza() {
		this.id=0;
		this.nombre="";
		this.profesores=null;
		this.descripcion="";
	}
	
	public String getPlaza() {
		return this.nombre;
	}
	public List<Profesor> getProfesores() {
		return this.profesores;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public int getId() {
		return this.id;
	}

	
	
	public void setPlaza(String nombre) {
		this.nombre=nombre;
	}
	public void setProfesores(List<Profesor> profesores) {
		this.profesores=profesores;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}
	public void setId(int id) {
		this.id=id;
	}

}
