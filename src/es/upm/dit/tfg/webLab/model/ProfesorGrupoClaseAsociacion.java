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
		return this.id;
	}
	
	public double getHorasTeoria() {
		return this.hTeoria;
	}
	public double getHorasLaboratorio() {
		return this.hLaboratorio;
	}
	public double getHorasPracticas() {
		return this.hPracticas;
	}
	public GrupoClase getGrupoClase() {
		return this.grupo;
	}
	public Profesor getProfesor() {
		return this.profesor;
	}
	
	
	public void setId(int id) {
		this.id=id;
	}
	public void setHorasTeoria(double hTeoria) {
		this.hTeoria=hTeoria;
	}
	public void setHorasLaboratorio(double hLaboratorio) {
		this.hLaboratorio=hLaboratorio;
	}
	public void setHorasPracticas(double hPracticas) {
		this.hPracticas=hPracticas;
	}
	public void setGrupoClase(GrupoClase grupo) {
		this.grupo=grupo;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor=profesor;
	}
}
