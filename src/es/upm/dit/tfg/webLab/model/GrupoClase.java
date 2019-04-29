package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class GrupoClase implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	//fetch = FetchType.EAGER,
	//@Fetch(value = FetchMode.)
	@OneToMany(mappedBy="grupo",cascade = CascadeType.ALL,orphanRemoval=true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ProfesorGrupoClaseAsociacion> asociacion;
	
	
	@ManyToOne
	private Asignatura asignatura;

	private int numeroAlumnos;
	private String nombre;
	private String descripcion;


	
	public GrupoClase() {
		this.nombre="";
		this.descripcion="";
		this.asociacion=new ArrayList<ProfesorGrupoClaseAsociacion>();
		this.id=0;
		this.numeroAlumnos=0;
		this.asignatura= new Asignatura();
	}
	
	public int getId() {
		return this.id;
	}
	public String getNombre() {
		return this.nombre;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public int getNumeroAlumnos() {
		return this.numeroAlumnos;
	}
	public List<ProfesorGrupoClaseAsociacion> getProfesores(){
		return asociacion;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	
	
	public void setId(int id) {	
		this.id=id;
	}
	public void setNombre(String nombre) {	
		this.nombre=nombre;
	}
	public void setDescripcion(String descripcion) {	
		this.descripcion=descripcion;	
	}
	public void setNumeroAlumnos(int nalumnos) {
		this.numeroAlumnos=nalumnos;
	}
	public void setProfesores(List<ProfesorGrupoClaseAsociacion> asociacion) {
		this.asociacion=asociacion;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura=asignatura;
	}
	
	
}
