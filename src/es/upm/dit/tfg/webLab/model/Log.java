package es.upm.dit.tfg.webLab.model;


import java.io.Serializable;

import javax.persistence.*;


@Entity
public class Log implements Serializable{


	@Id
	private int id ;
	private String fecha;
	private String hora;
	private String nivel;
	private String accion;
	private String otrosDetalles;
	
	@ManyToOne
	private Usuario usuario;
	
	
	public Log() {
		this.id=0;
		this.fecha="";
		this.hora="";
		this.nivel="";
		this.accion="";
		this.otrosDetalles="";
		this.usuario=null;
	}
	
	public int getId() {
		return this.id;
	}
	public String getFecha() {
		return this.fecha;
	}
	public String getHora() {
		return this.hora;
	}
	public String getNivel() {
		return this.nivel;
	}
	public String getAccion() {
		return this.accion;
	}
	public String getOtrosDetalles() {
		return this.otrosDetalles;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	

	public void setId(int id) {
		this.id=id;
	}
	public void setFecha(String fecha) {
		this.fecha=fecha;
	}
	public void setHora(String hora) {
		this.hora=hora;
	}
	public void setNivel(String nivel) {
		this.nivel=nivel;
	}
	public void setAccion(String accion) {
		this.accion=accion;
	}
	public void setOtrosDetalles(String otrosDetalles) {
		this.otrosDetalles=otrosDetalles;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario=usuario;
	}
	
}
