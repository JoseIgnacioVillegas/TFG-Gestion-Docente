package es.upm.dit.tfg.webLab.dao;


import es.upm.dit.tfg.webLab.model.ProfesorGrupoClaseAsociacion;

public interface ProfesorGrupoClaseAsociacionDAO {
	public void createAsociacion(ProfesorGrupoClaseAsociacion asociacion);
	public void deleteAsociacion(ProfesorGrupoClaseAsociacion asociacion);
	public ProfesorGrupoClaseAsociacion readAsociacion(int id);
	public void updateAsociacion(ProfesorGrupoClaseAsociacion asociacion);
}
