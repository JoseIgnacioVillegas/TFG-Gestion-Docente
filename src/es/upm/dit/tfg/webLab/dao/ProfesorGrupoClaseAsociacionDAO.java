package es.upm.dit.tfg.webLab.dao;


import es.upm.dit.tfg.webLab.model.ProfesorGrupoClaseAsociacion;

public interface ProfesorGrupoClaseAsociacionDAO {
	
	public void createAsociacion(ProfesorGrupoClaseAsociacion asociacion);
	
	public void deleteAsociacion(ProfesorGrupoClaseAsociacion asociacion);
	
	public ProfesorGrupoClaseAsociacion readAsociacion(int id);
	
	//public List<Asignatura> readAsignaturas();
	
	//public List<Asignatura> readAsignaturasPorPlan(String codigo);
	public void updateAsociacion(ProfesorGrupoClaseAsociacion asociacion);

}
