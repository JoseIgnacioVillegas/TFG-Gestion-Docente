package es.upm.dit.tfg.webLab.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Profesor implements Serializable{


	@Id
	private int id;

	@OneToMany(mappedBy="profesor",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ProfesorGrupoClaseAsociacion> gruposParticipa;
	
	
	@OneToOne
	private Usuario usuario;
	
	@OneToOne(mappedBy = "coordinador", fetch = FetchType.EAGER,cascade = { CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH}) 
	private Asignatura asignaturaCoordina;
	

	private String acronimo;
	private String dedicacion;
	

	@ManyToMany(mappedBy="profesores",cascade = { CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER)
	private List<Asignatura> asignaturasParticipa;

	
	@ManyToOne
	private Grupo grupo;
	
	@ManyToOne
	private Plaza plaza;
	
	
	
	public Profesor() {
		this.id=0;
		this.asignaturasParticipa=new ArrayList<Asignatura>();
		this.acronimo="";
		this.grupo=null;
		this.plaza=null;
		this.dedicacion="";
		this.usuario=null;
		this.asignaturaCoordina=null;
		this.gruposParticipa=new ArrayList<ProfesorGrupoClaseAsociacion>();
	}
	
	
	public int getId() {
		return this.id;
	}
	public String getAcronimo() {
		return this.acronimo;
	}
	public Grupo getGrupo() {
		return this.grupo;
	}
	public String getDedicacion() {
		return this.dedicacion;
	}
	public List<Asignatura> getAsignaturasParticipa() {
		return this.asignaturasParticipa;
	}
	public Plaza getPlaza() {
		return this.plaza;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	public Asignatura getAsignaturaCoordina() {
		return this.asignaturaCoordina;
	}
	public  List<ProfesorGrupoClaseAsociacion> getGrupoClase() {
		return this.gruposParticipa;
	}



	public void setId(int id) {
		this.id=id;
	}
	public void setAcronimo(String acronimo) {
		this.acronimo=acronimo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo=grupo;
	}
	public void setDedicacion(String dedicacion) {
		this.dedicacion=dedicacion;
	}
	public void setAsignaturasParticipa(List<Asignatura> asignaturasParticipa) {
		this.asignaturasParticipa=asignaturasParticipa;
	}
	public void setPlaza(Plaza plaza) {
		this.plaza=plaza;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario=usuario;
	}
	public void setAsignaturaCoordina( Asignatura asignaturaCoordina) {
		this.asignaturaCoordina=asignaturaCoordina;
	}
	public void setGrupoClase( List<ProfesorGrupoClaseAsociacion> gruposParticipa) {
		this.gruposParticipa=gruposParticipa;
	}
	
	
	
	public void deleteAsignaturaCoordina() {
		this.asignaturaCoordina=null;
	}
	public void addAsignaturaaParticipa(Asignatura asignaturaParticipa) {
		this.asignaturasParticipa.add(asignaturaParticipa);
	}
}
