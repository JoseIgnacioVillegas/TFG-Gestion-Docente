package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;


@Entity
public class PlanEstudios implements Serializable{
	
	@Id
	private String codigo;
	private String nombre;
	
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)  
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Asignatura> asignaturas;
	
	
	
	public PlanEstudios() {
		this.codigo="";
		this.nombre="";
		this.asignaturas=null;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public String getNombre() {
		return this.nombre;
	}
	public List<Asignatura> getAsignaturas() {
		return this.asignaturas;
	}
	
	
	public void setCodigo(String codigo) {
		this.codigo=codigo;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas=asignaturas;
	}
}

