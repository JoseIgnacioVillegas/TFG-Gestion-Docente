package es.upm.dit.tfg.webLab.model;

import java.io.Serializable;

public class ProfesorGrupoClaseAsociacionId implements Serializable{
	
	private int profesorId;
	private int grupoClaseId;

	
	
	public int hashCode() {
	    return (int)(profesorId + grupoClaseId);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof ProfesorGrupoClaseAsociacionId) {
	    	ProfesorGrupoClaseAsociacionId otherId = (ProfesorGrupoClaseAsociacionId) object;
	      return (otherId.profesorId == this.profesorId) 
	              && (otherId.grupoClaseId == this.grupoClaseId);
	    }
	    return false;
	  }
	
	
	public ProfesorGrupoClaseAsociacionId() {
		this.profesorId=0;
		this.grupoClaseId=0;
	}
	
	
	public int getProfesorId() {
		return this.profesorId;
	}
	public int getGrupoClaseId() {
		return this.grupoClaseId;
	}
	
	public void setProfesorId(int profesorId) {
		this.profesorId=profesorId;
	}
	public void setGrupoClaseId(int grupoClaseId) {
		this.grupoClaseId=grupoClaseId;
	}
	
}
