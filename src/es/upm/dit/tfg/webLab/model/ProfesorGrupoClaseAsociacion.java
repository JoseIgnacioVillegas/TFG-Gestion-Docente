package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="Profesor_Grupo")
@IdClass(ProfesorGrupoClaseAsociacionId.class)
public class ProfesorGrupoClaseAsociacion implements Serializable{
	
	
	@Id
	private int profesorId;
	
	//@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name="PROFESORID", referencedColumnName="id")
	private Profesor profesor;
	
	
	
	@Id
	private int grupoClaseId;
	//@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name="GRUPOID", referencedColumnName="id")
	private GrupoClase grupo;
	
	
	
	
	@Column(name="HorasTeoria")
	private double hTeoria;
	
	@Column(name="HorasLaboratorio")
	private double hLaboratorio;
	
	@Column(name="HorasPracticas")
	private double hPracticas;
	
	
	public ProfesorGrupoClaseAsociacion() {
		this.hTeoria=0.0;
		this.hLaboratorio=0.0;
		this.hPracticas=0.0;
		
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
	
	public void setHorasTeoria(double hTeoria) {
		this.hTeoria=hTeoria;
	}
	public void setHorasLaboratorio(double hLaboratorio) {
		this.hLaboratorio=hLaboratorio;
	}
	public void setHorasPracticas(double hPracticas) {
		this.hPracticas=hPracticas;
	}
	

}
