package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;


@Entity
public class Asignatura implements Serializable{
	@Id
	private String codigo;
	private String nombre;
	private String acronimo;
	private String tipo;
	private String curso;
	private String semestre;
	private double ects;
	private int horasTeoria;
	private int horasLab;
	private String comentario;
	private int numeroAlumnos;
	private double horasApolo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	/*
	(mappedBy = "asignaturaCoordina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)  
	@Fetch(value = FetchMode.JOIN)
	*/
	private Profesor coordinador;
	
	
	 @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	 /*
	 //(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
	 (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	 @JoinTable(
	   name="Asignaturas_profesor",
	   joinColumns=@JoinColumn(name="Asignatura", referencedColumnName="codigo"),
	   inverseJoinColumns=@JoinColumn(name="Profesores", referencedColumnName="id"))
	   */
	 private List<Profesor> profesores;
	 

	@ManyToOne
	private PlanEstudios plan;
	

	
	public Asignatura() {
		this.acronimo="";
		this.tipo="";
		this.curso="";
		this.semestre="";
		this.ects=0.0;
		this.horasTeoria=0;
		this.horasLab=0;
		this.plan=null;
		this.codigo="";
		this.nombre="";
		this.profesores=new ArrayList<Profesor>();
		this.coordinador=null;
		this.comentario="";
		this.numeroAlumnos=0;
		this.horasApolo=0.00;
	}
	

	public int getHorasLab() {
		return this.horasLab;
	}
	public int getHorasTeoria() {
		return this.horasTeoria;
	}
	public String getSemestre() {
		return this.semestre;
	}
	public String getCurso() {
		return this.curso;
	}
	public String getAcronimo() {
		return this.acronimo;
	}
	public String getTipo() {
		return this.tipo;
	}
	public double getEcts() {
		return this.ects;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public String getNombre() {
		return this.nombre;
	}
	public List<Profesor> getProfesores() {
		return this.profesores;
	}
	public PlanEstudios getPlanEstudios() {
		return this.plan;
	}
	public String getComentario() {
		return this.comentario;
	}
	public int getNumeroAlumnos() {
		return this.numeroAlumnos;
	}
	public double getHorasApolo() {
		return this.horasApolo;
	}
	public Profesor getCoordinador() {
		return this.coordinador;
	}

	
	
	
	
	
	public void setHorasLab(int hlab) {
		this.horasLab=hlab;
	}
	public void setHorasTeoria(int hteoria) {
		this.horasTeoria=hteoria;
	}
	public void setSemestre(String semestre) {
		this.semestre=semestre;
	}
	public void setCurso(String curso) {
		this.curso=curso;
	}
	public void setAcronimo(String acronimo) {
		this.acronimo=acronimo;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	public void setEcts(double ects) {
		this.ects=ects;
	}
	public void setCodigo(String codigo) {
		this.codigo=codigo;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setProfesores(List<Profesor> profesores) {
		this.profesores=profesores;
	}
	public void setPlanEstudios(PlanEstudios plan) {
		this.plan=plan;
	}
	public void setComentario(String comentario) {
		this.comentario=comentario;
	}
	public void setNumeroAlumnos(int nalumnos) {
		this.numeroAlumnos=nalumnos;
	}
	public void setHorasApolo(double horasApolo) {
		this.horasApolo=horasApolo;
	}
	public void setCoordinador(Profesor coordinador) {
		this.coordinador=coordinador;
	}
	public void deleteCoordinador() {
		this.coordinador=null;
	}
	
}



