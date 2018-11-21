package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Grupo implements Serializable{


	@Id
	private String nombre;
	private String acronimo;
	
	
	@OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER) //OJITO A ESTO QUE LO DE USER ME LO HE INVENTADO
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Profesor> profesores;

	
	public Grupo() {
		this.nombre="";
		this.acronimo="";
		this.profesores=null;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	
	public String getAcronimo() {
		return this.acronimo;
	}
	public List<Profesor> getProfesores() {
		return this.profesores;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo=acronimo;
		
	}
	
	public void setProfesores(List<Profesor> profesores) {
		this.profesores=profesores;
	}

}
