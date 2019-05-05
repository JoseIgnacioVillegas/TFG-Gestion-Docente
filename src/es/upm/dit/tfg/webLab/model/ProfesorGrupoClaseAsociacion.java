package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class ProfesorGrupoClaseAsociacion implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Profesor profesor;

	@ManyToOne
	private GrupoClase grupo;

	private double hTeoria;
	private double hLaboratorio;
	private double hPracticas;
	
	
	public ProfesorGrupoClaseAsociacion() {
		this.hTeoria=0.0;
		this.hLaboratorio=0.0;
		this.hPracticas=0.0;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Profesor getProfesor() {
		return profesor;
	}


	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}


	public GrupoClase getGrupo() {
		return grupo;
	}


	public void setGrupo(GrupoClase grupo) {
		this.grupo = grupo;
	}


	public double gethTeoria() {
		return hTeoria;
	}


	public void sethTeoria(double hTeoria) {
		this.hTeoria = hTeoria;
	}


	public double gethLaboratorio() {
		return hLaboratorio;
	}


	public void sethLaboratorio(double hLaboratorio) {
		this.hLaboratorio = hLaboratorio;
	}


	public double gethPracticas() {
		return hPracticas;
	}


	public void sethPracticas(double hPracticas) {
		this.hPracticas = hPracticas;
	}
	
}
